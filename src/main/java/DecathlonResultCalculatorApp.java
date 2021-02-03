import exception.FileExtensionNotSupportedException;
import parser.FileParser;
import pojo.DecathlonResult;
import service.PrizePlaceService;
import util.ExtensionUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DecathlonResultCalculatorApp {

    public static void main(String[] args) {
        System.out.println("DECATHLON RESULT CALCULATOR APP STARTED");
        String fileName = "src/main/resources/results.csv";

        try {
            FileParser parser = FileParser.getParserByExtension(ExtensionUtil.getInputExtension(fileName));
            List<DecathlonResult> results = parser.parse(new FileReader(fileName));

            PrizePlaceService prizePlaceService = new PrizePlaceService();
            prizePlaceService.calculateTotalScore(results);
            prizePlaceService.determinePrizePlaces(results);
        } catch (FileExtensionNotSupportedException | IllegalArgumentException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println("Exception while reading file: " + e.toString());
        } catch (IllegalAccessException e) {
            System.err.println("Exception while parsing results: " + e.toString());
        }
    }
}
