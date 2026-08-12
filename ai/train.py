import os
import tensorflow as tf
from tensorflow import keras
from tensorflow.keras import layers

# ============================================================
# CONFIG
# ============================================================

DATASET_DIR = "dataset/PlantVillage/raw/color"
MODEL_DIR = "models"

IMG_SIZE = 224
BATCH_SIZE = 32
EPOCHS = 10
VALIDATION_SPLIT = 0.20
SEED = 42

os.makedirs(MODEL_DIR, exist_ok=True)

# ============================================================
# SELECT CROPS
# ============================================================

SELECTED_PREFIXES = (
    "Apple___",
    "Corn_(maize)___",
    "Grape___",
    "Pepper,_bell___",
    "Potato___",
    "Tomato___",
)

# ============================================================
# FIND CLASSES AUTOMATICALLY
# ============================================================

all_classes = sorted(
    name
    for name in os.listdir(DATASET_DIR)
    if os.path.isdir(os.path.join(DATASET_DIR, name))
)

selected_classes = [
    name
    for name in all_classes
    if name.startswith(SELECTED_PREFIXES)
]

print("\n================================")
print("PLANTOPEDIA DATASET")
print("================================\n")

print("Selected classes:")

for index, class_name in enumerate(selected_classes):
    print(f"{index:02d} -> {class_name}")

print(f"\nTotal classes: {len(selected_classes)}")

if len(selected_classes) != 27:
    print(
        "\nWARNING: Expected 27 classes, "
        f"but found {len(selected_classes)}."
    )

# ============================================================
# CREATE TRAINING DATASET
# ============================================================

print("\nLoading training images...\n")

train_ds = tf.keras.utils.image_dataset_from_directory(
    DATASET_DIR,
    labels="inferred",
    label_mode="int",
    class_names=selected_classes,
    validation_split=VALIDATION_SPLIT,
    subset="training",
    seed=SEED,
    image_size=(IMG_SIZE, IMG_SIZE),
    batch_size=BATCH_SIZE,
)

print("\nLoading validation images...\n")

val_ds = tf.keras.utils.image_dataset_from_directory(
    DATASET_DIR,
    labels="inferred",
    label_mode="int",
    class_names=selected_classes,
    validation_split=VALIDATION_SPLIT,
    subset="validation",
    seed=SEED,
    image_size=(IMG_SIZE, IMG_SIZE),
    batch_size=BATCH_SIZE,
)

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
        layers.RandomRotation(0.1),
        layers.RandomZoom(0.1),
        layers.RandomContrast(0.1),
    ],
    name="data_augmentation",
)

# ============================================================
# MOBILE NET V3
# ============================================================

base_model = tf.keras.applications.MobileNetV3Small(
    input_shape=(IMG_SIZE, IMG_SIZE, 3),
    include_top=False,
    weights="imagenet",
)

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

x = layers.Dropout(0.2)(x)

outputs = layers.Dense(
    len(selected_classes),
    activation="softmax"
)(x)

model = keras.Model(
    inputs,
    outputs,
    name="Plantopedia"
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

model.summary()

# ============================================================
# TRAIN
# ============================================================

print("\n================================")
print("STARTING TRAINING")
print("================================\n")

history = model.fit(
    train_ds,
    validation_data=val_ds,
    epochs=EPOCHS,
)

# ============================================================
# SAVE KERAS MODEL
# ============================================================

keras_path = os.path.join(
    MODEL_DIR,
    "plantopedia_model.keras"
)

model.save(keras_path)

print(f"\nKeras model saved to:")
print(keras_path)

# ============================================================
# SAVE LABELS
# ============================================================

labels_path = os.path.join(
    MODEL_DIR,
    "labels.txt"
)

with open(labels_path, "w", encoding="utf-8") as file:

    for class_name in selected_classes:
        file.write(class_name + "\n")

print(f"Labels saved to:")
print(labels_path)

# ============================================================
# CONVERT TO TFLITE
# ============================================================

print("\nConverting model to TFLite...\n")

converter = tf.lite.TFLiteConverter.from_keras_model(model)

tflite_model = converter.convert()

tflite_path = os.path.join(
    MODEL_DIR,
    "plantopedia_model.tflite"
)

with open(tflite_path, "wb") as file:
    file.write(tflite_model)

print(f"TFLite model saved to:")
print(tflite_path)

# ============================================================
# COMPLETE
# ============================================================

print("\n================================")
print("TRAINING COMPLETE")
print("================================")

print(f"Classes : {len(selected_classes)}")
print(f"Model   : {tflite_path}")
print(f"Labels  : {labels_path}")