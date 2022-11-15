package clientModule.utility;

import common.data.Car;
import common.data.Coordinates;
import common.data.Mood;
import common.data.WeaponType;
import common.exceptions.IncorrectInputInScriptException;
import common.exceptions.ScriptRecursionException;
import common.exceptions.WrongAmountOfParametersException;
import common.utility.Request;
import common.utility.ResponseCode;
import common.utility.HumanBeingLite;
import common.utility.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Operates command input.
 */
public class Console {
    private Scanner scanner;
    private Stack<File> scriptFileNames = new Stack<>();
    private Stack<Scanner> scannerStack = new Stack<>();
    private AuthManager authManager;

    public Console(Scanner scanner, AuthManager authManager) {
        this.scanner = scanner;
        this.authManager = authManager;
    }

    /**
     * Mode for catching commands from user input.
     */
    public Request interactiveMode(ResponseCode serverResponseCode, User user) {
        String userInput = "";
        String[] userCommand = {"", ""};
        ProcessCode processCode = null;
        try {
            do {
                try {
                    if (fileMode() && (serverResponseCode == ResponseCode.SERVER_EXIT || serverResponseCode == ResponseCode.ERROR)) {
                        throw new IncorrectInputInScriptException();
                    }
                    while (fileMode() && !scanner.hasNextLine()) {
                        scanner.close();
                        scanner = scannerStack.pop();
                        System.out.println("Возвращаюсь из скрипта '" + scriptFileNames.pop().getName() + "'!");
                    }
                    if (fileMode()) {
                        userInput = scanner.nextLine();
                        if (!userInput.isEmpty()) {
                            System.out.print("$ ");
                            System.out.println(userInput);
                        }
                    } else {
                        System.out.print("$ ");
                        if (scanner.hasNext()) {
                            userInput = scanner.nextLine();
                        } else {
                            System.out.println("Клиент завершен!");
                            System.exit(0);
                        }
                    }
                    userCommand = (userInput.trim() + " ").split(" ", 2);
                    userCommand[1] = userCommand[1].trim();
                } catch (NoSuchElementException | IllegalStateException exception) {
                    System.out.println("Произошла ошибка при вводе команды!");
                    userCommand = new String[]{"", ""};
                }
                processCode = checkCommand(userCommand[0], userCommand[1]);
            } while (processCode == ProcessCode.ERROR && !fileMode() || userCommand[0].isEmpty());
            try {
                switch (processCode) {
                    case OBJECT:
                        HumanBeingLite humanToInsert = generateHumanToInsert();
                        return new Request(userCommand[0], userCommand[1], humanToInsert, user);
                    case UPDATE_OBJECT:
                        HumanBeingLite humanToUpdate = generateHumanToUpdate();
                        return new Request(userCommand[0], userCommand[1], humanToUpdate, user);
                    case SCRIPT:
                        File scriptFile = new File(userCommand[1]);
                        if (!scriptFile.exists()) throw new FileNotFoundException();
                        if (!scriptFileNames.isEmpty() && scriptFileNames.search(scriptFile) != -1) {
                            throw new ScriptRecursionException();
                        }
                        scannerStack.push(scanner);
                        scriptFileNames.push(scriptFile);
                        scanner = new Scanner(scriptFile);
                        System.out.println("Выполняю скрипт '" + scriptFile.getName() + "'!");
                        break;
                    case LOG_IN:
                        return authManager.handle();
                }
            } catch (FileNotFoundException exception) {
                System.out.println("Файл со скриптом не найден!");
            } catch (ScriptRecursionException exception) {
                System.out.println("Скрипты не могут вызываться рекурсивно!");
                throw new IncorrectInputInScriptException();
            }
        } catch (IncorrectInputInScriptException exception) {
            System.out.println("Выполнение скрипта прервано!");
            while (!scannerStack.isEmpty()) {
                scanner.close();
                scanner = scannerStack.pop();
            }
        }
        return new Request(userCommand[0], userCommand[1], null, user);
    }



    /**
     * Launches the command.
     * @param command Command to launch.
     * @return Exit code.
     */
    private ProcessCode checkCommand(String command, String argument) {
        try {
            switch (command) {
                case "":
                    return ProcessCode.ERROR;
                case "help":
                case "show":
                case "info":
                case "clear":
                case "history":
                case "sum_of_impact_speed":
                case "exit":
                case "log_out":
                    if (!argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.OK;
                case "insert":
                    if (argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.OBJECT;
                case "update":
                    if (argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.UPDATE_OBJECT;
                case "log_in":
                    if (!argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.LOG_IN;
                case "remove_key":
                case "remove_lower_key":
                case "execute_script":
                    if (argument.isEmpty()) throw new WrongAmountOfParametersException();
                    return ProcessCode.SCRIPT;
                default:
                    System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
                    return ProcessCode.ERROR;
            }
        } catch (WrongAmountOfParametersException e) {
            System.out.println("Проверьте правильность ввода аргументов!");
        }
        return ProcessCode.OK;
    }

    private HumanBeingLite generateHumanToInsert() {
        HumanBeingBuilder builder = new HumanBeingBuilder(scanner);
        if (fileMode()) {
            builder.setFileMode();
        } else {
            builder.setUserMode();
        }
        return new HumanBeingLite(
                builder.askName(),
                builder.askCoordinates(),
                builder.askRealHero(),
                builder.askHasToothpick(),
                builder.askImpactSpeed(),
                builder.askWeaponType(),
                builder.askMood(),
                builder.askCar()
        );
    }

    private HumanBeingLite generateHumanToUpdate() {
        HumanBeingBuilder builder = new HumanBeingBuilder(scanner);
        if (fileMode()) {
            builder.setFileMode();
        } else {
            builder.setUserMode();
        }
        String name = builder.askAboutChangingField("Хотите изменить имя человека?") ?
                builder.askName() : null;
        Coordinates coordinates = builder.askAboutChangingField("Хотите изменить координаты человека?") ?
                builder.askCoordinates() : null;
        Boolean realHero = builder.askAboutChangingField("Хотите изменить героизм человека?") ?
                builder.askRealHero() : false;
        boolean hasToothpick = builder.askAboutChangingField("Хотите изменить наличие зубочистки у человека?") ?
                builder.askHasToothpick() : false;
        float impactSpeed = builder.askAboutChangingField("Хотите изменить скорость удара человека?") ?
                builder.askImpactSpeed() : 0.0f;
        WeaponType weaponType = builder.askAboutChangingField("Хотите изменить тип оружия человека?") ?
                builder.askWeaponType() : null;
        Mood mood = builder.askAboutChangingField("Хотите изменить настроение человека?") ?
                builder.askMood() : null;
        Car car = builder.askAboutChangingField("Хотите изменить машину?") ?
                builder.askCar() : null;
        return new HumanBeingLite(
                name,
                coordinates,
                realHero,
                hasToothpick,
                impactSpeed,
                weaponType,
                mood,
                car
        );
    }

    private boolean fileMode() {
        return !scannerStack.isEmpty();
    }
}