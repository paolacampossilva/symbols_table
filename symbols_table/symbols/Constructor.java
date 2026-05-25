package symbols_table.symbols;

import symbols_table.*;
import symbols_table.scope.LogicalException;

import java.util.Set;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class Constructor extends Symbol 
{   
    // Atributo
    private final Parameter[] parameters;

    // Construtores
    public Constructor(String name) 
    {
        super(name);
        this.parameters = new Parameter[0];
    }

    public Constructor(String name, Parameter[] parameters) 
    {
        super(name);
        this.parameters = (parameters != null) ? parameters.clone() : new Parameter[0];
    }

    public Constructor(String name, Set<Modifier> modifiers) 
        throws LogicalException
    {
        super(name, modifiers);
        this.parameters = new Parameter[0];
    }

    public Constructor(String name, Parameter[] parameters, Set<Modifier> modifiers) 
        throws LogicalException
    {
        super(name, modifiers);
        this.parameters = (parameters != null) ? parameters.clone() : new Parameter[0];
    }

    // Métodos
    public Set<Modifier> verifyModifier(Set<Modifier> modifiers)
    {   
        for (Modifier m : modifiers) {
            if (m.ordinal() > 2)
                return null; 
        }

        return modifiers;
    }

    public Parameter[] getParameters() 
    {
        return parameters.clone();
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder("Constructor{");

        sb.append("name='").append(getName()).append("'");
        sb.append(", params=[");

        Parameter[] p = getParameters();

        for (int i = 0; i < p.length; i++) {
            sb.append(p[i].getType("toString"));
            if (i < p.length - 1) sb.append(", ");
        }

        sb.append("], modifiers=").append(getModifiers()).append("}");

        return sb.toString();
    }
    
} // Constructor
