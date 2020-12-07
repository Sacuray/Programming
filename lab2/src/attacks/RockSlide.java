package attacks;
import ru.ifmo.se.pokemon.*;
import java.lang.Math;

public class RockSlide extends PhysicalMove{
    public RockSlide(){
        super(Type.ROCK, 75, 90);
    }
    @Override
    protected void applyOppDamage(Pokemon pok, double damage) {
        pok.setMod(Stat.HP, (int) Math.round(damage));
    }

    @Override
    protected void applyOppEffects(Pokemon opp) {
        if (Math.random() < 0.3){
            Effect.flinch(opp);
        }
    }

    @Override
    protected String describe() {
        return "атакует и имеет 30% вероятность испугать цель";
    }
}
