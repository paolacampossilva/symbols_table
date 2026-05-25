package symbols_table;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public abstract class Symbol 
{
    // Atributos
    private String name;
    private Set<Modifier> modifiers;

    // Construtores
    public Symbol(String name) 
    {
        if (!isValidName(name)) 
            throw new IllegalArgumentException("Error: invalid symbol name " + name);
            
        this.name = name;
        this.modifiers = new HashSet<>();
    }

    public Symbol(String name, Set<Modifier> modifiers) 
    {
        if (!isValidName(name))
            throw new IllegalArgumentException("Invalid name: " + name);

        if (modifiers == null)
            throw new IllegalArgumentException("modifiers cannot be null");
    
        this.name = name;
        this.modifiers = new HashSet<>(modifiers);
    }

    public Symbol(Symbol symbol) 
    {
        this.name = symbol.name;
        this.modifiers = new HashSet<>(symbol.modifiers);
    }

    // Métodos
    public String getName() 
    {
        return this.name;
    }

    public Set<Modifier> getModifiers() 
    {
        return Collections.unmodifiableSet(this.modifiers);
    }

    public void addModifier(Modifier modifier) 
    {
        if (modifier != null)
            this.modifiers.add(modifier);
    }

    public boolean hasModifier(Modifier modifier) 
    {
        return this.modifiers.contains(modifier);
    }

    public static boolean isValidName(String name) 
    {
        return name != null && !name.contains(" ") && !name.contains(".");
    }

    public boolean hasPrivate() 
    {
        return hasModifier(Modifier.PRIVATE);
    }

    public boolean hasProtected() 
    {
        return hasModifier(Modifier.PROTECTED);
    }

    public boolean hasPublic() 
    {
        return hasModifier(Modifier.PUBLIC);
    }

    public boolean hasStatic() 
    {
        return hasModifier(Modifier.STATIC);
    }

    public boolean hasAbstract() 
    {
        return hasModifier(Modifier.ABSTRACT);
    }

    public boolean hasFinal() 
    {
        return hasModifier(Modifier.FINAL);
    }

    @Override
    public String toString() 
    {
        return "Symbol{name='" + getName() + "', modifiers=" + getModifiers() + "}";
    }

} // Symbol
