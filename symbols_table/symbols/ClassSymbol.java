package symbols_table.symbols;

import symbols_table.*;

public class ClassSymbol extends Symbol {
    // Atributos
    private ClassSymbol superclass;

    // Construtores
    public ClassSymbol(String name) {
        super(name);
    }

    public ClassSymbol(String name, ClassSymbol superclass) {
        super(name);
        this.superclass = superclass;
    }

    public ClassSymbol(ClassSymbol classSymbol) {
        super(classSymbol.getName());
        this.superclass = classSymbol.getSuperclass();
    }

    // Métodos
    public ClassSymbol getSuperclass() {
        return this.superclass;
    }
}
