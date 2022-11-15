package serverModule.utility;

import common.data.HumanBeing;
import common.data.WeaponType;
import common.data.Car;
import common.data.Mood;
import common.exceptions.DatabaseManagerException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Operates the collection itself.
 */
public class CollectionManager {
    private TreeMap<Integer, HumanBeing> humans = new TreeMap<>();
    private LocalDateTime lastInitTime;
    private DatabaseCollectionManager databaseCollectionManager;

    public CollectionManager(DatabaseCollectionManager databaseCollectionManager) {
        this.databaseCollectionManager = databaseCollectionManager;
        loadCollection();
    }

    /**
     * @return The collection itself.
     */
    public TreeMap<Integer, HumanBeing> getCollection() {
        return humans;
    }

    /**
     * Adds a new human to collection.
     * @param human A human to add.
     */
    public void addToCollection(int key, HumanBeing human) {
        humans.put(key, human);
    }

    /**
     * Generates next ID. It will be (the bigger one + 1).
     * @return Next ID.
     */
    public long generateId() {
        if (humans.isEmpty()) return 1;
        long lastId = 0;
        for (HumanBeing human : humans.values()) {
            lastId = Math.max(lastId, human.getId());
        }
        return lastId + 1;
    }


    /**
     * @return Name of the collection's type.
     */
    public String collectionType() {
        return humans.getClass().getName();
    }

    /**
     * Clears the collection.
     */
    public void clearCollection() {
        humans.clear();
    }

    /**
     * @param humanToCompare The human used to compare with others.
     */
    public List<HumanBeing> getGreater(HumanBeing humanToCompare) {
        return humans.values().stream().filter(HumanBeing -> HumanBeing.compareTo(humanToCompare) > 0).collect(Collectors.toList());
    }

    /**
     * @param keyToCompare The key used to take the all humans' keys, which are smaller than key in parameters.
     */
    public List<HumanBeing> getLowerKey(int keyToCompare) {
        return humans.entrySet().stream().filter(entry -> entry.getKey() < keyToCompare).map(Map.Entry::getValue).collect(Collectors.toList());
    }


    public void removeByValue(HumanBeing human) {
        humans.entrySet().removeIf(entry -> entry.getValue().equals(human));
    }

    /**
     * Removes a human from collection.
     * @param key A key of human.
     */
    public void removeFromCollection(int key) {
        humans.remove(key);
    }

    /**
     * @return Size of the collection.
     */
    public int collectionSize() {
        return humans.size();
    }

    /**
     * @param key The key used to take the human.
     * @return A human's key.
     */
    public HumanBeing getFromCollection(int key) {
        return humans.get(key);
    }

    /**
     * @param id ID, by which the key is found.
     * @return A human's key.
     */
    public Integer getKeyById(long id) {
        return humans.entrySet().stream().filter(entry -> entry.getValue().getId() == id).map(Map.Entry::getKey).findFirst().get();
    }

    /**
     * @return Sum of all humans' health or 0 if collection is empty.
     */
    public Integer getSumOfImpactSpeed() {
        return humans.values().stream().reduce(0, (sum, p) -> sum += (int)p.getImpactSpeed(), Integer::sum);
    }


    /**
     * @return Last initialization time or null if there wasn't initialization.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    public String showCollection() {
        if (humans.isEmpty()) return "Коллекция пуста!";
        return humans.values().stream().reduce("", (sum, m) -> sum += m + "\n\n", (sum1, sum2) -> sum1 + sum2).trim();
    }

    public void loadCollection() {
        humans = databaseCollectionManager.getCollection();
        lastInitTime = LocalDateTime.now();
        System.out.println("Коллекция загружена.");
    }
}