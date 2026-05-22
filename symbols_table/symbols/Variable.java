package symbols_table.symbols;

import java.util.Set;

import symbols_table.*;

public class Variable extends Symbol {
    // Atributo
    private Type type;

    // Construtores
    public Variable(String name, Type type) {
        super(name);

        this.type = new Type(type);
    }

    public Variable(String name, Type type, Set<Modifier> modifiers) {
        super(name, modifiers);

        this.type = new Type(type);
    }

    // Métodos
    public Type getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "name='" + getName() + '\'' +
                ", type=" + type +
                ", modifiers=" + getModifiers() +
                '}';
    }
}
