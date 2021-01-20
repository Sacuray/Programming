package humans;
import world.Place;
import java.util.Objects;

public class Readers implements AbleToUnderstand{

    private String name;
    private Narrator narrator;
    private Place place;

    public Readers(String name, Narrator narrator, Place place){
        this.name = name;
        this.narrator = narrator;
        this.place = place;
        System.out.println("Созданны читатели: " + getName() + ", место обитания: " + place.getNameObject() + ", расскасчик: " + narrator.getName());
    }

    public String getName() {
        return name;
    }

    public Narrator getNarrator() {
        return narrator;
    }

    public Place getPlace() {
        return place;
    }

    public void doubt(){
        if(place.getGInCenter() == 0.0f) {
            System.out.println(getName() + " сомневаются: Не могут лунный камень и маленький магнит поменять гравитацию, так не бывает и вообще вы гоните");
        }
    }

    @Override
    public void understand(){
        if(place.getGInCenter() == 0.0f) {
            System.out.println(getName() + " понимают, что и правда ничего удивительного здесь нет и " + narrator.getName() + " прав");
        }
    }

    @Override
    public String toString() {
        return "Readers{" +
                "name='" + name + '\'' +
                ", place=" + place.getNameObject() +
                ", narrator=" + narrator.getName() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Readers readers = (Readers) o;
        return this.name.equals(readers.name) && this.getPlace().equals(readers.getPlace());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, narrator, place);
    }
}
