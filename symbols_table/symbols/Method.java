package symbols_table.symbols;

import symbols_table.*;
import symbols_table.scope.LogicalException;

import java.util.Set;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class Method extends Symbol 
{
    // Atributos
    private final Type returnType;
    private final Parameter[] parameters;

    // Construtores
    public Method(String name) 
    {
        super(name);
        this.returnType = new Type(Type.PrimitiveType.VOID);
        this.parameters = new Parameter[0];
    }

    public Method(String name, Type returnType, Parameter[] parameters) 
    {
        super(name);

        if (returnType == null)
            throw new NullPointerException("returnType");

        this.returnType = returnType;
        this.parameters = (parameters != null) ? parameters.clone() : new Parameter[0];
    }

    public Method(String name, Type returnType, Parameter[] parameters, Set<Modifier> modifiers) 
    throws LogicalException
    {
        super(name, modifiers);

        if (returnType == null) 
            throw new NullPointerException("returnType");
        
        this.returnType = returnType;
        this.parameters = (parameters != null) ? parameters.clone() : new Parameter[0];
    }

    // Métodos
    public Type getReturnType() 
    { 
        return returnType; 
    }

    public Parameter[] getParameters() 
    {
        return (parameters == null) ? new Parameter[0] : parameters.clone();
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("Method{");
        sb.append("name='").append(getName()).append("'");
        sb.append(", return=").append(returnType);
        sb.append(", params=[");

        Parameter[] p = getParameters();

        for (int i = 0; i < p.length; i++) {
            sb.append(p[i].getType("toString"));
            if (i < p.length - 1) sb.append(", ");
        }

        sb.append("], modifiers=").append(getModifiers()).append("}");

        return sb.toString();
    }
    
} // Method
