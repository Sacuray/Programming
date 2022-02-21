package readers.simple.number;

import managers.IOManager;
import readers.ValueFormatException;

public class LongReader extends NumberReader<Long> {
    public LongReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    Long parseValue(String argument) throws ValueFormatException {
        try {
            return Long.parseLong(argument);
        } catch (Exception e) {
            throw new ValueFormatException("Некорректный формат числа!");
        }
    }

    @Override
    int compareValues(Long a, Long b) {
        return a.compareTo(b);
    }

    public LongReader setLowerBound(Long lowerBound) {
        super.setLowerBound(lowerBound);
        return this;
    }
}