package readers.complex;

import managers.IOManager;
import readers.ValueFormatException;
import readers.ValueReader;

/**
 * Класс, позволяющий определить правила чтения сложных значений.
 */
public abstract class ValueComplexReader<T> extends ValueReader<T> {
    protected ValueComplexReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected T parseNotNull(String argument) throws ValueFormatException {
        if (argument.equals(getTypeName())) {
            return readFields();
        } else {
            throw new ValueFormatException("Ошибка при определении типа данных!");
        }
    }

    protected abstract T readFields();

    protected abstract String getTypeName();
}