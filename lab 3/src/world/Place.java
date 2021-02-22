package world;

import items.Item;
import items.MaterialOfItem;

import java.util.Objects;

public class Place extends EnvironmentObject implements EnvObj{


    public Place(String name, float g){
        super(name, g);
        System.out.println("Созданно место: " + getNameObject() + ", ускорение свободного падения: " + getGInCenter());
    }

    @Override
    public void changeGravity() {
        setGInCenter(0.0f);
        setGOnEdge(getGOnEdge() * 2);
    }

    @Override
    public void checkItems() {
        boolean existMagnet = false;
        boolean existMoonRock = false;
        for(int i = 0; i < getArrayOfItems().size(); i++){
            if (getArrayOfItems().get(i).getMaterial() == MaterialOfItem.FERRUM){
                existMagnet = true;
            }
            if (getArrayOfItems().get(i).getMaterial() == MaterialOfItem.MOON_STONE){
                existMoonRock = true;
            }
        }
        if (existMagnet && existMoonRock){
            changeGravity();
            System.out.println("Система из лунного камня и магнита изменила гравитацию");
            System.out.println("Гравитация в центре: " + getGInCenter());
            System.out.println("Гравитация на краю: " + getGOnEdge());
        }
        else{
            System.out.println("Всё как обычно, ничего не произошло");
        }
    }

    @Override
    public String toString() {
        return "EnvironmentObject{" +
                "name='" + getNameObject() + '\'' +
                ", gInCenter=" + getGInCenter() +
                ", gOnEdge=" + getGOnEdge() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place env = (Place) o;
        return this.getNameObject().equals(env.getNameObject()) && this.getGInCenter() == env.getGInCenter() && this.getGOnEdge() == env.getGOnEdge();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameObject(), getGInCenter(), getGOnEdge());
    }
}
