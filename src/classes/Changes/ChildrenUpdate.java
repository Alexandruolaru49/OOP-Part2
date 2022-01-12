package classes.Changes;

import enums.Category;

import java.util.ArrayList;

public class ChildrenUpdate {
    private int id;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private String elf;

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
     * Getter pentru niceScore
     * @return
     *      scorul de cumintenie
     */
    public Double getNiceScore() {
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
     *      preferintele pentru cadouri
     */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /**
     * Setter pentru giftsPreferences
     * @param giftsPreferences
     *      preferintele pentru cadouri
     */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /**
     * Getter pentru elf
     * @return
     *      elful copilului
     */
    public String getElf() {
        return elf;
    }

    /**
     * Setter pentru elf
     * @param elf
     *      elful copilului
     */
    public void setElf(final String elf) {
        this.elf = elf;
    }
}

