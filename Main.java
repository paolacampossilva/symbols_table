import tests.*;

/**
 * Classe principal para rodar todos os testes do Trabalho 1 em sequência.
 * 
 * @author Paola Campos da Silva
 * @author João Pedro Huppes Arenales
 * 
 */

public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("Iniciando bateria de testes da Tabela de Símbolos...\n");

        // Chama o Teste 1 (Escopos Básicos e Múltiplas Classes)
        Test1.main(args);
        System.out.println();

        // Chama o Teste 2 (Herança e Polimorfismo)
        Test2.main(args);
        System.out.println();

        // Chama o Teste 3 (Aninhamento de Blocos e Erros)
        Test3.main(args);
        
        System.out.println(">>> Todos os testes foram executados com sucesso!");
    }
}
