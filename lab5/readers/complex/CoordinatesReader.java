package readers.complex;

import managers.IOManager;
import data.Coordinates;
import readers.simple.number.IntegerReader;
import readers.simple.number.FloatReader;

public class CoordinatesReader extends ValueComplexReader<Coordinates> {
    public CoordinatesReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    public Coordinates read(String fieldName) {
        writeFieldName(fieldName);
        return readFields();
    }

    @Override
    protected Coordinates readFields() {
        FloatReader floatReader = new FloatReader(ioManager);
        floatReader = (FloatReader) floatReader.setNullable(false);
        IntegerReader integerReader = (IntegerReader) new IntegerReader(ioManager).setNullable(false);
        float x = floatReader.read("x");
        int y = integerReader.setUpperBound(962).read("y");
        return new Coordinates(x, y);
    }

    @Override
    protected String getTypeName() {
        return Coordinates.class.getSimpleName();
    }
}