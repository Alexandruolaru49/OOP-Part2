package classes.Elves;

import classes.Children.Child;
import classes.Presents.Gift;
import classes.SantaClaus.Santa;

import java.util.ArrayList;
import java.util.Comparator;

public class YellowElf extends Elf {
    /**
     * Metoda care o suprascrie pe cea din clasa parinte si care
     * ofera copiilor care nu au primit niciun cadou unul, daca sunt
     * respectate conditiile din cerinta. Este copiata lista de cadouri
     * a Mosului, sortata crescator dupa pret, iar apoi, daca unul din
     * cadourile din lista sunt din aceeasi categorie cu cea preferata
     * a copilului si cantitatea acestuia nu este nula, se ofera copilului
     * doar daca acesta nu a primit niciun cadou. Altfel, nu se asigneaza
     * niciun alt cadou.
     * @param child
     *      copil
     * @param santa
     *      Mos Craciun
     */
    public void assignGifts(final Child child, final Santa santa) {

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

        if (child.getReceivedGifts().size() == 0) {
            for (int i = 0; i < gifts.size(); i++) {
                Gift gift = gifts.get(i);
                if (gift.getCategory().equals(child.getGiftsPreferences().get(0))) {
                    if (gift.accessQuantity() > 0) {
                        child.getReceivedGifts().add(new Gift(gift));
                        gift.setQuantity(gift.accessQuantity() - 1);
                    }
                    break;
                }
            }
        }

        santa.setSantaGiftsList(gifts);
    }

}
