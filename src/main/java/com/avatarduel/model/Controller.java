

import com.avatarduel.model.Player;

public class Controller {

    private Player player;
    
    public Controller(Player player1) {
        this.player = player1;
    }

    public void DrawPhase() {
        // Controller for DrawPhase
        
        // Draw 1 card from deck
        // Reset power to equal as land card value

    }

    public void MainPhase() {
        // Controller for MainPhase

        // Put character card on the field. (Can be 0 or > 0)
        // Character card which mentioned before can not attack in the battle phase in the same round when it summoned.
        // Change position of card (Attack or Defense)
        // Put maximum number of 1 Land Card
        // Put skill card on the field. (Can be 0 or > 0)
        // Throw 0 or more skill card
    }

    public void BattlePhase(Player player2) {
        // Controller for BattlePhase

        // Use character on the field to attack
        // If there's still card on the player2 field,player cannot directly attack the enemy HP.
        // Every character card only can attack once.
        // Card that want to attack must be in the Attack Position
        // If enemy card is in Attack Position:
            // Enemy card's attack must be < attacking card attack
            // After the attack,enemy card will be vanished
            // The difference of attack value will cost equally to the enemy HP.
        // If enemy card is in Defense Position:
            // Enemy card's defense must be < attacking card attack
            // After the attack,enemy card will be vanished
            // No reduce of health will procceed.
    }

    public void EndPhase() {
        // Controller for EndPhase

        // Player ending their turn
    }
}

