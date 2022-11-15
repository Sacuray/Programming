package serverModule;

import common.exceptions.WrongAmountOfParametersException;
import serverModule.commands.*;
import serverModule.utility.*;

import java.io.Console;
import java.util.Scanner;

public class App {
    public static final int PORT = 20003;

    public static void main(String[] args) {
        DatabaseManager databaseManager = new DatabaseManager();
        DatabaseUserManager databaseUserManager = new DatabaseUserManager(databaseManager);
        DatabaseCollectionManager databaseCollectionManager = new DatabaseCollectionManager(databaseManager, databaseUserManager);
        CollectionManager collectionManager = new CollectionManager(databaseCollectionManager);
        CommandManager commandManager = new CommandManager(new HelpCommand(),
                new InfoCommand(collectionManager),
                new ShowCommand(collectionManager),
                new InsertCommand(collectionManager, databaseCollectionManager),
                new UpdateCommand(collectionManager, databaseCollectionManager),
                new RemoveKeyCommand(collectionManager, databaseCollectionManager),
                new ClearCommand(collectionManager, databaseCollectionManager),
                new ExecuteScriptCommand(),
                new ExitCommand(),
                new HistoryCommand(),
                new RemoveLowerKeyCommand(collectionManager, databaseCollectionManager),
                new SumOfImpactSpeedCommand(collectionManager),
                new SignUpCommand(databaseUserManager),
                new SignInCommand(databaseUserManager),
                new LogOutCommand(databaseUserManager));
        RequestManager requestManager = new RequestManager(commandManager);
        Server server = new Server(PORT, requestManager);
        server.run();
        databaseManager.closeConnection();
    }
}