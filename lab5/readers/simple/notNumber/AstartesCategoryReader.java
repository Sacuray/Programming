package readers.simple.notNumber;

import managers.IOManager;
import data.AstartesCategory;

public class AstartesCategoryReader extends EnumReader<AstartesCategory> {
    public AstartesCategoryReader(IOManager ioManager) {
        super(ioManager);
    }

    @Override
    AstartesCategory[] getEnumValues() {
        return AstartesCategory.values();
    }

    @Override
    AstartesCategory valueOf(String name) {
        return AstartesCategory.valueOf(name);
    }
}