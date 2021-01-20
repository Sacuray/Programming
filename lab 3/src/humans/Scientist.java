package humans;
import items.Items;
import world.EnvironmentObject;
import world.Place;

import java.util.Objects;

public class Scientist implements AbleToUnderstand{

    private String name;
    private Place place;

     public Scientist(String name, Place place){
        this.name = name;
        this.place = place;
         System.out.println("Созданн учёный: " + getName() + ", место обитания: " + getPlace().getNameObject());
    }

    public String getName() {
        return name;
    }

    public Place getPlace() {
        return place;
    }

    public void bringItem(Items item){
        this.place.addInArrayOfItems(item);
        System.out.println(getName() + " приносит в " + place.getNameObject() + " : " + item.getItem());
        place.checkItems();
    }

    @Override
    public void understand(){
        if(place.getGInCenter() == 0.0f){
            System.out.println(getName() + " понимает, какое значимое открытие он совершил");
        }
    }

    @Override
    public String toString() {
        return "Scientist{" +
                "name='" + name + '\'' +
                ", place=" + place.getNameObject() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scientist sci = (Scientist) o;
        return this.name.equals(sci.name) && this.getPlace().equals(sci.getPlace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, place);
    }
}
