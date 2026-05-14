package hexlet.code;

import static hexlet.code.Parser.parse;

import hexlet.code.formatters.Formatter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

public class Differ {

    public static Path checkAndGetPath(String fileName) {
        Path path = Paths.get(fileName).toAbsolutePath().normalize();

        if (!Files.exists(path)) {
            throw new RuntimeException("File '" + path + "' does not exist");
        }
        return path;
    }

    public static String generate(String path1, String path2) {
        return generate(path1, path2, "stylish");
    }

    public static String generate(String path1, String path2, String format) {
        final var status = "status";
        List<Map<String, Object>> diffData = new ArrayList<>();
        Map<String, Object> content1;
        Map<String, Object> content2;
        try {
            content1 = parse(checkAndGetPath(path1));
            content2 = parse(checkAndGetPath(path2));
        } catch (IOException | RuntimeException e) {
            System.out.println(e.getMessage());
            return "";
        }

        var keys = new TreeSet<String>();
        keys.addAll(content1.keySet());
        keys.addAll(content2.keySet());

        for (String key : keys) {
            boolean in1 = content1.containsKey(key);
            boolean in2 = content2.containsKey(key);
            Map<String, Object> data = new HashMap<>();
            data.put("key", key);
            data.put("oldValue", content1.get(key));
            data.put("newValue", content2.get(key));

            if (in1 && !in2) {
                data.put(status, "removed");
            } else if (!in1 && in2) {
                data.put(status, "added");
            } else if (Objects.equals(content1.get(key), content2.get(key))) {
                data.put(status, "unchanged");
            } else {
                data.put(status, "updated");
            }
            diffData.add(data);
        }

        return Formatter.decide(diffData, format);
    }
}
