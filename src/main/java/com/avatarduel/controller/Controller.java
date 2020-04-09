package com.avatarduel.controller;

import com.avatarduel.player.*;
import com.avatarduel.card.*;
import com.avatarduel.card.Character;

public class Controller {

    private Player player;

    public Controller(Player player1) {
        this.player = player1;
    }

    public void DrawPhase() {
        // Controller for DrawPhase

        // Draw 1 card from deck
        player.getCardFromDeck();

        // Reset power to equal as land card value
        player.initializeStatus();

    }

    public void MainPhase() {
        // Controller for MainPhase
        int x, y;
        Position pos;
        // Put character card on the field. (Can be 0 or > 0)
        for (int i = 0; i < player.cardOnHand.size(); i++) {
            if ((player.cardOnHand.get(i).getType() == CardType.CHARACTER) && (player.cardOnHand.get(i).getElement() == Element.WATER) && (player.cardOnHand.get(i).power() <= player.status.water)) {
                Character water = player.cardOnHand.get(i);
                putCharacterOnTable(water, x, y, pos);
            } else if ((player.cardOnHand.get(i).getType() == CardType.CHARACTER) && (player.cardOnHand.get(i).getElement() == Element.FIRE) && (player.cardOnHand.get(i).power() <= player.status.fire)) {
                Character fire = player.cardOnHand.get(i);
                putCharacterOnTable(fire, x, y, pos);
            } else if ((player.cardOnHand.get(i).getType() == CardType.CHARACTER) && (player.cardOnHand.get(i).getElement() == Element.AIR) && (player.cardOnHand.get(i).power() <= player.status.air)) {
                Character air = player.cardOnHand.get(i);
                putCharacterOnTable(air, x, y, pos);
            } else if ((player.cardOnHand.get(i).getType() == CardType.CHARACTER) && (player.cardOnHand.get(i).getElement() == Element.EARTH) && (player.cardOnHand.get(i).power() <= player.status.earth)) {
                Character earth = player.cardOnHand.get(i);
                putCharacterOnTable(earth, x, y, pos);
            }
        }
        // Character card which mentioned before can not attack in the battle phase in the same round when it summoned.

        // Change position of card (Attack or Defense)
        player.changeCharacterPosition(water);
        player.changeCharacterPosition(fire);
        player.changeCharacterPosition(earth);
        player.changeCharacterPosition(air);

        // Put maximum number of 1 Land Card
        for (int i = 0; i < player.cardOnHand.size(); i++) {
            if (player.cardOnHand.get(i).getType() == CardType.LAND) {
                Land land = player.cardOnHand.get(i);
                player.putLandOnTable(land);

                // Put skill card on the field. (Can be 0 or > 0)
                for (int i = 0; i < player.cardOnHand.size(); i++) {
                    if ((player.cardOnHand.get(i).getType() == CardType.AURA) && (player.cardOnHand.get(i).getElement() == Element.EARTH) && (player.cardOnHand.get(i).power() <= player.status.earth)) {
                        Aura aura = player.cardOnHand.get(i);
                        player.putAuraOnTable(aura);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.AURA) && (player.cardOnHand.get(i).getElement() == Element.AIR) && (player.cardOnHand.get(i).power() <= player.status.air)) {
                        Aura aura = player.cardOnHand.get(i);
                        player.putAuraOnTable(aura);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.AURA) && (player.cardOnHand.get(i).getElement() == Element.FIRE) && (player.cardOnHand.get(i).power() <= player.status.fire)) {
                        Aura aura = player.cardOnHand.get(i);
                        player.putAuraOnTable(aura);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.AURA) && (player.cardOnHand.get(i).getElement() == Element.WATER) && (player.cardOnHand.get(i).power() <= player.status.water)) {
                        Aura aura = player.cardOnHand.get(i);
                        player.putAuraOnTable(aura);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.DESTROY) && (player.cardOnHand.get(i).getElement() == Element.EARTH) && (player.cardOnHand.get(i).power() <= player.status.earth)) {
                        Destroy destroy = player.cardOnHand.get(i);
                        player.putDestroyOnTable(destroy);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.DESTROY) && (player.cardOnHand.get(i).getElement() == Element.AIR) && (player.cardOnHand.get(i).power() <= player.status.air)) {
                        Destroy destroy = player.cardOnHand.get(i);
                        player.putDestroyOnTable(destroy);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.DESTROY) && (player.cardOnHand.get(i).getElement() == Element.FIRE) && (player.cardOnHand.get(i).power() <= player.status.fire)) {
                        Destroy destroy = player.cardOnHand.get(i);
                        player.putDestroyOnTable(destroy);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.DESTROY) && (player.cardOnHand.get(i).getElement() == Element.WATER) && (player.cardOnHand.get(i).power() <= player.status.water)) {
                        Destroy destroy = player.cardOnHand.get(i);
                        player.putDestroyOnTable(destroy);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.POWERUP) && (player.cardOnHand.get(i).getElement() == Element.EARTH) && (player.cardOnHand.get(i).power() <= player.status.earth)) {
                        PowerUp powerup = player.cardOnHand.get(i);
                        player.putPowerUpOnTable(powerup);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.POWERUP) && (player.cardOnHand.get(i).getElement() == Element.AIR) && (player.cardOnHand.get(i).power() <= player.status.air)) {
                        PowerUp powerup = player.cardOnHand.get(i);
                        player.putPowerUpOnTable(powerup);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.POWERUP) && (player.cardOnHand.get(i).getElement() == Element.FIRE) && (player.cardOnHand.get(i).power() <= player.status.fire)) {
                        PowerUp powerup = player.cardOnHand.get(i);
                        player.putPowerUpOnTable(powerup);
                    } else if ((player.cardOnHand.get(i).getType() == CardType.POWERUP) && (player.cardOnHand.get(i).getElement() == Element.WATER) && (player.cardOnHand.get(i).power() <= player.status.water)) {
                        PowerUp powerup = player.cardOnHand.get(i);
                        player.putPowerUpOnTable(powerup);
                    }
                }
            }
            // Throw 0 or more skill card

            //public void BattlePhase(Player player2) {
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
            //}

            //public void EndPhase() {
            // Controller for EndPhase

            // Player ending their turn
            // }
        }
    }
}
