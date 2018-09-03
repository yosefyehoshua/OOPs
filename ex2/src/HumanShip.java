import oop.ex2.GameGUI;
import java.awt.*;

/**
 a sub-class of a ship that controlled by a human.
 * Controlled by the user. The keys are: left-arrow and right-arrow to turn, up-arrow to accelerate, ’d’ to
 * fire a shot, ’s’ to turn on the shield, ’a’ to teleport.
 * @author Yosef12345
 */
public class HumanShip extends SpaceShip {

    /**----------------- methods ---------------------*/

    /**
     * class that characterize the behavior of each ship - controlled by a human..
     *
     * @param game the game object to which this ship belongs.
     * @return Act object to which this ship belongs.
     */
    public Act actLike(SpaceWars game) {
        Act act = new Act();
        // teleport //
        if (game.getGUI().isTeleportPressed()) { act.setTeleport(true); }

        // Accelerate and turn //
        if (game.getGUI().isUpPressed()) { act.setAccel(true); }
        if (game.getGUI().isLeftPressed()) { act.setTurn(TURN_RIGHT); }
        else if (game.getGUI().isRightPressed()) { act.setTurn(TURN_LEFT); }

        // Shield activation //
        if (game.getGUI().isShieldsPressed()) { act.setShieldOn(true); }

        // Firing a shot
        if (game.getGUI().isShotPressed()) { act.setShot(true); }

        return act;
    }

    @Override
    public Image getImage() {
        if (!shieldOn) {
            return GameGUI.SPACESHIP_IMAGE;
        } else {
            return GameGUI.SPACESHIP_IMAGE_SHIELD;
        }
    }
}
