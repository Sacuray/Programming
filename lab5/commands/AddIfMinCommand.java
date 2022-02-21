package commands;

import managers.Interpreter;
import data.SpaceMarine;
import readers.complex.SpaceMarineReader;

public class AddIfMinCommand extends Command {
    public AddIfMinCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        SpaceMarineReader reader = new SpaceMarineReader(ioManager);
        reader = (SpaceMarineReader) reader.setNullable(false);

        SpaceMarine spaceMarine = reader.readFields();
        if(collection.addIfMin(spaceMarine)){
            ioManager.writeLine("Добавлен объект:" + spaceMarine.toString());
        }
        else{
            ioManager.writeLine("Объект не удволетворяет условию");
        }
    }

    @Override
    public String getDescription() {
        return "{element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }

    @Override
    public String getName() {
        return "add_if_min";
    }
}