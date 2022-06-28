package model;

public class VehicleRuleBase {
    public static void init(BooleanRuleBase rb) {
        RuleVariable vehicle = new RuleVariable(rb, "vehicle");
  //      rb.addVariable(vehicle);
        vehicle.setLabels(VariableNames.bicycle+" "+VariableNames.tricycle+" "+VariableNames.motorcycle+" "+VariableNames.sportsCar+" "+VariableNames.sedan+" "+VariableNames.minivan+" "+VariableNames.sportsUtilityVehicle);
        vehicle.setPromptText(" What kind of vehicle is it? ");
        RuleVariable vehicleType = new RuleVariable(rb, VariableNames.vehicleType);
 //       rb.addVariable(vehicleType);
        vehicleType.setLabels(VariableNames.cycle+" "+VariableNames.automobile);
        vehicleType.setPromptText("What type of vehicle is it?");
        RuleVariable size = new RuleVariable(rb, VariableNames.size);
 //       rb.addVariable(size);
        size.setLabels(VariableNames.small+" "+VariableNames.medium+" "+VariableNames.large);
        size.setPromptText("What size is the vehicle?");
        RuleVariable motor = new RuleVariable(rb, VariableNames.motor);
//        rb.addVariable(motor);
        motor.setLabels(VariableNames.yes+" "+VariableNames.no);
        motor.setPromptText("Does the vehicle have a motor?");

        RuleVariable num_wheels = new RuleVariable(rb, VariableNames.numWheels);
 //       rb.addVariable(num_wheels);
        num_wheels.setLabels("2 3 4");
        num_wheels.setPromptText("How many wheels does it have?");
        RuleVariable num_doors = new RuleVariable(rb, VariableNames.numDoors);
//        rb.addVariable(num_doors);
        num_doors.setLabels("2 3 4");
        num_doors.setPromptText("How many doors does it have?");
        Condition cEquals = new Condition("=");
        Condition cNotEquals = new Condition("!=");
        Condition cLessThan = new Condition("<");
        Rule Bicycle = new Rule(rb, "bicycle",
                new Clause[]{
                        new Clause(vehicleType, cEquals, VariableNames.cycle),
                        new Clause(num_wheels, cEquals, "2"),
                        new Clause(motor, cEquals, VariableNames.no)

                }, new Clause(vehicle, cEquals, VariableNames.bicycle));


        Rule Tricycle = new Rule(rb, "tricycle",
                new Clause[]{
                        new Clause(vehicleType, cEquals, VariableNames.cycle),
                        new Clause(num_wheels, cEquals, "3"),
                        new Clause(motor, cEquals, VariableNames.no)},
                new Clause(vehicle, cEquals, VariableNames.tricycle));


        Rule Motorcycle = new Rule(rb, "motorcycle", new Clause[]

                {
                        new Clause(vehicleType, cEquals, VariableNames.cycle),
                        new Clause(num_wheels, cEquals, " 2 "),
                        new Clause(motor, cEquals, VariableNames.yes)
                },
                new Clause(vehicle, cEquals, VariableNames.motorcycle));

        Rule Sportscar = new Rule(rb, "sportscar", new Clause[]{
                new Clause(vehicleType, cEquals, VariableNames.automobile),
                new Clause(size, cEquals, VariableNames.small),
                new Clause(num_doors, cEquals, "2 ")

        }, new Clause(vehicle, cEquals, VariableNames.sportsCar));

        Rule Sedan = new Rule(rb, "Sedan",
                new Clause[]{

                        new Clause(vehicleType, cEquals, VariableNames.automobile),
                        new Clause(size, cEquals, VariableNames.medium),
                        new Clause(num_doors, cEquals, "4")
                }, new Clause(vehicle, cEquals, VariableNames.sedan));

        Rule Minivan = new Rule(rb, "minivan",
                new Clause[]{
                        new Clause(vehicleType, cEquals, VariableNames.automobile),
                        new Clause(size, cEquals, VariableNames.medium),

                        new Clause(num_doors, cEquals, "3")
                },
                new Clause(vehicle, cEquals, VariableNames.minivan));
        Rule SUV = new Rule(rb, "SU", new
                Clause[]{
                new Clause(vehicleType, cEquals, VariableNames.automobile),
                new Clause(size, cEquals, VariableNames.large),
                new Clause(num_doors, cEquals, "4")
        },
                new Clause(vehicle, cEquals, VariableNames.sportsUtilityVehicle));

        Rule Cycle = new Rule(rb, "Cycle",
                new Clause(num_wheels, cLessThan, "4"),
                new Clause(vehicleType, cEquals, VariableNames.cycle));

        Rule Automobile = new Rule(rb, "automobile", new Clause[]{

                new Clause(num_wheels, cEquals, "4"),
                new Clause(motor, cEquals, VariableNames.yes)}
                , new Clause(vehicleType, cEquals, VariableNames.automobile));

    }
}
