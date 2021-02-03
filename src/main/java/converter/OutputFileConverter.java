package converter;

import enums.OutputFileExtension;
import pojo.DecathlonResult;

import java.util.List;

public abstract class OutputFileConverter {

    public abstract void convert(List<DecathlonResult> results);

}
