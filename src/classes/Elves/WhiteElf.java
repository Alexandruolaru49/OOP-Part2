package classes.Elves;

import classes.Children.Child;

public class WhiteElf extends Elf{
    @Override
    public void updateBudget(Child child) {
        child.updateBudget(this);
    }
}
