package symbols_table.scope;

public class LogicalException extends Exception {
    public LogicalException(String clazz) {
        super("Error: " + clazz + " is not acceptable as a parameter for this method.");
    }

    public LogicalException(String scope, String symbol) {
        super("Error: is not acceptable to add a " + symbol + " without a open " + scope);
    }
}
