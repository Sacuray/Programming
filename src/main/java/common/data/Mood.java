package common.data;

import java.io.Serializable;

public enum Mood implements Serializable{
    SORROW,
    LONGING,
    GLOOM,
    APATHY;

    public static String list() {
        String list = "";
        for (Mood mood : values()) {
            list += mood.name() + ", ";
        }
        return list.substring(0, list.length() - 2);
    }
}