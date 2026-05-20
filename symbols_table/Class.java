package symbols_table;

import java.util.TreeMap;

public class Class extends Symbol
    implements Scope
{
    public Class superclass;

    private TreeMap<String, Atribute> atributes;
    private TreeMap<String, Method> methods;
    private TreeMap<String, Constructor> constructors;
}
