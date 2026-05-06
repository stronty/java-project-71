package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formatter(List<Map<String, Object>> data) {
        final String[] diff = {"{\n"};

        data.forEach(line -> {
            var status = line.get("status").toString();
            var key = line.get("key");
            var oldValue = String.valueOf(line.getOrDefault("oldValue", ""));
            var newValue = String.valueOf(line.getOrDefault("newValue", ""));

            switch (status) {
                case "removed" -> diff[0] = diff[0] + "  - " + key + ": " + oldValue + "\n";
                case "added" -> diff[0] = diff[0] + "  + " + key + ": " + newValue + "\n";
                case "unchanged" -> diff[0] = diff[0] + "    " + key + ": " + oldValue + "\n";
                case "updated" -> {
                    diff[0] = diff[0] + "  - " + key + ": " + oldValue + "\n";
                    diff[0] = diff[0] + "  + " + key + ": " + newValue + "\n";
                }
                default -> { }
            }
        });
        diff[0] = diff[0] + "}";
        return diff[0];
    }
}
