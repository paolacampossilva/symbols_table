package symbols_table.scope;

import symbols_table.symbols.*;

import symbols_table.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class SymbolTable {

    private GlobalScope globalScope;
    private ClassSymbol currentClass;
    private Scope currentScope;

    // Construtor
    public SymbolTable() {
        globalScope = new GlobalScope();
        currentScope = globalScope;
        currentClass = null;
    }

    // to do
    public ClassSymbol addClass(String name) throws DuplicateSymbolException, LogicalException {
        ClassSymbol newClass = new ClassSymbol(name, globalScope);
        globalScope.define(newClass);
        return newClass;
    }

    public ClassSymbol findClass(String name) {
        Symbol sym = globalScope.search(name);
        if (sym instanceof ClassSymbol)
            return (ClassSymbol) sym;
        return null;
    }

    public void openClass(ClassSymbol clazz) {
        currentClass = clazz;
        currentScope = clazz;
    }

    public void closeClass() {
        if (currentClass != null)
            currentScope = this.currentScope.getParent();
        this.currentClass = null;
    }

    public Method addMethod(String name, Parameter[] parameters) throws DuplicateSymbolException, LogicalException {
        if (currentClass == null)
            throw new LogicalException(currentClass.getClass().getSimpleName());
        Method newMethod = new Method(name, parameters);
        currentClass.define(newMethod);
        return newMethod;
    }

    public void openMethod(Method method) throws DuplicateSymbolException, LogicalException {
        MethodScope methodScope = new MethodScope(currentClass);
        if (method.getParameters() != null) {
            for (Parameter p : method.getParameters())
                methodScope.define(p);
        }
        currentScope = methodScope;
    }

    public void closeMethod() {
        if (currentScope != null) {
            currentScope = currentScope.getParent();
        }
    }

    public void openBlock() {
        BlockScope newBlock = new BlockScope(currentScope);
        currentScope = newBlock;
    }

    public void closeBlock() {
        if (currentScope != null)
            currentScope = currentScope.getParent();

    }

    public Symbol findSymbol(String name) {
        if (currentScope != null)
            return currentScope.search(name);
        return null;
    }

    public void print() {
        System.out.println("Estado Atual da Tabela de Símbolos" + "\n" + "Escopo Global: " + globalScope + "\n"
                + "Classe Corrente: ");

        if (currentClass != null)
            System.out.println(currentClass.getName() + "\n");
        else
            System.out.println("nenhuma");
        System.out.println("Escopo Corrente: " + currentScope.getClass().getSimpleName());
    }

}
