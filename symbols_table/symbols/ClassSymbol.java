package symbols_table.symbols;

import symbols_table.scope.*;

import symbols_table.*;

import java.util.TreeMap;

public class ClassSymbol extends Symbol
        implements Scope {
    public ClassSymbol superclass;

    private TreeMap<String, Atribute> atributes;
    private TreeMap<String, Method> methods;
    private TreeMap<String, Constructor> constructors;
}
