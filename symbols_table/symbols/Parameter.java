package symbols_table.symbols;

import java.util.Set;
import symbols_table.*;

public class Parameter extends Symbol {

    private Type type;

    public Parameter(String name, Type type) {
        super(name);
        this.type = type;
    }

    public Parameter(String name, Type type, Set<Modifier> modifiers) {
        super(name, modifiers);
        this.type = type;
    }

    // Métodos
    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + getName() + '\'' +
                ", type=" + type +
                ", modifiers=" + getModifiers() +
                '}';
    }
}