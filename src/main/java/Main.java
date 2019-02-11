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

            switch (commandName) {
                case "register":
                    Command Register = MyJsonParser.parseUserInfo(commandData);
                    Register.execute();
                    break;
                case "addProject":
                    Command addProject = MyJsonParser.parseProjectInfo(commandData);
                    addProject.execute();
                    break;
                case "bid":
                    Command bid = MyJsonParser.parseBidInfo(commandData);
                    bid.execute();
                    break;
                case "auction":
                    Command auction = MyJsonParser.parseAuctionInfo(commandData);
                    auction.execute();
                    isFinished = true;
                    break;
            }
        }
    }

    private static Pair<String, String> getCommandParts() {
        String command = scanner.nextLine();
        int spaceIndex = command.indexOf(" ");
        return new Pair<> (command.substring(0, spaceIndex), command.substring(spaceIndex));
    }
}

