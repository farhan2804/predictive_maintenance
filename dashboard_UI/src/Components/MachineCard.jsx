import { useEffect, useState } from "react";

const MachineCard = ({ machine }) => {
  const [sensor, setSensor] = useState(null);

  useEffect(() => {
    fetch(`http://localhost:8080/api/sensors/machine/${machine.id}/latest`)
      .then((response) => response.json())
      .then((data) => setSensor(data))
      .catch((error) => console.error(error));
  }, [machine.id]);

return (
  <div className="machine-card">
    <h2>{machine.name}</h2>

    <p>
      <strong>Machine Type:</strong> {machine.type}
    </p>

    <p>
      <strong>Location:</strong> {machine.location}
    </p>

    <p>
      <strong>Status:</strong> {machine.status}
    </p>

    {sensor && (
      <>
        <p>
          <strong>Temperature:</strong>{" "}
          {sensor.temperature?.toFixed(2)}
        </p>

        <p>
          <strong>Vibration:</strong>{" "}
          {sensor.vibration?.toFixed(2)}
        </p>

        <p
          style={{
            color:
              sensor.healthStatus === "CRITICAL"
                ? "red"
                : sensor.healthStatus === "WARNING"
                ? "orange"
                : "green",
          }}
        >
          <strong>Health Status:</strong>{" "}
          {sensor.healthStatus}
        </p>
      </>
    )}
  </div>
);
}
export default MachineCard;
