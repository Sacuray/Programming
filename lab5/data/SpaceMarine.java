package data;


import java.time.ZonedDateTime;
import java.util.Objects;


public class SpaceMarine implements Comparable<SpaceMarine> {
    public int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    public String name; //Поле не может быть null, Строка не может быть пустой
    public Coordinates coordinates; //Поле не может быть null
    public java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    public Long health; //Поле может быть null, Значение поля должно быть больше 0
    public AstartesCategory category; //Поле не может быть null
    public Weapon weaponType; //Поле может быть null
    public MeleeWeapon meleeWeapon; //Поле не может быть null
    public Chapter chapter; //Поле может быть null

    public SpaceMarine(int id, String name, Coordinates coordinates, java.time.ZonedDateTime creationDate, Long health, AstartesCategory category, Weapon weaponType, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.health = health;
        this.category = category;
        this.weaponType = weaponType;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "data.Ticket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", health=" + health +
                ", category=" + category +
                ", weaponType='" + weaponType + '\'' +
                ", meleeWeapon=" + meleeWeapon +
                ", chapter=" + chapter +
                '}';
    }

    @Override
    public int compareTo(SpaceMarine o) {
        return (int)(this.health - o.health);
    }
}