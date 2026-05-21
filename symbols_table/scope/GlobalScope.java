package symbols_table.scope;

import symbols_table.symbols.*;

import symbols_table.*;

import java.util.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class GlobalScope implements Scope {
    // Atributos
    private TreeMap<String, ClassSymbol> classes;

    // Construtores
    public GlobalScope() {
        this.classes = new TreeMap<>();
    }

    // Métodos
    public ClassSymbol getClass(String name) {
        return classes.get(name);
    }

    public void addClass(ClassSymbol classSymbol) {
        if (this.getClass(classSymbol.getName()) == null)
            classes.put(classSymbol.getName(), classSymbol);
    }

    @Override
    public Scope getParent() {
        return null; 
    }

    @Override
    public Symbol search(String name) {
        if (classes.containsKey(name)) {
            return this.getClass(name);
        }
        
        // 2. Como não há escopo pai para delegar a busca (ele é o topo da hierarquia),
        // se o símbolo não foi encontrado até aqui, retornamos null garantindo 
        // que o identificador não existe na tabela.
        return null; 
    }
}
