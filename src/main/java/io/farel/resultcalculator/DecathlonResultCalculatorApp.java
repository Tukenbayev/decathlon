package io.farel.resultcalculator;

import io.farel.resultcalculator.converter.OutputFileConverter;
import io.farel.resultcalculator.enums.OutputFileExtension;
import io.farel.resultcalculator.exception.FileExtensionNotSupportedException;
import io.farel.resultcalculator.parser.FileParser;
import io.farel.resultcalculator.pojo.DecathlonResult;
import io.farel.resultcalculator.service.PrizePlaceService;
import io.farel.resultcalculator.util.ExtensionUtil;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DecathlonResultCalculatorApp {

    public static void main(String[] args) {
        System.out.println("DECATHLON RESULT CALCULATOR APP STARTED");
        String fileName = "src/main/resources/results.csv";
        OutputFileExtension outputExtension = OutputFileExtension.XML;

        try {
            FileParser parser = FileParser.buildParser(ExtensionUtil.getInputExtension(fileName));
            List<DecathlonResult> results = parser.parse(new FileReader(fileName));

            PrizePlaceService prizePlaceService = new PrizePlaceService();
            prizePlaceService.calculateTotalScore(results);
            prizePlaceService.determinePrizePlaces(results);

            OutputFileConverter converter = OutputFileConverter.buildConverter(outputExtension);
            converter.convertAndWrite(results);
        } catch (FileExtensionNotSupportedException | IllegalArgumentException e) {
            System.err.println(e.toString());
        } catch (IOException e) {
            System.err.println("Exception while reading/writing to file: " + e.toString());
        } catch (IllegalAccessException e) {
            System.err.println("Exception while parsing/writing results: " + e.toString());
        }
    }
}
