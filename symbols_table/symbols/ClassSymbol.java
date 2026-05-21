package symbols_table.symbols;

import symbols_table.scope.*;

import symbols_table.*;

import java.util.TreeMap;

public class ClassSymbol extends Symbol implements Scope {
    // Atributos
    public ClassSymbol superclass;

    private TreeMap<String, Attribute> attributes;
    private TreeMap<String, Method> methods;
    private TreeMap<String, Constructor> constructors;

    // Construtores
    public ClassSymbol() {
        this.attributes = new TreeMap<>();
        this.methods = new TreeMap<>();
        this.constructors = new TreeMap<>();
    }

    public ClassSymbol(String name) {
        this.name = name;

        this.attributes = new TreeMap<>();
        this.methods = new TreeMap<>();
        this.constructors = new TreeMap<>();
    }

    public ClassSymbol(String name, ClassSymbol superclass) {
        this.name = name;
        this.superclass = superclass;

        this.attributes = new TreeMap<>();
        this.methods = new TreeMap<>();
        this.constructors = new TreeMap<>();
    }

    Attribute getAttribute(String attributeName) {
        return attributes.get(attributeName);
    }

    Method getMethod(String methodName) {
        return methods.get(methodName);
    }

    Constructor getConstructor(String constructorName) {
        return constructors.get(constructorName);
    }


}
