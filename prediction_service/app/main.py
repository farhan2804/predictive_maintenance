from fastapi import FastAPI

app = FastAPI()

@app.get("/")
def home():
    return {"message": "Prediction Service Running"}