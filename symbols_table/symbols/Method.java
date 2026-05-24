package symbols_table.symbols;

import symbols_table.*;

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

    public Method(String name, Type returnType, Parameter[] parameters, Set<Modifier> modifiers) {
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
        return "Method{" + getName() + " : " + returnType + "}";
    }
    
} // Method
