package classes.Elves;

import classes.Children.Child;
import classes.SantaClaus.Santa;

public class Elf {
    /**
     * Metoda ce va fi suprascrisa in toate clasele ce
     * mostenesc clasa "Elf" si care va actualiza bugetul
     * copilului (Utilizat la design pattern-ul "Visitor").
     * @param child
     *      copil
     */
    public void updateBudget(final Child child) {
    }

    /**
     * Metoda ce va fi implementata si utilizata doar de
     * clasa "YellowElf", facand modificari inn lista de
     * cadouri primite ale copilului, daca este cazul.
     * @param child
     *      copil
     * @param santa
     *      Mos Craciun
     */
    public void assignGifts(final Child child, final Santa santa) {
    }
}
