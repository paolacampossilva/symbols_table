package symbols_table.scope;

import symbols_table.*;
import symbols_table.symbols.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class ClassScope 
    implements Scope 
{
    // Atributos
    private final Scope parent;
    private ClassSymbol classSymbol;
    private TreeMap<String, Attribute> attributes;
    private TreeMap<String, Method> methods;
    private TreeMap<String, Constructor> constructors;

    // Construtores
    public ClassScope(ClassSymbol classSymbol, Scope parent) 
    {
        this.classSymbol = classSymbol;
        this.parent = parent;

        this.attributes = new TreeMap<>();
        this.methods = new TreeMap<>();
        this.constructors = new TreeMap<>();
    }

    // Métodos
    public ClassSymbol getClassSymbol() 
    {
        return classSymbol;
    }

    Attribute getAttribute(String attributeName) 
    {
        return attributes.get(attributeName);
    }

    Method getMethod(String methodName) 
    {
        return methods.get(methodName);
    }

    public List<Method> getAllMethods() 
    {
        return new ArrayList<>(methods.values());
    }

    Constructor getConstructor(String constructorName) 
    {
        return constructors.get(constructorName);
    }

    @Override
    public Scope getParent() 
    {
        return parent;
    }

    @Override
    public void define(Symbol symbol) 
        throws DuplicateSymbolException, LogicalException 
    {
        if (symbol instanceof Attribute) {
            if (attributes.containsKey(symbol.getName()))
                throw new DuplicateSymbolException(symbol.getName());
            
            attributes.put(symbol.getName(), (Attribute) symbol);
        } 
        else if (symbol instanceof Constructor) {
            if (constructors.containsKey(symbol.getName()))
                throw new DuplicateSymbolException(symbol.getName());

            constructors.put(symbol.getName(), (Constructor) symbol);
        } 
        else if (symbol instanceof Method) {
            Method m = (Method) symbol;

            String signature = generateMethodSignature(m.getName(), m.getParameters());

            if (methods.containsKey(signature))
                throw new DuplicateSymbolException(signature);
            
            methods.put(signature, m);
        } 
        else
            throw new LogicalException("ClassScope", symbol.getClass().getName());
    }

    @Override
    public Symbol search(String name) 
    {
        if (attributes.containsKey(name))
            return attributes.get(name);

        if (constructors.containsKey(name))
            return constructors.get(name);

        for (Method m : methods.values())
            if (m.getName().equals(name))
                return m;

        if (parent != null)
            return parent.search(name);

        return null;
    }

    public static String generateMethodSignature(String name, Parameter[] parameters)
        throws LogicalException
    {
        if (parameters == null || parameters.length == 0)
            return name + "()";

        StringBuilder sig = new StringBuilder(name + "(");

        for (int i = 0; i < parameters.length; ++i) {
            if (parameters[i].getType() != null)
                sig.append(parameters[i].getType().toString());
            else
                sig.append("unknown");

            if (i < parameters.length - 1)
                sig.append(",");
        }
        sig.append(")");

        return sig.toString();
    }
    
} // ClassScope
