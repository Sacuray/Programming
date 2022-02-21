package commands;

import managers.Interpreter;
import data.SpaceMarine;
import readers.simple.number.LongReader;

import java.util.List;
import java.util.Vector;

public class FilterByHealthCommand extends Command {
    public FilterByHealthCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        Long health = new LongReader(ioManager).setLowerBound(0L).setNullable(true).read();
        List ls = collection.filterByHealth(health);
        if(ls == null){
            ioManager.writeLine("Таких элементов нет");
        }
        else{
            ioManager.writeLine(ls.toString());
        }
    }

    @Override
    public String getDescription() {
        return "health : вывести элементы, значение поля health которых равно заданному";
    }

    @Override
    public String getName() {
        return "filter_by_health";
    }
}