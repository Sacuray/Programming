package attacks;
import ru.ifmo.se.pokemon.*;
import java.lang.Math;

public class Waterfall extends PhysicalMove{
    public Waterfall(){
        super(Type.WATER, 80, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon pok, double damage) {
        pok.setMod(Stat.HP, (int) Math.round(damage));
    }

    @Override
    protected void applyOppEffects(Pokemon opp) {
        if (Math.random() < 0.2){
            Effect.flinch(opp);
        }
    }

    @Override
    protected String describe() {
        return "атакует и имеет 20% вероятность испугать цель";
    }
}
