package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import entities.Anuncio;
import entities.Cliente;
import exception.DomainException;

public class Program {
	
static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
				
		List<Anuncio> list = new ArrayList<>();
		int option =0;
		
		System.out.println("Bem Vindo ao sistema de cadastro de An?ncios!");
		do {
			try {	
				System.out.println("Por favor digite o n?mero correspondente a op??o para prosseguir:");
				System.out.println("1) Para cadastrar um novo An?ncio");
				System.out.println("2) Para visualizar o relat?rio dos An?ncios cadastrados");
				System.out.println("3) Para pesquisar an?ncio pelo nome do cliente");
				System.out.println("4) Para pesquisar an?ncio por data");
				System.out.println("5) Para finalizar o sistema");
				option = sc.nextInt();
				sc.nextLine();

				switch (option) {
				case 1: {//OK
					list.add(registerNewAd());
					break;
				}
				case 2:{ //OK
					verifyVoidList(list);
					showAllAds(list);			
					break;
				}
				case 3:{//OK
					verifyVoidList(list);
					sourcePerName(list);
					break;
				}
				case 4: {//OK
					verifyVoidList(list);
					sourcePerDate(list);
					break;
				}
				case 5: {//OK
					System.out.println("Intera??o encerrada!");
					System.out.println("Obrigado por ter utilizado este sistema. Espero que tenha gostado.");
					break;
				}
				default:
					System.out.println("Op??o Inv?lida.");
				}
			}
			catch (ParseException e) {
				System.out.println("A data digitada n?o ? v?lida. Tente Novamente!");
				System.out.println();
			}
			catch (InputMismatchException e) {
				System.out.println("O valor digitado n?o ? v?lido. Tente Novamente!");
				System.out.println();
				sc.nextLine();
			}
			catch (DomainException e) {
				System.out.println("Ocorreu um erro durante a solicita??o: "+e.getMessage());
				System.out.println();
			}
		}while(option!=5);

		sc.close();

	}
//Methods
	public static void verifyVoidList(List<Anuncio> list) {
		if (list.size()==0)
			throw new DomainException("Ainda n?o h? an?ncios cadastrados no sistema.");
	}
	
	public static Anuncio registerNewAd() throws ParseException{
		System.out.printf("Digite o nome do an?ncio: ");
		String nameAd = sc.nextLine();
		System.out.printf("Digite o nome do Cliente: ");
		String name = sc.nextLine();
		System.out.printf("Digite a data de in?cio do an?ncio (dd/MM/yyyy): ");
		Date initialDate = sdf.parse(sc.next());
		System.out.printf("Digite a data de t?rmino do an?ncio (dd/MM/yyyy): ");
		sc.nextLine();
		Date finalDate = sdf.parse(sc.next());
		System.out.printf("Quanto deseja investir por dia, em reais: R$");
		double investimentPerDay = sc.nextDouble();
		sc.nextLine();
		Cliente client = new Cliente(name);
		Anuncio ad = new Anuncio(nameAd, initialDate, finalDate, investimentPerDay, client);	
		System.out.println("An?ncio cadastrado com sucesso!");
		System.out.println();
		return ad;
	}
	
	public static void showAllAds(List<Anuncio> list) {
		System.out.println("Estes s?o todos os an?ncios cadastrados no sistema at? o momento:");
		System.out.println("---------------------------------------");
		for (Anuncio ad : list) {
			System.out.println(ad);
			System.out.println("---------------------------------------");
		}		
	}
	
	public static void sourcePerName(List<Anuncio> list) {
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
			System.out.println("N?o foi encontrado nenhum cliente cadastrado com esse nome.");
	}
	
	public static void sourcePerDate(List<Anuncio> list) throws ParseException {
		System.out.printf("Por favor digite a data a ser pesquisada (dd/MM/yyyy): ");
		Date source = sdf.parse(sc.next());
		sc.nextLine();
		int count = 0;
		for (Anuncio ad : list) {
			if(ad.getInitialDate().before(source) && ad.getFinalDate().after(source)) {
				System.out.println("An?ncio localizado dentro do intervalo de tempo digitado:");
				System.out.println("---------------------------------------");
				System.out.println(ad);
				count = 1;
			}
			if(ad.getInitialDate().equals(source)) {
				System.out.printf("An?ncio localizado com data inicial em: %s \n",sdf.format(source));
				System.out.println("---------------------------------------");
				System.out.println(ad);
				count = 1;
			}
			if(ad.getFinalDate().equals(source)) {
				System.out.printf("An?ncio localizado com data final em: %s \n",sdf.format(source));
				System.out.println("---------------------------------------");
				System.out.println(ad);
				count = 1;
			}
		}
		if (count == 0) {
			System.out.println("Nenhum an?ncio localizado para a data digitada.");
			System.out.println();
		}
	}
}
