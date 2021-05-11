package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Anuncio;
import entities.Cliente;
import exception.DomainException;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);	
		List<Anuncio> list = new ArrayList<>();
		int option =0;
		
		System.out.println("Bem Vindo ao sistema de cadastro de Anuncios!");
		do {
			try {	
				System.out.println("Por favor digite o n�mero correspondente a op��o para prosseguir:");
				System.out.println("1) Para cadastrar um novo Anuncio");
				System.out.println("2) Para visualizar o relat�rio dos Anuncios cadastrados");
				System.out.println("3) Para pesquisar anuncio pelo nome do cliente");
				System.out.println("4) Para pesquisar anuncio por data");
				System.out.println("5) Para finalizar o sistema");
				option = sc.nextInt();
				sc.nextLine();

				switch (option) {
				case 1: {
					System.out.printf("Digite o nome do anuncio: ");
					String nameAd = sc.nextLine();
					System.out.printf("Digite o nome do Cliente: ");
					String name = sc.nextLine();
					System.out.printf("Digite a data de in�cio do an�ncio (dd/MM/yyyy): ");
					Date initialDate = sdf.parse(sc.next());
					System.out.printf("Digite a data de t�rmino do an�ncio (dd/MM/yyyy): ");
					sc.nextLine();
					Date finalDate = sdf.parse(sc.next());
					System.out.printf("Quanto deseja investir por dia, em reais: R$");
					double investimentPerDay = sc.nextDouble();
					sc.nextLine();
					Cliente client = new Cliente(name);
					Anuncio ad = new Anuncio(nameAd, initialDate, finalDate, investimentPerDay, client);
					list.add(ad);	
					System.out.println("An�ncio cadastrado com sucesso!");
					break;
				}
				case 2:{ //OK
					verifyVoidList(list);
					System.out.println("Esses s�o todos os anuncios cadastrados no sistema at� o momento:");
					System.out.println("---------------------------------------");
					for (Anuncio ad : list) {
						System.out.println(ad);
						System.out.println("---------------------------------------");
					}					
					break;
				}
				case 3:{//OK
					verifyVoidList(list);
					System.out.printf("Por favor digite o nome do cliente para iniciar a pesquisa: ");
					String source = sc.nextLine();
					int count = 0;
					for (Anuncio ad: list) {
						if(ad.getClient().getName().equals(source)) {
							System.out.println("Cliente localizado:");
							System.out.println("---------------------------------------");
							System.out.println(ad);
							count = 1;
						}
					}
					if(count == 0)
						System.out.println("N�o foi encontrado nenhum cliente cadastrado com esse nome.");
					break;
				}
				case 4: {//OK
					verifyVoidList(list);
					System.out.printf("Por favor digite a data a ser pesquisada (dd/MM/yyyy): ");
					Date source = sdf.parse(sc.next());
					sc.nextLine();
					int count = 0;
					for (Anuncio ad : list) {
						if(ad.getInitialDate().before(source) && ad.getFinalDate().after(source)) {
							System.out.println("An�ncio localizado dentro do intervalo de tempo digitado:");
							System.out.println("---------------------------------------");
							System.out.println(ad);
							count = 1;
						}
						if(ad.getInitialDate().equals(source)) {
							System.out.printf("An�ncio localizado com data inicial em: %s \n",sdf.format(source));
							System.out.println("---------------------------------------");
							System.out.println(ad);
							count = 1;
						}
						if(ad.getFinalDate().equals(source)) {
							System.out.printf("An�ncio localizado com data final em: %s \n",sdf.format(source));
							System.out.println("---------------------------------------");
							System.out.println(ad);
							count = 1;
						}
					}
					if (count == 0)
						System.out.println("Nenhum an�ncio localizado para a data digitada.");
					break;
				}
				case 5: {//OK
					System.out.println("Intera��o encerrada!");
					System.out.println("Obrigado por ter utilizado este sistema. Espero que tenha gostado.");
					break;
				}
				default:
					System.out.println("Op��o Inv�lida.");
				}
			}
			catch (ParseException e) {
				System.out.println("A data digitada n�o � v�lida. Tente Novamente!");
				System.out.println();
			}
			catch (InputMismatchException e) {
				System.out.println("O valor digitado n�o � v�lido. Tente Novamente!");
				System.out.println();
				sc.nextLine();
			}
			catch (DomainException e) {
				System.out.println("Ocorreu um erro durante a solicita��o: "+e.getMessage());
				System.out.println();
			}
		}while(option!=5);

		sc.close();

	}
	public static void verifyVoidList(List<Anuncio> list) {
		if (list.size()==0)
			throw new DomainException("Ainda n�o h� an�ncios cadastrados no sistema.");
	}
}
