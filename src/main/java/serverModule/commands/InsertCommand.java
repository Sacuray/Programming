package serverModule.commands;

import common.data.HumanBeing;
import common.exceptions.DatabaseManagerException;
import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.HumanBeingLite;
import common.utility.User;
import serverModule.utility.CollectionManager;
import serverModule.utility.DatabaseCollectionManager;
import serverModule.utility.ResponseOutputer;

import java.time.LocalDateTime;

/**
 * Command 'insert'. Adds a new element to collection.
 */
public class InsertCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public InsertCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("insert null {element}", "добавить новый элемент с заданным ключом");
        this.collectionManager = collectionManager;
        this.databaseCollectionManager = databaseCollectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (user == null) throw new NonAuthorizedUserException();
            if (argument.isEmpty() || objectArgument == null) throw new WrongAmountOfParametersException();
            int key = Integer.parseInt(argument);
            HumanBeingLite humanLite = (HumanBeingLite) objectArgument;
            collectionManager.addToCollection(key, databaseCollectionManager.insertHumanBeing(humanLite, user, key));
            ResponseOutputer.append("Успешно добавлено в коллекцию!\n");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ResponseOutputer.append("Вместе с этой командой должен быть передан параметр! Наберит 'help' для справки\n");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("Произошла ошибка при обращении к базе данных!\n");
        } catch (NonAuthorizedUserException e) {
            ResponseOutputer.append("Необходимо авторизоваться!\n");
        }
        return false;
    }
}