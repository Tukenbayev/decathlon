package io.farel.resultcalculator.parser;

import io.farel.resultcalculator.enums.InputFileExtension;
import io.farel.resultcalculator.exception.FileExtensionNotSupportedException;
import io.farel.resultcalculator.pojo.DecathlonResult;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class FileParser {

    public abstract List<DecathlonResult> parse(FileReader fileReader) throws IOException, IllegalAccessException;

    public static FileParser buildParser(InputFileExtension extension) throws FileExtensionNotSupportedException {
        switch (extension) {
            case CSV:
                return new CsvParser();
            case XLS:
            case XLSX:
                return new XlsParser();
            default:
                throw new FileExtensionNotSupportedException();
        }
    }
}
