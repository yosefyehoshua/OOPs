import java.lang.Math;
/**
 * a sub-class of an aggressive ship This ship pursues other ships and tries to fire at them. It will always
 * accelerate, and turn towards the nearest ship. When its angle to the nearest ship is less than 0.21
 * radians, it will try to fire.
 *
 * @author Yosef Yehoshua.
 */
public class AggressiveShip extends SpaceShip {

    /**_________________constance_____________________*/

    private static final double MIN_ANGLE = 0.21;

    /**----------------- data members ---------------*/

    /** minAngle to an other ship */
    private static final double minAngle = MIN_ANGLE;

    /**----------------- methods ---------------------*/

    /**
     * class that characterize the behavior of each ship.
     *
     * @param game the game object to which this ship belongs.
     * @return Act object to which this ship belongs.
     */
    public Act actLike(SpaceWars game) {
        Act act = new Act();
        double angleFromShip = this.getPhysics().angleTo(game.getClosestShipTo(this)
                .getPhysics());

        // shot when getting to close to an other spaceship... //
        if (Math.abs(angleFromShip) < minAngle) { act.setShot(true); }

        // chase from close spaceships //
        act.setAccel(true);
        if (angleFromShip < 0) {
            act.setTurn(-1);
        } else if (angleFromShip > 0) {
            act.setTurn(1);
        }

        return act;
    }
}