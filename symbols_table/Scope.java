package symbols_table;

import symbols_table.scope.DuplicateSymbolException;
import symbols_table.scope.LogicalException;

public interface Scope {
    Scope getParent();

    void define(Symbol symbol) throws DuplicateSymbolException, LogicalException;

    Symbol search(String name);
} // Scope
