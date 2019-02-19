import javafx.util.Pair;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isFinished = false;

    public static void main(String[] args) {
        while (!isFinished) {
            Pair<String, String> commandParts = getCommandParts();
            String commandName = commandParts.getKey();
            String commandData = commandParts.getValue();
            Command cmd;

            switch (commandName) {
                case "register":
                    cmd = MyJsonParser.parseUserInfo(commandData);
                    break;
                case "addProject":
                    cmd =  MyJsonParser.parseProjectInfo(commandData);
                    break;
                case "bid":
                    cmd =  MyJsonParser.parseBidInfo(commandData);
                    break;
                case "auction":
                    cmd = MyJsonParser.parseAuctionInfo(commandData);
                    isFinished = true;
                    break;
                default :
                    cmd = null;
                    break;
            }
            if(cmd != null)
                cmd.execute();
        }
    }

    private static Pair<String, String> getCommandParts() {
        String command = scanner.nextLine();
        int spaceIndex = command.indexOf(" ");
        return new Pair<> (command.substring(0, spaceIndex), command.substring(spaceIndex));
    }
}

