package filesprocessing.filters;
import filesprocessing.ErrorTypeOne;

/**
 * a class factory that creates filter object according to the commendfile
 */
public class FilterFactory {

    /**
     * constants
     */
    private static final int TYPE1_YES = 1;
    private static final int TYPE1_NO = 2;
    private static final int TYPE1_YES_NOT = 3;
    private static final int TYPE1_NO_NOT = 4;
    private static final String YES = "YES";
    private static final String NO = "NO";
    private static final int YES_OR_NOT_SPOT = 1;
    protected static final String NOT = "NOT";
    private static final int TYPE_MIN_LENGTH = 2;
    private static final int TYPE_MAX_LENGTH = 3;
    private static final int TYPE2_CONDITION = 1;
    private static final int TYPE2_CONDITION_NOT = 2;
    private static final int NOT_GREATER_THEN_SPOT = 1;
    private static final int FIRST_NUM = 1;
    private static final int SECOND_NUM = 2;
    private static final int TYPE4_MIN_LENGTH = 3;
    private static final int TYPE4_MAX_LENGTH = 4;

    /**
     * a function that splits the given string line to an array returns a filter object calling other
     * functions the
     * creates an check for validation of the line given
     * @param line string line - commend from commend file
     * @param lineNumber lineNumber of line in commend file
     * @return filter object
     * @throws ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    public static Filter getFilter(String line, int lineNumber) throws ErrorTypeOne {
        String[] commendOfFilter = line.split("#");
        if (commendOfFilter.length <= 4){
            try {
                return filterIsValid(commendOfFilter, lineNumber);
            } catch (ErrorTypeOne e) {
                throw new ErrorTypeOne();
            }
        } else {
            throw new ErrorTypeOne();
        }
    }

    /**
     * check if the array of strings commend has valid values using type1,2,3,4 functions, and creates a
     * filter object accordingly
     * @param commendOfFilter array of strings commend
     * @param lineNumber lineNumber of line in commend file
     * @return filter object
     * @throws ErrorTypeOne ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    private static Filter filterIsValid(String[] commendOfFilter, int lineNumber) throws ErrorTypeOne {
        int filterRecipe;
        switch (commendOfFilter[0]){
            case "hidden":
                filterRecipe = isFilterValidType1(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new HiddenFilter(filterRecipe, lineNumber) ;
            case "executable":
                filterRecipe = isFilterValidType1(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new ExecutableFilter(filterRecipe, lineNumber);
            case "writable":
                filterRecipe = isFilterValidType1(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new WritableFilter(filterRecipe, lineNumber);
            case "suffix":
                filterRecipe = isFilterValidType2(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new SuffixFilter(commendOfFilter, filterRecipe, lineNumber);
            case "prefix":
                filterRecipe = isFilterValidType2(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new PrefixFilter(commendOfFilter, filterRecipe, lineNumber);
            case "contains":
                filterRecipe = isFilterValidType2(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new ContainsFilter(commendOfFilter, filterRecipe, lineNumber);
            case "file":
                filterRecipe = isFilterValidType2(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new FilterFile(commendOfFilter, filterRecipe, lineNumber);
            case "smaller_than":
                filterRecipe = isFilterValidType3(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new SmallerThanFilter(commendOfFilter, filterRecipe, lineNumber);
            case "greater_than":
                filterRecipe = isFilterValidType3(commendOfFilter, TYPE_MIN_LENGTH, TYPE_MAX_LENGTH);
                return new GreaterThenFilter(commendOfFilter, filterRecipe, lineNumber);
            case "between":
                filterRecipe = isFilterValidType4(commendOfFilter, TYPE4_MIN_LENGTH, TYPE4_MAX_LENGTH);
                return new BetweenFilter(commendOfFilter, filterRecipe, lineNumber);
            case "all":
                return new AllFilter(lineNumber);
            default:
                throw new ErrorTypeOne();
        }
    }

    /**
     * returns default filter AllFilter if something goes wrong
     * @param lineNumber lineNumber of line in commend file
     * @return returns default filter
     */
    public static AllFilter defaultFilter(int lineNumber) {
        return new AllFilter(lineNumber);
    }

