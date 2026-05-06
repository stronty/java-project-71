package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Plain {
    private static boolean isComplex(Object value) {
        return value instanceof Map
                || value instanceof Iterable
                || value != null && value.getClass().isArray();
    }

    public static String formatter(List<Map<String, Object>> data) {
        final String[] diff = {""};
        data.forEach(line -> {
            var status = line.get("status").toString();
            var key = line.get("key");
            String oldValue;
            String newValue;
            var oldType = line.get("oldValue");
            var newType = line.get("newValue");

            if (isComplex(oldType)) {
                oldValue = "[complex value]";
            } else if (oldType instanceof String) {
                oldValue = "'" + oldType + "'";
            } else {
                oldValue = String.valueOf(oldType);
            }

            if (isComplex(newType)) {
                newValue = "[complex value]";
            } else if (newType instanceof String) {
                newValue = "'" + newType + "'";
            } else {
                newValue = String.valueOf(newType);
            }

            switch (status) {
                case "removed" -> diff[0] = diff[0] + "Property '" + key + "' was removed\n";
                case "added" -> diff[0] = diff[0] + "Property '" + key + "' was added with value: " + newValue + "\n";
                case "updated" -> diff[0] = diff[0] + "Property '" + key + "' was updated. From " + oldValue + " to " + newValue + "\n";
                default -> {
                }
            }
        });
        return diff[0];
    }
}
