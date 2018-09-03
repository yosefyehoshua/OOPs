import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
abstract public class SpaceShip{

    /**------------------ constance ------------------*/

    public static final int HEALTH = 22;
    public static final int MAX_ENERGY = 210;
    public static final int CURRENT_ENERGY = 190;
    public static final int COOL_DOWN = 7;
    public static final int SHIELD_ON_COLLISION_ENERGY = 18;
    public static final int SHIELD_DOWN_COLLISION_ENERGY = 10;
    public static final int SHOT_ENERGY = 19;
    public static final int SHIELD_ENERGY = 3;
    public static final int TELEPORT_ENERGY = 140;
    public static final int NEUTRAL_NUMBER = 0;
    public static final int TURN_LEFT = -1;
    public static final int TURN_RIGHT = 1;

    /**----------------- data members ---------------*/

    /** int - the health of a ship */
    private int health = HEALTH;

    /** maximal energy that a ship can have */
    private int maxEnergy = MAX_ENERGY;

    /** the current energy that a ship have during the game */
    private int currentEnergy = CURRENT_ENERGY;

    /** SpaceShipPhysics object */
    private SpaceShipPhysics spaceShipPhysics = new SpaceShipPhysics();

    /** coolDown between shots */
    private int coolDown = COOL_DOWN;

    /** shieldOn if the shield is activated */
    protected boolean shieldOn;


    /**----------------- getters ---------------------*/

    /**
     * Gets the spaceShipPhysics object that controls this ship.
     * @return the spaceShipPhysics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() { return spaceShipPhysics; }

    /**--------------------- methods--------------------*/

    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
        shieldOn = false;
        Act act = actLike(game);
        if (act.getTeleport()) { teleport(); }
        spaceShipPhysics.move(act.getAccel(), act.getTurn());
        if (act.getShieldOn()) { shieldOn(); }
        if (act.getShot()) { fire(game); }
        if (coolDown < COOL_DOWN) { coolDown++; }
        currentEnergy++;
        swapBetweenEnergies();
        }

    /**
     * abstract class that characterize the behavior of each ship
     *
     * @param game the game object to which this ship belongs.
     * @return Act object to which this ship belongs.
     */
    public abstract Act actLike(SpaceWars game);

    /**
     * swap between maxEnergy and currentEnergy if maxEnergy < currentEnergy
     */
    private void swapBetweenEnergies() {
        if (maxEnergy < currentEnergy) {
            int temp = maxEnergy;
            maxEnergy = currentEnergy;
            currentEnergy = temp;
        }
    }

    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip() {
        if (!shieldOn) {
            health--;
            maxEnergy = maxEnergy - SHIELD_DOWN_COLLISION_ENERGY;
            swapBetweenEnergies();
        } else {
            maxEnergy = maxEnergy + SHIELD_ON_COLLISION_ENERGY;
            currentEnergy = currentEnergy + SHIELD_ON_COLLISION_ENERGY;
        }
    }

    /**
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
            health = HEALTH;
            maxEnergy = MAX_ENERGY;
            currentEnergy = CURRENT_ENERGY;
            shieldOn = false;
            spaceShipPhysics = new SpaceShipPhysics();
        }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return (health == NEUTRAL_NUMBER);
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
        if (!shieldOn) {
            health--;
            maxEnergy = maxEnergy - SHIELD_DOWN_COLLISION_ENERGY;
            swapBetweenEnergies();
        }
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * 
     * @return the image of this ship.
     */
    public Image getImage(){
        if (!shieldOn) {
            return GameGUI.ENEMY_SPACESHIP_IMAGE;
        } else {
            return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
        }
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
        if (currentEnergy >= SHOT_ENERGY && coolDown == COOL_DOWN) {
            game.addShot(spaceShipPhysics);
            currentEnergy = currentEnergy - SHOT_ENERGY;
            coolDown = NEUTRAL_NUMBER;
        }
    }

    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currentEnergy >= SHIELD_ENERGY) {
            shieldOn = true;
            currentEnergy = currentEnergy - SHIELD_ENERGY;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
        if (currentEnergy >= TELEPORT_ENERGY) {
            currentEnergy = currentEnergy - TELEPORT_ENERGY;
            spaceShipPhysics = new SpaceShipPhysics();
        }
    }
}
