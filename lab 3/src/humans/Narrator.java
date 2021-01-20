package humans;

import items.Items;
import world.Place;

import java.util.Objects;

public class Narrator{

    private String name;
    private Place place;

    public Narrator(String name, Place place){
        this.name = name;
        this.place = place;
        System.out.println("Созданн расскасчик: " + getName() + ", место обитания: " + place.getNameObject());
    }

    public String getName() {
        return name;
    }

    public Place getPlace() {
        return place;
    }

    public void answer(){
        if(place.getGInCenter() == 0.0f) {
            System.out.println(getName() + " замечает, что в лунном камне и магните, как и в любом веществе энергии: " + Items.getCountOfEnergy());
            System.out.println(getName() + " читает лекцию по физике");
            System.out.println(getName() + " замечает, что ускорение свободного падения на краю области равно " + place.getGOnEdge());
            System.out.println(getName() + ": Это больше чем норма, а значит невесомость в центре компенсируется высоким притяжением по краю");
        }
    }

    @Override
    public String toString() {
        return "Narrator{" +
                "name='" + name + '\'' +
                ", place=" + place.getNameObject() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Narrator nar = (Narrator) o;
        return this.name.equals(nar.name) && this.getPlace().equals(nar.getPlace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }

}
