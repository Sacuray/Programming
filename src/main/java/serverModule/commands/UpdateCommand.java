package serverModule.commands;

import common.data.*;
import common.exceptions.*;
import common.utility.HumanBeingLite;
import common.utility.User;
import serverModule.utility.CollectionManager;
import serverModule.utility.DatabaseCollectionManager;
import serverModule.utility.ResponseOutputer;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Command 'update'. Updates the information about selected human.
 */
public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private DatabaseCollectionManager databaseCollectionManager;

    public UpdateCommand(CollectionManager collectionManager, DatabaseCollectionManager databaseCollectionManager) {
        super("update id {element}", "обновить значение элемента коллекции по id");
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
            if (collectionManager.collectionSize() == 0) throw new EmptyCollectionException();
            long id = Long.parseLong(argument);
            int key = collectionManager.getKeyById(id);
            HumanBeing oldHuman = collectionManager.getFromCollection(key);
            if (oldHuman == null) throw new NotFoundHumanException();
            if (!oldHuman.getOwner().equals(user)) throw new PermissionDeniedException();
            if (!databaseCollectionManager.checkHumanBeingByIdAndUserId(oldHuman.getId(), user)) throw new IllegalDatabaseEditException();
            HumanBeingLite humanLite = (HumanBeingLite) objectArgument;
            databaseCollectionManager.updateHumanBeingByID(id, humanLite);
            String name = humanLite.getName() == null ? oldHuman.getName() : humanLite.getName();
            Coordinates coordinates = humanLite.getCoordinates() == null ? oldHuman.getCoordinates() : humanLite.getCoordinates();
            LocalDate creationDate = oldHuman.getCreationDate();
            boolean realHero = humanLite.getRealHero() ? oldHuman.getRealHero() : humanLite.getRealHero();
            boolean hasToothpick = humanLite.getHasToothpick() ? oldHuman.getHasToothpick() : humanLite.getHasToothpick();
            float impactSpeed = humanLite.getImpactSpeed() == -1 ? oldHuman.getImpactSpeed() : humanLite.getImpactSpeed();
            WeaponType weaponType = humanLite.getWeaponType() == null ? oldHuman.getWeaponType() : humanLite.getWeaponType();
            Car car = humanLite.getCar() == null ? oldHuman.getCar() : humanLite.getCar();
            Mood mood = humanLite.getMood() == null ? oldHuman.getMood() : humanLite.getMood();
            collectionManager.removeFromCollection(key);
            collectionManager.addToCollection(key, new HumanBeing(
                    id,
                    key,
                    name,
                    coordinates,
                    creationDate,
                    realHero,
                    hasToothpick,
                    impactSpeed,
                    weaponType,
                    mood,
                    car,
                    user
            ));
            ResponseOutputer.append("Успешно изменено!\n");
            return true;
        } catch (WrongAmountOfParametersException exception) {
            ResponseOutputer.append("Вместе с этой командой должен быть передан параметр! Наберит 'help' для справки!\n");
        } catch (EmptyCollectionException exception) {
            ResponseOutputer.append("Коллекция пуста!\n");
        } catch (NotFoundHumanException e) {
            ResponseOutputer.append("HumanBeing с таким id в коллекции нет!\n");
        } catch (PermissionDeniedException e) {
            ResponseOutputer.append("Принадлежащие другим пользователям объекты доступны только для чтения!\n");
        } catch (DatabaseManagerException e) {
            ResponseOutputer.append("Произошла ошибка при обращении к базе данных!\n");
        } catch (IllegalDatabaseEditException e) {
            ResponseOutputer.append("Произошло нелегальное изменение объекта в базе данных!\n");
            ResponseOutputer.append("Перезапустите клиент для избежания ошибок!\n");
        } catch (NonAuthorizedUserException e) {
            ResponseOutputer.append("Необходимо авторизоваться!\n");
        }
        return false;
    }
}