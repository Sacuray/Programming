package commands;

import managers.Interpreter;
import data.SpaceMarine;

public class ShowCommand extends Command {
    public ShowCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        for (SpaceMarine worker : collection.getAll()) {
            ioManager.writeLine(worker.toString());
        }
    }

    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public String getName() {
        return "show";
    }
}