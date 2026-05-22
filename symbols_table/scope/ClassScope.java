package symbols_table.scope;

import symbols_table.symbols.*;

import symbols_table.*;

import java.util.TreeMap;

public class ClassScope implements Scope {
    // Atributos
    private final Scope parent;
    private ClassSymbol classSymbol;

    private TreeMap<String, Attribute> attributes;
    private TreeMap<String, Method> methods;
    private TreeMap<String, Constructor> constructors;

    // Construtores
    public ClassScope(ClassSymbol classSymbol, Scope parent) {
        this.classSymbol = classSymbol;
        this.parent = parent;

        this.attributes = new TreeMap<>();
        this.methods = new TreeMap<>();
        this.constructors = new TreeMap<>();
    }

    // Métodos
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
        return parent;
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
