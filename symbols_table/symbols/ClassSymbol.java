package symbols_table.symbols;

import symbols_table.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class ClassSymbol extends Symbol 
{
    // Atributos
    private ClassSymbol superclass;

    // Construtores
    public ClassSymbol(String name) 
    {
        super(name);
    }

    public ClassSymbol(String name, ClassSymbol superclass) 
    {
        super(name);
        this.superclass = superclass;
    }

    public ClassSymbol(ClassSymbol classSymbol) 
    {
        super(classSymbol.getName(), classSymbol.getModifiers());
        ClassSymbol superclass = classSymbol.getSuperclass();
        this.superclass = (superclass != null) ? new ClassSymbol(superclass) : null; 
    }

    // Métodos
    public ClassSymbol getSuperclass() 
    {
        return this.superclass;
    }

    @Override
    public String toString() 
    {
        String sc = (superclass != null) ? superclass.getName() : "none";
        return "ClassSymbol{name='" + getName() + "', superclass=" + sc + ", modifiers=" + getModifiers() + "}";
    }
    
} // ClassSymbol
