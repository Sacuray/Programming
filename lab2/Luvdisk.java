package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Luvdisk extends Pokemon{
    public Luvdisk(String name, int level){
        super(name, level);
        setType(Type.WATER);
        setStats(43, 30, 55, 40, 65, 97);
        setMove(new Tackle(), new Confide(), new DrainingKiss(), new Waterfall());
    }
}
