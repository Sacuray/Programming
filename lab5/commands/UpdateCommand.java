package commands;

import managers.Interpreter;
import readers.complex.SpaceMarineReader;
import readers.simple.number.IntegerReader;
import data.SpaceMarine;

public class UpdateCommand extends Command {
    public UpdateCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        SpaceMarine value = new SpaceMarineReader(ioManager).setNullable(false).read();
        SpaceMarine element = collection.get(id);
        if (element == null) {
            ioManager.writeLine("Элемент с данным ключём не найден");
        } else {
            collection.remove(id);
            collection.add(value);
            ioManager.writeLine("Значение обновлено: " + value.toString());
        }
    }

    @Override
    public String getDescription() {
        return "id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public String getName() {
        return "update";
    }
}