package commands;

import managers.Interpreter;

public class InfoCommand extends Command {
    public InfoCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        ioManager.writeLine("Тип коллекции: " + collection.getType());
        ioManager.writeLine("Дата инициализации: " + collection.getInitializationDate().toString());
        ioManager.writeLine("Количество элементов: " + collection.getElementsCount());
    }

    @Override
    public String getDescription() {
        return ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }

    @Override
    public String getName() {
        return "info";
    }
}