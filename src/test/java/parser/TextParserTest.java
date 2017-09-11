package parser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class TextParserTest {

    @Test(expected = RuntimeException.class)
    public void shouldThrownExceptionWhenGivenTextIsBlank() {
        // given
        String text = "";

        // when
        new TextParser().parse(text);
    }

    @Test
    public void shouldDivideTextInParts() {
        // given
        String text = "This is a sentence";

        // when
        List<String> parts = new TextParser().parse(text);

        // then
        assertThat(parts, is(not(emptyList())));
        assertThat(parts, is(Arrays.asList("This", "is", "a", "sentence")));
    }

    @Test
    public void shouldRemoveAllPunctuationsFromText() {
        // given
        String text = "This, is a sentence.";

        // when
        List<String> parts = new TextParser().parse(text);

        // then
        assertThat(parts, is(Arrays.asList("This", "is", "a", "sentence")));
    }

    @Test
    public void shouldReplaceNewLineCharsWithSpaceForWindows() {
        // given
        String text = "This is\r\na sentence";

        // when
        List<String> parts = new TextParser().parse(text);

        // then
        assertThat(parts, is(Arrays.asList("This", "is", "a", "sentence")));
    }

    @Test
    public void shouldReplaceNewLineCharsWithSpaceForUnix() {
        // given
        String text = "This is\na sentence";

        // when
        List<String> parts = new TextParser().parse(text);

        // then
        assertThat(parts, is(Arrays.asList("This", "is", "a", "sentence")));
    }

    @Test
    public void shouldTrimSpacesToOneSpaceBetweenWords() {
        // given
        String text = "This  is\n a  sentence";

        // when
        List<String> parts = new TextParser().parse(text);

        // then
        assertThat(parts, is(Arrays.asList("This", "is", "a", "sentence")));
    }

}