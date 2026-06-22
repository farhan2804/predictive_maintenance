import { useEffect, useState } from "react";
import MachineCard from "./Components/MachineCard";
import "./App.css";

const App = () => {
  const [machines, setMachines] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/api/machines")
      .then((response) => response.json())
      .then((data) => setMachines(data))
      .catch((error) => console.error(error));
  }, []);

  return (
    <div className="app-container">
      <div className="dashboard-header">
        <h1>🏭 AI-Powered Predictive Maintenance Platform</h1>
        <p>
          Real-Time Machine Health Monitoring & Failure Prediction
        </p>
      </div>

      <div className="stats-row">
        <div className="stat-card">
          <h2>{machines.length}</h2>
          <p>Total Machines</p>
        </div>

        <div className="stat-card">
          <h2>24/7</h2>
          <p>Monitoring</p>
        </div>

        <div className="stat-card">
          <h2>AI</h2>
          <p>Prediction Engine</p>
        </div>

        <div className="stat-card">
          <h2>Live</h2>
          <p>Sensor Data</p>
        </div>
      </div>

      <div className="dashboard-grid">
        {machines.map((machine) => (
          <MachineCard
            key={machine.id}
            machine={machine}
          />
        ))}
      </div>
    </div>
  );
};

export default App;