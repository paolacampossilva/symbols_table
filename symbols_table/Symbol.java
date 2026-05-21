package symbols_table;

import symbols_table.scope.*;
import symbols_table.symbols.*;;

public abstract class Symbol {
    public String name;
    public Modifier[] modifiers;

    public Symbol() {
    };

    public Symbol(String name, Modifier[] modifiers) {
        this.name = name;
        for (int i = 0; i < modifiers.length; ++i)
            this.modifiers[i] = modifiers[i];
    }

    public Symbol(Symbol symbol) {
        this.name = symbol.name;
        for (int i = 0; i < modifiers.length; ++i)
            this.modifiers[i] = symbol.modifiers[i];
    }

    public boolean isValidName(String name) {
        return !(name.contains(" "));
    }

    public boolean isValidModifier() {
        int i;
        for (i = 0; i < modifiers.length; ++i) {
            if (true)
                return false;
        }

        return true;
    }

}
