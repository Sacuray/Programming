package attacks;
import ru.ifmo.se.pokemon.*;
import java.lang.Math;

public class DrainingKiss extends SpecialMove{
    double recovered_HP = 0.0;
    public DrainingKiss(){
        super(Type.FAIRY, 50, 100);
    }
    @Override
    protected void applyOppDamage(Pokemon opp, double damage){
        opp.setMod(Stat.HP,(int) Math.round(damage));
        recovered_HP = damage * -0.75;
    }
    protected void applySelfEffects(Pokemon pok){
        pok.setMod(Stat.HP,(int) Math.round(recovered_HP));
    }
    @Override
    protected String describe() {
        return "атакует, и восстанавливает здоровье на 75% урона";
    }
}
