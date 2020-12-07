package attacks;
import ru.ifmo.se.pokemon.*;
import java.lang.Math;

public class Facade extends PhysicalMove{
    public Facade(){
        super(Type.NORMAL, 70, 100);
    }
    @Override
    protected void applyOppDamage(Pokemon opp, double damage){
        Status oppCond = opp.getCondition();
        if (oppCond.equals(Status.BURN) | oppCond.equals(Status.POISON) | oppCond.equals(Status.PARALYZE)) {
            opp.setMod(Stat.HP, (int)Math.round(damage) * 2);
        }
        else{
            opp.setMod(Stat.HP, (int)Math.round(damage));
        }
    }
    @Override
    protected String describe(){
        return "атакует, сила удваивается, если цель горит, отравлена или парализованна";
    }
}
