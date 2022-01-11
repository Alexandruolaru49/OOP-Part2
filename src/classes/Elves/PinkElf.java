package classes.Elves;

import classes.Children.Child;

public class PinkElf extends Elf{
    @Override
    public void updateBudget(Child child) {
        child.updateBudget(this);
    }
}
