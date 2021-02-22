package items;

public enum MaterialOfItem {

    COAL("Уголь", true, 30000.0f),
    MOON_STONE("Лунная порода", false, 0.0f),
    FERRUM("Железо", false, 0.0f);

    private String material;
    private boolean burnable;
    private float heatOfBurn;


    MaterialOfItem(String material, Boolean burnable, float heatOfBurn){
        this.burnable = burnable;
        this.material = material;
        this.heatOfBurn = heatOfBurn;

    }

    public Boolean getBurnable() {
        return burnable;
    }

    public String getNameMaterial() {
        return material;
    }

    public float getHeatOfBurn() {
        return heatOfBurn;
    }

    @Override
    public String toString() {
        return "Item{" +
                "material='" + material + '\'' +
                "burnable='" + burnable + '\'' +
                "heatOfBurn='" + heatOfBurn + '\'' +
                '}';
}

    }