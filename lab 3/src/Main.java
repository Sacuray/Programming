import items.Items;
import world.Place;
import humans.*;


public class Main {
    public static void main(String[] args){
        Place house = new Place("Дом Знайки", 9.8f);
        Narrator narrator = new Narrator("Носов", house);
        Scientist scientist = new Scientist("Знайка", house);
        Readers readers = new Readers("Советские дети", narrator, house);
        scientist.bringItem(Items.MOON_STONE);
        scientist.bringItem(Items.MAGNET);
        scientist.understand();
        readers.doubt();
        narrator.answer();
        readers.understand();

    }
}