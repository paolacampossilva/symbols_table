package symbols_table.symbols;

import symbols_table.scope.*;

import symbols_table.*;

import java.util.TreeMap;

public class ClassSymbol extends Symbol implements Scope {
    // Atributos
    private ClassSymbol superclass;
    private Scope globalScope;

    private TreeMap<String, Attribute> attributes;
    private TreeMap<String, Method> methods;
    private TreeMap<String, Constructor> constructors;

    // Construtores

    public ClassSymbol(String name, Scope globalScope) {
        super(name);

        this.globalScope = globalScope;

        this.attributes = new TreeMap<>();
        this.methods = new TreeMap<>();
        this.constructors = new TreeMap<>();
    }

    public ClassSymbol(String name, ClassSymbol superclass, Scope globalScope) {
        super(name);

        this.superclass = superclass;
        this.globalScope = globalScope;

        this.attributes = new TreeMap<>();
        this.methods = new TreeMap<>();
        this.constructors = new TreeMap<>();
    }

    public ClassSymbol(ClassSymbol classSymbol) {
        super(classSymbol.getName());
        
        this.superclass = classSymbol.getSuperclass();

        this.attributes = new TreeMap<>();
        this.methods = new TreeMap<>();
        this.constructors = new TreeMap<>();
    }

    // Métodos
    ClassSymbol getSuperclass() {
        return this.superclass;
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

    public void addAttribute(Attribute attribute) {
        attributes.put(attribute.getName(), attribute); 
    }

    public void addMethod(Method method) {
        methods.put(method.getName(), method);
    }

    public void addConstructor(Constructor constructor) {
        constructors.put(constructor.getName(), constructor);
    }

    @Override
    public Scope getParent() {
        if (this.superclass != null) {
            return this.superclass; 
        } 
        else {
            return this.globalScope; 
        }
    }

    @Override
    public Symbol search(String name) {
        if (attributes.containsKey(name)) 
            return this.getAttribute(name);

        if (methods.containsKey(name)) 
            return this.getMethod(name);

        if (constructors.containsKey(name)) 
            return this.getConstructor(name);

        Scope parent = getParent();
        if (parent != null) {
            return parent.search(name); 
        }

        return null; // Símbolo não encontrado
    }

}
