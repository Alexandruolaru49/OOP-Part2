package simulation;

import classes.Changes.AnnualChanges;
import classes.Changes.ChildrenUpdate;
import classes.Children.Child;
import classes.Presents.Gift;
import classes.SantaClaus.Santa;
import classes.AverageScoreStrategies.ApplyStrategy;
import classes.Elves.Elf;
import classes.Elves.ElfFactory;
import reader.Input;
import common.Constants;

import java.util.ArrayList;
import java.util.Comparator;

public final class Initialize {

    private static Initialize instance = null;

    private Initialize() {

    }

    /**
     * Metoda pentru a crea o singura instanta a clasei daca aceasta
     * nu exista, sau pentru a intoarce respectiva instanta (clasa Singleton)
     * @return
     *      instanta
     */
    public static Initialize getInstance() {
        if (instance == null) {
            instance = new Initialize();
        }
        return instance;
    }

    /**
     * Metoda care creeaza o lista noua de copii, adaugand in ea
     * doar pe cei care au varsta mai mica de 19 ani
     * @param input
     *      obiect ce contine datele de intrare
     * @return
     *      lista de copii initiala, fara cei mai mari de 18 ani
     */
    public ArrayList<Child> makeInitialList(final Input input) {

        ArrayList<Child> initialList = new ArrayList<Child>();
        ArrayList<Child> children = input.getChildren();

        for (int i = 0; i < children.size(); i++) {
            Child child = children.get(i);
            if (child.getAge() <= Constants.YOUNG_ADULT_AGE) {
                initialList.add(new Child(child));
            }
        }
        return initialList;
    }

    /**
     * Metoda ce intoarce lista de copii din anul 0
     * @param santa
     *      Mos Craciun
     * @param input
     *      obiectul ce contine datele de intrare
     * @return
     *      lista de copii din primul an
     */
    public ArrayList<Child> getFirstYearList(final Santa santa, final Input input) {
        ArrayList<Child> firstYearList = getUpdatedList(santa, input);
        return firstYearList;
    }

    /**
     * Metoda care aplica strategia pe lista initiala  de copii, calculandu-le si
     * setandu-le fiecaruia scorul mediu de cumintenie. Aceasta noua lista
     * creata va fi parcursa, iar pentru fiecare copil vor fi calculate si
     * setate: bugetul asignat, cadourile primite, iar scorul curent de
     * cumintenie va fi salvat in istoricul de scoruri de cumintenie.
     * Aceasta metoda este folosita pentru calcularea listei de copii din anul 0.
     * @param santa
     *      Mos Craciun
     * @param input
     *      obiect ce contine datele de intrare
     * @return
     *      lista actualizata de copii
     */
    public ArrayList<Child> getUpdatedList(final Santa santa, final Input input) {
        ArrayList<Child> initialList = makeInitialList(input);
        ArrayList<Child> updatedList;
        ApplyStrategy objApplyStrategy = new ApplyStrategy();

        updatedList = objApplyStrategy.applyStrategy(initialList);

        //APLIC NICE SCORE BONUS PT FIECARE COPIL DOAR LA RUNDA 0:
        for (int i = 0; i < updatedList.size(); i++) {
            Child child = updatedList.get(i);
            Double score = child.getAverageScore();
            updatedList.get(i).setAverageScore(score + score * child.accessNiceScoreBonus() / 100);
        }
        //!!

        //SORTAM IN FUNCTIE DE STRATEGIA DE ASIGNARE A CADOURILOR - AICI ID
        updatedList.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });


        for (int i = 0; i < updatedList.size(); i++) {
            Child child = updatedList.get(i);
            child.calculateAssignedBudget(santa, updatedList);

            //AICI APLICAM ELFII PT BUGET --DESIGN PATTERN VISITOR
            ElfFactory elfFactory = new ElfFactory();
            //System.out.println(child.accessElf());
            //System.out.println(child.toString());
            Elf elf = elfFactory.createElf(child.accessElf()); //creez elf in functie de tipul elfului pe care il are copilul
            //elf.updateBudget(child); --> tipul de elf o sa apeleze metoda pentru tipul de child. (visitor)
            if (!child.accessElf().equals(Constants.YELLOW)) {
                elf.updateBudget(child);
            }

            //AICI TREBUIE SA FAC MODIFICARI LA IMPARTIREA CADOURILOR - DUPA ID.
            child.calculateReceivedGifts(santa);
            //AICI TREBUIE SA APLIC ELFUL YELLOW


            child.getNiceScoreHistory().add(child.accessNiceScore());
        }
        return updatedList;
    }

    /**
     * Metoda care citeste schimbarile anuale si face modificari in lista
     * de copii. Varsta copiilor este incrementata, iar copiii noi sunt si ei
     * adaugati in lista (sunt pastrati doar cei cu varsta <= 18 ani). Este
     * actualizat noul buget al lui Mos Craciun, sunt adaugate cadourile noi,
     * sunt actualizate scorurile de cumintenie si preferintele copiilor,
     * am aplicat strategia si am setat bugetul si cadourile primite pentru
     * copii.
     * @param childrenList
     *      lista de copii din anul trecut
     * @param santa
     *      Mos Craciun
     * @param input
     *      obiectul ce contine datele de intrare
     * @param year
     *      anul curent
     * @return
     *      lista de copii pentru anul curent
     */
    public ArrayList<Child> getAnnualList(final ArrayList<Child> childrenList, final Santa santa,
                                          final Input input, final int year) {

        Updates updates = Updates.getInstance();
        AnnualChanges changes = input.getAnnualChanges().get(year - 1);
        Double newSantaBudget = changes.getNewSantaBudget();
        ArrayList<Child> newChildren = changes.getNewChildren();
        ArrayList<ChildrenUpdate> childrenUpdates = changes.getChildrenUpdates();
        ArrayList<Gift> newGifts = changes.getNewGifts();

        ArrayList<Child> newChildrenList = new ArrayList<>();
        for (int i = 0; i < childrenList.size(); i++) {
                newChildrenList.add(new Child(childrenList.get(i)));
        }

        newChildrenList = updates.increaseAge(newChildrenList);

        newChildrenList = updates.updateWithNewChildren(newChildrenList, newChildren);

        santa.setSantaBudget(newSantaBudget);
        updates.addNewGifts(santa, newGifts);

        updates.makeUpdates(newChildrenList, childrenUpdates);

        ArrayList<Child> updatedList;
        ApplyStrategy objApplyStrategy = new ApplyStrategy();

        updatedList = objApplyStrategy.applyStrategy(newChildrenList);
        updates.removeOver18(updatedList);

        for (int i = 0; i < updatedList.size(); i++) {
            Child child = updatedList.get(i);
            child.calculateAssignedBudget(santa, updatedList);

            //AICI APLICAM ELFII PT BUGET
            ElfFactory elfFactory = new ElfFactory();
            //System.out.println(child.accessElf());
            //System.out.println(child.toString());
            Elf elf = elfFactory.createElf(child.accessElf()); //creez elf in functie de tipul elfului pe care il are copilul
            //elf.updateBudget(child); --> tipul de elf o sa apeleze metoda pentru tipul de child. (visitor)
            if (!child.accessElf().equals(Constants.YELLOW)) {
                elf.updateBudget(child);
            }

            //AICI TREBUIE SA FAC MODIFICARI LA IMPARTIREA CADOURILOR- IN FUNCTIE DE STRATEGY.
            child.calculateReceivedGifts(santa);


            //AICI TREBUIE SA APLIC ELFUL YELLOW

        }

        return updatedList;
    }
}
