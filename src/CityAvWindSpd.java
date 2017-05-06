
public class CityAvWindSpd  extends City{

	String name;
	double [][] monthlyWindSpd;

	public CityAvWindSpd(String name, double [][] monthlyWindSpd) {
		super(name);
		setMonthlyWindSpd (monthlyWindSpd);
		// TODO Auto-generated constructor stub
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public double[][] getMonthlyWindSpd() {
		return monthlyWindSpd;
	}


	public void setMonthlyWindSpd(double[][] monthlyWindSpd) {
		this.monthlyWindSpd = monthlyWindSpd;
	}

	




}
