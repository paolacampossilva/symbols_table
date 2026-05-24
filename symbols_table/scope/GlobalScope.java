package symbols_table.scope;

import symbols_table.*;
import symbols_table.symbols.*;

import java.util.TreeMap;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class GlobalScope 
    implements Scope 
{
    // Atributos
    private TreeMap<String, ClassSymbol> classes;

    // Construtor
    public GlobalScope() 
    {
        this.classes = new TreeMap<>();
    }

    // Métodos
    public ClassSymbol getClass(String name) 
    {
        return classes.get(name);
    }

    @Override
    public Scope getParent() 
    {
        return null;
    }

    @Override
    public Symbol search(String name) 
    {
        if (classes.containsKey(name))
            return this.getClass(name);

        return null;
    }

    @Override
    public void define(Symbol symbol) 
        throws DuplicateSymbolException, LogicalException 
    {
        if (symbol instanceof ClassSymbol) {
            String name = symbol.getName();

            if (classes.containsKey(name))
                throw new DuplicateSymbolException(name);

            classes.put(name, (ClassSymbol) symbol);
        } 
        else
            throw new LogicalException(symbol.getClass().getName());
    }

    @Override
    public String toString() 
    {
        return "GlobalScope" + classes.keySet();
    }
    
} // GlobalScope
