/**
 * A class that create all the spaceship objects according to the command line arguments.
 */
public class SpaceShipFactory {
    public static SpaceShip[] createSpaceShips(String[] args) {
        SpaceShip [] ships = new SpaceShip[args.length];

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case ("h"):
                    ships[i] = new HumanShip();
                    break;
                case ("r"):
                    ships[i] = new RunnerShip();
                    break;
                case ("b"):
                    ships[i] = new BasherShip();
                    break;
                case ("a"):
                    ships[i] = new AggressiveShip();
                    break;
                case ("d"):
                    ships[i] = new DrunkShip();
                    break;
                case ("s"):
                    ships[i]  = new SpecialShip();
                    break;
                default:
                    ships[i] = null;
                    break;
            }
        }
        return ships;
    }
}
