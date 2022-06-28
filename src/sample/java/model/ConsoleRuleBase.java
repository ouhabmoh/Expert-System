package model;

public class ConsoleRuleBase {
    public static void init(BooleanRuleBase rb){
        RuleVariable usageType = new RuleVariable(rb, VN2.usageType);
        usageType.setLabels(VN2.all+" "+VN2.gaming);
        RuleVariable storage = new RuleVariable(rb, VN2.storage);
        storage.setLabels("0 32 120 160 250 320 500 1000 1500");
        RuleVariable size = new RuleVariable(rb, VN2.size);
        size.setLabels(VN2.small+" "+VN2.medium+" "+VN2.large);
        RuleVariable mark = new RuleVariable(rb, VN2.mark);
        mark.setLabels(VN2.pc+" "+VN2.xbox+" "+VN2.ps+" "+VN2.nintendo);
        RuleVariable budget = new RuleVariable(rb, VN2.budget);
        budget.setLabels("200 500 1000");
        RuleVariable playingQuality = new RuleVariable(rb, VN2.playingQuality);
        playingQuality.setLabels(VN2.low+" "+VN2.medium+" "+VN2.high);
        RuleVariable nbGames = new RuleVariable(rb, VN2.nbGames);
        nbGames.setLabels(VN2.few+" "+VN2.average+" "+VN2.more);
        RuleVariable screen = new RuleVariable(rb, VN2.screen);
        screen.setLabels(VN2.no+" "+VN2.yes);
        RuleVariable type = new RuleVariable(rb, VN2.type);
        type.setLabels(VN2.xbox360Arcade+" "+VN2.xbox360Elite+" "+VN2.xbox360Slim+" "+VN2.ps3Slim+" "+VN2.ps4Slim+" "+VN2.ps3UltraSlim+" "+VN2.ps4Pro+" "+VN2.pc+" "+VN2.nintendo+" "+VN2.ps4+" "+VN2.ps3);

        Condition cEquals = new Condition("=");
        Condition cNotEquals = new Condition("!=");
        Condition cLessThan = new Condition("<");
        Condition cMore = new Condition(">");

        Rule r1 = new Rule(rb, "pc",
                new Clause(usageType, cEquals, VN2.all),
                new Clause(mark,cEquals,VN2.pc));


        Rule r2 = new Rule(rb, "gaming",
                new Clause(usageType, cEquals, VN2.gaming),
                new Clause(mark,cEquals,VN2.nintendo+" "+VN2.xbox+" "+VN2.ps));
//screenToSize
        Rule r3 = new Rule(rb, "small",
                new Clause(screen, cEquals, VN2.no),
                new Clause(size, cEquals, VN2.small));
///sizeToScreen
        Rule r4 = new Rule(rb, "noScreen",
                new Clause(size, cEquals, VN2.small),
                new Clause(screen, cEquals, VN2.no));

        Rule r5 = new Rule(rb, "xbox360 arcade",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "0"),
                        new Clause(size, cEquals, VN2.large),
                        new Clause(mark, cEquals, VN2.xbox),
                        new Clause(budget, cMore, "150"),
                        new Clause(playingQuality, cEquals, VN2.low),
                        new Clause(nbGames, cEquals, VN2.few),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.xbox360Arcade));

        Rule r6 = new Rule(rb, "xbox360 slim",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "250"),
                        new Clause(size, cEquals, VN2.medium),
                        new Clause(mark, cEquals, VN2.xbox),
                        new Clause(budget, cMore, "175"),
                        new Clause(playingQuality, cEquals, VN2.low),
                        new Clause(nbGames, cEquals, VN2.few),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.xbox360Slim));

        Rule r7 = new Rule(rb, "xbox360 elite",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "120"),
                        new Clause(size, cEquals, VN2.large),
                        new Clause(mark, cEquals, VN2.xbox),
                        new Clause(budget, cMore, "160"),
                        new Clause(playingQuality, cEquals, VN2.low),
                        new Clause(nbGames, cEquals, VN2.few),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.xbox360Elite));

        Rule r8 = new Rule(rb, "ps3 phat",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "160"),
                        new Clause(size, cEquals, VN2.large),
                        new Clause(mark, cEquals, VN2.ps),
                        new Clause(budget, cMore, "150"),
                        new Clause(playingQuality, cEquals, VN2.low),
                        new Clause(nbGames, cEquals, VN2.few),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.ps3));

        Rule r9 = new Rule(rb, "ps3 slim",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "320"),
                        new Clause(size, cEquals, VN2.medium),
                        new Clause(mark, cEquals, VN2.ps),
                        new Clause(budget, cMore, "170"),
                        new Clause(playingQuality, cEquals, VN2.low),
                        new Clause(nbGames, cEquals, VN2.few),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.ps3Slim));


        Rule r10 = new Rule(rb, "ps3 UltraSlim",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "500"),
                        new Clause(size, cEquals, VN2.medium),
                        new Clause(mark, cEquals, VN2.ps),
                        new Clause(budget, cMore, "180"),
                        new Clause(playingQuality, cEquals, VN2.low),
                        new Clause(nbGames, cEquals, VN2.few),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.ps3UltraSlim));

        Rule r11 = new Rule(rb, "ps4",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "500"),
                        new Clause(size, cEquals, VN2.medium),
                        new Clause(mark, cEquals, VN2.ps),
                        new Clause(budget, cMore, "200"),
                        new Clause(playingQuality, cEquals, VN2.medium),
                        new Clause(nbGames, cEquals, VN2.more),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.ps4));

        Rule r12 = new Rule(rb, "ps4 slim",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "1000"),
                        new Clause(size, cEquals, VN2.medium),
                        new Clause(mark, cEquals, VN2.ps),
                        new Clause(budget, cMore, "220"),
                        new Clause(playingQuality, cEquals, VN2.medium),
                        new Clause(nbGames, cEquals, VN2.more),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.ps4Slim));

        Rule r13 = new Rule(rb, "ps4 pro",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "1000"),
                        new Clause(size, cEquals, VN2.medium),
                        new Clause(mark, cEquals, VN2.ps),
                        new Clause(budget, cMore, "250"),
                        new Clause(playingQuality, cEquals, VN2.high),
                        new Clause(nbGames, cEquals, VN2.more),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.ps4Pro));

        Rule r14 = new Rule(rb, "pc",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.all),
                        new Clause(storage, cEquals, "1500"),
                        new Clause(size, cEquals, VN2.large),
                        new Clause(mark, cEquals, VN2.pc),
                        new Clause(budget, cMore, "1000"),
                        new Clause(playingQuality, cEquals, VN2.high),
                        new Clause(nbGames, cEquals, VN2.more),
                        new Clause(screen, cEquals, VN2.yes)
                },
                new Clause(type, cEquals, VN2.pc));

        Rule r15 = new Rule(rb, "nintendo",
                new Clause[]{
                        new Clause(usageType, cEquals, VN2.gaming),
                        new Clause(storage, cEquals, "32"),
                        new Clause(size, cEquals, VN2.small),
                        new Clause(mark, cEquals, VN2.nintendo),
                        new Clause(budget, cMore, "250"),
                        new Clause(playingQuality, cEquals, VN2.medium),
                        new Clause(nbGames, cEquals, VN2.few),
                        new Clause(screen, cEquals, VN2.no)
                },
                new Clause(type, cEquals, VN2.nintendo));

    }
}
