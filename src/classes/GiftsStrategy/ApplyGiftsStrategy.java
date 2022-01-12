package classes.GiftsStrategy;

import classes.Children.Child;
import classes.SantaClaus.Santa;

import java.util.ArrayList;
import java.util.Comparator;

public class ApplyGiftsStrategy {
    /**
     * Metoda care aplica strategia de asignare a cadourilor.
     * Mai intai, se creeaza strategia, iar in functie de tipul ei,
     * se va apela metoda "assignGifts" din clasa corespunzatoare.
     * Dupa ce se va face acest lucru, listele de copii vor fi
     * sortate inapoi in mod crescator dupa id, iar noua lista
     * va fi intoarsa de catre metoda.
     * @param initialList
     *      lista initiala de copii
     * @param strategy
     *      strategia
     * @param santa
     *      Mos Craciun
     * @return
     *      lista actualizata de copii
     */
    public ArrayList<Child> applyStrategy(final ArrayList<Child> initialList,
                                          final String strategy, final Santa santa) {

        ArrayList<Child> updatedList = new ArrayList<Child>();
        AssignGiftsStrategy assignGiftsStrategy = GiftsStrategyFactory.createStrategy(strategy);
        assignGiftsStrategy.assignGifts(initialList, santa);

        initialList.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        for (int i = 0; i < initialList.size(); i++) {
            updatedList.add(new Child(initialList.get(i)));
        }

        return updatedList;
    }
}
