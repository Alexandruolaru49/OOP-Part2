package classes.AverageScoreStrategies;

import classes.Children.Child;
import classes.Factory.ChildrenFactory;
import common.Constants;

import java.util.ArrayList;

public class ApplyStrategy {
    /**
     * Metoda care aplica straegia. Este primita ca parametru lista initiala
     * de copii, care este parcursa, iar pentru fiecare copil este definit
     * tipul dupa varsta (baby, kid, teen sau young-adult), fiind apoi aplicata
     * metoda "createChild" din ChildrenFactory. Acestui copil i se aplica strategia
     * in functie de tipul sau, prin metoda "createStrategy". Mai intai este creata
     * instanta, iar in functie de tipul de copil, ii este calculat scorul mediu
     * de cumintenie si apoi este adaugat in lista noua. Acest lucru se intampla
     * pentru fiecare copil din lista initiala.
     * @param initialList
     *      lista initiala de copii
     * @return
     *      noua lista de copii
     */
    public ArrayList<Child> applyStrategy(final ArrayList<Child> initialList) {

        ArrayList<Child> updatedList = new ArrayList<Child>();
        for (int i = 0; i < initialList.size(); i++) {
            Child child = initialList.get(i);
            String type = "";
            if (child.getAge() < Constants.BABY_UPPER) {
                type = Constants.BABY;
            }
            if (child.getAge() >= Constants.KID_LOWER && child.getAge() < Constants.KID_UPPER) {
                type = Constants.KID;
            }
            if (child.getAge() >= Constants.TEEN_LOWER && child.getAge() <= Constants.TEEN_UPPER) {
                type = Constants.TEEN;
            }
            if (child.getAge() > Constants.YOUNG_ADULT_AGE) {
                type = Constants.YOUNG_ADULT;
            }

            Child newChild = ChildrenFactory.createChild(type, child);
            UpdateChildrenStrategy strategy = StrategyFactory.createStrategy(type, newChild);
            strategy.calculateAverageScore(newChild);
            updatedList.add(new Child(newChild));
        }
        return updatedList;
    }
}

