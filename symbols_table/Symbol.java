package symbols_table;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Symbol {
    private String name;
    private Set<Modifier> modifiers;

    // Construtor
    public Symbol(String name) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Error: invalid symbol name " + name);
        }
        this.name = name;
        this.modifiers = new HashSet<>();
    }

    public Symbol(String name, Set<Modifier> modifiers) {
        if (!isValidName(name)) {
            throw new IllegalArgumentException("Error: invalid symbol name " + name);
        }
        this.name = name;
        if (modifiers != null)
            this.modifiers = new HashSet<>(modifiers);
        // Caso aconteça um erro, ou algo inesperado, a coleção é inciada vazia
        else
            this.modifiers = new HashSet<>();
    }

    // Cópia
    public Symbol(Symbol symbol) {
        this.name = symbol.name;
        this.modifiers = new HashSet<>(symbol.modifiers);
    }

    // Acessos ao atributos privados
    public String getName() {
        return this.name;
    }

    public Set<Modifier> getModifiers() {
        return Collections.unmodifiableSet(this.modifiers);
    }

    // Método de adição de novos modificadores
    public void addModifier(Modifier modifier) {
        if (modifier != null)
            this.modifiers.add(modifier);
    }

    // Verificadores
    public boolean hasModifier(Modifier modifier) {
        return this.modifiers.contains(modifier);
    }

    public static boolean isValidName(String name) {
        return name != null && !name.contains(" ") && !name.contains(".");
    }

    // Verificadores para Scope
    public boolean hasPrivate() {
        return hasModifier(Modifier.PRIVATE);
    }

    public boolean hasProtected() {
        return hasModifier(Modifier.PROTECTED);
    }

    public boolean hasPublic() {
        return hasModifier(Modifier.PUBLIC);
    }

    public boolean hasStatic() {
        return hasModifier(Modifier.STATIC);
    }

    public boolean hasAbstract() {
        return hasModifier(Modifier.ABSTRACT);
    }

    public boolean hasFinal() {
        return hasModifier(Modifier.FINAL);
    }

    @Override
    public String toString() {
        return "Symbol{name =" + name + "', modifiers=" + modifiers + "}";
    }

} // Symbol