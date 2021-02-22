import items.Item;
import items.MaterialOfItem;
import world.Place;
import humans.*;


public class Main {
    public static void main(String[] args){
        Place house = new Place("Дом Знайки", 9.8f);
        Narrator narrator = new Narrator("Носов", house);
        Scientist scientist = new Scientist("Знайка", house);
        Readers readers = new Readers("Советские дети", narrator, house);
        Item moon_rock = new Item("Лунный камень", MaterialOfItem.MOON_STONE, 1000);
        Item rock_coal = new Item("Кусок угля", MaterialOfItem.COAL, 1000);
        Item magnet = new Item("Магнит", MaterialOfItem.FERRUM, 1000);
        Item penny = new Item("копейка", MaterialOfItem.FERRUM, 2);
        scientist.bringItem(moon_rock);
        scientist.bringItem(magnet);
        scientist.understand();
        readers.doubt();
        narrator.answer(rock_coal, penny);
        readers.understand();

    }
}