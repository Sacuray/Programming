package readers.complex;

import data.Coordinates;
import managers.IOManager;
import data.Chapter;
import readers.simple.number.IntegerReader;
import readers.simple.StringReader;

public class ChapterReader extends ValueComplexReader<Chapter> {
    public ChapterReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    public Chapter read(String fieldName) {
        writeFieldName(fieldName);
        return readFields();
    }

    @Override
    protected Chapter readFields() {
        String name = new StringReader(ioManager).setCanBeEmpty(false).setNullable(false).read("имя");
        int MarinesCount = new IntegerReader(ioManager).setUpperBound(1000).setLowerBound(0).setNullable(false).read("Количество бойцов");
        return new Chapter(name, MarinesCount);
    }

    @Override
    protected String getTypeName() {
        return Chapter.class.getSimpleName();
    }
}