package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Pineco extends Pokemon{
    public Pineco(String name, int level){
        super(name, level);
        setType(Type.BUG);
        setStats(50, 65, 90, 35, 35, 15);
        setMove(new Tackle(), new Rest(), new RockSlide());
    }
}
