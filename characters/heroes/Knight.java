package characters.heroes;

import characters.Attack;
import characters.Character;

public class Knight extends Character {
    public Knight() {
        super(
                "Arthea", "Knight", 2,
                120, 8, 15, 6, 30,
                "assets/Knight/Knight_spritelist.png", 128, 128
        );
        Attack attack1 = new Attack("Sword Strike", 18);
        Attack attack2 = new Attack("Shield Slam", 28);

        addAttack(attack1);
        addAttack(attack2);
        configureAnimations();
    }

    private void configureAnimations() {
        // Positions dans la spritesheet principale
        setAnimationPosition("idle", 0, 0, 6);
        setAnimationPosition("walk", 1, 0, 8);
        setAnimationPosition("run", 2, 0, 7);
        setAnimationPosition("attack1", 3, 0, 5);
        setAnimationPosition("attack2", 4, 0, 2);
        setAnimationPosition("attack3", 5, 0, 5);
        setAnimationPosition("attack4", 6, 0, 5);
        setAnimationPosition("jump", 7, 0, 6);
        setAnimationPosition("hurt", 8, 0, 3);
        setAnimationPosition("death", 9, 0, 4);

        // Délais d'animation de base (en millisecondes)
        setAnimationDelay("idle", 115);
        setAnimationDelay("walk", 105);
        setAnimationDelay("run", 95);
        setAnimationDelay("jump", 110);
        setAnimationDelay("attack1", 100);
        setAnimationDelay("attack2", 120);
        setAnimationDelay("attack3", 100);
        setAnimationDelay("attack4", 100);
        setAnimationDelay("hurt", 130);
        setAnimationDelay("death", 160);

        // Charger la spritesheet bonus pour les effets spéciaux
        loadAdditionalSpritesheet("bonus", "assets/Knight/Knight_spritelist_bonus.png", 128, 128);


        setAnimationPositionInSheet("hang", "bonus", 0, 0, 6);
        setAnimationPositionInSheet("pull_up", "bonus", 1, 0, 6);
        setAnimationPositionInSheet("climb", "bonus", 2, 0, 6);
        setAnimationPositionInSheet("roll", "bonus", 3, 0, 12);
        setAnimationPositionInSheet("elixir", "bonus", 4, 0, 7);
        setAnimationPositionInSheet("take", "bonus", 5, 0, 7);
        setAnimationPositionInSheet("special_attack1", "bonus", 6, 0, 8);
        setAnimationPositionInSheet("special_attack2", "bonus", 7, 0, 5);
        setAnimationPositionInSheet("special_attack3", "bonus", 8, 0, 7);
        setAnimationPositionInSheet("rest", "bonus", 9, 0, 4);

        // Délais pour animations bonus
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
