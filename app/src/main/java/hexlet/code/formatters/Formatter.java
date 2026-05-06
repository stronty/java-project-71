package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String decide(List<Map<String, Object>> diffData, String format) {
        if (format.equals("json")) {
            return Json.formatter(diffData);
        } else if (format.equals("plain")) {
            return Plain.formatter(diffData);
        }

        return Stylish.formatter(diffData);
    }
}
