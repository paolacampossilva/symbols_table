package symbols_table.scope;

import symbols_table.symbols.*;

import java.util.ArrayList;
import java.util.List;

import symbols_table.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class SymbolTable {

    private GlobalScope globalScope;
    private ClassScope currentClass;
    private Scope currentScope;
    private List<Method> allMethods = new ArrayList<>();

    // Construtor
    public SymbolTable() {
        globalScope = new GlobalScope();
        currentScope = globalScope;
        currentClass = null;
    }

    public ClassSymbol addClass(String name) throws DuplicateSymbolException, LogicalException {
        ClassSymbol newClass = new ClassSymbol(name);
        globalScope.define(newClass);
        return newClass;
    }

    public ClassSymbol findClass(String name) {
        Symbol sym = globalScope.search(name);
        if (sym instanceof ClassSymbol)
            return (ClassSymbol) sym;
        return null;
    }

    public void openClass(ClassScope clazz) {
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
            throw new LogicalException("Method", "ClassScope");

        for (Method m : allMethods) {
            if (m.getName().equals(name))
                if (hasSameParameters(m.getParameters(), parameters))
                    throw new DuplicateSymbolException();
        }

        Method newMethod = new Method(name, parameters);
        try {
            currentClass.define(newMethod);
        } catch (DuplicateSymbolException e) {
        }

        allMethods.add(newMethod);
        return newMethod;
    }

    public List<Method> findMethods(String name, ClassSymbol clazz) {
        List<Method> result = new ArrayList<>();
        for (Method m : allMethods)
            if (m.getName().equals(name))
                result.add(m);

        return result;
    }

    public Method findMethod(String name, List<Type> argumTypes, ClassSymbol clazz) {
        for (Method m : allMethods) {
            if (m.getName().equals(name)) {
                Parameter[] params = m.getParameters();
                if (params.length != argumTypes.size())
                    continue;

                boolean match = true;
                for (int i = 0; i < params.length; ++i)
                    if (!params[i].getType().isEqualTo(argumTypes.get(i))) {
                        match = false;
                        break;
                    }

                if (match)
                    return m;
            }
        }
        return null;
    }

    public boolean hasSameParameters(Parameter[] p1, Parameter[] p2) {

        if (p1 == null && p2 == null)
            return true;
        if (p1 == null || p2 == null)
            return false;
        if (p1.length != p2.length)
            return false;
        for (int i = 0; i < p1.length; ++i)
            if (!p1[i].getType().isEqualTo((p2[i].getType())))
                return false;

        return true;
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

        if (currentClass != null && currentClass.getClassSymbol() != null)
            System.out.println(currentClass.getClassSymbol().getName() + "\n");
        else
            System.out.println("nenhuma\n");
        System.out.println("Escopo Corrente: " + currentScope.getClass().getSimpleName() + "\n");
    }

}
