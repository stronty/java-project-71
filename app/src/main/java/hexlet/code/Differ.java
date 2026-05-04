package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

//import picocli.CommandLine;
//import picocli.CommandLine.Parameters;
//import picocli.CommandLine.Option;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Tool for the toolless
 * \@param \input the description of the parameter
 * \@return the result of the operation
 */
public class Differ {

    public static Path getPath(String fileName) throws Exception{
        Path path = Paths.get(fileName).toAbsolutePath().normalize();

        if(!Files.exists(path)) {
          throw new Exception("File '" + path + "' does not exist");

        }
        return path;

    }

    public static Map<String, Object> parse(Path path) throws IOException {
            String json = Files.readString(path);

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> data = mapper.readValue(json, Map.class);
            return new TreeMap<>(data);

    }

    public static String generate(String path1, String path2) {
        final String[] diff = {"{\n"};

        try{
            Map<String, Object> content1 = parse(getPath(path1));
            Map<String, Object> content2 = parse(getPath(path2));

            var keys = new TreeSet<String>();
            keys.addAll(content1.keySet());
            keys.addAll(content2.keySet());

            for(String key : keys) {
                boolean in1 = content1.containsKey(key);
                boolean in2 = content2.containsKey(key);

                if (in1 && !in2) {
                    diff[0] = diff[0] + "  - " + key + ": " + content1.get(key).toString() + "\n";
                } else if (!in1 && in2) {
                    diff[0] = diff[0] + "  + " + key + ": " + content2.get(key).toString() + "\n";
                } else if (content1.get(key).equals(content2.get(key))) {
                    diff[0] = diff[0] + "    " + key + ": " + content2.get(key) + "\n";
                } else {
                    diff[0] = diff[0] + "  - " + key + ": " + content1.get(key).toString() + "\n";
                    diff[0] = diff[0] + "  + " + key + ": " + content2.get(key).toString() + "\n";
                }
            }


        } catch (Exception b) {
            throw new RuntimeException(b);
        }

        diff[0] = diff[0] + "}\n";
        return diff[0];
    }
}
