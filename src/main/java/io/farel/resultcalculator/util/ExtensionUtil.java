package io.farel.resultcalculator.util;

import io.farel.resultcalculator.enums.InputFileExtension;

public class ExtensionUtil {

    /**
     * Returns file extension by input file name
     * @param fileName ex: result.csv
     * @return {@link InputFileExtension}
     */
    public static InputFileExtension getInputExtension(String fileName) {
        int dotIdx = 0;
        for (int idx = fileName.length() - 1; idx > 0; idx--) {
            if (fileName.charAt(idx) == '.') {
                dotIdx = idx;
                break;
            }
        }

        return InputFileExtension.get(fileName.substring(dotIdx+1));
    }
}
