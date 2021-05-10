package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import exception.DomainException;
import services.Calculadora;

public class Anuncio {
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private String nameAd;
	private Date initialDate;
	private Date finalDate;
	private double investimentPerDay;
	private Cliente client;
	private Calculadora calc;

//Constructor
	public Anuncio(String nameAd, Date initialDate, Date finalDate, double investimentPerDay, Cliente client) {
		if (investimentPerDay <0) {
			throw new DomainException("O valor do investimento não pode ser negativo.");
		}
		if (!finalDate.after(initialDate)) {
			throw new DomainException("A data final não pode ser anterior a data inicial!");
		}
		
		this.nameAd = nameAd;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.investimentPerDay = investimentPerDay;
		this.client = client;
		calc = new Calculadora();
		calc.totalCalc(investimentPerDay);
	}

//Getter and Setters
	public String getNameAd() {
		return nameAd;
	}

	public Date getInitialDate() {
		return initialDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public double getInvestimentPerDay() {
		return investimentPerDay;
	}

	public Cliente getClient() {
		return client;
	}
	
//Methods
	public double totalInvestiment() {		
		long days = finalDate.getTime() - initialDate.getTime();
		return investimentPerDay*TimeUnit.DAYS.convert(days,TimeUnit.MILLISECONDS);
	}
	
//toString
	@Override
	public String toString() {
		return "Nome do anuncio: "+nameAd+String.format("\n")
				+"Nome do Cliente: "+client.getName()+String.format("\n")
				+"Data inicial do anuncio: "+sdf.format(initialDate)+String.format("\n")
				+"Data final do anuncio: "+sdf.format(finalDate)+String.format("\n")
				+"Total investido: R$"+String.format("%.2f", totalInvestiment())+String.format("\n")
				+"Total aproximado de visualizações: "+String.format("%.0f", calc.getTotalViews())+String.format("\n")
				+"Total aproximado de cliques: "+String.format("%.0f", calc.getTotalClick())+String.format("\n")
				+"Total aproximado de compartilhamentos: "+String.format("%.0f", calc.getTotalShare())+String.format("\n");		
	}
	
}
