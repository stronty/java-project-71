import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DifferTest {

    @Test
    void testStandard() {
        String expected = "{\n" +
                "  - follow: false\n" +
                "    host: hexlet.io\n" +
                "  - proxy: 123.234.53.22\n" +
                "  - timeout: 50\n" +
                "  + timeout: 20\n" +
                "  + verbose: true\n" +
                "}\n";
        String actual = Differ.generate("src/examples/example1.json", "src/examples/example2.json");
        assertEquals(expected, actual);
    }
    @Test
    void testEqual() {
        String expected =
        "{\n" +
        "    follow: false\n" +
        "    host: hexlet.io\n" +
        "    proxy: 123.234.53.22\n" +
        "    timeout: 50\n" +
        "}\n";
        String actual = Differ.generate("src/examples/example1.json", "src/examples/example1.json");
        assertEquals(expected, actual);


    }
}