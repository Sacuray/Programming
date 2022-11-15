package common.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * Car.
 */
public class Car implements Serializable {
    private String name;
    private boolean cool;

    public Car(String name, boolean cool) {
        this.name = name;
        this.cool = cool;
    }

    /**
     * @return Name of the Car.
     */
    public String getName() {
        return name;
    }

    /**
     * @return cool of the Car.
     */
    public boolean getCool() {
        return cool;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", cool='" + cool + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car Car = (Car) o;
        return Objects.equals(name, Car.name) &&
                Objects.equals(cool, Car.cool);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cool);
    }
}