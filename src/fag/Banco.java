package fag;

import java.util.Scanner;
import java.text.NumberFormat; //biblioteca para usar formatação numerica
import java.util.Locale; //locate para usar o modelo brasileiro

public class Banco {

   
    static double limiteDiario = 500; //variavel global para o limite 
    
    public static void main(String[] args) {
        System.out.println("Bem vindo ao banco Homofóbico");
        Scanner scan = new Scanner(System.in);
        double saldo = 0;
        String senha = criarSenha(scan); //cria senha
        boolean senhaConfirme = confirmaSenha(scan, senha); //confirma se está certa a senha

        if (senhaConfirme == true) {
            menu(scan); //mostra o menu
            int opcao = escolha(scan); //recebe a escolha do menu do usuário
            while (opcao != 4) { //escolha para o banco continuar rodando dependendo da opção do usuario
                if (opcao == 1) {
                    consultaSaldo(saldo);
                } else if (opcao == 2) {
                    saldo = depositar(saldo, scan);
                } else if (opcao == 3) {
                    saldo = sacar(saldo, scan);
                }
                menu(scan);
                opcao = escolha(scan);
            }
        }
        System.out.println("Fim da operação");
        scan.close();
    }

    public static int escolha(Scanner scan) { //escolhe a opção
        int opcao = scan.nextInt();
        scan.nextLine();
        return opcao;
    }

    public static double consultaSaldo(double saldo) { //mostra o saldo
    	NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        System.out.println("Seu saldo é de: " + formatoMoeda.format(saldo));
        return saldo;

    }

    public static double depositar(double saldo, Scanner scan) { //operacao depositar dinheiro
        System.out.println("Qual valor deseja depositar? ");
        double deposito = scan.nextDouble();
        scan.nextLine();
        if (deposito <= 0) {
            System.out.println("Depósito inválido");
        } else {
        	
            saldo += deposito;
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")); 
            System.out.println("Seu novo saldo é de: " + formatoMoeda.format(saldo));
        }
        return saldo;
    }

    public static double sacar(double saldo, Scanner scan) { //operacao saldo
        System.out.println("Qual valor deseja sacar? ");
        double saque = scan.nextDouble();
        scan.nextLine();

        if (saque > saldo) {
            System.out.println("Saldo insuficiente.");
        } else if (saque > limiteDiario) { //caso tenha sacado mais do que o limite
        	NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            System.out.println("Você só pode sacar até R$" + formatoMoeda.format(limiteDiario) + " hoje.");
        } else {
            saldo -= saque;
            limiteDiario -= saque; // o limite diario diminui conforme os saque
            NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            System.out.println("Saque feito. Seu novo saldo é de: " + formatoMoeda.format(saldo));
            System.out.println("Limite restante para saque hoje:" + formatoMoeda.format(limiteDiario));
        }

        return saldo;
    }

    public static void menu(Scanner scan) { //escolha de operação
        System.out.println("\nEscolha a operação: ");
        System.out.println("1- Consultar saldo");
        System.out.println("2- Depositar dinheiro");
        System.out.println("3- Sacar dinheiro");
        System.out.println("4- Sair");
    }

    public static String criarSenha(Scanner scan) {
        System.out.println("Crie sua senha: ");
        String senha = scan.nextLine();
        System.out.println("Confirme sua senha");
        String confirmacao = scan.nextLine();
        if (senha.equals(confirmacao)) { //confere se as senhas digitadas são iguais
            return senha;
        } else {
            System.out.println("Senhas não conferem, senha padrão aplicada.");
            return senha;
        }
    }

    public static boolean confirmaSenha(Scanner scan, String senha) { //tentativas de acertar a senha
        for (int i = 1; i <= 3; i++) {
            System.out.println("Digite sua senha: ");
            String tentativa = scan.nextLine();
            if (tentativa.equals(senha)) {
                return true;
            } else {
                System.out.println("Senha incorreta. Você tem " + (3 - i) + " tentativas restantes");
            }
        }
        System.out.println("Número de tentativas ultrapassado.");
        return false;
    }
}
