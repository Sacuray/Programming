package commands;

import managers.Interpreter;
import data.SpaceMarine;
import readers.complex.SpaceMarineReader;

public class AddCommand extends Command {
    public AddCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        SpaceMarineReader reader = new SpaceMarineReader(ioManager);
        reader = (SpaceMarineReader) reader.setNullable(false);

        SpaceMarine spaceMarine = reader.readFields();
        collection.add(spaceMarine);
        ioManager.writeLine("Добавлен объект: " + spaceMarine.toString());
    }

    @Override
    public String getDescription() {
        return "{element} : добавить новый элемент в коллекцию";
    }

    @Override
    public String getName() {
        return "add";
    }
}