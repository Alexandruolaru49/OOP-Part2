package classes.AverageScoreStrategies;

import classes.Children.Child;
import common.Constants;

public final class StrategyFactory {

    /**
     * Constructor - not used - for checkstyle
     */
    private StrategyFactory() {

    }
    /**
     * Metoda care primeste tipul strategiei si copilul si
     * intoarce strategia in functie de tipul copilului.
     * @param strategy
     *      tipul strategiei
     * @param child
     *      copilul
     * @return
     *      strategia
     */
    public static UpdateChildrenStrategy createStrategy(final String strategy,
                                                        final Child child) {
        switch (strategy) {
            case Constants.BABY:
                return new BabyUpdateStrategy(child);
            case Constants.KID:
                return new KidUpdateStrategy(child);
            case Constants.TEEN:
                return new TeenUpdateStrategy(child);
            case Constants.YOUNG_ADULT:
                return new YoungAdultUpdateStrategy(child);
            default:
        }
        throw new IllegalArgumentException("The strategy type " + strategy + " is not recognized.");
    }
}
