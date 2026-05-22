package symbols_table.symbols;

import java.util.Set;

import symbols_table.*;

public class Attribute extends Symbol {
    // Atributo
    private Type type;

    // Construtores
    public Attribute(String name, Type type) {
        super(name);

        this.type = new Type(type);
    }

    public Attribute(String name, Type type, Set<Modifier> modifiers) {
        super(name, modifiers);

        this.type = new Type(type);
    }

    // Métodos
    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + getName() + '\'' +
                ", type=" + type +
                ", modifiers=" + getModifiers() +
                '}';
    }
}
