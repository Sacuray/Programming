package managers;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;


import com.google.gson.reflect.TypeToken;
import data.*;
import com.google.gson.Gson;
import managers.IOManager;


public class SpaceMarineCollection {
    private Vector<SpaceMarine> vector = new Vector<>();
    private final LocalDate initializationDate;

    public SpaceMarineCollection() {
        initializationDate = LocalDate.now();
    }

    public void add(SpaceMarine element){
        vector.add(element);
    }

    public void remove(int id){
        SpaceMarine element = get(id);
        vector.remove(element);
    }

    public SpaceMarine get(int id){
        for (SpaceMarine element : vector) {
            if (element.id == id) {
                return element;
            }
        }
        return null;
    }

    public void clear(){
        vector.clear();
    }

    public boolean removeLower(SpaceMarine element){
        return vector.removeIf(spaceMarine -> spaceMarine.compareTo(element) < 0);
    }

    public void insert_at(int index, SpaceMarine element){
        vector.insertElementAt(element, index);
    }

    public boolean addIfMin(SpaceMarine element){
        long health = 9223372036854775807L;
        for(SpaceMarine el : vector){
            if(el.health < health){
                health = el.health;
            }
        }
        if(element.health < health){
            vector.add(element);
            return true;
        }
        else {
            return false;
        }
    }

    public List filterByHealth(Long health){
        LinkedList<SpaceMarine> ls = new LinkedList<SpaceMarine>();
        for(SpaceMarine elem : vector){
            if(elem.health == health){
                ls.add(elem);
            }
        }
        return ls;
    }

    public List filterContainsName(String name){
        LinkedList<SpaceMarine> ls = new LinkedList<SpaceMarine>();
        for(SpaceMarine elem : vector){
            if(elem.name.contains(name)){
                ls.add(elem);
            }
        }
        return ls;
    }

    public Long sum_of_health(){
        Long sum = 0L;
        System.out.println(vector);
        for(SpaceMarine element : vector){
            sum += element.health;
        }
        return sum;
    }

    public void loadFromFile(String filename){
        managers.IOManager ioManager = new IOManager();
        try {
            ioManager.setInputFile(filename);
            String json = ioManager.readUntilEnd();
            Gson gson = new Gson();
            System.out.print(json);

            vector = gson.fromJson(json, new TypeToken<Vector<SpaceMarine>>(){}.getType());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToFile(String filename) {
        IOManager ioManager = new IOManager();
        try {
            ioManager.setOutputFile(filename);
            Gson gson = new Gson();
            String json = gson.toJson(vector);
            ioManager.writeLine(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public LocalDate getInitializationDate() {
        return initializationDate;
    }

    public String getType() {
        return vector.getClass().getSimpleName();
    }

    public int getElementsCount() {
        return vector.size();
    }

    public List<SpaceMarine> getAll() {
        return new LinkedList<>(vector);
    }

}
