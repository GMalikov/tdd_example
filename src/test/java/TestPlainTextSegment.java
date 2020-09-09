import com.example.PlainText;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TestPlainTextSegment {
    @Test
    public void plainTextEvaluatesAsIs() {
        Map<String, String> variables = new HashMap<>();
        String text = "abc def";
        Assertions.assertEquals(text, new PlainText(text).evaluate(variables));
    }
}
