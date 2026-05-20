package symbols_table;

import java.util.List;

public class SymbolTable 
{
    public Class addClass(String name)
        throws DuplicateSymbolException
    {
        // insert your code here
        return null;
    }

    public Class findClass(String name)
    {
        // insert your code here
        return null;
    }

    public void openClass(Class clazz)
    {
        // insert your code here
    }

    public void closeClass()
    {
        // insert your code here
    }

    public Method addMethod(String name, ParameterList parameters)
        throws DuplicateSymbolException
    {
        // insert your code here
        return null;
    }

    public List<Method> findMethods(String name, Class clazz)
    {
        // insert your code here
        return null;
    }

    public Method findMethod(String name, List<Type> argumentTypes, Class clazz)
    {
        // insert your code here
        return null;
    }

    public void openMethod(Method method)
    {
        // insert your code here
    }

    public void closeMethod()
    {
        // insert your code here
    }

    public void openBlock()
    {
        // insert your code here

    }

    public void closeBlock()
    {
        // insert your code here

    }

    public void print()
    {
        // insert your code here

    }

    private GlobalScope globalScope;
    private Class currentClass;

}
