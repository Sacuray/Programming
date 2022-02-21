package commands;

import managers.Interpreter;
import data.SpaceMarine;
import readers.complex.SpaceMarineReader;
import readers.simple.number.IntegerReader;

public class InsertAtCommand extends Command {

    public InsertAtCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        Integer id = new IntegerReader(ioManager).setNullable(false).read();
        SpaceMarine spaceMarine = new SpaceMarineReader(ioManager).setNullable(false).read();
        collection.insert_at(id, spaceMarine);
        ioManager.writeLine("Добавлен объект: " + spaceMarine.toString() + "в позицию" + id);
    }

    @Override
    public String getDescription() {
        return "index {element} : добавить новый элемент в заданную позицию";
    }

    @Override
    public String getName() {
        return "insert_at";
    }
}