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
        System.out.println("[INFO] ========================================");
        System.out.println("[INFO] TESTE 3 — Aninhamento Profundo e Erros");
        System.out.println("[INFO] ========================================");

        SymbolTable table = new SymbolTable();

        try {
            ClassSymbol calcSym = table.addClass("Calculadora");
            table.openClass(new ClassScope(calcSym, table.getGlobalScope()));

            // 1. Classe -> Método
            Parameter paramA = new Parameter("a", new Type(Type.PrimitiveType.INT));
            Method procMetodo = table.addMethod("processar", new Type(Type.PrimitiveType.VOID), new Parameter[]{paramA});
            table.openMethod(procMetodo); 

            // 2. Método -> Bloco 1
            System.out.println("[INFO] --- Abrindo Bloco 1 ---");
            table.openBlock();
            table.define(new Variable("temp", new Type(Type.PrimitiveType.INT)));

            // 3. Bloco 1 -> Bloco 2 (Aninhado)
            System.out.println("[INFO] --- Abrindo Bloco 2 (Aninhado) ---");
            table.openBlock();
            table.define(new Variable("x", new Type(Type.PrimitiveType.INT)));

            System.out.println("[PASS] Busca 'x' (no Bloco 2): " + table.findSymbol("x"));
            System.out.println("[PASS] Busca 'temp' (sobe para o Bloco 1): " + table.findSymbol("temp"));
            System.out.println("[PASS] Busca 'a' (sobe para o Método): " + table.findSymbol("a"));
            
            System.out.println("\n[INFO] Estado da Tabela (Profundidade Máxima):");
            table.print();

            // 4. Testando Robustez (Fail-safes Semânticos)
            System.out.println("\n[INFO] --- Testando Fail-safes Locais ---");
            try {
                // Tenta definir 'x' de novo no mesmo Bloco 2
                table.define(new Variable("x", new Type(Type.PrimitiveType.FLOAT)));
            } catch (Exception e) {
                System.out.println("[PASS] Fail-safe (Variável duplicada): " + e.getMessage());
            }

            // 5. Fechando Escopos e verificando a destruição das variáveis
            table.closeBlock(); // Fecha Bloco 2
            System.out.println("\n[INFO] --- Bloco 2 Fechado ---");
            System.out.println("[PASS] 'x' sumiu? " + (table.findSymbol("x") == null ? "Sim" : "Não"));

            table.closeBlock(); // Fecha Bloco 1
            table.closeMethod();
            table.closeClass();
            
            // 6. Fechar além do limite (Fail-safe da própria SymbolTable)
            System.out.println("\n[INFO] --- Fechando escopos em excesso (Não deve quebrar o app) ---");
            table.closeBlock();
            table.closeMethod();
            table.closeClass();
            System.out.println("[PASS] Sobreviveu ao fechamento excessivo!");

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("[INFO] ========================================\n");
    }
}
