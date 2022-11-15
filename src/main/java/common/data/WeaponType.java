package common.data;

import java.io.Serializable;

public enum WeaponType implements Serializable {
    AXE,
    RIFLE,
    KNIFE,
    MACHINE_GUN;

    public static String list() {
        String list = "";
        for (WeaponType weaponType : values()) {
            list += weaponType.name() + ", ";
        }
        return list.substring(0, list.length() - 2);
    }
}
