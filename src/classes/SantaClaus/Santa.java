package classes.SantaClaus;

import classes.Children.Child;
import classes.Presents.Gift;

import java.util.ArrayList;
import java.util.Comparator;

public final class Santa {

    private static Santa instance = null;

    private Santa() {

    }

    /**
     * Metoda pentru a crea o singura instanta a clasei daca aceasta
     * nu exista, sau pentru a intoarce respectiva instanta (clasa Singleton)
     * @return
     *      instanta
     */
    public static Santa getInstance() {
        if (instance == null) {
            instance = new Santa();
        }
        return instance;
    }

    private Double santaBudget;
    private ArrayList<Gift> santaGiftsList;

    /**
     * Metoda care calculeaza si intoarce suma tuturor scorurilor
     * medii de cumintenie ale tuturor copiilor
     * @param children
     *      lista de copii
     * @return
     *      suma tuturor scorurilor medii de cumintenie ale tuturor copiilor
     */
    public Double getAllScores(final ArrayList<Child> children) {

        children.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        Double sum = 0d;
        for (Child i : children) {
            sum = sum + i.getAverageScore();
        }
        return sum;
    }

    /**
     * Getter pentru santaBudget
     * @return
     *      bugetul Mosului
     */
    public Double getSantaBudget() {
        return santaBudget;
    }

    /**
     * Setter pentru santaBudget
     * @param santaBudget
     *      bugetul Mosului
     */
    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /**
     * Getter pentru santaGiftsList
     * @return
     *      lista de cadouri a Mosului
     */
    public ArrayList<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    /**
     * Setter pentru santaGiftsList
     * @param santaGiftsList
     *      lista de cadouri a Mosului
     */
    public void setSantaGiftsList(final ArrayList<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
