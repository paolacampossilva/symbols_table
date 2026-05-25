package tests;

import symbols_table.*;
import symbols_table.scope.*;
import symbols_table.symbols.*;

import java.util.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 * 
 *         TESTE 2 — Herança, Polimorfismo e Busca Qualificada
 *         Código fonte simulado no parser:
 *         -------------------------------------------------------
 *         public class Funcionario {
 *         protected float salario;
 *         public void pagar() {}
 *         public void pagar(int bonus) {}
 *         }
 *         public class Gerente extends Funcionario {
 *         private static float acoes;
 *         }
 *         -------------------------------------------------------
 */

public class Test2 {
    public static void main(String[] args) {
        System.out.println(" ========================================");
        System.out.println(" TEST 2 — Inheritance and Qualified Search");
        System.out.println(" ========================================");

        SymbolTable table = new SymbolTable();

        try {
            // 1. Classe Base: public class Employee
            ClassSymbol funcSym = table.addClass("Employee");
            funcSym.addModifier(Modifier.PUBLIC);

            ClassScope funcScope = new ClassScope(funcSym, table.getGlobalScope());
            table.openClass(funcScope);

            // protected float wage;
            Set<Modifier> modsProtected = new HashSet<>(Arrays.asList(Modifier.PROTECTED));
            table.define(new Attribute("wage", new Type(Type.PrimitiveType.FLOAT), modsProtected));

            // public void pay()
            Method pagar1 = table.addMethod("pay", new Type(Type.PrimitiveType.VOID), new Parameter[0]);
            pagar1.addModifier(Modifier.PUBLIC);

            // public void pagar(int bonus) - Polimorfismo
            Parameter[] paramBonus = { new Parameter("bonus", new Type(Type.PrimitiveType.INT)) };
            Method pagar2 = table.addMethod("pay", new Type(Type.PrimitiveType.VOID), paramBonus);
            pagar2.addModifier(Modifier.PUBLIC);

            table.closeClass();

            // 2. Classe Filha: public class Gerente extends Funcionario
            System.out.println(" --- Declaring Manager extends Employee ---");
            ClassSymbol gerSym = table.addClass("Manager");
            gerSym.addModifier(Modifier.PUBLIC);

            ClassScope gerScope = new ClassScope(gerSym, funcScope);
            table.openClass(gerScope);

            // private static float acoes;
            Set<Modifier> modsPrivateStatic = new HashSet<>(Arrays.asList(Modifier.PRIVATE, Modifier.STATIC));
            table.define(new Attribute("actions", new Type(Type.PrimitiveType.FLOAT), modsPrivateStatic));

            System.out.println(" Manager inherited protected 'salary'? " + table.findSymbol("salary"));
            table.closeClass();

            // 3. Buscas Qualificadas
            System.out.println("\n --- Qualified Searches and Methods ---");
            System.out.println(" Qualified 'Employee.salary': " + table.resolveQualified("Employee.salary"));
            System.out.println(" Qualified 'Manager.actions': " + table.resolveQualified("Manager.actions"));

            // 4. Resolve chamada de método sobrecarregado
            List<Type> argsInt = new ArrayList<>();
            argsInt.add(new Type(Type.PrimitiveType.INT));
            System.out.println(" Exact search pay(int): " + table.findMethod("pay", argsInt, funcSym));

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(" ========================================\n");
    }
} // Test2
