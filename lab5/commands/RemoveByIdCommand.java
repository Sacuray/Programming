package commands;

import managers.Interpreter;
import readers.simple.number.IntegerReader;

public class RemoveByIdCommand extends Command {
    public RemoveByIdCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        if (collection.get(id) == null) {
            ioManager.writeLine("Элемент с заданным ключём отстутствует.");
        } else {
            collection.remove(id);
            ioManager.writeLine("Элемент удалён.");
        }
    }

    @Override
    public String getDescription() {
        return "id : удалить элемент из коллекции по его id";
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}