package symbols_table.scope;

import symbols_table.symbols.*;
import symbols_table.*;
import java.util.TreeMap;

public class BlockScope implements Scope {
    private TreeMap<String, Variable> variables;
}
