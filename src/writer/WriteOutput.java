package writer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

public class WriteOutput {
    /**
     * Metoda care scrie campul obiectului output in fisierul dat de
     * calea de output
     * @param output
     *      obiectul de tip "Output", al carui camp va fi scris in fisier
     * @param outputPath
     *      calea de output
     */
    public void write(final Output output, final String outputPath) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File(outputPath), output);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
