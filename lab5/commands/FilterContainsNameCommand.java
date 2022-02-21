package commands;

import managers.Interpreter;
import data.SpaceMarine;
import readers.simple.StringReader;
import readers.simple.number.LongReader;

import java.util.List;
import java.util.Vector;

public class FilterContainsNameCommand extends Command {
    public FilterContainsNameCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {
        String name = new StringReader(ioManager).setCanBeEmpty(false).setNullable(false).read();
        List ls = collection.filterContainsName(name);
        if(ls == null){
            ioManager.writeLine("Таких элементов нет");
        }
        else{
            ioManager.writeLine(ls.toString());
        }
    }

    @Override
    public String getDescription() {
        return "name : вывести элементы, значение поля name которых содержит заданную подстроку";
    }

    @Override
    public String getName() {
        return "filter_contains_name";
    }
}