package classes.Elves;

import classes.Children.Child;

public class PinkElf extends Elf {
    /**
     * Metoda care va apela metoda de actualizare a bugetului
     * a copilului, trimitandu-i ca parametru tipul de elf
     * dat de instanta. (Utilizat la design pattern-ul "Visitor").
     * @param child
     *      copil
     */
    @Override
    public void updateBudget(final Child child) {
        child.updateBudget(this);
    }
}
