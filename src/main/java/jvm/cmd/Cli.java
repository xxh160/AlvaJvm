package jvm.cmd;

import org.apache.commons.cli.*;

/**
 * @author hwd
 * @date 2021-01-09 9:58 AM
 **/
public class Cli {
    private static Options options;
    private final CommandLine commandLine;

    static {
        Cli.options = new Options();
        Option classpath = Option.builder("cp")
                .longOpt("classpath")
                .hasArg(true)
                .argName("path")
                .numberOfArgs(1)
                .desc("to specify class search path of directories and zip/jar files")
                .build();
        Option help = Option.builder("h")
                .longOpt("help")
                .hasArg(false)
                .desc("to print the help message")
                .build();
        Cli.options.addOption(classpath).addOption(help);
    }

    private static void help() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("alva_jvm main_class [options] [args...]", Cli.options);
    }

    private Cli(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        this.commandLine = parser.parse(Cli.options, args);
    }
    
    public void exec() throws ParseException {
        if (this.commandLine.hasOption("h")) {
            Cli.help();
            return;
        }
        if (this.commandLine.getArgs().length == 0) throw new ParseException("Main class required");
        //Todo: execute instructions
    }

    /**
     * jvm程序的入口方法
     *
     * @param args 程序选项
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            Cli.help();
            return;
        }
        try {
            Cli cli = new Cli(args);
            cli.exec();
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }

}
