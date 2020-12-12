package pokemons;
import ru.ifmo.se.pokemon.*;
import attacks.*;

public class Porygon2 extends Porygon{
    public Porygon2(String name, int level){
        super(name, level);
        setType(Type.NORMAL);
        setStats(85, 80, 90, 105, 95, 60);
        setMove(new SignalBeam(), new IceBeam(), new DefenceCurl());
    }
}
