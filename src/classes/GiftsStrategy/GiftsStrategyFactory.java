package classes.GiftsStrategy;

import common.Constants;

public class GiftsStrategyFactory {

    private GiftsStrategyFactory() {

    }

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
