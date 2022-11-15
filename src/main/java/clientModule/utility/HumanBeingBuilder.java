package clientModule.utility;

import common.data.Car;
import common.data.Coordinates;
import common.data.HumanBeing;
import common.data.WeaponType;
import common.data.Mood;
import common.exceptions.NotDeclaredValueException;

import java.util.Scanner;

/**
 * Asks a user a human's value.
 */
public class HumanBeingBuilder {
    private Scanner scanner;
    private boolean fileMode;

    public HumanBeingBuilder(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Sets a scanner to scan user input.
     * @param scanner Scanner to set.
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * @return Scanner, which uses for user input.
     */
    public Scanner getScanner() {
        return scanner;
    }

    public void setFileMode() {
        this.fileMode = true;
    }

    public void setUserMode() {
        this.fileMode = false;
    }

    /**
     * Asks a user the human's name.
     * @return Human's name.
     */
    public String askName() {
        String name;
        while (true) {
            try {
                System.out.println("Введите имя:");
                System.out.print(">");
                name = scanner.nextLine().trim();
                if (fileMode) System.out.println(name);
                if (name.equals("")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение поля 'name' не может быть пустым!");
            }
        }
        return name;
    }

    /**
     * Asks a user the human's X coordinate.
     * @return Human's X coordinate.
     */
    public float askX() {
        String strX;
        float x;
        while (true) {
            try {
                System.out.println("Введите координату X < 68:");
                System.out.print(">");
                strX = scanner.nextLine().trim();
                x = Float.parseFloat(strX);
                if (fileMode) System.out.println(strX);
                if (x >= 68.0f) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение должно быть меньше 68!");
            } catch (NumberFormatException exception) {
                System.out.println("Значение 'X' должно быть числом!");
            }
        }
        return x;
    }

    /**
     * Asks a user the human's Y coordinate.
     * @return Human's Y coordinate.
     */
    public Float askY() {
        String strY;
        float y;
        while (true) {
            try {
                System.out.println("Введите координату Y:");
                System.out.print(">");
                strY = scanner.nextLine().trim();
                y = Float.parseFloat(strY);
                if (fileMode) System.out.println(strY);
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Значение 'X' должно быть числом!");
            }
        }
        return y;
    }

    /**
     * Asks a user the human's coordinates.
     * @return Human's coordinates.
     */
    public Coordinates askCoordinates() {
        float x;
        Float y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * Asks a user the human's realHero.
     * @return Human's realHero.
     */
    public boolean askRealHero() {
        String strRealHero;
        boolean realHero;
        while (true) {
            try {
                System.out.println("Введите, крутой ли у вас герой:");
                System.out.print(">");
                strRealHero = scanner.nextLine().trim();
                realHero = Boolean.parseBoolean(strRealHero);
                if (fileMode) System.out.println(strRealHero);
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Значение 'realHero' должно быть boolean!");
            }
        }
        return realHero;
    }

    /**
     * Asks a user the human's hasToothpick.
     * @return Human's hasToothpick.
     */
    public Boolean askHasToothpick() {
        String strHasToothpick;
        Boolean hasToothpick;
        while (true) {
            try {
                System.out.println("Введите наличие зубочистки:");
                System.out.print(">");
                strHasToothpick = scanner.nextLine().trim();
                hasToothpick = Boolean.parseBoolean(strHasToothpick);
                if (fileMode) System.out.println(strHasToothpick);
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Значение 'hasToothpick' должно быть boolean!");
            }
        }
        return hasToothpick;
    }

    /**
     * Asks a user the human's achievements.
     * @return Human's achievements.
     */
    public float askImpactSpeed() {
        Float impactSpeed;
        while (true) {
            try {
                System.out.println("Введите скорость удара:");
                System.out.print(">");
                impactSpeed = Float.parseFloat(scanner.nextLine().trim());
                if (fileMode) System.out.println(impactSpeed);
                if (impactSpeed == null) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение поля 'impactSpeed' не может быть пустым!");
            }
        }
        return impactSpeed;
    }

    /**
     * Asks a user the human's weapon type.
     * @return Human's weapon type.
     */
    public WeaponType askWeaponType() {
        String strWeapon;
        WeaponType weapon;
        while (true) {
            try {
                System.out.println("Список доступного оружия - " + WeaponType.list());
                System.out.println("Введите оружие");
                System.out.print(">");
                strWeapon = scanner.nextLine().trim();
                if (fileMode) System.out.println(strWeapon);
                weapon = WeaponType.valueOf(strWeapon.toUpperCase());
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println("Такого оружия нет!");
            }
        }
        return weapon;
    }
    public Mood askMood() {
        String strMood;
        Mood mood;
        while (true) {
            try {
                System.out.println("Список доступых настроений - " + Mood.list());
                System.out.println("Введите настроение");
                System.out.print(">");
                strMood= scanner.nextLine().trim();
                if (fileMode) System.out.println(strMood);
                mood = Mood.valueOf(strMood.toUpperCase());
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println("Такого настроения нет!");
            }
        }
        return mood;
    }

    /**
     * Asks a user the human's car.
     * @return Human's car.
     */
    public Car askCar() {
        return new Car(askCarName(), askCarCool());
    }

    /**
     * Asks a user the human car's name.
     * @return Car's name.
     */
    public String askCarName() {
        String name;
        while (true) {
            try {
                System.out.println("Введите имя машины:");
                System.out.print(">");
                name = scanner.nextLine().trim();
                if (fileMode) System.out.println(name);
                if (name.equals("")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Значение поля 'name' не может быть пустым!");
            }
        }
        return name;
    }

    /**
     * Asks a user the human car's parentLegion.
     * @return Car's parentLegion.
     */
    public Boolean askCarCool() {
        Boolean cool;
        while (true) {
            try{
                System.out.println("Введите, крута ли ваша машина:");
                System.out.print(">");
                cool = Boolean.getBoolean(scanner.nextLine().trim().toLowerCase());
                if (fileMode) System.out.println(cool);
                break;
            } catch (IllegalArgumentException exception) {
                System.out.println("Такого оружия нет!");
            }
        }
        return cool;
    }

    /**
     * Asks a user a question.
     * @return Answer (true/false).
     * @param ask A question.
     */
    public boolean askAboutChangingField(String ask) {
        String res = ask + " (+/-):";
        String answer;
        while (true) {
            try {
                System.out.println(res);
                System.out.print(">");
                answer = scanner.nextLine().trim();
                if (fileMode) System.out.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new NotDeclaredValueException();
                break;
            } catch (NotDeclaredValueException exception) {
                System.out.println("Ответ должен быть представлен знаками '+' или '-'!");
            }
        }
        return answer.equals("+");
    }
}