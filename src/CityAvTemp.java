
public class CityAvTemp extends City{
	String name;
	double[][] monthlyTemp ;
	
	public CityAvTemp (String name, double [][] monthlyTemp){
		super (name);
		setMonthlyTemp (monthlyTemp);
		
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	
	public double[][] getMonthlyTemp() {
		return monthlyTemp;
	}

	
	public void setMonthlyTemp(double[][] monthlyTemp) {
		this.monthlyTemp = monthlyTemp;
	}
	
	
}
