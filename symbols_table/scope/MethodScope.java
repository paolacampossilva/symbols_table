package symbols_table.scope;

import symbols_table.symbols.*;
import symbols_table.*;
import java.util.TreeMap;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */
public class MethodScope implements Scope {
    private final Scope parent;
    private TreeMap<String, Symbol> symbols;

    // Construtor
    public MethodScope(Scope parent) {
        this.parent = parent;
        this.symbols = new TreeMap<>();
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    @Override
    public void define(Symbol symbol) throws DuplicateSymbolException, LogicalException {
        if (symbol instanceof ClassSymbol)
            throw new LogicalException(symbol.getClass().getSimpleName());

        String name = symbol.getName();

        if (symbols.containsKey(name))
            throw new DuplicateSymbolException(name);

        symbols.put(name, symbol);
    }

    @Override
    public Symbol search(String name) {
        if (symbols.containsKey(name))
            return symbols.get(name);
        else if (this.parent != null)
            return this.parent.search(name);
        else
            return null;
    }

    @Override
    public String toString() {
        return "MethodScope" + symbols.keySet();
    }

}
