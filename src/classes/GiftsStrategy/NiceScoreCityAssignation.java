package classes.GiftsStrategy;

import classes.Children.Child;
import classes.Presents.Gift;
import classes.SantaClaus.Santa;
import enums.Category;
import enums.Cities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NiceScoreCityAssignation implements AssignGiftsStrategy {
    /**
     * Metoda care asigneaza cadourile copiilor dupa strategia
     * "niceScoreCity". Mai intai, va fi creat un HashMap unde
     * cheile vor fi orasele, iar valorile vor fi scorurile medii
     * de cumintenie pentru respectivul oras. Dupa ce se vor calcula
     * pentru fiecare oras in parte, se vor sorta descrescator dupa
     * acest scor, iar daca doua orase au acelasi scor, sortarea se
     * va face crescator lexicografic. Vom parcurge apoi orasele, pe
     * rand, precum si lista de copii, asignandu-le cadouri in aceeasi
     * maniera ca la celelalte metode de impartire a cadourilor.
     *      lista de copii
     * @param santa
     *      Mos Craciun
     */
    @Override
    public void assignGifts(final ArrayList<Child> children, final Santa santa) {

        HashMap<Cities, Double> hashMap = new HashMap<>();

        for (Cities city : Cities.values()) {
            int number = 0;
            Double sum = 0d;
            for (Child child : children) {
                if (child.getCity().equals(city)) {
                    number++;
                    sum = sum + child.getAverageScore();
                }
            }
            Double average;
            if (number != 0) {
                average = sum / number;
            } else {
                average = 0d;
            }
            hashMap.put(city, average);
        }

        List<Map.Entry<Cities, Double>> list =
                new LinkedList<Map.Entry<Cities, Double>>(hashMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Cities, Double>>() {
            public int compare(final Map.Entry<Cities, Double> o1,
                               final Map.Entry<Cities, Double> o2) {

                if (o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().toString().compareTo(o2.getKey().toString());
                } else {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            }
        });

        HashMap<Cities, Double> newHashMap = new LinkedHashMap<Cities, Double>();
        for (Map.Entry<Cities, Double> entry : list) {
            newHashMap.put(entry.getKey(), entry.getValue());
        }

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

        for (Map.Entry<Cities, Double> entry : newHashMap.entrySet()) {
            for (Child child : children) {
                if (child.getCity().equals(entry.getKey())) {
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
            }
        }
        santa.setSantaGiftsList(gifts);
    }
}
