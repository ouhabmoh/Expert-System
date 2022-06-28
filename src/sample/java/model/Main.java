package model;

import java.util.Enumeration;

public class Main {

    static BooleanRuleBase vehicles;
    static RuleBase currentRuleBase;
    static BooleanRuleBase consoles;

    public static void main(String[] args) {

//        vehicles = new BooleanRuleBase("Vehicles Rule Base");
//
//        VehicleRuleBase.init(vehicles);
//        currentRuleBase = vehicles;
//        demoVehiclesFC((BooleanRuleBase) currentRuleBase);
//        System.out.println(currentRuleBase.forwardChain());

        consoles = new BooleanRuleBase("consoles Rule Base");
        ConsoleRuleBase.init(consoles);
        currentRuleBase = consoles;
        demoConsoleFC((BooleanRuleBase) currentRuleBase);
      //  System.out.println(currentRuleBase.forwardChain());
        currentRuleBase.backwardChain(VN2.screen);
        Enumeration<String> e = consoles.variableList.keys();

//         Iterating through the Hashtable
//         object
//
//         Checking for next element in Hashtable object
//         with the help of hasMoreElements() method
        while (e.hasMoreElements()) {

            // Getting the key of a particular entry
            String key = e.nextElement();

            // Print and display
            System.out.println("variable name : " + key
                    + "\t\t value : "
                    + consoles.variableList.get(key).value);
        }
//        System.out.println(vehicles.variableList);
        System.out.println(consoles.variableList.get("type").value);

    }

    public static void initVehiclesRuleBase(BooleanRuleBase rb) {


    }

    public static void demoVehiclesFC (BooleanRuleBase rb) {
        rb.setVariableValue(VariableNames.vehicle, null);
        rb.setVariableValue(VariableNames.vehicleType, null);
        rb.setVariableValue (VariableNames.size, VariableNames.medium);
        rb.setVariableValue (VariableNames.numWheels, "4");
        rb.setVariableValue(VariableNames.numDoors,"3");
        rb.setVariableValue(VariableNames.motor, VariableNames.yes);

    }

    public static void demoConsoleFC (BooleanRuleBase rb) {
        rb.setVariableValue(VN2.usageType, VN2.gaming);
        rb.setVariableValue(VN2.screen, VN2.no);
        rb.setVariableValue(VN2.storage, "32");
        rb.setVariableValue(VN2.size, null);
        rb.setVariableValue(VN2.budget, "300");
        rb.setVariableValue(VN2.playingQuality, VN2.medium);
        rb.setVariableValue(VN2.nbGames, VN2.few);

    }

    public void demoVehiclesBC(BooleanRuleBase rb) {
        rb.setVariableValue(VariableNames.vehicle, null);
        rb.setVariableValue(VariableNames.vehicleType, VariableNames.automobile);
        rb.setVariableValue (VariableNames.size, VariableNames.medium);
        rb.setVariableValue (VariableNames.numWheels, "4");
        rb.setVariableValue(VariableNames.numDoors,"3");
        rb.setVariableValue(VariableNames.motor, VariableNames.yes);



    }
}

