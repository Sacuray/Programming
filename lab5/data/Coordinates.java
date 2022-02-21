package data;

public class Coordinates {
    private float x;
    private Integer y; //Максимальное значение поля: 962, Поле не может быть null

    public Coordinates(float x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "data.Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}