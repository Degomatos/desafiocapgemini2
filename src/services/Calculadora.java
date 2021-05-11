package services;

public class Calculadora {

	private double views = 0;
	private double totalViews;
	private double click = 0;
	private double totalClick;
	private double share = 0;
	private double totalShare;
	
	public Calculadora() {
	}

	public double getTotalViews() {
		return totalViews;
	}

	public double getTotalClick() {
		return totalClick;
	}

	public double getTotalShare() {
		return totalShare;
	}
	
	public void totalCalc(double investiment) {
		views = investiment*30; //30 peoples view the ads per each R$1.00
		totalViews = views;
		
		for(int i=0;i<3;i++){
			click = views*0.12; //~12% will click
			totalClick += click;
			share = click*0.15; //~15% will share
			totalShare =+ share;
			views = 40*share;
			totalViews += views;
		}
	}

}
