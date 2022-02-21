package commands;

import managers.Interpreter;
import readers.complex.SpaceMarineReader;
import data.SpaceMarine;

public class SumOfHealthCommand extends Command {
    public SumOfHealthCommand(Interpreter interpreter) {
        super(interpreter);
    }

    @Override
    public void execute() {

        ioManager.writeLine("Сумма значений здоровья элементов коллекции равна: " + collection.sum_of_health());
    }

    @Override
    public String getDescription() {
        return "вывести сумму значений поля health для всех элементов коллекции";
    }

    @Override
    public String getName() {
        return "sum_of_health";
    }
}