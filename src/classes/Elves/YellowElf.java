package classes.Elves;

import classes.Children.Child;
import classes.Presents.Gift;
import classes.SantaClaus.Santa;

import java.util.ArrayList;
import java.util.Comparator;

public class YellowElf extends Elf{

    public void assignGifts(Child child, Santa santa) {

        // Cream o lista noua de cadouri identica cu lista mosului de cadouri.
        ArrayList<Gift> gifts = new ArrayList<Gift>();
        for (int i = 0; i < santa.getSantaGiftsList().size(); i++) {
            gifts.add(new Gift(santa.getSantaGiftsList().get(i)));
        }

        // Sortam lista de cadouri crescator dupa pret.
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
//                        if (gift.accessQuantity() == 0) {
//                            santa.getSantaGiftsList().remove(i);
//                        }
                    }
                    break;
                }
            }
        }

        santa.setSantaGiftsList(gifts);
    }

}
