package classes.Children;

import classes.Elves.BlackElf;
import classes.Elves.PinkElf;
import classes.Elves.WhiteElf;

public class Kid extends Child {

    public Kid(final Child child) {
        this.setFirstName(child.getFirstName());
        this.setLastName(child.getLastName());
        this.setAge(child.getAge());
        this.setId(child.getId());
        this.setCity(child.getCity());
        this.setNiceScore(child.accessNiceScore());
        this.setGiftsPreferences(child.getGiftsPreferences());
        this.setAverageScore(child.getAverageScore());
        this.setNiceScoreHistory(child.getNiceScoreHistory());
        this.setAssignedBudget(child.getAssignedBudget());
        this.setReceivedGifts(child.getReceivedGifts());
        this.setNiceScoreBonus(child.accessNiceScoreBonus());
        this.setElf(child.accessElf());
    }

    @Override
    public void updateBudget(BlackElf blackElf) {
        Double budget = this.getAssignedBudget();
        budget = budget - budget * 30 / 100;
        this.setAssignedBudget(budget);
    }

    @Override
    public void updateBudget(PinkElf pinkElf) {
        Double budget = this.getAssignedBudget();
        budget = budget + budget * 30 / 100;
        this.setAssignedBudget(budget);
    }

    @Override
    public void updateBudget(WhiteElf whiteElf) {

    }

}
