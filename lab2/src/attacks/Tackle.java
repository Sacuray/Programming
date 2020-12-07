package attacks;
import ru.ifmo.se.pokemon.*;
import java.lang.Math;

public class Tackle extends PhysicalMove{
    public Tackle(){
        super(Type.NORMAL, 40, 100);
    }
    @Override
    protected void applyOppDamage(Pokemon opp, double damage){
        opp.setMod(Stat.HP, (int)Math.round(damage));
    }
    @Override
    protected String describe(){
        return "атакует";
    }
}
