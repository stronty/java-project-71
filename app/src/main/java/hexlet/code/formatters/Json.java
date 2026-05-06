package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Json {
    public static String formatter(List<Map<String, Object>> data) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> sorted = new ArrayList<>();

        data.forEach(line -> {

            Map<String, Object> dataToJson = new LinkedHashMap<>();
            var status = line.get("status").toString();
            dataToJson.put("key", line.get("key"));
            dataToJson.put("status", status);

            switch (status) {
                case "unchanged" -> {
                    dataToJson.put("value", line.get("oldValue"));
                }
                case "added" -> {
                    dataToJson.put("value", line.get("newValue"));
                }
                case "removed" -> {
                    dataToJson.put("value", line.get("oldValue"));
                }
                default -> {
                    dataToJson.put("oldValue", line.get("oldValue"));
                    dataToJson.put("newValue", line.get("newValue"));
                }
            }
            sorted.add(dataToJson);
        });

        try {
            return objectMapper.writeValueAsString(sorted);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
