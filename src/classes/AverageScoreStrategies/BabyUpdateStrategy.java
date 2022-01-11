package classes.AverageScoreStrategies;

import classes.Children.Child;
import common.Constants;

public class BabyUpdateStrategy implements UpdateChildrenStrategy {

    private Child child;

    /**
     * Constructor
     * @param child
     *      copil
     */
    public BabyUpdateStrategy(final Child child) {
        this.child = child;
    }

    /**
     * Metoda care calculeaza scorul mediu al unui copil de
     * tipul "baby"
     * @param childToCalculateFor
     *      copil
     */
    @Override
    public void calculateAverageScore(final Child childToCalculateFor) {
        Double averageScore = Constants.BABY_AVERAGE_SCORE;
        childToCalculateFor.setAverageScore(averageScore);
    }

    /**
     * Getter pentru child
     * @return
     *      copil
     */
    public Child getChild() {
        return child;
    }

    /**
     * Setter pentru child
     * @param child
     *      copil
     */
    public void setChild(final Child child) {
        this.child = child;
    }
}
