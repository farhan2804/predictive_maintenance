import { useEffect, useState } from "react";

const MachineCard = ({ machine }) => {
  const [sensor, setSensor] = useState(null);
  const [prediction, setPrediction] = useState(null);

  useEffect(() => {
    const fetchSensorData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/sensors/machine/${machine.id}/latest`
        );

        const sensorData = await response.json();

        setSensor(sensorData);

        const predictionResponse = await fetch(
          "http://localhost:8080/api/predictions",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              temperature: sensorData.temperature,
              vibration: sensorData.vibration,
              pressure: sensorData.pressure,
              rpm: sensorData.rpm,
              current: sensorData.current,
            }),
          }
        );

        const predictionData = await predictionResponse.json();

        setPrediction(predictionData);
      } catch (error) {
        console.error(error);
      }
    };

    fetchSensorData();

    const interval = setInterval(fetchSensorData, 5000);

    return () => clearInterval(interval);
  }, [machine.id]);

  const getStatusClass = () => {
    if (!prediction) return "";

    switch (prediction.prediction) {
      case "CRITICAL":
        return "critical";
      case "WARNING":
        return "warning";
      default:
        return "healthy";
    }
  };

  const getRemainingLife = () => {
    if (!prediction) return "--";

    if (prediction.probability >= 80) {
      return "7 Days";
    }

    if (prediction.probability >= 60) {
      return "14 Days";
    }

    if (prediction.probability >= 30) {
      return "30 Days";
    }

    return "90+ Days";
  };

  const getMaintenanceRecommendation = () => {
    if (!prediction) return "--";

    if (prediction.probability >= 80) {
      return "Immediate Inspection";
    }

    if (prediction.probability >= 60) {
      return "Maintenance Within 7 Days";
    }

    if (prediction.probability >= 30) {
      return "Monitor Closely";
    }

    return "No Action Required";
  };

  return (
    <div className="machine-card">
      <h2>{machine.name}</h2>

      <p>
        ⚙️ <strong>Type:</strong> {machine.type}
      </p>

      <p>
        📍 <strong>Location:</strong> {machine.location}
      </p>

      <p>
        🔄 <strong>Status:</strong> {machine.status}
      </p>

      {sensor && (
        <>
          <hr />

          <p>
            🌡️ <strong>Temperature:</strong>{" "}
            {sensor.temperature?.toFixed(2)} °C
          </p>

          <p>
            📳 <strong>Vibration:</strong>{" "}
            {sensor.vibration?.toFixed(2)}
          </p>

          <p>
            📈 <strong>Failure Probability:</strong>{" "}
            {prediction?.probability ?? "--"}%
          </p>

          <p>
            ⏳ <strong>Remaining Useful Life:</strong>{" "}
            {getRemainingLife()}
          </p>

          <p>
            🛠️ <strong>Maintenance:</strong>{" "}
            {getMaintenanceRecommendation()}
          </p>

          {prediction && (
            <div className={`status-badge ${getStatusClass()}`}>
              {prediction.prediction}
            </div>
          )}
        </>
      )}
    </div>
  );
};

export default MachineCard;