import javax.swing.*;
import java.awt.*;

/**
 * a sub-class of a ship that is almost invisible it is really hard to spot it, but the moment it come close
 * make no mistakes it will shot you down!
 *
 * @author Yosef12345
 */
public class SpecialShip extends SpaceShip {

    /**_________________constance_____________________*/

    /** minDistance from an other ship */
    private static final double MIN_DISTANCE = 0.1;
    /** minAngle to an other ship */
    private static final double MIN_ANGLE = 0.2;

    public static final Image INVISIBLE_IMAGE =
            new ImageIcon(SpecialShip.class.getResource("invisible.gif"), "").getImage();
    public static final Image SCARY_IMAGE =
            new ImageIcon(SpecialShip.class.getResource("scary.gif"), "").getImage();

    /**
     * class that characterize the behavior of each ship.
     *
     * @param game the game object to which this ship belongs.
     * @return Act object to which this ship belongs.
     */
    public Act actLike(SpaceWars game) {
        Act act = new Act();
        double distanceFromShip = this.getPhysics().distanceFrom(game.getClosestShipTo(this)
                .getPhysics());
        double angleFromShip = this.getPhysics().angleTo(game.getClosestShipTo(this)
                .getPhysics());

        // shot when getting to close to an other spaceship... //
        if (Math.abs(angleFromShip) < MIN_ANGLE) {
            act.setShot(true);
        }

        if (distanceFromShip < MIN_DISTANCE) {
            act.setShieldOn(true);
        }
        // runs from close spaceships //
        act.setAccel(true);
        if (angleFromShip < 0) {
            act.setTurn(TURN_LEFT);
        } else if (angleFromShip > NEUTRAL_NUMBER) {
            act.setTurn(TURN_RIGHT);
        }

        return act;
    }


    @Override
    public Image getImage() {
        if (!shieldOn) {
            return INVISIBLE_IMAGE;
        } else {
            return SCARY_IMAGE;
        }
    }
}
