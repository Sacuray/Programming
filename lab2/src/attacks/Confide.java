package attacks;
import ru.ifmo.se.pokemon.*;

public class Confide extends StatusMove{
    public Confide(){
        super(Type.NORMAL, 0, 0);
    }
    @Override
    protected void applyOppEffects(Pokemon opp){
        opp.setMod(Stat.SPECIAL_ATTACK, -1);
    }
    @Override
    protected String describe(){
        return "понижает специальную атаку цели на 1";
    }
}
