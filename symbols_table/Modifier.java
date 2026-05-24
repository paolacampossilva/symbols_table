package symbols_table;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public enum Modifier 
{
    PUBLIC, PROTECTED, PRIVATE, STATIC, ABSTRACT, FINAL;

    @Override
    public String toString() 
    {
        return name().toLowerCase();
    }

} // Modifier
