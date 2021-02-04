package io.farel.resultcalculator.converter;

import io.farel.resultcalculator.enums.OutputFileExtension;
import io.farel.resultcalculator.exception.FileExtensionNotSupportedException;
import io.farel.resultcalculator.pojo.DecathlonResult;

import java.io.IOException;
import java.util.List;

public abstract class OutputFileConverter {

    public abstract void convertAndWrite(List<DecathlonResult> results) throws IOException, IllegalAccessException;

    public static OutputFileConverter buildConverter(OutputFileExtension extension) throws FileExtensionNotSupportedException {
        switch (extension) {
            case XML:
                return new XmlConverter();
            case JSON:
                return new JsonConverter();
            default:
                throw new FileExtensionNotSupportedException();
        }
    }

}
