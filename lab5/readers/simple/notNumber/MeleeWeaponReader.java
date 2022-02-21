package readers.simple.notNumber;

import managers.IOManager;
import data.MeleeWeapon;

public class MeleeWeaponReader extends EnumReader<MeleeWeapon> {
    public MeleeWeaponReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    MeleeWeapon[] getEnumValues() {
        return MeleeWeapon.values();
    }

    @Override
    MeleeWeapon valueOf(String name) {
        return MeleeWeapon.valueOf(name);
    }
}