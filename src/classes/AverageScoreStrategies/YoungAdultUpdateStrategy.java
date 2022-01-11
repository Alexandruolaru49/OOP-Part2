package classes.AverageScoreStrategies;

import classes.Children.Child;

public class YoungAdultUpdateStrategy implements UpdateChildrenStrategy {

    private Child child;

    /**
     * Constructor
     * @param child
     *      copil
     */
    public YoungAdultUpdateStrategy(final Child child) {
        this.child = child;
    }

    /**
     * Metoda care calculeaza scorul mediu al unui copil de
     * tipul "young-adult" - este irelevanta deoarece acest
     * tip de copil nu ajunge pe lista.
     * @param childToCalculateFor
     *      copil
     */
    @Override
    public void calculateAverageScore(final Child childToCalculateFor) {
        childToCalculateFor.setAverageScore(0d);
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
