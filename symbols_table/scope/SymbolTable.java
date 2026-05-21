package symbols_table.scope;

import symbols_table.symbols.*;

import symbols_table.*;

import java.util.List;

public class SymbolTable {
    public ClassSymbol addClass(String name)
            throws DuplicateSymbolException {
        // insert your code here
        return null;
    }

    public ClassSymbol findClass(String name) {
        // insert your code here
        return null;
    }

    public void openClass(ClassSymbol clazz) {
        // insert your code here
    }

    public void closeClass() {
        // insert your code here
    }

    public Method addMethod(String name, Parameter[] parameters)
            throws DuplicateSymbolException {
        // insert your code here
        return null;
    }

    public List<Method> findMethods(String name, ClassSymbol clazz) {
        // insert your code here
        return null;
    }

    public Method findMethod(String name, List<Type> argumentTypes, ClassSymbol clazz) {
        // insert your code here
        return null;
    }

    public void openMethod(Method method) {
        // insert your code here
    }

    public void closeMethod() {
        // insert your code here
    }

    public void openBlock() {
        // insert your code here

    }

    public void closeBlock() {
        // insert your code here

    }

    public void print() {
        // insert your code here

    }

    private GlobalScope globalScope;
    private ClassSymbol currentClass;
}
