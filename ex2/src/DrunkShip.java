import java.util.Random;


/**
 * a sub-class of a ship that runs by a drunken driver.
 *
 * @author Yosef Yehoshua.
 */
public class DrunkShip extends SpaceShip {

    /**----------------- methods ---------------------*/

    /**
     * class that characterize the behavior of each ship - drunken driver.
     *
     * @param game the game object to which this ship belongs.
     * @return Act object to which this ship belongs.
     */
    public Act actLike(SpaceWars game) {
        Act act = new Act();
        // random vars to implement drunken driver acts
        Random random = new Random();
        int randTurn = random.nextInt((TURN_RIGHT - TURN_LEFT) + 1) + TURN_LEFT;
        boolean randBool = random.nextBoolean();

        act.setTeleport(randBool);
        act.setAccel(randBool);
        act.setTurn(randTurn);
        return act;
    }
}