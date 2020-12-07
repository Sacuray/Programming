package attacks;
import ru.ifmo.se.pokemon.*;


public class Rest extends StatusMove{
    public Rest(){
        super(Type.PSYCHIC, 0, 0);
    }
    @Override
    protected void applySelfEffects (Pokemon pok) {
        pok.setMod(Stat.HP, (int) (pok.getHP() - pok.getStat(Stat.HP)));
        pok.setCondition(new Effect().condition(Status.SLEEP).attack(0.0).turns(2));
    }
    @Override
    protected String describe () {
        return "восстанавливает здоровье и засыпает на 2 хода";
    }
}
