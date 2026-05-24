package symbols_table.symbols;

import symbols_table.*;

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
    {
        super(name);
        this.type = new Type(type);
    }

    public Attribute(String name, Type type, Set<Modifier> modifiers) 
    {
        super(name, modifiers);
        this.type = new Type(type);
    }

    // Métodos
    public Type getType() 
    {
        return new Type(this.type); 
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
