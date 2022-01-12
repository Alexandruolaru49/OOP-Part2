package classes.GiftsStrategy;

import common.Constants;

public final class GiftsStrategyFactory {

    private GiftsStrategyFactory() {

    }

    /**
     * Metoda care primeste un tip de strategie si creeaza
     * o instanta a unei strategii in functie de tipul primit
     * @param strategy
     *      strategia
     * @return
     *      instanta a unei clase-strategie
     */
    public static AssignGiftsStrategy createStrategy(final String strategy) {
        switch (strategy) {
            case Constants.ID:
                return new IdAssignation();
            case Constants.NICE_SCORE:
                return new NiceScoreAssignation();
            case Constants.NICE_SCORE_CITY:
                return new NiceScoreCityAssignation();
            default:
        }
        throw new IllegalArgumentException("The strategy type " + strategy + " is not recognized.");
    }
}
