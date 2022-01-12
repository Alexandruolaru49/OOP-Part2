package classes.Elves;

import common.Constants;

public class ElfFactory {
    /**
     * Metoda care primeste un String ce reprezinta tipul
     * elfului si creeaza o instanta de respectivul tip
     * @param elfType
     *      tipul de elf
     * @return
     *      instanta de elf
     */
    public Elf createElf(final String elfType) {
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
