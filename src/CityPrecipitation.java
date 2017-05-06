
public class CityPrecipitation extends City{

	String name;
	double [][] snow ;
	double [][] rain;
	
	public CityPrecipitation(String name, double [][] snow, double[][] rain) {
		super(name);
		
		setSnow (snow);
		setRain (rain);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the snow
	 */
	public double[][] getSnow() {
		return snow;
	}


	/**
	 * @param snow the snow to set
	 */
	public void setSnow(double[][] snow) {
		this.snow = snow;
	}


	/**
	 * @return the rain
	 */
	public double[][] getRain() {
		return rain;
	}


	/**
	 * @param rain the rain to set
	 */
	public void setRain(double[][] rain) {
		this.rain = rain;
	}

}
