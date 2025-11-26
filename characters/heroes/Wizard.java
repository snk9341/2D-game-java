package characters.heroes;

import characters.Attack;
import characters.Character;

public class Wizard extends Character {
    
    public Wizard() {
        super(
            "Gandalf", "Wizard", 5,
            85, 9, 4, 7, 100,
            "assets/Wizard/Wizard_spritelist.png", 128, 128
        );
        Attack attack1 = new Attack("Magic Bolt", 22);
        Attack attack2 = new Attack("Fire Blast", 35);

        addAttack(attack1);
        addAttack(attack2);
        configureAnimations();
    }
    
    private void configureAnimations() {
        // Positions
        setAnimationPosition("idle", 0, 0, 6);
        setAnimationPosition("walk", 1, 0, 7);
        setAnimationPosition("run", 2, 0, 8);
        setAnimationPosition("jump", 3, 0, 11);
        setAnimationPosition("attack1", 4, 0, 10);
        setAnimationPosition("attack2", 5, 0, 4);
        setAnimationPosition("attack3", 6, 0, 7);
        setAnimationPosition("attack4", 7, 0, 5);
        setAnimationPosition("hurt", 8, 0, 4);
        setAnimationPosition("death", 9, 0, 4);
        
        setAnimationDelay("idle", 125);
        setAnimationDelay("walk", 110);
        setAnimationDelay("run", 100);
        setAnimationDelay("jump", 95);
        setAnimationDelay("attack1", 100);
        setAnimationDelay("attack2", 130);
        setAnimationDelay("attack3", 105);
        setAnimationDelay("attack4", 200);   
        setAnimationDelay("hurt", 140);
        setAnimationDelay("death", 160);


        setAnimationPositionInSheet("hang", "bonus", 0, 0, 6);
        setAnimationPositionInSheet("pull_up", "bonus", 1, 0, 6);
        setAnimationPositionInSheet("climb", "bonus", 2, 0, 6);
        setAnimationPositionInSheet("roll", "bonus", 3, 0, 9);
        setAnimationPositionInSheet("elixir", "bonus", 4, 0, 7);
        setAnimationPositionInSheet("take", "bonus", 5, 0, 6);
        setAnimationPositionInSheet("special_attack1", "bonus", 6, 0, 8);
        setAnimationPositionInSheet("special_attack2", "bonus", 7, 0, 16);
        setAnimationPositionInSheet("special_attack3", "bonus", 8, 0, 18);
        setAnimationPositionInSheet("rest", "bonus", 9, 0, 5);

        setAnimationDelay("special_attack1", 120);
        setAnimationDelay("special_attack2", 140);
        setAnimationDelay("special_attack3", 130);
        setAnimationDelay("roll", 80);
        setAnimationDelay("take", 120);
        setAnimationDelay("hang", 150);
        setAnimationDelay("pull_up", 130);
        setAnimationDelay("rest", 250);
        setAnimationDelay("elixir", 110);
    }
}
