package symbols_table;

import symbols_table.scope.DuplicateSymbolException;

public interface Scope {
    Scope getParent();

    void define(Symbol symbol) throws DuplicateSymbolException;

    Symbol search(String name);
} // Scope
