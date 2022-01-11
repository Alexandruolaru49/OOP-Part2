package writer;

import classes.Children.Child;

import java.util.ArrayList;

public class Auxiliary {
    private ArrayList<Child> children;

    /**
     * Constructor ce creeaza o lista noua de copii
     */
    public Auxiliary() {
        children = new ArrayList<>();
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
     * Setter pentru children
     * @param children
     *      lista de copii
     */
    public void setChildren(final ArrayList<Child> children) {
        for (Child i : children) {
            this.children.add(new Child(i));
        }
    }

}
