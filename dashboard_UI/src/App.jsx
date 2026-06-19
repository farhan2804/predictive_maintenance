import { useEffect, useState } from "react";
import MachineCard from "./Components/MachineCard";
import "./index.css";
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
    <div>
      <h1>Predictive Maintenance Dashboard</h1>

      <div className="dashboard-grid">
        {machines.map((machine) => (
          <MachineCard key={machine.id} machine={machine} />
        ))}
      </div>
    </div>
  );
};

export default App;
