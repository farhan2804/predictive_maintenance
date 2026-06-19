import json
import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import joblib

# Load JSON data
with open("dataset/sensor_data.json", "r") as file:
    data = json.load(file)

# Convert to DataFrame
df = pd.DataFrame(data)

# Keep only records that have healthStatus
df = df[df["healthStatus"].notna()]

# Features
X = df[[
    "temperature",
    "vibration",
    "pressure",
    "rpm",
    "current"
]]

# Target
y = df["healthStatus"]

# Split dataset
X_train, X_test, y_train, y_test = train_test_split(
    X,
    y,
    test_size=0.2,
    random_state=42
)

# Train model
model = RandomForestClassifier(
    n_estimators=100,
    random_state=42
)

model.fit(X_train, y_train)

# Test accuracy
predictions = model.predict(X_test)

accuracy = accuracy_score(y_test, predictions)

print(f"Model Accuracy: {accuracy:.2f}")

# Save model
joblib.dump(
    model,
    "trained_model/predictor.pkl"
)

print("Model saved successfully!")