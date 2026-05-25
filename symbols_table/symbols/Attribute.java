package symbols_table.symbols;

import symbols_table.*;
import symbols_table.scope.LogicalException;

import java.util.Set;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class Attribute extends Symbol 
{
    // Atributo
    private final Type type;

    // Construtores
    public Attribute(String name, Type type) 
        throws LogicalException
    {
        super(name);
        this.type = new Type(type);
    }

    public Attribute(String name, Type type, Set<Modifier> modifiers) 
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

    public Type getType(String sent) 
    {
        return sent == "toString" ? this.type : null;
    }

    @Override
    public String toString() 
    {
        return "Attribute{" +
                "name='" + getName() + '\'' +
                ", type=" + type +
                ", modifiers=" + getModifiers() +
                '}';
    }

} // Attribute