    /**
     * type1 function, checks if the array string is valid, according to certain filters
     * @param commendOfFilter array of strings commend
     * @param minLength min length of commendOfFilter
     * @param maxLength max length of commendOfFilter
     * @return 1,2,3 or 4, according to the pattern
     * @throws ErrorTypeOne
     */
    public static int isFilterValidType1(String[] commendOfFilter, int minLength, int maxLength) throws
            ErrorTypeOne {
        switch (Filter.isFilterCommendsValid(commendOfFilter, minLength, maxLength)) {
            case Filter.NO_NOT_SUFFIX:
                switch (commendOfFilter[YES_OR_NOT_SPOT]) {
                    case YES:
                        return TYPE1_YES;
                    case NO:
                        return TYPE1_NO;
                    default:
                        throw new ErrorTypeOne();
                }
            case Filter.NOT_SUFFIX_ON_SPOT:
                switch (commendOfFilter[YES_OR_NOT_SPOT]) {
                    case YES:
                        return TYPE1_YES_NOT;
                    case NO:
                        return TYPE1_NO_NOT;
                    default:
                        throw new ErrorTypeOne();
                }
            default:
                throw new ErrorTypeOne();
        }
    }

    /**
     * type2 function checks if the filter is valid or not, according to certain filters
     * @param commendOfFilter array of strings commend
     * @param minLength min length of commendOfFilter
     * @param maxLength max length of commendOfFilter
     * @return 1 or 2, according to the pattern
     * @throws ErrorTypeOne
     */
    public static int isFilterValidType2(String[] commendOfFilter, int minLength, int maxLength) throws
            ErrorTypeOne {
        switch (Filter.isFilterCommendsValid(commendOfFilter, minLength, maxLength)) {
            case Filter.NO_NOT_SUFFIX:
                return TYPE2_CONDITION;
            case Filter.NOT_SUFFIX_ON_SPOT:
                return TYPE2_CONDITION_NOT;
            default:
                throw new ErrorTypeOne();
        }
    }

    /**
     * type3 function that checks a specific pattern for greaterthan filter and smallerthan filter, and alike
     * @param commendOfFilter array of strings commend
     * @param minLength min length of commendOfFilter
     * @param maxLength max length of commendOfFilter
     * @return 1 or 2, that each fit to a certain pattern
     * @throws ErrorTypeOne
     */
    public static int isFilterValidType3(String[] commendOfFilter, int minLength, int maxLength) throws
            ErrorTypeOne {
        switch (Filter.isFilterCommendsValid(commendOfFilter, minLength, maxLength)) {
            case Filter.NO_NOT_SUFFIX:
                if (Double.parseDouble(commendOfFilter[NOT_GREATER_THEN_SPOT]) < 0.0) {
                    throw new ErrorTypeOne();
                } else {
                    return 1;
                }
            case Filter.NOT_SUFFIX_ON_SPOT:
                if (Double.parseDouble(commendOfFilter[NOT_GREATER_THEN_SPOT]) < 0.0) {
                    throw new ErrorTypeOne();
                } else {
                    return 2;
                }
            default:
                throw new ErrorTypeOne();
        }
    }

    /**
     * type4 function that check a spesipic conditions for between filter and alike
     * @param commendOfFilter array of strings commend
     * @param minLength min length of commendOfFilter
     * @param maxLength max length of commendOfFilter
     * @return 1/2 according to patten
     * @throws ErrorTypeOne ErrorTypeOne Exception dealing with bad line commend format from the commendfile
     */
    public static int isFilterValidType4(String[] commendOfFilter, int minLength, int maxLength) throws
            ErrorTypeOne {
        switch (Filter.isFilterCommendsValid(commendOfFilter, minLength, maxLength)) {
            case Filter.NO_NOT_SUFFIX:
                if (Double.parseDouble(commendOfFilter[FIRST_NUM]) >= 0.0 &&
                        Double.parseDouble(commendOfFilter[FIRST_NUM])
                                <= Double.parseDouble(commendOfFilter[SECOND_NUM])) {
                    return 1;
                } else {
                    throw new ErrorTypeOne();
                }
            case Filter.NOT_SUFFIX_ON_SPOT:
                if (Double.parseDouble(commendOfFilter[FIRST_NUM]) >= 0.0 &&
                        Double.parseDouble(commendOfFilter[FIRST_NUM])
                                <= Double.parseDouble(commendOfFilter[SECOND_NUM])) {
                    return 2;
                } else {
                    throw new ErrorTypeOne();
                }
            default:
                throw new ErrorTypeOne();
        }
    }
}
