package classes.GiftsStrategy;

import classes.Children.Child;
import classes.SantaClaus.Santa;

import java.util.ArrayList;

public interface AssignGiftsStrategy {
    /**
     * Semnatura unei metode care asigneaza cadourile copiilor,
     * care va fi implementata in clasele ce reprezinta cate
     * o strategie de asignare, fiecare implementand aceasta
     * interfata.
     * @param children
     *      lista de copii
     * @param santa
     *      Mos Craciun
     */
    void assignGifts(ArrayList<Child> children, Santa santa);
}
