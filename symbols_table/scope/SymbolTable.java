package symbols_table.scope;

import symbols_table.*;
import symbols_table.symbols.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 */

public class SymbolTable 
{
    // Atributos
    private GlobalScope globalScope;
    private ClassScope currentClass;
    private Scope currentScope;
    private final Map<String, ClassScope> classScopes = new HashMap<>();

    // Construtor
    public SymbolTable() 
    {
        globalScope = new GlobalScope();
        currentScope = globalScope;
        currentClass = null;
    }

    // Métodos
    public void define(Symbol symbol) 
        throws DuplicateSymbolException, LogicalException 
    {
        if (currentScope == null)
            throw new IllegalStateException("No open scope");

        currentScope.define(symbol);
    }

    public GlobalScope getGlobalScope() 
    {
        return globalScope;
    }

    public Symbol resolveQualified(String qualifiedName) 
    {
        if (qualifiedName == null || !qualifiedName.contains("."))
            throw new IllegalArgumentException("Invalid qualified name: " + qualifiedName);
    
        int dot = qualifiedName.indexOf('.');
        String qualifier = qualifiedName.substring(0, dot);
        String member = qualifiedName.substring(dot + 1);
    
        ClassSymbol cls = findClass(qualifier);
        if (cls == null) 
            return null;
    
        ClassScope scope = classScopes.get(qualifier); 
        if (scope == null) 
            return null;
    
        return scope.search(member);
    }

    public ClassSymbol addClass(String name) 
        throws DuplicateSymbolException, LogicalException 
    {
        ClassSymbol newClass = new ClassSymbol(name);
        globalScope.define(newClass);

        return newClass;
    }

    public ClassSymbol findClass(String name) 
    {
        Symbol sym = globalScope.search(name);

        if (sym instanceof ClassSymbol)
            return (ClassSymbol) sym;

        return null;
    }

    public void openClass(ClassScope clazz) 
    {
        classScopes.put(clazz.getClassSymbol().getName(), clazz);
        currentClass = clazz;
        currentScope = clazz;
    }

    public void closeClass() 
    {
        if (currentClass != null)
            currentScope = globalScope; 
        
        currentClass = null;
    }

    public Method addMethod(String name, Type returnType, Parameter[] parameters)
        throws DuplicateSymbolException, LogicalException 
    {
        if (currentClass == null)
            throw new LogicalException("ClassScope", "Method");

        Method newMethod = new Method(name, returnType, parameters);
        
        currentClass.define(newMethod); 
        
        return newMethod;
    }

    public List<Method> findMethods(String name, ClassSymbol clazz) 
    {
        List<Method> result = new ArrayList<>();

        if (clazz == null) 
            return result;

        ClassScope scope = classScopes.get(clazz.getName());
        if (scope == null) 
            return result;

        for (Method m : scope.getAllMethods())
            if (m.getName().equals(name))
                result.add(m);
        
        return result;
    }

    public Method findMethod(String name, List<Type> argTypes, ClassSymbol clazz) 
    {
        if (clazz == null) 
            return null;

        ClassScope scope = classScopes.get(clazz.getName());
        if (scope == null) 
            return null;

        for (Method m : scope.getAllMethods()) {
            if (!m.getName().equals(name)) 
                continue;
            
            Parameter[] params = m.getParameters();
            if (params.length != argTypes.size()) 
                continue;
            
            boolean match = true;
            
            for (int i = 0; i < params.length; i++) {
                if (!params[i].getType().isEqualTo(argTypes.get(i))) { 
                    match = false; break; 
                }
            }

            if (match) 
                return m;
        }

        return null;
    }

    public void openMethod(Method method) 
        throws DuplicateSymbolException, LogicalException 
    {
        if (currentClass == null)
            throw new LogicalException("ClassScope", "MethodScope");

        MethodScope methodScope = new MethodScope(currentClass);
        if (method.getParameters() != null) {
            for (Parameter p : method.getParameters())
                methodScope.define(p);
        }

        currentScope = methodScope;
    }

    public void closeMethod() 
    {
        while (currentScope != null && !(currentScope instanceof ClassScope))
            currentScope = currentScope.getParent();
    }

    public void openBlock() 
    {
        BlockScope newBlock = new BlockScope(currentScope);
        currentScope = newBlock;
    }

    public void closeBlock() 
    {
        if (currentScope != null)
            currentScope = currentScope.getParent();

    }

    public Symbol findSymbol(String name) 
    {
        if (currentScope != null)
            return currentScope.search(name);

        return null;
    }

    public void print() 
    {
        String cls = (currentClass != null && currentClass.getClassSymbol() != null) ? currentClass.getClassSymbol().getName() : "none";
        
        System.out.println("[INFO]  --- Symbol Table State ---");
        System.out.println("[INFO]  global scope : " + globalScope);
        System.out.println("[INFO]  current class: " + cls);
        System.out.println("[INFO]  current scope: " + currentScope.getClass().getSimpleName());
        System.out.println("[INFO]  ----------------------------");
    }

} // SymbolTable
