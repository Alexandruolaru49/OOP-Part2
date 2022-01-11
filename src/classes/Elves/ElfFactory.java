package classes.Elves;

import common.Constants;

public class ElfFactory {

    //private String elfType;

//    public ElfFactory(String type) {
//        this.elfType = type;
//    }

    public Elf createElf(String elfType) {
        switch (elfType) {
            case Constants.WHITE:
                return new WhiteElf();
            case Constants.BLACK:
                return new BlackElf();
            case Constants.PINK:
                return new PinkElf();
            case Constants.YELLOW:
                return new YellowElf();
            default:
        }
        throw new IllegalArgumentException("The elf type " + elfType + " is not recognized.");
    }
}
