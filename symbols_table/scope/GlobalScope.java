package symbols_table.scope;

import symbols_table.symbols.*;

import symbols_table.*;

import java.util.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class GlobalScope implements Scope {
    // Atributos
    private TreeMap<String, ClassSymbol> classes;

    // Construtores
    public GlobalScope() {
        this.classes = new TreeMap<>();
    }

    // Métodos
    public ClassSymbol getClass(String name) {
        return classes.get(name);
    }

    @Override
    public Scope getParent() {
        return null;
    }

    @Override
    public Symbol search(String name) {
        if (classes.containsKey(name))
            return this.getClass(name);
        return null;
    }

    @Override
    public void define(Symbol symbol) throws DuplicateSymbolException, LogicalException {
        if (symbol instanceof ClassSymbol) {
            String name = symbol.getName();

            if (classes.containsKey(name))
                throw new DuplicateSymbolException();

            classes.put(name, (ClassSymbol) symbol);
        } else
            throw new LogicalException(symbol.getClass().getName());
    }

}
