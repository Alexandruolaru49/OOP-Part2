package writer;

import java.util.ArrayList;

public class Output {

    private ArrayList<Auxiliary> annualChildren;

    /**
     * Constructor care creeaza o lista noua.
     */
    public Output() {
        this.annualChildren = new ArrayList<Auxiliary>();
    }

    /**
     * Metoda ce adauga un obiect auxiliar la lista annualChildren
     * (practic adauga o lista anuala de copii)
     * @param auxiliary
     *      obiect auxiliar(care contine o lista de copii)
     */
    public void addList(final Auxiliary auxiliary) {
        this.annualChildren.add(auxiliary);
    }

    /**
     * Getter pentru annualChildren
     * @return
     *      lista anuala de copii
     */
    public ArrayList<Auxiliary> getAnnualChildren() {
        return annualChildren;
    }

    /**
     * Setter pentru annualChildren
     * @param annualChildren
     *      lista anuala de copii
     */
    public void setAnnualChildren(final ArrayList<Auxiliary> annualChildren) {
        this.annualChildren = annualChildren;
    }

}
