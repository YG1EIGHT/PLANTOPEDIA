import tensorflow as tf
import numpy as np
from PIL import Image
import os

MODEL_PATH = "ai/models/plantopedia_model.tflite"
LABELS_PATH = "ai/models/labels.txt"

# Change this to any PlantVillage image you want to test
IMAGE_PATH = "ai/dataset/PlantVillage/raw/color/Tomato___Early_blight"

# ------------------------------------------------------------
# Find first image inside the selected folder
# ------------------------------------------------------------

image_file = None

for file in os.listdir(IMAGE_PATH):
    if file.lower().endswith((".jpg", ".jpeg", ".png")):
        image_file = os.path.join(IMAGE_PATH, file)
        break

if image_file is None:
    raise RuntimeError("No image found.")

print("Testing image:")
print(image_file)

# ------------------------------------------------------------
# Load labels
# ------------------------------------------------------------

with open(LABELS_PATH, "r", encoding="utf-8") as f:
    labels = [line.strip() for line in f.readlines()]

# ------------------------------------------------------------
# Load model
# ------------------------------------------------------------

interpreter = tf.lite.Interpreter(
    model_path=MODEL_PATH
)

interpreter.allocate_tensors()

input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()

# ------------------------------------------------------------
# Load image
# ------------------------------------------------------------

image = Image.open(image_file).convert("RGB")

image = image.resize((224, 224))

image_array = np.asarray(image).astype(np.float32)

# MobileNetV3 expected preprocessing
image_array = image_array / 127.5 - 1.0

image_array = np.expand_dims(
    image_array,
    axis=0
)

# ------------------------------------------------------------
# Run inference
# ------------------------------------------------------------

interpreter.set_tensor(
    input_details[0]["index"],
    image_array
)

interpreter.invoke()

output = interpreter.get_tensor(
    output_details[0]["index"]
)[0]

# ------------------------------------------------------------
# Top 5 predictions
# ------------------------------------------------------------

top_indices = np.argsort(output)[::-1][:5]

print("\nTop predictions:\n")

for index in top_indices:

    print(
        f"{labels[index]} : "
        f"{output[index] * 100:.2f}%"
    )