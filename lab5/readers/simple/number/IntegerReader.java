package readers.simple.number;

import managers.IOManager;
import readers.ValueFormatException;

public class IntegerReader extends NumberReader<Integer> {
    public IntegerReader(IOManager ioManager) {
        super(ioManager);
        this.setNullable(true);
    }

    @Override
    Integer parseValue(String argument) throws ValueFormatException {
        try {
            return Integer.parseInt(argument);
        } catch (Exception e) {
            throw new ValueFormatException("Некорректный формат числа!");
        }
    }

    public IntegerReader setUpperBound(Integer upperBound) {
        super.setUpperBound(upperBound);
        return this;
    }

    public IntegerReader setLowerBound(Integer upperBound) {
        super.setLowerBound(lowerBound);
        return this;
    }

    @Override
    int compareValues(Integer a, Integer b) {
        return a.compareTo(b);
    }
}