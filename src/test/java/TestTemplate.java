import com.example.MissingValueException;
import com.example.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestTemplate {

    private Template template;

    @BeforeEach
    public void setUp() throws Exception {
        template = new Template("${one}, ${two}, ${three}");
        template.set("one", "1");
        template.set("two", "2");
        template.set("three", "3");
    }

    @Test
    public void multipleVariables() throws Exception {
        assertTemplateEvaluatesTo("1, 2, 3");
    }

    @Test
    public void unknownVariablesAreIgnored() throws Exception {
        template.set("doesnotexist", "whatever");
        assertTemplateEvaluatesTo("1, 2, 3");
    }

    @Test
    public void missingValueRaisesException() throws Exception {
        try {
            new Template("${foo}").evaluate();
            Assertions.fail("evaluate() should throw an exception if " +
                    "a variable was left without a value!");
        } catch (MissingValueException expected) {
            Assertions.assertEquals("No value for ${foo}", expected.getMessage());
        }
    }

    @Test
    public void variablesGetProcessedJustOnce() throws Exception {
        template.set("one", "${one}");
        template.set("two", "${two}");
        template.set("three", "${three}");
        assertTemplateEvaluatesTo("${one}, ${two}, ${three}");
    }

    private void assertTemplateEvaluatesTo(String expected) {
        Assertions.assertEquals(expected, template.evaluate());
    }


}
