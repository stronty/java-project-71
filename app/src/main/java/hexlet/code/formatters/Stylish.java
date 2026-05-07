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

            if (status.equals("removed")) {
                diff[0] = diff[0] + "  - " + key + ": " + oldValue + "\n";
            } else if (status.equals("added")) {
                diff[0] = diff[0] + "  + " + key + ": " + newValue + "\n";
            } else if (status.equals("unchanged")) {
                diff[0] = diff[0] + "    " + key + ": " + oldValue + "\n";
            } else if (status.equals("updated")) {
                diff[0] = diff[0] + "  - " + key + ": " + oldValue + "\n";
                diff[0] = diff[0] + "  + " + key + ": " + newValue + "\n";
            }
        });
        diff[0] = diff[0] + "}";
        return diff[0];
    }
}
