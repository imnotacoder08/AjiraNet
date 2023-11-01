package AjiraNet;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AjiraNet {
    public static void main(String[] args) {
        Network ajiraNet = new Network();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("> ");
            String[] command = scanner.nextLine().split(" ");
            if (command.length == 0) {
                continue;
            }

            String action = command[0];
            List<String> arguments = Arrays.asList(command).subList(1, command.length);

            if (action.equals("ADD") && arguments.size() >= 2) {
                String result = ajiraNet.addDevice(arguments.get(1), arguments.get(0), arguments.size() == 3 ? Integer.parseInt(arguments.get(2)) : 5);
                System.out.println(result);
            } else if (action.equals("CONNECT") && arguments.size() >= 2) {
                String result = ajiraNet.connectDevices(arguments.get(0), arguments.subList(1, arguments.size()));
                System.out.println(result);
            } else if (action.equals("SET_DEVICE_STRENGTH") && arguments.size() == 2) {
                    try {
                         int strength = Integer.parseInt(arguments.get(1));
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid command syntax");;
                        continue;
                    }
                String result = ajiraNet.setDeviceStrength(arguments.get(0), Integer.parseInt(arguments.get(1)));
                System.out.println(result);
            } else if (action.equals("INFO_ROUTE") && arguments.size() == 2) {
                String result = ajiraNet.infoRoute(arguments.get(0), arguments.get(1));
                System.out.println(result);
            } else {
                System.out.println("Error: Invalid command syntax.");
            }
        }
    }
}
