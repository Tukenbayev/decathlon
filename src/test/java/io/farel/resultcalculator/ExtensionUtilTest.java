package io.farel.resultcalculator;

import io.farel.resultcalculator.enums.InputFileExtension;
import io.farel.resultcalculator.util.ExtensionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ExtensionUtilTest {

    @Test
    @DisplayName("Should return file extension from file name")
    void shouldReturnExtension() {
        String fileName = "result.csv";
        Assertions.assertEquals(InputFileExtension.CSV, ExtensionUtil.getInputExtension(fileName));
    }

    @Test
    @DisplayName("Should throw exception")
    void shouldThrowException() {
        String fileName = "result.docx";
        Assertions.assertThrows(IllegalArgumentException.class, () -> ExtensionUtil.getInputExtension(fileName));
    }
}
