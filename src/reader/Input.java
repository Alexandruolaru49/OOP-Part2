package reader;

import classes.Changes.AnnualChanges;
import classes.Children.Child;
import classes.Presents.Gift;

import java.util.ArrayList;

public class Input {

    private final int numberOfYears;
    private final Double santaBudget;
    private final ArrayList<Child> children;
    private final ArrayList<Gift> santaGiftsList;
    private final ArrayList<AnnualChanges> annualChanges;

    /**
     * Constructor
     * @param numberOfYears
     *      numarul de ani pe care se face simularea
     * @param santaBudget
     *      bugetul lui Mos Craciun
     * @param children
     *      lista de copii
     * @param santaGiftsList
     *      lista de cadouri a Mosului
     * @param annualChanges
     *      lista de schimbari anuale
     */
    public Input(final int numberOfYears, final Double santaBudget,
                     final ArrayList<Child> children, final ArrayList<Gift> santaGiftsList,
                     final ArrayList<AnnualChanges> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.children = children;
        this.santaGiftsList = santaGiftsList;
        this.annualChanges = annualChanges;
    }

    /**
     * Getter pentru numberOfYears
     * @return
     *      numarul de ani
     */
    public int getNumberOfYears() {
        return numberOfYears;
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
     * Getter pentru children
     * @return
     *      lista de copii
     */
    public ArrayList<Child> getChildren() {
        return children;
    }

    /**
     * Getter pentru santaGiftsList
     * @return
     *      lista de cadouri
     */
    public ArrayList<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    /**
     * Getter pentru annualChanges
     * @return
     *      lista de schimbari anuale
     */
    public ArrayList<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

}
