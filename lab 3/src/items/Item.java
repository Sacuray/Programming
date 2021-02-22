package items;

import java.util.Objects;

public class Item {
    public final static float speedOfLight = 300000000.0f;
    private String name;
    private MaterialOfItem material;
    private float burnEnergy;
    private double nuclearEnergy;
    private float mass;
    private final int koef = 1000000;

    public Item(String name, MaterialOfItem material, float mass){
        this.name = name;
        this.material = material;
        this.mass = mass;

        if (material.getBurnable() == Boolean.TRUE){
            burnEnergy = mass * material.getHeatOfBurn() / koef;
        }
        else{
            burnEnergy = 0.0f;
        }

        this.nuclearEnergy = mass * speedOfLight * speedOfLight / koef / koef;
        System.out.println("Созданн предмет: " + getName() + ", из материала: " + getMaterial().getNameMaterial() + ", массой: " + getMass() + " грамм, теплота сгорания: " + getMaterial().getHeatOfBurn() + " Дж/грамм" );
    }

    public String getName() {
        return name;
    }

    public float getBurnEnergy() {
        return burnEnergy;
    }

    public float getMass() {
        return mass;
    }

    public MaterialOfItem getMaterial() {
        return material;
    }

    public double getNuclearEnergy() {
        return nuclearEnergy;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", material=" + material +
                ", burnEnergy=" + burnEnergy +
                ", mass=" + mass +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Float.compare(item.burnEnergy, burnEnergy) == 0 &&
                Float.compare(item.mass, mass) == 0 &&
                Objects.equals(name, item.name) &&
                material == item.material;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, material, burnEnergy, mass);
    }
}
