package symbols_table.scope;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class DuplicateSymbolException extends Exception 
{   
    // Construtores
    public DuplicateSymbolException() 
    {
        super("Error: duplicate Symbol on the same Scope");
    }

    public DuplicateSymbolException(String name) 
    {
        super("Error: The Symbol '" + name + "' was already declared in this Scope");
    }

} // DuplicateSymbolException
