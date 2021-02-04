package io.farel.resultcalculator.converter;

import io.farel.resultcalculator.pojo.DecathlonResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class XmlConverter extends OutputFileConverter{

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyHHmmss").withZone(ZoneId.systemDefault());

    @Override
    public void convertAndWrite(List<DecathlonResult> results) throws IOException, IllegalAccessException {
        String fileName = "src/main/resources/places-" + formatter.format(Instant.now()) + ".xml";

        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (String tag : toXML(results)) {
            writer.write(tag);
        }
        writer.close();
    }

    private List<String> toXML(List<DecathlonResult> results) throws IllegalAccessException {
        List<String> tags = new ArrayList<>();
        String fieldTemplate = "\t\t<tagName>value</tagName>\n";

        tags.add("<DecathlonResults>\n");
        Field[] fields = DecathlonResult.class.getFields();
        for (DecathlonResult result : results) {
            tags.add("\t<DecathlonResult>\n");
            for (Field field : fields) {
                tags.add(fieldTemplate.replaceAll("tagName", field.getName()).replaceFirst("value", String.valueOf(field.get(result))));
            }
            tags.add("\t</DecathlonResult>\n");
        }
        tags.add("</DecathlonResults>");
        return tags;
    }

}
