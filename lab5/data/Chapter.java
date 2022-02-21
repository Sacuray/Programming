package data;

public class Chapter {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private int marinesCount; //Значение поля должно быть больше 0, Максимальное значение поля: 1000

    public Chapter(String name, int marinesCount){
        this.name = name;
        this.marinesCount = marinesCount;
    }

    @Override
    public String toString() {
        return "data.Event{" +
                ", name='" + name + '\'' +
                ", marinesCount=" + marinesCount +
                '}';
    }

}