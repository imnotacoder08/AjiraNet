package AjiraNet;

import java.util.*;

public class Network {
    private Map<String, Device> devices;

    public Network() {
        devices = new HashMap<>();
    }

    public String addDevice(String name, String deviceType, int strength) {
        if (!deviceType.equals("COMPUTER") && !deviceType.equals("REPEATER")) {
            return "Error: Invalid command syntax.";
        }
        if (devices.containsKey(name)) {
            return "Error: That name already exists.";
        }
        if (!name.matches("[a-zA-Z0-9]+")) {
            return "Error: Device name must be alphanumeric.";
        }

        Device device = new Device(name, deviceType, strength);
        devices.put(name, device);

        return "Successfully added " + name + ".";
    }

    public String connectDevices(String name, List<String> connectedDevices) {
        if (!devices.containsKey(name)) {
            return "Error: Node not found.";
        }

        Device device = devices.get(name);
        if (device.getDeviceType().equals("repeater")) {
            return "Error: Repeater cannot be connected to other devices.";
        }

        for (String connectedDevice : connectedDevices) {
            if (connectedDevice.equals(name)) {
                return "Error: Cannot connect device to itself.";
            }
            if (!devices.containsKey(connectedDevice)) {
                return "Error: Node not found.";
            }
            if (device.getConnections().containsKey(connectedDevice)) {
                return "Error: Devices are already connected.";
            }

            device.getConnections().put(connectedDevice, devices.get(connectedDevice));
            devices.get(connectedDevice).getConnections().put(name, device);
        }

        return "Successfully connected.";
    }

    public String setDeviceStrength(String name, int strength) {
        if (!devices.containsKey(name)) {
            return "Error: Node not found.";
        }
        if (devices.get(name).getDeviceType().equals("repeater")) {
            return "Error: Strength cannot be defined for a repeater.";
        }
        if (strength < 0) {
            return "Error: Strength cannot be negative.";
        }

        devices.get(name).setStrength(strength);

        return "Successfully defined strength.";
    }

    public String infoRoute(String srcName, String destName) {
        if (!devices.containsKey(srcName) || !devices.containsKey(destName)) {
            return "Error: Node not found.";
        }

        if (devices.get(srcName).getDeviceType().equals("repeater") || devices.get(destName).getDeviceType().equals("repeater")) {
            return "Error: Route cannot be calculated with a repeater.";
        }

        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();

        if (findRoute(srcName, destName, visited, path)) {
            return String.join(" -> ", path);
        } else {
            return "Error: Route not found!";
        }
    }

    private boolean findRoute(String current, String dest, Set<String> visited, List<String> path) {
        visited.add(current);
        path.add(current);

        if (current.equals(dest)) {
            return true;
        }

        Device device = devices.get(current);
        for (String neighbor : device.getConnections().keySet()) {
            if (!visited.contains(neighbor)) {
                if (findRoute(neighbor, dest, visited, path)) {
                    return true;
                }
            }
        }

        path.remove(path.size() - 1);
        return false;
    }
}
