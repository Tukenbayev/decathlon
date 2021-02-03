package parser;

import enums.InputFileExtension;
import exception.FileExtensionNotSupportedException;
import pojo.DecathlonResult;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public abstract class FileParser {

    public abstract List<DecathlonResult> parse(FileReader fileReader) throws IOException, IllegalAccessException;

    public static FileParser getParserByExtension(InputFileExtension extension) throws FileExtensionNotSupportedException {
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
