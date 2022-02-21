package commands;

import managers.Interpreter;
import managers.IOManager;
import managers.SpaceMarineCollection;

public abstract class Command {
    protected final Interpreter interpreter;
    protected final IOManager ioManager;
    protected final SpaceMarineCollection collection;

    public Command(Interpreter interpreter) {
        this.interpreter = interpreter;
        this.ioManager = interpreter.getIOManager();
        this.collection = interpreter.getCollection();
    }

    public abstract void execute();

    public abstract String getDescription();

    public abstract String getName();
}
