package attacks;
import ru.ifmo.se.pokemon.*;


public class DefenceCurl extends StatusMove{
    public DefenceCurl(){
        super(Type.NORMAL, 0, 0);
    }
    @Override
    protected void applySelfEffects(Pokemon pok){
        pok.setMod(Stat.DEFENSE, 1);
    }
    @Override
    protected String describe(){
        return "повышает свою защиту на 1 ступень";
    }
}
