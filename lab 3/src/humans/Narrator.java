package humans;

import items.Item;
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

    public void answer(Item rock_coal, Item penny){
        final int koef = 1000000;
        System.out.println("Носов: Сейчас всё объясню");
        System.out.println("Носов: Если сжечь этот " + rock_coal.getName() + ", массой " + rock_coal.getMass() + " грамм, ведилится энергия " + rock_coal.getBurnEnergy() + "МДж");
        System.out.println("Носов: С другой стороны в  " + penny.getName() + ", массой " + penny.getMass() + " грамм, содержится энергия " + penny.getNuclearEnergy() + "ТераДж");
        int comparing = (int) (penny.getNuclearEnergy() / rock_coal.getBurnEnergy() * koef);
        System.out.println("Носов: и это в " + comparing + " раз больше, чем теплота сгорания угля");
        float mass_of_coal = comparing * rock_coal.getMass() / koef;
        System.out.println("Носов: Значит, чтобы получить такое количество энергии, надо сжечь " + mass_of_coal + " тонн угля");
        System.out.println("Носов: Внутренняя ядерная энергия содержится в любом предмете и ч ем больше масса, тем больше энергии");
        System.out.println("Носов: Так что здесь нет ничего удивительного и лунный камень с магнитом вполне могут изменить гравитационное поле, энергии в них для этого достаточно");
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
