package symbols_table;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Symbol {
    private String name;
    private Set<Modifier> modifiers;

    // Construtor Default; Inicialização da coleção vazia (evita
    // NullPointerException)
    public Symbol() {
        this.modifiers = new HashSet<>();
    };

    // Construtor
    public Symbol(String name, Set<Modifier> modifiers) {
        this.name = name;
        if (modifiers != null)
            this.modifiers = modifiers;
        // Caso aconteça um erro, ou algo inesperado, a coleção é inciada vazia
        else
            this.modifiers = new HashSet<>();
    }

    // Cópia
    public Symbol(Symbol symbol) {
        if (symbol != null) {
            this.name = symbol.name;
            this.modifiers = new HashSet<>(symbol.modifiers);
        } else
            this.modifiers = new HashSet<>();
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

    public boolean isValidName(String name) {
        if (name != null) {
            if (!name.contains(" ") || !name.contains(".")) {
                return true;
            }
        }
        return false;
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