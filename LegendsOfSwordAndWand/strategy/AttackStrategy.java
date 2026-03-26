package strategy;

import model.Unit;

public interface AttackStrategy {

    void attack(Unit attacker, Unit target);

}