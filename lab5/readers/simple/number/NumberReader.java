package readers.simple.number;

import managers.IOManager;
import readers.ValueFormatException;
import readers.simple.ValueSimpleReader;

abstract class NumberReader<T extends Number> extends ValueSimpleReader<T> {
    T upperBound;
    T lowerBound;

    NumberReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected T parseNotNull(String argument) throws ValueFormatException {
        T value = parseValue(argument);
        if (upperBound != null && compareValues(value, upperBound) >= 1) {
            throw new ValueFormatException("Данное поле не может быть больше " + upperBound.toString());
        }
        if (lowerBound != null && compareValues(value, lowerBound) < 1) {
            throw new ValueFormatException("Данное поле не может быть меньше " + lowerBound.toString());
        }
        return value;
    }

    abstract T parseValue(String argument) throws ValueFormatException;

    abstract int compareValues(T a, T b);

    public NumberReader<T> setUpperBound(T upperBound) {
        this.upperBound = upperBound;
        return this;
    }

    public NumberReader<T> setLowerBound(T lowerBound) {
        this.lowerBound = lowerBound;
        return this;
    }
}