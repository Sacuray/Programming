package readers.simple;

import managers.IOManager;
import readers.ValueFormatException;

import java.time.ZonedDateTime;

public class ZonedDateTimeReader extends ValueSimpleReader<ZonedDateTime> {
    public ZonedDateTimeReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    protected ZonedDateTime parseNotNull(String argument) throws ValueFormatException {
        try {
            return ZonedDateTime.parse(argument);
        } catch (Exception e) {
            throw new ValueFormatException("Не удаётся распознать формат даты!");
        }
    }
}