package readers.simple.notNumber;

import managers.IOManager;
import data.Weapon;

public class WeaponReader extends EnumReader<Weapon> {
    public WeaponReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    Weapon[] getEnumValues() {
        return Weapon.values();
    }

    @Override
    Weapon valueOf(String name) {
        return Weapon.valueOf(name);
    }
}