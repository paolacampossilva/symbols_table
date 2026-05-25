package tests;

import symbols_table.*;
import symbols_table.scope.*;
import symbols_table.symbols.*;

/**
 * TESTE 3 — Aninhamento Profundo e Fail-Safes Estruturais
 * Simula:
 * class Calculadora {
 * void processar(int a) {
 * { // Bloco 1
 * int temp;
 * { // Bloco 2 (Aninhado)
 * int x;
 * int temp; // ERRO: Duplicado no mesmo escopo/hierarquia local
 * }
 * }
 * }
 * }
 * 
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 * 
 */

public class Test3 
{
    public static void main(String[] args) 
    {
        System.out.println(" ========================================");
        System.out.println(" TEST 3 — Deep Nesting and Errors");
        System.out.println(" ========================================");

        SymbolTable table = new SymbolTable();

        try {
            ClassSymbol calcSym = table.addClass("Calculator");
            table.openClass(new ClassScope(calcSym, table.getGlobalScope()));

            // 1. Classe -> Método
            Parameter paramA = new Parameter("a", new Type(Type.PrimitiveType.INT));
            Method procMetodo = table.addMethod("process", new Type(Type.PrimitiveType.VOID),
                    new Parameter[] { paramA });
            table.openMethod(procMetodo);

            // 2. Método -> Bloco 1
            System.out.println(" --- Opening Block 1 ---");
            table.openBlock();
            table.define(new Variable("temp", new Type(Type.PrimitiveType.INT)));

            // 3. Bloco 1 -> Bloco 2 (Aninhado)
            System.out.println(" --- Opening Block 2 (Nested) ---");
            table.openBlock();
            table.define(new Variable("x", new Type(Type.PrimitiveType.INT)));

            System.out.println(" Search for 'x' (in Block 2): " + table.findSymbol("x"));
            System.out.println(" Search for 'temp' (goes up to Block 1): " + table.findSymbol("temp"));
            System.out.println(" Search 'a' (go up to the Method): " + table.findSymbol("a"));

            System.out.println("\n Table Status (Maximum Depth):");
            table.print();

            // 4. Testando Robustez (Fail-safes Semânticos)
            System.out.println("\n --- Testing Local Fail-safes ---");
            try {
                // Tenta definir 'x' de novo no mesmo Bloco 2
                table.define(new Variable("x", new Type(Type.PrimitiveType.FLOAT)));
            } catch (Exception e) {
                System.out.println(" Fail-safe (Duplicate variable): " + e.getMessage());
            }

            // 5. Fechando Escopos e verificando a destruição das variáveis
            table.closeBlock(); // Fecha Bloco 2
            System.out.println("\n --- Block 2 Closed ---");
            System.out.println(" was 'x' destroyed? " + (table.findSymbol("x") == null ? "Y" : "N"));

            table.closeBlock(); // Fecha Bloco 1
            table.closeMethod();
            table.closeClass();

            // 6. Fechar além do limite (Fail-safe da própria SymbolTable)
            System.out.println("\n --- Closing excess scopes (Shouldn't break the app) ---");
            table.closeBlock();
            table.closeMethod();
            table.closeClass();
            System.out.println(" It survived the excessive lockdown!");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(" ========================================\n");
    }
}
