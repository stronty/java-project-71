package hexlet.code.formatters;

import java.lang.StringBuilder;
import java.util.List;
import java.util.Map;

public class Plain {
    private static boolean isComplex(Object value) {
        return value instanceof Map
                || value instanceof Iterable
                || value != null && value.getClass().isArray();
    }

    public static String formatter(List<Map<String, Object>> data) {
        StringBuilder diff = new StringBuilder();
        final var property = "Property '";
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

            if (status.equals("removed")) {
                diff.append(property + key + "' was removed\n");
            } else if (status.equals("added")) {
                diff.append(property + key + "' was added with value: " + newValue + "\n");
            } else if (status.equals("updated")) {
                diff.append(property + key + "' was updated."
                        + " From " + oldValue + " to " + newValue + "\n");
            }
        });
        int lastNewline = diff.lastIndexOf("\n");
        if (lastNewline != -1) {
            diff.setLength(lastNewline);
        }
        return diff.toString();
    }
}
