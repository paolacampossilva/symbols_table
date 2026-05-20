package symbols_table;

public abstract class Symbol {
    public String name;
    public Modifier[] modifiers;

    Symbol() {
    };

    Symbol(String name, Modifier[] modifiers) {
        this.name = name;
        for (int i = 0; i < modifiers.length; ++i)
            this.modifiers[i] = modifiers[i];
    }

    Symbol(Symbol symbol) {
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
            if (!modifiers[i].isValid())
                return false;
        }

        return true;
    }

}
