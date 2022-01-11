package main;

import classes.Children.Child;
import classes.SantaClaus.Santa;
import reader.Input;
import reader.InputLoader;
import simulation.Initialize;
import writer.Auxiliary;
import writer.Output;
import writer.WriteOutput;
import checker.Checker;
import common.Constants;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;


public final class Main {

    private Main() {
        ///constructor for checkstyle
    }

    /**
     * Metoda folosita pentru a sterge fisierele de output create
     * (preluata de la prima tema primita)
     * @param directory
     *          director
     */
    public void deleteFiles(final File[] directory) {
        if (directory != null) {
            for (File file : directory) {
                if (!file.delete()) {
                    System.out.println("nu s-a sters");
                }
            }
        }
    }
    /**
     * Metoda folosita pentru a crea fisierele de output; apeleaza metoda
     * "action" trimitand ca parametri calea de input si calea de output;
     * apeleaza checkerul pentru a calcula punctajul.
     * @param args
     *          the arguments used to call the main method
     */
    public static void main(final String[] args) {

        File outputDirectory = new File("output");
        Main main = new Main();
        main.deleteFiles(outputDirectory.listFiles());
        int numberOfTests = Constants.TESTS_NUMBER;
        for (int i = 1; i <= numberOfTests; i++) {
            //System.out.println("Test " + i);
            String inputPath = "tests/test" + i + ".json";
            String outputPath = "output/out_" + i + ".json";
            action(inputPath, outputPath);
        }

        Checker.calculateScore();
    }

    /**
     *  Metoda citeste datele de intrare prin metoda "read";
     *  Cu ajutorul acestor informatii, este creata lista de copii
     *  din anul 0, iar apoi restul, listele fiind si sortate
     *  crescator dupa id-ul fiecarui copil. Aceste liste sunt adaugate in
     *  campul annualChildren al obiectului output, care este folosit
     *  ca parametru la scrierea in fisiere. Pe langa cele de citire, sunt
     *  apelate urmatoarele metode: "getFirstYearList" care construieste
     *  lista initiala a copiilor, metoda de setare a listei curente drept camp
     *  pentru obiectul auxiliar, metoda de adaugare a listei curente
     *  la cele deja existente ("addList") si metoda "getAnnualList" care
     *  creeaza lista pentru urmatorul an. La final se apeleaza metoda de
     *  scriere in fisiere "write".
     * @param inputPath
     *          calea de input, de unde vor fi citite datele
     * @param outputPath
     *          calea de output, unde vor fi scrise datele
     */
    public static void action(final String inputPath,
                              final String outputPath) {


        InputLoader inputLoader = new InputLoader(inputPath);
        Input input = inputLoader.read();

//        for (int i = 0; i < input.getChildren().size(); i++) {
//            System.out.println(input.getChildren().get(i).toString());
//        }

        Santa santa = Santa.getInstance();
        santa.setSantaBudget(input.getSantaBudget());
        santa.setSantaGiftsList(input.getSantaGiftsList());


        Initialize initialize = Initialize.getInstance();
        ArrayList<Child> childrenList;
        childrenList = initialize.getFirstYearList(santa, input);

        childrenList.sort(new Comparator<Child>() {
            @Override
            public int compare(final Child o1, final Child o2) {
                return Integer.compare(o1.getId(), o2.getId());
            }
        });

        Output output = new Output();
        Auxiliary auxiliary = new Auxiliary();

        auxiliary.setChildren(childrenList);

        output.addList(auxiliary);

        int noYears = input.getNumberOfYears();
        for (int year = 1; year <= noYears; year++) {

            ArrayList<Child> newList = initialize.getAnnualList(childrenList, santa, input, year);
            newList.sort(new Comparator<Child>() {
                @Override
                public int compare(final Child o1, final Child o2) {
                    return Integer.compare(o1.getId(), o2.getId());
                }
            });

            auxiliary = new Auxiliary();
            auxiliary.setChildren(newList);
            output.addList(auxiliary);

            childrenList = new ArrayList<>();
            for (int i = 0; i < newList.size(); i++) {
                childrenList.add(new Child(newList.get(i)));
            }
        }

        WriteOutput writeOutput = new WriteOutput();
        writeOutput.write(output, outputPath);
    }
}
