import java.lang.Math;
/**
 * a sub-class of a ship that tries to avoid all other ships.
 * This spaceship attempts to run away from the fight. It will always accelerate, and will constantly turn
 * away from the closest ship. If the nearest ship is closer than 0.25 units, and if its angle to the Runner
 * is less than 0.23 radians, the Runner feels threatened and will attempt to teleport.
 * @author Yosef Yehoshua.
 */
public class RunnerShip extends SpaceShip {

    /**_________________constance_____________________*/

    /** minDistance from an other ship */
    private static final double MIN_DISTANCE = 0.25;
    /** minAngle to an other ship */
    private static final double MIN_ANGLE = 0.23;

    /**----------------- methods ---------------------*/

    /**
     * class that characterize the behavior of each ship - avoid all other ships..
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

        // teleport when getting to close to an other spaceship... //
        if (distanceFromShip < MIN_DISTANCE && Math.abs(angleFromShip) < MIN_ANGLE) {
            act.setTeleport(true);
        }

        // runs from close spaceships //
        act.setAccel(true);
        if (angleFromShip < 0) {
            act.setTurn(TURN_RIGHT);
        } else if (angleFromShip > 0) {
            act.setTurn(TURN_LEFT);
        }

        return act;
    }
}