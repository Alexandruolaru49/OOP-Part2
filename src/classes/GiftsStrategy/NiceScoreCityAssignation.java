package classes.GiftsStrategy;

import classes.Children.Child;
import classes.Presents.Gift;
import classes.SantaClaus.Santa;
import common.Constants;
import enums.Category;
import enums.Cities;

import java.util.*;

public class NiceScoreCityAssignation implements AssignGiftsStrategy{

    @Override
    public void assignGifts(ArrayList<Child> children, Santa santa) {

        //MAI INTAI FACEM UN HASHMAP CU ORASE SI MEDIA DE CUMINTENIE:
        HashMap <Cities, Double> hashMap = new HashMap<>();

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

        //SORTAM HASHMAPUL DESCRESCATOR DUPA VALUE:

        //CREAM O LISTA CU ELEMENTELE DIN HASHMAP
        List<Map.Entry<Cities, Double> > list =
                new LinkedList<Map.Entry<Cities, Double>>(hashMap.entrySet());

        //SORTAM LISTA DESCRESCATOR DUPA VALOARE
        Collections.sort(list, new Comparator<Map.Entry<Cities, Double> >() {
            public int compare(Map.Entry<Cities, Double> o1,
                               Map.Entry<Cities, Double> o2)
            {
                //  aici trb completat daca sunt egale !!
                if (o1.getValue().equals(o2.getValue())) {
                    return o1.getKey().toString().compareTo(o2.getKey().toString());
                } else {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            }
        });

        //PUNEM DATELE INAPOI IN HASHMAP
        HashMap<Cities, Double> newHashMap = new LinkedHashMap<Cities, Double>();
        for (Map.Entry<Cities, Double> entry : list) {
            newHashMap.put(entry.getKey(), entry.getValue());
        }

        //AFISAM ORASELE IN ORDINE:
        for (Map.Entry<Cities, Double> entry : newHashMap.entrySet()) {
            System.out.print(entry.getKey().toString() + " " + entry.getValue() + "; ");
        }
        System.out.println();

        // Cream o lista noua de cadouri identica cu lista mosului de cadouri.
        ArrayList<Gift> gifts = new ArrayList<Gift>();
        for (int i = 0; i < santa.getSantaGiftsList().size(); i++) {
            gifts.add(new Gift(santa.getSantaGiftsList().get(i)));
        }

        gifts.sort(new Comparator<Gift>() {
            @Override
            public int compare(Gift o1, Gift o2) {
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });


        //ASIGNAM CADOURILE COPIILOR

        for (Map.Entry<Cities, Double> entry : newHashMap.entrySet()) {
            for (Child child : children) {
                if (child.getCity().equals(entry.getKey())) {
                    Double budget = child.getAssignedBudget();
                    ArrayList<Gift> giftsReceived = new ArrayList<Gift>();

                    for (Category category : child.getGiftsPreferences()) {
                        for (int i = 0; i < gifts.size(); i++) {
                            if (gifts.get(i).getCategory().equals(category) && gifts.get(i).accessQuantity() > 0) { //am schimbat aici
                                if (budget >= gifts.get(i).getPrice()) {
                                    budget = budget - gifts.get(i).getPrice();
                                    giftsReceived.add(new Gift(gifts.get(i)));
                                    gifts.get(i).setQuantity(gifts.get(i).accessQuantity() - 1);
//                                    if (gifts.get(i).accessQuantity() == 0) {
//                                        gifts.remove(i);
//                                    }
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
