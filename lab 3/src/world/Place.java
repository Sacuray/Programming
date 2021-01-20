package world;

import items.Items;
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
        if (getArrayOfItems().contains(Items.MOON_STONE) && getArrayOfItems().contains(Items.MAGNET)){
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
