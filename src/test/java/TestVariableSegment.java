import com.example.MissingValueException;
import com.example.Variable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestVariableSegment {

    private Map<String, String> variables;

    @BeforeEach
    public void setUp() {
        variables = new HashMap<>();
    }

    @Test
    public void variableEvaluatesToItsValue() {
        Map<String, String> variables = new HashMap<>();
        String name = "myvar";
        String value = "myvalue";
        variables.put(name, value);
        Assertions.assertEquals(value, new Variable(name).evaluate(variables));
    }

    @Test
    public void missingVariableRaisesException() {
        String name = "myvar";
        try {
            new Variable(name).evaluate(variables);
            Assertions.fail("Missing variable value should raise an exception");
        } catch (MissingValueException expected) {
            Assertions.assertEquals("No value for ${" + name + "}", expected.getMessage());
        }
    }
}
