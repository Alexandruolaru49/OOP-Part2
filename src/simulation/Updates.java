package simulation;

import classes.Changes.ChildrenUpdate;
import classes.Children.Child;
import classes.Presents.Gift;
import classes.SantaClaus.Santa;
import common.Constants;
import enums.Category;

import java.util.ArrayList;

public final class Updates {

    private static Updates instance = null;

    private Updates() {

    }

    /**
     * Metoda pentru a crea o singura instanta a clasei daca aceasta
     * nu exista, sau pentru a intoarce respectiva instanta (clasa Singleton)
     * @return
     *      instanta
     */
    public static Updates getInstance() {
        if (instance == null) {
            instance = new Updates();
        }
        return instance;
    }

    /**
     * Metoda care elimina din lista copiii de peste 18 ani
     * @param childrenList
     *      lista de copii
     */
    public void removeOver18(final ArrayList<Child> childrenList) {
        for (int i = 0; i < childrenList.size(); i++) {
            Child child = childrenList.get(i);
            if (child.getAge() > Constants.YOUNG_ADULT_AGE) {
                childrenList.remove(i);
            }
        }
    }

    /**
     * Metoda ce incrementeaza varsta copiilor, eliminandu-i pe aceea
     * care au peste 18 ani.
     * @param childrenList
     *      lista de copii
     * @return
     *      lista de copii
     */
    public ArrayList<Child> increaseAge(final ArrayList<Child> childrenList) {
        for (int i = 0; i < childrenList.size(); i++) {
            Child child = childrenList.get(i);
            child.setAge(child.getAge() + 1);
        }
        removeOver18(childrenList);
        return childrenList;
    }

    /**
     * Metoda ce adauga lista anuala de copii la cea deja existenta
     * si adaug scorul lor de cumintenie curent la istoric.
     * @param childrenList
     *      lista de copii
     * @param newChildren
     *      lista cu noii copii ce trebuie inserati
     * @return
     *      lista noua de copii
     */
    public ArrayList<Child> updateWithNewChildren(final ArrayList<Child> childrenList,
                                                  final ArrayList<Child> newChildren) {
        for (int i = 0; i < newChildren.size(); i++) {
            Child child = new Child(newChildren.get(i));
            if (child.getAge() <= Constants.YOUNG_ADULT_AGE) {
                child.getNiceScoreHistory().add(child.accessNiceScore());
                childrenList.add(new Child(child));
            }
        }
        return childrenList;
    }

    /**
     * Metoda ce adauga noile cadouri anuale in lista deja existenta cu cadouri
     * @param santa
     *      Mos Craciun
     * @param newGifts
     *      Lista cu noile cadouri anuale
     */
    public void addNewGifts(final Santa santa, final ArrayList<Gift> newGifts) {
        for (int i = 0; i < newGifts.size(); i++) {
            Gift gift = newGifts.get(i);
            santa.getSantaGiftsList().add(new Gift(gift));
        }
    }

    /**
     * Metoda ce elimina categoriile duplicat din lista de categorii,
     * comparand cu lista noua de categorii
     * @param initialList
     *      lista initiala de categorii ale cadourilor
     * @param updatedList
     *      lista noua fara categorii duplicat
     */
    public ArrayList<Category> removeDuplicateCategories(final ArrayList<Category> initialList,
                                          final ArrayList<Category> updatedList) {
        for (int i = 0; i < updatedList.size(); i++) {
            for (int j = 0; j < initialList.size(); j++) {
                if (initialList.get(j).equals(updatedList.get(i))) {
                    initialList.remove(j);
                }
            }
        }
        return initialList;
    }

    /**
     * Metoda care elimina categoriile duplicate din aceeasi lista.
     * @param list
     *      lista initiala de categorii
     * @return
     *      lista actualizata de categorii
     */
    public ArrayList<Category> removeDuplicatesFromSameList(final ArrayList<Category> list) {
        ArrayList<Category> updatedList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (!updatedList.contains(list.get(i))) {
                updatedList.add(list.get(i));
            }
        }
        return updatedList;
    }

    /**
     * Metoda ce insereaza noile categorii in lista deja existenta
     * @param initialList
     *      lista initiala de categorii ale cadourilor
     * @param updatedList
     *      lista actualizata de categorii ale cadourilor
     */
    public ArrayList<Category> updateGiftPreferences(final ArrayList<Category> initialList,
                                                     final ArrayList<Category> updatedList) {
        for (int i = 0; i < initialList.size(); i++) {
            updatedList.add(initialList.get(i));
        }
        return updatedList;
    }

    /**
     * Metoda care actualizeaza scorul de cumintenie si preferintele cadourilor
     * pentru copii. In cazul scorurilor de cumintenie, se adauga noul scor in
     * istoricul copilului, iar in cazul preferintelor de cadouri, se elimina
     * categoriile duplicatele.
     * @param childrenList
     *      lista de copii
     * @param childrenUpdates
     *      lista cu actualizari pentru copii
     */
    public void makeUpdates(final ArrayList<Child> childrenList,
                            final ArrayList<ChildrenUpdate> childrenUpdates) {

        for (int i = 0; i < childrenUpdates.size(); i++) {
            int id = childrenUpdates.get(i).getId();
            Double niceScore = childrenUpdates.get(i).getNiceScore();
            ArrayList<Category> giftsPreferences = childrenUpdates.get(i).getGiftsPreferences();

            for (int j = 0; j < childrenList.size(); j++) {
                if (childrenList.get(j).getId() == id) {
                    if (niceScore != null) {
                        ArrayList<Double> newNiceScoreHistory = new ArrayList<>();
                        newNiceScoreHistory = childrenList.get(j).getNiceScoreHistory();
                        newNiceScoreHistory.add(niceScore);
                        childrenList.get(j).setNiceScoreHistory(newNiceScoreHistory);
                    }
                    if (giftsPreferences != null) {
                        childrenList.get(j).setGiftsPreferences(
                        removeDuplicateCategories(childrenList.get(j).getGiftsPreferences(),
                                giftsPreferences));
                        ArrayList<Category> newGiftsPreferences = new ArrayList<>();

                        newGiftsPreferences = updateGiftPreferences(childrenList.get(j)
                                        .getGiftsPreferences(), giftsPreferences);
                        newGiftsPreferences = removeDuplicatesFromSameList(newGiftsPreferences);
                        childrenList.get(j).setGiftsPreferences(newGiftsPreferences);
                    }
                }
            }
        }
    }


}
