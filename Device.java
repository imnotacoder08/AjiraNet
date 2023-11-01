package AjiraNet;

import java.util.HashMap;
import java.util.Map;

public class Device {
    private String name;
    private String deviceType;
    private int strength;
    private Map<String, Device> connections;

    public Device(String name, String deviceType, int strength) {
        this.name = name;
        this.deviceType = deviceType;
        this.strength = strength;
        this.connections = new HashMap<>();
    }
    public String getName() {
        return name;
    }
    public String getDeviceType() {
        return deviceType;
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public Map<String, Device> getConnections() {
        return connections;
    }
}
