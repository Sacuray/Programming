package common.utility;

import common.data.Mood;
import common.data.Coordinates;
import common.data.WeaponType;
import common.data.Car;

import common.utility.User;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

public class HumanBeingLite implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private boolean realHero;
    private Boolean hasToothpick; //Поле не может быть null
    private float impactSpeed;
    private WeaponType weaponType; //Поле не может быть null
    private Mood mood; //Поле может быть null
    private Car car; //Поле может быть null

    public HumanBeingLite(String name, Coordinates coordinates, boolean realHero, Boolean hasToothpick, float impactSpeed, WeaponType weaponType, Mood mood, Car car){
        this.name = name;
        this.coordinates = coordinates;
        this.realHero = realHero;
        this.hasToothpick = hasToothpick;
        this.impactSpeed = impactSpeed;
        this.weaponType = weaponType;
        this.mood = mood;
        this.car = car;
    }


    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
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


    @Override
    public String toString() {
        return "HumanBeingLite{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", impactSpeed=" + impactSpeed +
                ", realHero=" + realHero +
                ", hasToothpick='" + hasToothpick + '\'' +
                ", weaponType=" + weaponType +
                ", car=" + car +
                ", mood=" + mood +
                '}';
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, realHero, hasToothpick, weaponType, car, mood, impactSpeed);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HumanBeingLite that = (HumanBeingLite) o;
        return impactSpeed == that.impactSpeed && realHero == that.realHero && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates)  && Objects.equals(hasToothpick, that.hasToothpick) && weaponType == that.weaponType && Objects.equals(car, that.car) && Objects.equals(mood, that.mood);
    }

}

