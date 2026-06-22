from fastapi import FastAPI
from pydantic import BaseModel
import joblib
import pandas as pd

app = FastAPI()

# Load trained model
model = joblib.load("trained_model/predictor.pkl")


class SensorInput(BaseModel):
    temperature: float
    vibration: float
    pressure: float
    rpm: float
    current: float


@app.post("/predict")
def predict(data: SensorInput):

    features = pd.DataFrame([{
        "temperature": data.temperature,
        "vibration": data.vibration,
        "pressure": data.pressure,
        "rpm": data.rpm,
        "current": data.current
    }])

    prediction = model.predict(features)

    if prediction[0] == "HEALTHY":
        failure_probability = 10

    elif prediction[0] == "WARNING":
        failure_probability = 60

    else:
        failure_probability = 90

    return {
        "prediction": prediction[0],
        "probability": failure_probability
    }