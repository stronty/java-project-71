package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Parser {
    public static String getExtension(Path path) {
        String fileName = path.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');

        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public static Map<String, Object> parse(Path path) throws RuntimeException, IOException {
        String content = Files.readString(path);

        ObjectMapper mapper;
        String extension = getExtension(path);
        Map<String, Object> data = new HashMap<>();

        if (extension.equals("json")) {
            mapper = new ObjectMapper();
            data = mapper.readValue(content, Map.class);
        } else if (extension.equals("yaml") || extension.equals("yml")) {
            mapper = new YAMLMapper();
            data = mapper.readValue(content, Map.class);
        } else {
            throw new RuntimeException("Format does not exist");
        }

        return new TreeMap<>(data);
    }
}
