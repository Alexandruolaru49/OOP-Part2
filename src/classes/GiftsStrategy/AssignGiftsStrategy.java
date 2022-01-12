package classes.GiftsStrategy;

import classes.Children.Child;
import classes.SantaClaus.Santa;

import java.util.ArrayList;

public interface AssignGiftsStrategy {
    void assignGifts(ArrayList<Child> children, Santa santa);
}
