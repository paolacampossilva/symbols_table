package tests;
import symbols_table.*;
import symbols_table.scope.*;
import symbols_table.symbols.*;
import java.util.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 * 
 *   TESTE 2 — Herança, Polimorfismo e Busca Qualificada
 *   Código fonte simulado no parser:
 * -------------------------------------------------------
 * public class Funcionario {
 * protected float salario;
 *   public void pagar() {}
 * public void pagar(int bonus) {} 
 * }
 *   public class Gerente extends Funcionario {
 * private static float acoes;
 * }
 * -------------------------------------------------------
 */

public class Test2 
{
    public static void main(String[] args) 
    {
        System.out.println("[INFO] ========================================");
        System.out.println("[INFO] TESTE 2 — Herança e Busca Qualificada");
        System.out.println("[INFO] ========================================");

        SymbolTable table = new SymbolTable();

        try {
            // 1. Classe Base: public class Funcionario
            ClassSymbol funcSym = table.addClass("Funcionario");
            funcSym.addModifier(Modifier.PUBLIC);
            
            ClassScope funcScope = new ClassScope(funcSym, table.getGlobalScope());
            table.openClass(funcScope);
            
            // protected float salario;
            Set<Modifier> modsProtected = new HashSet<>(Arrays.asList(Modifier.PROTECTED));
            table.define(new Attribute("salario", new Type(Type.PrimitiveType.FLOAT), modsProtected));
            
            // public void pagar()
            Method pagar1 = table.addMethod("pagar", new Type(Type.PrimitiveType.VOID), new Parameter[0]);
            pagar1.addModifier(Modifier.PUBLIC);
            
            // public void pagar(int bonus) - Polimorfismo
            Parameter[] paramBonus = { new Parameter("bonus", new Type(Type.PrimitiveType.INT)) };
            Method pagar2 = table.addMethod("pagar", new Type(Type.PrimitiveType.VOID), paramBonus);
            pagar2.addModifier(Modifier.PUBLIC);
            
            table.closeClass();

            // 2. Classe Filha: public class Gerente extends Funcionario
            System.out.println("[INFO] --- Declarando Gerente extends Funcionario ---");
            ClassSymbol gerSym = table.addClass("Gerente");
            gerSym.addModifier(Modifier.PUBLIC);
            
            ClassScope gerScope = new ClassScope(gerSym, funcScope); 
            table.openClass(gerScope);

            // private static float acoes;
            Set<Modifier> modsPrivateStatic = new HashSet<>(Arrays.asList(Modifier.PRIVATE, Modifier.STATIC));
            table.define(new Attribute("acoes", new Type(Type.PrimitiveType.FLOAT), modsPrivateStatic));
            
            System.out.println("[PASS] Gerente herdou 'salario' protegido? " + table.findSymbol("salario"));
            table.closeClass();

            // 3. Buscas Qualificadas
            System.out.println("\n[INFO] --- Buscas Qualificadas e Métodos ---");
            System.out.println("[PASS] Qualificado 'Funcionario.salario': " + table.resolveQualified("Funcionario.salario"));
            System.out.println("[PASS] Qualificado 'Gerente.acoes': " + table.resolveQualified("Gerente.acoes"));
            
            // 4. Resolve chamada de método sobrecarregado
            List<Type> argsInt = new ArrayList<>();
            argsInt.add(new Type(Type.PrimitiveType.INT));
            System.out.println("[PASS] Busca exata pagar(int): " + table.findMethod("pagar", argsInt, funcSym));

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("[INFO] ========================================\n");
    }
} // Test2
