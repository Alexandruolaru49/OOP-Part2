package classes.AverageScoreStrategies;

import classes.Children.Child;

public interface UpdateChildrenStrategy {
    /**
     * Metoda care calculeaza si seteaza scorul mediu de cumintenie
     * pentru copilul primit ca parametru.
     * @param child
     *      copil
     */
    void calculateAverageScore(Child child);
}
