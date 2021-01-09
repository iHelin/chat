package chat;

import chat.project.netty.NettyServer;
import org.apache.commons.cli.*;

//启动服务端
public class ServerMain {

    private static int port = 8888;

    public static void main(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        options.addOption("h", "help", false, "Print this usage information");
        options.addOption("p", "port", true, "Set server port.");
        CommandLine commandLine = parser.parse(options, args);

        if (commandLine.hasOption('h')) {
            System.out.println("Use -p to set port.");
            System.exit(0);
        }
        if (commandLine.hasOption('p')) {
            port = Integer.parseInt(commandLine.getOptionValue('p'));
        }
        NettyServer.start(port);
    }
}
