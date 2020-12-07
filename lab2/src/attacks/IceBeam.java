package attacks;
import java.lang.Math;
import ru.ifmo.se.pokemon.*;

public class IceBeam extends SpecialMove {
    public IceBeam() {
        super(Type.ICE, 90, 100);
    }
    @Override
    protected void applyOppDamage (Pokemon pok, double damage) {
        pok.setMod (Stat.HP, (int) Math.round (damage));
    }
    @Override
    protected void applyOppEffects (Pokemon pok){
        if (Math.random() <= 0.1){
            Effect.freeze(pok);
        }
    }
    @Override
    protected String describe(){
        return "атакует и имеет 10% вероятность заморозить цель";
    }
}
