package equipment_service.dto;

public class PredictionRequest {

    private double temperature;
    private double vibration;
    private double pressure;
    private double rpm;
    private double current;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getVibration() {
        return vibration;
    }

    public void setVibration(double vibration) {
        this.vibration = vibration;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getRpm() {
        return rpm;
    }

    public void setRpm(double rpm) {
        this.rpm = rpm;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }
}