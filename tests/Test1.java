package tests;

import symbols_table.*;
import symbols_table.scope.*;
import symbols_table.symbols.*;
import java.util.*;

/**
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 * 
 *   TESTE 1 — Escopos Básicos, Construtores e Modificadores
 *   Código fonte simulado no parser:
 * -------------------------------------------------------
 * public class Carro {
 * private int ano;
 *   public Carro() {}
 *   public void ligar() {
 * int chave;
 * }
 * }
 * -------------------------------------------------------
 */

public class Test1 
{
    public static void main(String[] args) 
    {
        System.out.println("[INFO] ========================================");
        System.out.println("[INFO] TESTE 1 — Escopos Básicos e Modificadores");
        System.out.println("[INFO] ========================================");

        SymbolTable table = new SymbolTable();

        try {
            // 1. Declarando a Classe (public class Carro)
            ClassSymbol carroSym = table.addClass("Carro");
            carroSym.addModifier(Modifier.PUBLIC); // Aplicando modificador explicitamente
            
            ClassScope carroScope = new ClassScope(carroSym, table.getGlobalScope());
            table.openClass(carroScope);

            // 2. Definindo Atributo (private int ano)
            Set<Modifier> modsPrivado = new HashSet<>(Arrays.asList(Modifier.PRIVATE));
            Attribute ano = new Attribute("ano", new Type(Type.PrimitiveType.INT), modsPrivado);
            table.define(ano);

            // 3. Definindo Construtor (public Carro())
            Set<Modifier> modsPublico = new HashSet<>(Arrays.asList(Modifier.PUBLIC));
            Constructor construtor = new Constructor("Carro", modsPublico);
            table.define(construtor);

            // 4. Declarando Método (public void ligar())
            Method ligarMetodo = table.addMethod("ligar", new Type(Type.PrimitiveType.VOID), new Parameter[0]);
            ligarMetodo.addModifier(Modifier.PUBLIC);
            table.openMethod(ligarMetodo); 

            // 5. Definindo Variável Local (variáveis locais usam o escopo de bloco/método)
            Variable chave = new Variable("chave", new Type(Type.PrimitiveType.INT));
            table.define(chave);

            System.out.println("\n[INFO] --- Realizando Buscas ---");
            System.out.println("[PASS] Busca 'chave' (local no método): " + table.findSymbol("chave"));
            System.out.println("[PASS] Busca 'ano' (sobe p/ classe): " + table.findSymbol("ano"));
            
            table.closeMethod();
            table.closeClass();

            System.out.println("\n[INFO] --- Buscas Globais ---");
            System.out.println("[PASS] findClass('Carro'): " + table.findClass("Carro"));
            System.out.println("[PASS] findMethods('ligar'): " + table.findMethods("ligar", carroSym));

            // 6. Testando Robustez
            System.out.println("\n[INFO] --- Testando Fail-safes ---");
            try {
                table.addClass("Carro"); // Tentativa de duplicar classe no GlobalScope
            } catch (DuplicateSymbolException e) {
                System.out.println("[PASS] Fail-safe (Classe duplicada barrada): " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("[INFO] ========================================\n");
    }
} // Test 1
