package classes.Children;

import classes.Presents.Gift;
import classes.SantaClaus.Santa;
import classes.Elves.BlackElf;
import classes.Elves.PinkElf;
import classes.Elves.WhiteElf;
import common.Constants;
import enums.Category;
import enums.Cities;
import java.util.ArrayList;
import java.util.Comparator;

public class Child {
    private int id;
    private String lastName;
    private String firstName;
    private Cities city;
    private int age;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory = new ArrayList<Double>();
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts = new ArrayList<Gift>();
    private Double niceScoreBonus = 0d;
    private String elf;

    /**
     * Default Constructor
     */
    public Child() {

    }

    /**
     * Copy-constructor
     * @param child
     *      copil
     */
    public Child(final Child child) {
        this.id = child.getId();
        this.lastName = child.getLastName();
        this.firstName = child.getFirstName();
        this.city = child.getCity();
        this.age = child.getAge();
        this.niceScore = child.accessNiceScore();
        this.averageScore = child.getAverageScore();
        this.assignedBudget = child.getAssignedBudget();
        //!!
        this.niceScoreBonus = child.accessNiceScoreBonus();
        this.elf = child.accessElf();

        this.giftsPreferences = new ArrayList<>();
        for (int i = 0; i < child.getGiftsPreferences().size(); i++) {
            this.giftsPreferences.add(child.getGiftsPreferences().get(i));
        }

        this.niceScoreHistory = new ArrayList<>();
        for (int i = 0; i < child.getNiceScoreHistory().size(); i++) {
            this.niceScoreHistory.add(child.getNiceScoreHistory().get(i));
        }

        this.receivedGifts = new ArrayList<>();
        for (int i = 0; i < child.getReceivedGifts().size(); i++) {
            this.receivedGifts.add(child.getReceivedGifts().get(i));
        }
    }

    /**
     * Metoda care calculeaza si seteaza bugetul copilului pentru anul
     * curent dupa formula data.
     * @param santa
     *      Mos Craciun
     * @param children
     *      Lista de copii
     */
    public void calculateAssignedBudget(final Santa santa, final ArrayList<Child> children) {
        Double budgetUnit = (santa.getSantaBudget()) / (santa.getAllScores(children));
        this.setAssignedBudget(this.getAverageScore() * budgetUnit);
    }

    /**
     * Metoda care actualizeaza bugetul conform elfului "Black"
     * @param blackElf
     *      elf de tipul "Black"
     */
    public void updateBudget(final BlackElf blackElf) {
        Double budget = this.getAssignedBudget();
        budget = budget - budget * Constants.THIRTY / Constants.ONE_HUNDRED;
        this.setAssignedBudget(budget);
    }

    /**
     * Metoda care actualizeaza bugetul conform elfului "Pink"
     * @param pinkElf
     *      elf de tipul "Pink"
     */
    public void updateBudget(final PinkElf pinkElf) {
        Double budget = this.getAssignedBudget();
        budget = budget + budget * Constants.THIRTY / Constants.ONE_HUNDRED;
        this.setAssignedBudget(budget);
    }

    /**
     * Metoda care actualizeaza bugetul conform elfului "White"
     * (nu actualizeaza nimic)
     * @param whiteElf
     *      elf de tipul "White"
     */
    public void updateBudget(final WhiteElf whiteElf) {

    }

    /**
     * Metoda care seteaza cadourile pe care le primeste copilul in anul
     * curent. Sunt accesate preferintele copilului pentru a vedea ce
     * tip de cadouri isi doreste acesta, iar apoi ii sunt oferite daca
     * bugetul inca permite cumpararea cadoului.
     * @param santa
     *      Mos Craciun
     */
    public void calculateReceivedGifts(final Santa santa) {
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

        Double budget = this.getAssignedBudget();
        ArrayList<Gift> giftsReceived = new ArrayList<Gift>();

        for (Category category : this.getGiftsPreferences()) {
            for (int i = 0; i < gifts.size(); i++) {
                if (gifts.get(i).getCategory().equals(category)) {
                    if (budget >= gifts.get(i).getPrice() && gifts.get(i).accessQuantity() > 0) {
                        budget = budget - gifts.get(i).getPrice();
                        giftsReceived.add(new Gift(gifts.get(i)));
                        gifts.get(i).setQuantity(gifts.get(i).accessQuantity() - 1);
                        break;
                    }
                }
            }
        }
        this.setReceivedGifts(giftsReceived);
        santa.setSantaGiftsList(gifts);
    }

    /**
     * Getter pentru id
     * @return
     *      id-ul copilului
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pentru id
     * @param id
     *      id-ul copilului
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Getter pentru lastName
     * @return
     *      numele de familie
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter pentru lastName
     * @param lastName
     *      numele de familie
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter pentru firstName
     * @return
     *      prenumele
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter pentru firstName
     * @param firstName
     *      prenumele
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter pentru age
     * @return
     *      varsta
     */
    public int getAge() {
        return age;
    }

    /**
     * Setter pentru age
     * @param age
     *      varsta
     */
    public void setAge(final int age) {
        this.age = age;
    }

    /**
     * Getter pentru city
     * @return
     *      orasul
     */
    public Cities getCity() {
        return city;
    }

    /**
     * Setter pentru oras
     * @param city
     *      orasul
     */
    public void setCity(final Cities city) {
        this.city = city;
    }

    /**
     * Metoda prin care este accesat scorul de cumintenie
     * (nu este getter pentru a nu fi serializat de catre
     * metoda de scriere in fisier)
     * @return
     *      scorul de cumintenie
     */
    public Double accessNiceScore() {
        return niceScore;
    }

    /**
     * Setter pentru niceScore
     * @param niceScore
     *      scorul de cumintenie
     */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /**
     * Getter pentru giftsPreferences
     * @return
     *      preferintele de cadouri
     */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * Setter pentru giftsPreferences
     * @param giftsPreferences
     *      preferintele de cadouri
     */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * Getter pentru averageScore
     * @return
     *      scorul mediu
     */
    public Double getAverageScore() {
        return averageScore;
    }

    /**
     * Setter pentru averageScore
     * @param averageScore
     *      scorul mediu
     */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * Getter pentru niceScoreHistory
     * @return
     *      istoricul cu scorurile de cumintenie
     */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /**
     * Setter pentru niceScoreHistory
     * @param niceScoreHistory
     *      istoricul cu scorurile de cumintenie
     */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * Getter pentru assignedBudget
     * @return
     *      bugetul asignat
     */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * Setter pentru assignedBudget
     * @param assignedBudget
     *      bugetul asignat
     */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * Getter pentru receivedGifts
     * @return
     *      cadourile primite
     */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * Setter pentru receivedGifts
     * @param receivedGifts
     *      cadourile primite
     */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * Metoda prin care se acceseaza "niceScoreBonus"
     * - nu este getter pentru a nu fi serializat de catre
     * metoda de scriere in fisier.
     * @return
     *      bonusul de cumintenie
     */
    public Double accessNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * Setter pentru "niceScoreBonus"
     * @param niceScoreBonus
     *      bonusul de cumintenie
     */
    public void setNiceScoreBonus(final Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    /**
     * Metoda prin care se acceseaza "elf"
     * - nu este getter pentru a nu fi serializat de catre
     * metoda de scriere in fisier.
     * @return
     *      elful copilului
     */
    public String accessElf() {
        return elf;
    }

    /**
     * Setter pentru "elf"
     * @param elf
     *      elful copilului
     */
    public void setElf(final String elf) {
        this.elf = elf;
    }

}
