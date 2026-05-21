package symbols_table.scope;

public class DuplicateSymbolException extends Exception {
    public DuplicateSymbolException() {
        super("Error: duplicate Symbol on the same Scope");
    }

    public DuplicateSymbolException(String name) {
        super("Error: The Symbol '" + name + "' was already declared in this Scope");
    }
} // Duplicate Symbol Exception
