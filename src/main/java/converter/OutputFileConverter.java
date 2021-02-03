package converter;

import pojo.DecathlonResult;

import java.util.List;

public abstract class OutputFileConverter {

    public abstract void convert(List<DecathlonResult> results);

}
