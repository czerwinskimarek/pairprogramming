package parser;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextParser {

    public List<String> parse(String text) {
        if (text.isEmpty()) {
            throw new RuntimeException();
        }
        return Arrays.asList(text
                .replaceAll("\\.", "")
                .replaceAll("\r\n", " ")
                .replaceAll("\n", " ")
                .replaceAll(",", "")
                .split(" ")).stream()
                    .filter(word -> !word.isEmpty())
                .collect(Collectors.toList());
    }

}
