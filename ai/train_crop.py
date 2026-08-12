import os
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers

# ============================================================
# CONFIG
# ============================================================

DATASET_DIR = "ai/dataset/PlantVillage/raw/color"
MODEL_DIR = "models"

IMG_SIZE = 224
BATCH_SIZE = 32
EPOCHS = 15
VALIDATION_SPLIT = 0.20
SEED = 42

os.makedirs(MODEL_DIR, exist_ok=True)

# ============================================================
# CROP MAPPING
# ============================================================

CROP_PREFIXES = {
    "Apple": "Apple___",
    "Corn": "Corn_(maize)___",
    "Grape": "Grape___",
    "Pepper": "Pepper,_bell___",
    "Potato": "Potato___",
    "Tomato": "Tomato___",
}

# ============================================================
# CREATE TEMPORARY CROP DATASET
# ============================================================

crop_root = os.path.join(MODEL_DIR, "crop_dataset")

os.makedirs(crop_root, exist_ok=True)

for crop in CROP_PREFIXES:
    os.makedirs(
        os.path.join(crop_root, crop),
        exist_ok=True
    )

# Create links/copies of images grouped by crop
for crop, prefix in CROP_PREFIXES.items():

    destination = os.path.join(
        crop_root,
        crop
    )

    for class_name in os.listdir(DATASET_DIR):

        if not class_name.startswith(prefix):
            continue

        class_path = os.path.join(
            DATASET_DIR,
            class_name
        )

        if not os.path.isdir(class_path):
            continue

        for filename in os.listdir(class_path):

            source = os.path.join(
                class_path,
                filename
            )

            target = os.path.join(
                destination,
                f"{class_name}_{filename}"
            )

            if not os.path.exists(target):

                try:
                    os.link(source, target)

                except OSError:
                    import shutil
                    shutil.copy2(source, target)

print("\n================================")
print("PLANTOPEDIA CROP DATASET")
print("================================")

print("""
Apple
Corn
Grape
Pepper
Potato
Tomato
""")

# ============================================================
# LOAD DATASET
# ============================================================

train_ds = tf.keras.utils.image_dataset_from_directory(
    crop_root,
    labels="inferred",
    label_mode="int",
    validation_split=VALIDATION_SPLIT,
    subset="training",
    seed=SEED,
    image_size=(IMG_SIZE, IMG_SIZE),
    batch_size=BATCH_SIZE,
)

val_ds = tf.keras.utils.image_dataset_from_directory(
    crop_root,
    labels="inferred",
    label_mode="int",
    validation_split=VALIDATION_SPLIT,
    subset="validation",
    seed=SEED,
    image_size=(IMG_SIZE, IMG_SIZE),
    batch_size=BATCH_SIZE,
)

print("\nCrop classes:")
class_names = train_ds.class_names`r`nprint(class_names)

# ============================================================
# PERFORMANCE
# ============================================================

AUTOTUNE = tf.data.AUTOTUNE

train_ds = train_ds.prefetch(AUTOTUNE)
val_ds = val_ds.prefetch(AUTOTUNE)

# ============================================================
# DATA AUGMENTATION
# ============================================================

data_augmentation = keras.Sequential(
    [
        layers.RandomFlip("horizontal"),
        layers.RandomRotation(0.15),
        layers.RandomZoom(0.20),
        layers.RandomContrast(0.20),
        layers.RandomBrightness(0.15),
    ]
)

# ============================================================
# MOBILE NET V3
# ============================================================

base_model = tf.keras.applications.MobileNetV3Small(
    input_shape=(IMG_SIZE, IMG_SIZE, 3),
    include_top=False,
    weights="imagenet",
)

# First train classifier with frozen backbone
base_model.trainable = False

# ============================================================
# MODEL
# ============================================================

inputs = keras.Input(
    shape=(IMG_SIZE, IMG_SIZE, 3)
)

x = data_augmentation(inputs)

x = tf.keras.applications.mobilenet_v3.preprocess_input(x)

x = base_model(
    x,
    training=False
)

x = layers.GlobalAveragePooling2D()(x)

x = layers.Dropout(0.3)(x)

outputs = layers.Dense(
    6,
    activation="softmax"
)(x)

model = keras.Model(
    inputs,
    outputs,
    name="PlantopediaCrop"
)

# ============================================================
# COMPILE
# ============================================================

model.compile(
    optimizer=keras.optimizers.Adam(
        learning_rate=0.001
    ),
    loss="sparse_categorical_crossentropy",
    metrics=["accuracy"],
)

# ============================================================
# TRAIN
# ============================================================

print("\n================================")
print("TRAINING CROP MODEL")
print("================================")

model.fit(
    train_ds,
    validation_data=val_ds,
    epochs=EPOCHS,
)

# ============================================================
# SAVE
# ============================================================

keras_path = os.path.join(
    MODEL_DIR,
    "plantopedia_crop.keras"
)

model.save(keras_path)

# ============================================================
# LABELS
# ============================================================

labels_path = os.path.join(
    MODEL_DIR,
    "crop_labels.txt"
)

with open(labels_path, "w") as f:

    for name in class_names:
        f.write(name + "\n")

# ============================================================
# TFLITE
# ============================================================

converter = tf.lite.TFLiteConverter.from_keras_model(
    model
)

tflite_model = converter.convert()

tflite_path = os.path.join(
    MODEL_DIR,
    "plantopedia_crop.tflite"
)

with open(tflite_path, "wb") as f:
    f.write(tflite_model)

print("\n================================")
print("CROP MODEL COMPLETE")
print("================================")

print(f"Model : {tflite_path}")
print(f"Labels: {labels_path}")
