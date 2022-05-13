package sorting.view;

import sorting.model.Data;

public class OutputConsole implements Output {
    @Override
    public void print(Data data) {
        System.out.println(data.getMessage());
    }
}
