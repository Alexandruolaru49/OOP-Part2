package classes.Changes;

import classes.Children.Child;
import classes.Presents.Gift;

import java.util.ArrayList;

public class AnnualChanges {
    private Double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<ChildrenUpdate> childrenUpdates;
    //!!
    private String strategy;

    /**
     * Getter pentru newSantaBudget
     * @return
     *      noul buget al Mosului
     */
    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     * Setter pentru newSantaBudget
     * @param newSantaBudget
     *      noul buget al mosului
     */
    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    /**
     * Getter pentru newGifts
     * @return
     *      noile cadouri
     */
    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    /**
     * Setter pentru newGifts
     * @param newGifts
     *      noile cadouri
     */
    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    /**
     * Getter pentru newChildren
     * @return
     *      noii copii
     */
    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    /**
     * Setter pentru newChildren
     * @param newChildren
     *      noii copii
     */
    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    /**
     * Getter pentru childrenUpdates
     * @return
     *      lista de actualizari pentru copii
     */
    public ArrayList<ChildrenUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    /**
     * Setter pentru childrenUpdates
     * @param childrenUpdates
     *      lista de actualizari pentru copii
     */
    public void setChildrenUpdates(final ArrayList<ChildrenUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(final String strategy) {
        this.strategy = strategy;
    }
}
