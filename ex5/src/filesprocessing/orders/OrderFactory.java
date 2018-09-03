package filesprocessing.orders;
import filesprocessing.ErrorTypeOne;

/**
 * a class factory that creates Order object according to the coomendfile
 */
public class OrderFactory {

    /**
     * constants
     */
    private static final String ASB_ORDER = "abs";
    private static final String TYPE_ORDER = "type";
    private static final String SIZE_ORDER = "size";
    private static final int NAME_OF_ORDER = 0;

    /**
     * a function that calls for a creation of an order object given a line fron the commendfile for build
     * instruction. the function split the line (string)
     * @param line string - an agreed format for instructions
     * @return AN ORDER OBJECT of some type
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    public static Order getOrder(String line) throws ErrorTypeOne {
        String[] commendOfOrder = line.split("#");
        if (commendOfOrder.length <= 2){
            try {
                return isOrderIsValid(commendOfOrder);
            } catch (ErrorTypeOne e) {
                throw new ErrorTypeOne();
            }
        } else {
            throw new ErrorTypeOne();
        }
    }

    /**
     * THIS FUNCTION checks is the line read from the commend, now an array of strings file is valid, if is
     * it will create an order
     * object according to instroction in line
     * @param commendOfOrder an array of strings ,each cell in array has info. about type, and action of
     *                       this order
     * @return an order object
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    private static Order isOrderIsValid(String[] commendOfOrder) throws ErrorTypeOne {
        boolean reversed;
        switch (commendOfOrder[NAME_OF_ORDER]) {
            case ASB_ORDER:
                reversed = Order.isReverse(commendOfOrder);
                return new OrderAbs(reversed);
            case TYPE_ORDER:
                reversed = Order.isReverse(commendOfOrder);
                return new OrderType(reversed);
            case SIZE_ORDER:
                reversed = Order.isReverse(commendOfOrder);
                return new OrderSize(reversed);
            default:
                throw new ErrorTypeOne();
        }
    }

    /**
     * a function that define a defualt order - abs
     * @return OrderAbs type object
     */
    public static Order defaultOrder() {
        return new OrderAbs(false);
    }
}
