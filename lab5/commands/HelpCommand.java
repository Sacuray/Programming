package commands;

import managers.Interpreter;

public class HelpCommand extends Command {
    public HelpCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        for (Command command : interpreter.getAllCommands()) {
            ioManager.write(command.getName() + " ");
            ioManager.writeLine(command.getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "вывести справку по доступным командам";
    }

    @Override
    public String getName() {
        return "help";
    }
}