package symbols_table.scope;

public class LogicalException extends Exception {
    public LogicalException(String type) {
        super("Error: " + type + " is not acceptable as a parameter for this method.");
    }
}
