package commands;

import managers.Interpreter;
import readers.simple.StringReader;

public class SaveCommand extends Command {
    public SaveCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        String filename = new StringReader(ioManager).setCanBeEmpty(false).read();
        collection.saveToFile(filename);
    }

    @Override
    public String getDescription() {
        return ": сохранить коллекцию в файл";
    }

    @Override
    public String getName() {
        return "save";
    }
}