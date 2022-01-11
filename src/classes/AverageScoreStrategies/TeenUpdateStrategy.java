package classes.AverageScoreStrategies;

import classes.Children.Child;

public class TeenUpdateStrategy implements UpdateChildrenStrategy {

    private Child child;

    /**
     * Constructor
     * @param child
     *      copil
     */
    public TeenUpdateStrategy(final Child child) {
        this.child = child;
    }

    /**
     * Metoda care calculeaza scorul mediu al unui copil de
     * tipul "teen"
     * @param childToCalculateFor
     *      copil
     */
    @Override
    public void calculateAverageScore(final Child childToCalculateFor) {
        if (childToCalculateFor.getNiceScoreHistory().size() == 0) {
            childToCalculateFor.setAverageScore(childToCalculateFor.accessNiceScore());
        } else {
            Double sum = 0d;
            for (int i = 0; i < childToCalculateFor.getNiceScoreHistory().size(); i++) {
                sum = sum + (childToCalculateFor.getNiceScoreHistory().get(i) * (i + 1));
            }
            sum = (2 * sum) / (childToCalculateFor.getNiceScoreHistory().size()
                    * (childToCalculateFor.getNiceScoreHistory().size() + 1));
            childToCalculateFor.setAverageScore(sum);
        }
    }
}
