package classes.GiftsStrategy;

import classes.Children.Child;
import classes.Presents.Gift;
import classes.SantaClaus.Santa;
import enums.Category;

import java.util.ArrayList;
import java.util.Comparator;

public class NiceScoreAssignation implements AssignGiftsStrategy {
    /**
     * Metoda care asigneaza cadourile copiilor dupa strategia "niceScore".
     * Astfel, se sorteaza lista de copii crescator dupa averageScore, se
     * copiaza lista de cadouri a Mosului care se sorteaza crescator
     * dupa pret, iar apoi se distribuie cadourile: pentru fiecare
     * copil din lista de cauta in lista Mosului cadoul cel mai ieftin
     * care face parte din categoria dorita a copilului si se asigneaza
     * daca bugetul sau permite si daca cantitatea cadoului este nenula.
     * @param children
     *      lista de copii
     * @param santa
     *      Mos Craciun
     */
    @Override
    public void assignGifts(final ArrayList<Child> children, final Santa santa) {

        children.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                if (o1.getAverageScore().equals(o2.getAverageScore())) {
                    return Integer.compare(o1.getId(), o2.getId());
                } else {
                    return Double.compare(o2.getAverageScore(), o1.getAverageScore());
                }
            }
        });

        ArrayList<Gift> gifts = new ArrayList<Gift>();
        for (int i = 0; i < santa.getSantaGiftsList().size(); i++) {
            gifts.add(new Gift(santa.getSantaGiftsList().get(i)));
        }

        gifts.sort(new Comparator<Gift>() {
            @Override
            public int compare(final Gift o1, final Gift o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });

        for (Child child : children) {
            Double budget = child.getAssignedBudget();
            ArrayList<Gift> giftsReceived = new ArrayList<Gift>();

            for (Category category : child.getGiftsPreferences()) {
                for (int i = 0; i < gifts.size(); i++) {
                    if (gifts.get(i).getCategory().equals(category)
                            && gifts.get(i).accessQuantity() > 0) {
                        if (budget >= gifts.get(i).getPrice()) {
                            budget = budget - gifts.get(i).getPrice();
                            giftsReceived.add(new Gift(gifts.get(i)));
                            gifts.get(i).setQuantity(gifts.get(i).accessQuantity() - 1);
                            break;
                        }
                    }
                }
            }
            child.setReceivedGifts(giftsReceived);
        }
        santa.setSantaGiftsList(gifts);
    }
}
