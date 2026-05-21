package symbols_table.scope;

import symbols_table.symbols.*;

import symbols_table.*;

import java.util.*;

/**
 * @author Paola Campos da Silca
 * @author João Pedro Huppes Arenales
 */

public class GlobalScope
        implements Scope {
    private TreeMap<String, ClassSymbol> classes;
}
