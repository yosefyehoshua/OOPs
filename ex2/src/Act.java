/**
 * a class that holds data that and will help us build a "protocol" between the types of ships and
 * SpaceShip class
 */
public class Act {

    /**--------------- data members ---------------*/

    /** boolean indicator for teleportasion */
    private boolean Teleport = false;

    /** int indicator default at 0, and has a value of -1 for left turn or 1 for right */
    private int turn = 0;

    /** boolean indicator for acceleration */
    private boolean accel = false;

    /** boolean value if shield is up */
    private boolean shieldOn = false;

    /** boolean value */
    private boolean shot = false;

    /**----------------- getters ---------------------*/

    /**
     * @return doTeleport
     */
    public boolean getTeleport() {
        return Teleport;
    }

    /**
     * @return turn
     */
    public int getTurn() {
        return turn;
    }

    /**
     * @return accel
     */
    public boolean getAccel() {
        return accel;
    }

    /**
     * @return shieldOn
     */
    public boolean getShieldOn() { return shieldOn; }

    /**
     * @return shieldOn
     */
    public boolean getShot() { return shot; }

    /**----------------- setters ---------------------*/

    /**
     * set doTeleport
     */
    public void setTeleport(boolean bool) {
        Teleport = bool;
    }

    /**
     * set turn
     */
    public void setTurn(int number) {
        turn = number;
    }

    /**
     * set accel
     */
    public void setAccel(boolean bool) {
        accel = bool;
    }

    /**
     * set accel
     */
    public void setShieldOn(boolean bool) {
        shieldOn = bool;
    }

    /**
     * set accel
     */
    public void setShot(boolean bool) {
        shot = bool;
    }


}
