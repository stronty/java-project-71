package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {
    public static String formatter(List<Map<String, Object>> data) {
        StringBuilder diff = new StringBuilder("{\n");

        data.forEach(line -> {
            var status = line.get("status").toString();
            var key = line.get("key");
            var oldValue = String.valueOf(line.getOrDefault("oldValue", ""));
            var newValue = String.valueOf(line.getOrDefault("newValue", ""));

            if (status.equals("removed")) {
                diff.append("  - " + key + ": " + oldValue + "\n");
            } else if (status.equals("added")) {
                diff.append("  + " + key + ": " + newValue + "\n");
            } else if (status.equals("unchanged")) {
                diff.append("    " + key + ": " + oldValue + "\n");
            } else if (status.equals("updated")) {
                diff.append("  - " + key + ": " + oldValue + "\n");
                diff.append("  + " + key + ": " + newValue + "\n");
            }
        });
        diff.append("}");
        return diff.toString();
    }
}
