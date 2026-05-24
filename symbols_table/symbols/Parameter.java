package symbols_table.symbols;

import symbols_table.*;

import java.util.Set;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class Parameter extends Symbol 
{
    // Atributo
    private Type type;

    //Construtores
    public Parameter(String name, Type type) 
    {
        super(name);
        this.type = (type != null) ? new Type(type) : null; 
    }
    
    public Parameter(String name, Type type, Set<Modifier> modifiers) 
    {
        super(name, modifiers);
        this.type = (type != null) ? new Type(type) : null;
    }

    // Métodos
    public Type getType() 
    {
        return (this.type != null) ? new Type(this.type) : null;
    }

    @Override
    public String toString() 
    {
        return "Parameter{" +
                "name='" + getName() + '\'' +
                ", type=" + type +
                ", modifiers=" + getModifiers() +
                '}';
    }

} // Parameter
