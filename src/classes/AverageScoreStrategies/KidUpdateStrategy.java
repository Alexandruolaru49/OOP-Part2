package classes.AverageScoreStrategies;

import classes.Children.Child;

public class KidUpdateStrategy implements UpdateChildrenStrategy {

    private Child child;

    /**
     * Constructor
     * @param child
     *      copil
     */
    public KidUpdateStrategy(final Child child) {
        this.child = child;
    }

    /**
     * Metoda care calculeaza scorul mediu al unui copil de
     * tipul "kid"
     * @param childToCalculateFor
     *      copil
     */
    @Override
    public void calculateAverageScore(final Child childToCalculateFor) {
        if (childToCalculateFor.getNiceScoreHistory().size() == 0) {
            childToCalculateFor.setAverageScore(childToCalculateFor.accessNiceScore());
        } else {
            Double sum = 0d;
            for (Double i : childToCalculateFor.getNiceScoreHistory()) {
                sum = sum + i;
            }
            sum = sum / (childToCalculateFor.getNiceScoreHistory().size());
            childToCalculateFor.setAverageScore(sum);
        }
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
