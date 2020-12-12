package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Forretress extends Pineco{
    public Forretress(String name, int level){
        super(name, level);
        setType(Type.BUG, Type.STEEL);
        setStats(75, 90, 140, 60, 60, 40);
        setMove(new Tackle(), new Rest(), new RockSlide(), new FlashCannon());
    }
}
