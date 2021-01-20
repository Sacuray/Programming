package items;

public enum Items {
    MOON_STONE("Лунный камень"),
    MAGNET("Магнит");

    final private static String countOfEnergy = "Очень много, прям конца-края не видно";
    private String item;

    Items(String item){
        this.item = item;
    }

    public static String getCountOfEnergy(){
        return countOfEnergy;
    }

    public String getItem() {
        return item;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item='" + item + '\'' +
                '}';
    }
}
