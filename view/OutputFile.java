package sorting.view;

import sorting.model.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFile implements Output {
    @Override
    public void print(Data data) {
        File file = new File("./" + data.getOutputFile());

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(data.getMessage());
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
    }
}
