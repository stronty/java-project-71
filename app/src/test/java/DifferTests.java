import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DifferTests {

    @Test
    void testStandard() {
        String expected = "{\n"
              + "  - follow: false\n"
              +  "    host: hexlet.io\n"
              +  "  - proxy: 123.234.53.22\n"
              +  "  - timeout: 50\n"
              +  "  + timeout: 20\n"
              +  "  + verbose: true\n"
              +  "}\n";
        String actual = Differ.generate("src/examples/example1.json", "src/examples/example2.json");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testEqual() {
        String expected =
            "{\n"
            + "    follow: false\n"
            + "    host: hexlet.io\n"
            + "    proxy: 123.234.53.22\n"
            + "    timeout: 50\n"
            + "}\n";
        String actual = Differ.generate("src/examples/example1.json", "src/examples/example1.json");
        Assertions.assertEquals(expected, actual);


    }

    @Test
    void testYamlStandard() {
        String expected =
            "{\n"
            + "  - follow: false\n"
            + "    host: hexlet.io\n"
            + "  - proxy: 123.234.53.22\n"
            + "  - timeout: 50\n"
            + "  + timeout: 20\n"
            + "  + verbose: true\n"
            + "}\n";
        String actual = Differ.generate("src/examples/example1.yaml",
            "src/examples/example2.yaml");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testYamlEqual() {
        String expected =
            "{\n"
            + "    follow: false\n"
            + "    host: hexlet.io\n"
            + "    proxy: 123.234.53.22\n"
            + "    timeout: 50\n"
            + "}\n";
        String actual = Differ.generate("src/examples/example1.yaml",
            "src/examples/example1.yaml");
        Assertions.assertEquals(expected, actual);
    }

}
