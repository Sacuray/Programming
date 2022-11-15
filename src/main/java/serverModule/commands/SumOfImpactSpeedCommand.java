package serverModule.commands;

import common.exceptions.EmptyCollectionException;
import common.exceptions.NonAuthorizedUserException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.User;
import serverModule.utility.CollectionManager;
import serverModule.utility.ResponseOutputer;

/**
 * Command 'sum_of_impact_speed'. Prints the sum of impact_speed of all humans.
 */
public class SumOfImpactSpeedCommand extends AbstractCommand{
    private CollectionManager collectionManager;

    public SumOfImpactSpeedCommand(CollectionManager collectionManager) {
        super("sum_of_impact_speed", "вывести сумму значений поля impactSpeed для всех элементов коллекции");
        this.collectionManager = collectionManager;
    }

    /**
     * Executes the command.
     * @return Command exit status.
     */
    @Override
    public boolean execute(String argument, Object objectArgument, User user) {
        try {
            if (user == null) throw new NonAuthorizedUserException();
            if (!argument.isEmpty() || objectArgument != null) throw new WrongAmountOfParametersException();
            double sum_of_impact_speed = collectionManager.getSumOfImpactSpeed();
            if (sum_of_impact_speed == 0) throw new EmptyCollectionException();
            ResponseOutputer.append("Сумма скоростей ударов всех людей: " + sum_of_impact_speed + "\n");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ResponseOutputer.append("У этой команды нет параметров!\n");
        } catch (EmptyCollectionException exception) {
            ResponseOutputer.append("Коллекция пуста!\n");
        } catch (NonAuthorizedUserException e) {
            ResponseOutputer.append("Необходимо авторизоваться!\n");
        }
        return false;
    }
}