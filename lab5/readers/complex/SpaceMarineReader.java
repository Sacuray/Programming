package readers.complex;

import managers.IOManager;
import data.*;
import readers.simple.StringReader;
import readers.simple.notNumber.WeaponReader;
import readers.simple.notNumber.MeleeWeaponReader;
import readers.simple.notNumber.AstartesCategoryReader;
import readers.simple.number.IntegerReader;
import readers.simple.number.LongReader;

import java.time.ZonedDateTime;

public class SpaceMarineReader extends ValueComplexReader<SpaceMarine>{
    public SpaceMarineReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    public SpaceMarine readFields() {
        CoordinatesReader coordinatesReader = (CoordinatesReader) new CoordinatesReader(ioManager).setNullable(false);
        Integer id = (int)(System.currentTimeMillis() % Integer.MAX_VALUE);
        String name = new StringReader(ioManager).setCanBeEmpty(false).setNullable(false).read("имя");
        Coordinates coordinates = coordinatesReader.read("координаты");
        ZonedDateTime creationDate =  ZonedDateTime.now();
        Long health = new LongReader(ioManager).setLowerBound(0L).setNullable(false).read("здоровье");
        AstartesCategory category = new AstartesCategoryReader(ioManager).setNullable(false).read("категория");
        Weapon weapon = new WeaponReader(ioManager).setNullable(true).read("оружие");
        MeleeWeapon meleeWeapon = new MeleeWeaponReader(ioManager).setNullable(false).read("холодное оружие");
        Chapter chapter = new ChapterReader(ioManager).setNullable(false).read("полк");
        return new SpaceMarine(id, name, coordinates, creationDate, health, category, weapon, meleeWeapon, chapter);
    }

    @Override
    protected String getTypeName() {
        return SpaceMarine.class.getSimpleName();
    }
}
