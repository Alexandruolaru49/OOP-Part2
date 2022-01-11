package reader;

import classes.Changes.AnnualChanges;
import classes.Children.Child;
import classes.Presents.Gift;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.Constants;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class InputLoader {
    private final String inputPath;

    /**
     * Constructor
     * @param inputPath
     *      calea de input
     */
    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * Metoda care face citirea din fisierul dat de inputPath
     * si care creeaza o instanta de tipul "Input", setandu-i
     * campurile cu valorile citite din fisier.
     * @return
     *      instanta de tip "Input"
     */
    public Input read() {

        int numberOfYears = 0;
        Double santaBudget = 0d;
        JSONParser jsonParser = new JSONParser();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Child> children = new ArrayList<>();
        ArrayList<Gift> santaGiftsList = new ArrayList<>();
        ArrayList<AnnualChanges> annualChanges = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(inputPath));

            numberOfYears = ((Long) jsonObject.get(Constants.NUMBER_OF_YEARS)).intValue();
            santaBudget = ((Long) jsonObject.get(Constants.SANTA_BUDGET)).doubleValue();
            JSONObject initialData = (JSONObject) jsonObject.get(Constants.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray) initialData.get(Constants.CHILDREN);
            children = mapper.readValue(jsonChildren.toString(),
                    new TypeReference<ArrayList<Child>>() { });
            JSONArray jsonGifts = (JSONArray) initialData.get(Constants.SANTA_GIFTS_LIST);
            santaGiftsList = mapper.readValue(jsonGifts.toString(),
                    new TypeReference<ArrayList<Gift>>() { });
            JSONArray jsonUpdates = (JSONArray) jsonObject.get(Constants.ANNUAL_CHANGES);
            annualChanges = mapper.readValue(jsonUpdates.toString(),
                    new TypeReference<ArrayList<AnnualChanges>>() { });
        } catch (ParseException | IOException exception) {
            exception.printStackTrace();
        }
        return new Input(numberOfYears, santaBudget, children, santaGiftsList, annualChanges);
    }
}
