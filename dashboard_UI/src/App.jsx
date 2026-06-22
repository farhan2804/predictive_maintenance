import { useEffect, useState } from "react";
import MachineCard from "./Components/MachineCard";
import "./App.css";

const App = () => {
  const [machines, setMachines] = useState([]);
  const [lastUpdated, setLastUpdated] = useState(
    new Date().toLocaleTimeString(),
  );
  useEffect(() => {
    fetch("http://localhost:8080/api/machines")
      .then((response) => response.json())
      .then((data) => setMachines(data))
      .catch((error) => console.error(error));
    const timer = setInterval(() => {
      setLastUpdated(new Date().toLocaleTimeString());
    }, 1000);

    return () => clearInterval(timer);
  }, []);

  return (
    <div className="app-container">
      <div className="dashboard-header">
        <div className="live-indicator">
          <span className="live-dot"></span>
          LIVE SYSTEM
        </div>

        <h1>AI Predictive Maintenance Platform</h1>

        <p>
          Real-Time Equipment Health Monitoring & Failure Prediction using
          Machine Learning
        </p>

        <div className="tech-stack">
          React • Spring Boot • FastAPI • PostgreSQL • Random Forest
        </div>

        <div className="last-updated">Last Updated: {lastUpdated}</div>
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
          <MachineCard key={machine.id} machine={machine} />
        ))}
      </div>
    </div>
  );
};

export default App;
