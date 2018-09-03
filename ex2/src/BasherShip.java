/**
 * a sub-class of a ship that tries to Collide with the closest ship.
 * This ship attempts to collide with other ships. It will always accelerate, and will constantly turn
 * towards the closest ship. If it gets within a distance of 0.19 units (inclusive) from another ship, it
 * will attempt to turn on its shields.

 * @author Yosef Yehoshua.
 */
public class BasherShip extends SpaceShip {

    /** minDistance from an other ship */
    private static final double MIN_DISTANCE = 0.19;

    /**----------------- methods ---------------------*/

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

        // Shield On when getting to close to an other spaceship... //
        if (distanceFromShip < MIN_DISTANCE) { act.setShieldOn(true); }

        // chase from close spaceships //
        act.setAccel(true);
        if (angleFromShip < NEUTRAL_NUMBER) {
            act.setTurn(TURN_LEFT);
        } else if (angleFromShip > NEUTRAL_NUMBER) {
            act.setTurn(TURN_RIGHT);
        }

        return act;
    }
}