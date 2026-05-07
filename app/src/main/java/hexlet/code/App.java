package hexlet.code;

import java.util.concurrent.Callable;
import picocli.CommandLine;

@CommandLine.Command(name = "gendiff")
public final class App implements Callable<Integer> {
    @CommandLine.Parameters(paramLabel = "filepath1",
          description = "path to first file")
    private String file1;
    @CommandLine.Parameters(paramLabel = "filepath2",
          description = "path to second file")
    private String file2;

    @CommandLine.Option(names = {"-f", "--format"},
          description = "output format [default: stylish]")
    private String format = "stylish";
    @CommandLine.Option(names = {"-v", "--version"}, versionHelp = true,
          description = "Print version information and exit")
    private boolean versionInfoRequested;
    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true,
          description = "Show this help message and exit.")
    private boolean usageHelpRequested;

    public static void main(String[] args) throws Exception {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() {
        String result = Differ.generate(file1, file2, format);
        System.out.println(result);
        return 0;
    }
}
