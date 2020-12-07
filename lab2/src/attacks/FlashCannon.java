package attacks;
import ru.ifmo.se.pokemon.*;
import java.lang.Math;

public class FlashCannon extends SpecialMove {
    public FlashCannon(){
        super(Type.STEEL, 80, 100);
    }
    @Override
    protected void applyOppDamage(Pokemon pok, double damage) {
        pok.setMod(Stat.HP, (int) Math.round(damage));
    }

    @Override
    protected void applyOppEffects(Pokemon opp) {
        if (Math.random() < 0.1){
            opp.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    @Override
    protected String describe() {
        return "атакует и имеет 10% вероятность понизить специальную защиту цели";
    }
}
