package world;

import items.Item;
import java.util.Objects;
import java.util.ArrayList;

public abstract class EnvironmentObject {
    private String nameObject;
    private ArrayList<Item> arrayOfItems = new ArrayList<Item>();
    private float gInCenter; //This is physical constant, not stupid naming
    private float gOnEdge; //This is physical constant, not stupid naming

    public EnvironmentObject(String name, float g){
        setNameObject(name);
        setGInCenter(g);
        setGOnEdge(g);
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public void setGInCenter(float g) {
        this.gInCenter = g;//g can change from time to time, because we are in the fantasy
    }

    public void setGOnEdge(float g) {
        this.gOnEdge = g;//g can change from time to time, because we are in the fantasy
    }

    public void addInArrayOfItems(Item item) {
        this.arrayOfItems.add(item);
    }

    public ArrayList<Item> getArrayOfItems() {
        return arrayOfItems;
    }

    public String getNameObject() {
        return nameObject;
    }

    public float getGOnEdge() {
        return gOnEdge;
    }

    public float getGInCenter() {
        return gInCenter;
    }

    @Override
    public String toString() {
        return "EnvironmentObject{" +
                "name='" + nameObject + '\'' +
                ", gInCenter=" + gInCenter +
                ", gOnEdge=" + gOnEdge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnvironmentObject env = (EnvironmentObject) o;
        return this.nameObject.equals(env.nameObject) && this.gInCenter == env.getGInCenter() && this.gOnEdge == env.getGOnEdge();
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameObject, gInCenter, gOnEdge);
    }
}
