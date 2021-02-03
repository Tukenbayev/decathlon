package parser;

import pojo.DecathlonResult;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class CsvParser extends FileParser{

    @Override
    public List<DecathlonResult> parse(FileReader fileReader) throws IOException, IllegalAccessException {
        List<DecathlonResult> results = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(fileReader)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > 1) {
                    String[] values = line.split(";");
                    results.add(createFromStrValues(values));
                }
            }
        }

        return results;
    }

    private DecathlonResult createFromStrValues(String[] values) throws IllegalAccessException {
        DecathlonResult result = new DecathlonResult();
        Field[] fields = DecathlonResult.class.getFields();
        for (int i = 0; i < values.length; i++) {
            fields[i].setAccessible(true);
            if (i == 0) { // athlete name
                fields[i].set(result, values[i]);
            } else if (i == values.length - 1) { // 1500m event result
                String[] time = values[i].split("\\."); // 5.25.76
                fields[i].set(result, Double.parseDouble(time[0]) * 60 + Double.parseDouble(time[1]) + Double.parseDouble("0." + time[2]));
            } else {
                fields[i].set(result, Double.valueOf(values[i]));
            }
        }
        return result;
    }
}
