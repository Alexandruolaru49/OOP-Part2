package classes.Factory;

import classes.Children.Child;
import classes.Children.Baby;
import classes.Children.Kid;
import classes.Children.Teen;
import classes.Children.YoungAdult;
import common.Constants;

public final class ChildrenFactory {
    /**
     * Constructor - nefolosit - pentru checkstyle
     */
    private ChildrenFactory() {

    }

    /**
     * Metoda care primeste tipul copilului si copilul si creeaza
     * unul nou in functie de tip, fiind apelat un copy-constructor.
     * @param type
     *      tipul de copil (in functie de varsta sa)
     * @param child
     *      copilul
     * @return
     *      un copil nou
     */
    public static Child createChild(final String type, final Child child) {
        switch (type) {
            case Constants.BABY:
                return new Baby(child);
            case Constants.KID:
                return new Kid(child);
            case Constants.TEEN:
                return new Teen(child);
            case Constants.YOUNG_ADULT:
                return new YoungAdult(child);
            default:
        }
        throw new IllegalArgumentException("The child type " + type + " is not recognized.");
    }
}
