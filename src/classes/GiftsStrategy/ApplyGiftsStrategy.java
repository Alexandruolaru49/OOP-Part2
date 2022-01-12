package classes.GiftsStrategy;

import classes.Children.Child;
import classes.SantaClaus.Santa;

import java.util.ArrayList;
import java.util.Comparator;

public class ApplyGiftsStrategy {

    public ArrayList<Child> applyStrategy(final ArrayList<Child> initialList, String strategy, Santa santa) {

        ArrayList<Child> updatedList = new ArrayList<Child>();

        //CREAM STRATEGIA
        AssignGiftsStrategy assignGiftsStrategy = GiftsStrategyFactory.createStrategy(strategy);
        //APLICAM STRATEGIA DE ASIGNARE A CADOURILOR
        assignGiftsStrategy.assignGifts(initialList, santa);

        //SORTAM INAPOI LISTA DUPA ID
        initialList.sort(new Comparator<Child>() {
            @Override
            public int compare(Child o1, Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        //ADAUGAM ELEMENTELE IN NOUA LISTA PE CARE O VOM INTOARCE
        for (int i = 0; i < initialList.size(); i++) {
            updatedList.add(new Child(initialList.get(i)));
        }

        return updatedList;
    }
}
