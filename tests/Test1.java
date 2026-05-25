package tests;

import symbols_table.*;
import symbols_table.scope.*;
import symbols_table.symbols.*;

import java.util.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 * 
 *         TESTE 1 — Escopos Básicos, Construtores e Modificadores
 *         Código fonte simulado no parser:
 *         -------------------------------------------------------
 *         public class Carro {
 *             private int ano;
 * 
 *             public Carro() {}
 * 
 *             public void ligar() {
 *                 int chave;
 *           }
 *         }
 *         -------------------------------------------------------
 */

public class Test1 
{
    public static void main(String[] args) 
    {
        System.out.println(" ========================================");
        System.out.println(" TEST 1 — Basic Scopes and Modifiers");
        System.out.println(" ========================================");

        SymbolTable table = new SymbolTable();

        try {
            // 1. Declarando a Classe (public class Car)
            ClassSymbol carroSym = table.addClass("Car");
            carroSym.addModifier(Modifier.PUBLIC); // Aplicando modificador explicitamente

            ClassScope carroScope = new ClassScope(carroSym, table.getGlobalScope());
            table.openClass(carroScope);

            // 2. Definindo Atributo (private int year)
            Set<Modifier> modsPrivado = new HashSet<>(Arrays.asList(Modifier.PRIVATE));
            Attribute ano = new Attribute("year", new Type(Type.PrimitiveType.INT), modsPrivado);
            table.define(ano);

            // 3. Definindo Construtor (public Carro())
            Set<Modifier> modsPublico = new HashSet<>(Arrays.asList(Modifier.PUBLIC));
            Constructor construtor = new Constructor("Car", modsPublico);
            table.define(construtor);

            // 4. Declarando Método (public void turn_on())
            Method ligarMetodo = table.addMethod("turn_on", new Type(Type.PrimitiveType.VOID), new Parameter[0]);
            ligarMetodo.addModifier(Modifier.PUBLIC);
            table.openMethod(ligarMetodo);

            // 5. Definindo Variável Local (variáveis locais usam o escopo de bloco/método)
            Variable chave = new Variable("key", new Type(Type.PrimitiveType.INT));
            table.define(chave);

            System.out.println("\n --- Performing Searches ---");
            System.out.println(" Search 'key' (local in the method): " + table.findSymbol("key"));
            System.out.println(" Search 'year' (up to class): " + table.findSymbol("year"));

            table.closeMethod();
            table.closeClass();

            System.out.println("\n --- Global Searchs ---");
            System.out.println(" findClass('Car'): " + table.findClass("Car"));
            System.out.println(" findMethods('turn_on'): " + table.findMethods("turn_on", carroSym));

            // 6. Testando Robustez
            System.out.println("\n --- Testing Fail-safes ---");
            try {
                table.addClass("Car"); // Tentativa de duplicar classe no GlobalScope
            } catch (DuplicateSymbolException e) {
                System.out.println(" Fail-safe (Duplicate class blocked): " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(" ========================================\n");
    }
} // Test 1
