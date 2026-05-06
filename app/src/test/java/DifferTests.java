import hexlet.code.Differ;
import java.nio.file.Paths;
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
              +  "}";
        String actual = Differ.generate(
                "src/examples/example1.json",
                "src/examples/example2.json",
                "stylish");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testRelativeAndAbsolutePath() {
        String rel = Differ.generate(
                "src/examples/example1.json",
                "src/examples/example2.json",
                "stylish"
        );

        String abs1 = Paths.get("src/examples/example1.json")
                .toAbsolutePath()
                .toString();

        String abs2 = Paths.get("src/examples/example2.json")
                .toAbsolutePath()
                .toString();

        String abs = Differ.generate(abs1, abs2, "stylish");

        Assertions.assertEquals(rel, abs);
    }

    @Test
    void testEqual() {
        String expected =
            "{\n"
            + "    follow: false\n"
            + "    host: hexlet.io\n"
            + "    proxy: 123.234.53.22\n"
            + "    timeout: 50\n"
            + "}";
        String actual = Differ.generate(
                "src/examples/example1.json",
                "src/examples/example1.json",
                "stylish");
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
            + "}";
        String actual = Differ.generate(
            "src/examples/example1.yaml",
            "src/examples/example2.yaml",
            "stylish");
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
                + "}";
        String actual = Differ.generate(
                "src/examples/example1.yaml",
                "src/examples/example1.yaml",
                "stylish");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPlainJson() {
        String expected =
                "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";

        String actual = Differ.generate(
                "src/examples/stylish1.json",
                "src/examples/stylish2.json",
                "plain"
        );

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testToJson() {
        String expected = "[{\"key\":\"chars1\",\"status\":\"unchanged\",\"value\":[\"a\",\"b\",\"c\"]},"
                + "{\"key\":\"chars2\",\"status\":\"updated\",\"oldValue\":[\"d\",\"e\",\"f\"],\"newValue\":false},"
                + "{\"key\":\"checked\",\"status\":\"updated\",\"oldValue\":false,\"newValue\":true},"
                + "{\"key\":\"default\",\"status\":\"updated\",\"oldValue\":null,\"newValue\":[\"value1\",\"value2\"]},"
                + "{\"key\":\"id\",\"status\":\"updated\",\"oldValue\":45,\"newValue\":null},"
                + "{\"key\":\"key1\",\"status\":\"removed\",\"value\":\"value1\"},"
                + "{\"key\":\"key2\",\"status\":\"added\",\"value\":\"value2\"},"
                + "{\"key\":\"numbers1\",\"status\":\"unchanged\",\"value\":[1,2,3,4]},"
                + "{\"key\":\"numbers2\",\"status\":\"updated\",\"oldValue\":[2,3,4,5],\"newValue\":[22,33,44,55]},"
                + "{\"key\":\"numbers3\",\"status\":\"removed\",\"value\":[3,4,5]},"
                + "{\"key\":\"numbers4\",\"status\":\"added\",\"value\":[4,5,6]},"
                + "{\"key\":\"obj1\",\"status\":\"added\",\"value\":{\"nestedKey\":\"value\",\"isNested\":true}},"
                + "{\"key\":\"setting1\",\"status\":\"updated\",\"oldValue\":\"Some value\",\"newValue\":\"Another value\"},"
                + "{\"key\":\"setting2\",\"status\":\"updated\",\"oldValue\":200,\"newValue\":300},"
                + "{\"key\":\"setting3\",\"status\":\"updated\",\"oldValue\":true,\"newValue\":\"none\"}]";
        String actual = Differ.generate("src/examples/stylish1.json", "src/examples/stylish2.json", "json");

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testJsonYamlConsistency() {
        String jsonResult = Differ.generate(
                "src/examples/example1.json",
                "src/examples/example2.json",
                "stylish"
        );

        String yamlResult = Differ.generate(
                "src/examples/example1.yaml",
                "src/examples/example2.yaml",
                "stylish"
        );

        Assertions.assertEquals(jsonResult, yamlResult);
    }

}
