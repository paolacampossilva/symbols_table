package symbols_table.symbols;

import symbols_table.*;
import symbols_table.scope.LogicalException;

import java.util.Set;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class Variable extends Symbol 
{
    // Atributo
    private final Type type;

    // Construtores
    public Variable(String name, Type type) 
        throws LogicalException
    {
        super(name);
        this.type = new Type(type);
    }

    public Variable(String name, Type type, Set<Modifier> modifiers) 
        throws LogicalException
    {
        super(name, modifiers);
        this.type = new Type(type);
    }

    // Métodos
    public Type getType() 
        throws LogicalException
    {
        return new Type(this.type); 
    }

    @Override
    public String toString() 
    {
        return "Variable{" +
                "name='" + getName() + '\'' +
                ", type=" + type +
                ", modifiers=" + getModifiers() +
                '}';
    }
    
} // Variable
