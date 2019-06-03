import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    
    public static void main(String[] args) {
    	try {
    		Interface controle = (Interface) Naming.lookup("rmi://localhost:4444/CadastroService");

    		Scanner sc = new Scanner(System.in);
    		String nome;
    		String op ="1";
    		int id;
    		float valor;
    		while (!op.equals("0")) {
                    System.out.println("1 - cadastrar");
                    System.out.println("2 - alterar"); 
                    System.out.println("3 - deletar");
                    System.out.println("4 - recuperar");
                    System.out.println("0 - fechar");
                    System.out.println("Opção:");
    			op = sc.next();
    			switch (op) {
    			case "1":
    				System.out.println("Cadastrar um produto");
    				System.out.print("Id:");
    				id = sc.nextInt();
    				System.out.print("Nome:");
    				nome = sc.next();
    				System.out.println("Valor:");
    				valor = sc.nextFloat();
    				if(controle.cadastoProduto(id, nome, valor)) {
    					System.out.println("Cadastrado Sucesso");
    				}else {
    					System.out.println("Falha \n");
    				}
    				break;
    			case "2":
    				System.out.println("Alterar as informações \n Id:");
    				id=sc.nextInt();
    				if(controle.pesquisa(id)) {
    					System.out.print("Nome:");
    					nome = sc.next();
    					System.out.println("Valor:");
    					valor = sc.nextFloat();
    					if(controle.atualizaProduto(id, nome, valor)) {
    						System.out.println("Alteração Sucesso! \n");
    					}else {
    						System.out.println("Alteração Falha.\n");
    					}
    				}else {
    					System.out.println("Não Cadastrado.\n");
    				}
    				break;
    			case "3":
    				System.out.println("Deletar:\n id:");
    				id =sc.nextInt();
    				if(controle.removerProduto(id)) {
    					System.out.println("Removido\n");
    				}else {
    					System.out.println("Não Cadastrado!\n");
    				}
    				break;
    			case "4":
    				System.out.println("Recuperar :\n Informe id:");
    				id = sc.nextInt();
    				System.out.println(controle.pesquisaInfo(id)+"\n");
    				break;
    			default:
    				break;
    			}
    		}
    	} catch (MalformedURLException | RemoteException | NotBoundException e) {
    		System.out.println("Erro:" + e.toString());
    	} catch (Exception e) {
    		
    		System.out.println(e.getMessage());
    		System.exit(-1);
    	}
    }
} 
