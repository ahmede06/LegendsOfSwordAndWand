package strategy;

import model.Unit;

public class NormalAttack implements AttackStrategy {

    @Override
    public void attack(Unit attacker, Unit target) {

        target.takeDamage(attacker.getAttack());

    }
}