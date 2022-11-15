package common.data;

import common.utility.User;
import java.util.Objects;
import java.time.LocalDate;

public class HumanBeing implements Comparable<HumanBeing>{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private int key;
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private boolean realHero;
    private Boolean hasToothpick; //Поле не может быть null
    private float impactSpeed;
    private WeaponType weaponType; //Поле не может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле может быть null
    private User owner;

    public HumanBeing(long id, int key, String name, Coordinates coordinates, java.time.LocalDate creationDate, boolean realHero, Boolean hasToothpick, float impactSpeed, WeaponType weaponType, Mood mood, Car car, User owner){
        this.id = id;
        this.key = key;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }
    public long getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }
    public boolean getRealHero(){
        return realHero;
    }

    public Boolean getHasToothpick() {
        return hasToothpick;
    }

    public float getImpactSpeed() {
        return impactSpeed;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Car getCar() {
        return car;
    }

    public Mood getMood() {
        return mood;
    }

    public User getOwner() {
        return owner;
    }

    @Override
    public int compareTo(HumanBeing o) {
        if (this.realHero == o.realHero) {
            return Boolean.compare(this.hasToothpick, o.hasToothpick);
        }
        return Boolean.compare(this.realHero, o.realHero);
    }
    @Override
    public String toString() {
        String info = "";
        info += "Человек №" + key;
        info += " (добавлен " + creationDate +  ")";
        info += "\n ID: " + id;
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Реальный герой: " + realHero;
        info += "\n Имеет зубочистку: " + hasToothpick;
        info += "\n Скорость: " + impactSpeed;
        info += "\n Оружие: " + weaponType;
        info += "\n Машина: " + car;
        info += "\n Настроение: " + mood;
        return info;
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, realHero, hasToothpick, weaponType, car, mood, impactSpeed, owner);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeing that = (HumanBeing) o;
        return id == that.id && impactSpeed == that.impactSpeed && realHero == that.realHero && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates) && Objects.equals(creationDate, that.creationDate) && Objects.equals(hasToothpick, that.hasToothpick) && weaponType == that.weaponType && Objects.equals(car, that.car) && Objects.equals(mood, that.mood) && Objects.equals(owner, that.owner);
    }

}

