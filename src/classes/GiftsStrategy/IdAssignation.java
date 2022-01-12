package classes.GiftsStrategy;

import classes.Children.Child;
import classes.Presents.Gift;
import classes.SantaClaus.Santa;
import enums.Category;

import java.util.ArrayList;
import java.util.Comparator;

public class IdAssignation implements AssignGiftsStrategy{

    @Override
    public void assignGifts(ArrayList<Child> children, Santa santa) {

        //Mai intai sortam lista crescator dupa ID.
        children.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

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

        for (Child child : children) {
            Double budget = child.getAssignedBudget();
            ArrayList<Gift> giftsReceived = new ArrayList<Gift>();

            for (Category category : child.getGiftsPreferences()) {
                for (int i = 0; i < gifts.size(); i++) {
                    if (gifts.get(i).getCategory().equals(category)) {
                        if (budget >= gifts.get(i).getPrice() && gifts.get(i).accessQuantity() > 0) { //am schimbat aici
                            budget = budget - gifts.get(i).getPrice();
                            giftsReceived.add(new Gift(gifts.get(i)));
                            gifts.get(i).setQuantity(gifts.get(i).accessQuantity() - 1);
//                            if (gifts.get(i).accessQuantity() == 0) {
//                                gifts.remove(i);
//                            }
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
