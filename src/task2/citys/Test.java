package task2.citys;

import java.util.LinkedList;
import java.util.List;

/*
 * Class for test. Contain graph of cities and targets: sources and destination
 */
public class Test
{
	private List<City> cities = new LinkedList<>(); // City graph
	private List<String[]> sourceDest = new LinkedList<>(); // Targets
	
	public List<City> getCities() {
		return cities;
	}
	
	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	public List<String[]> getSourceDest() {
		return sourceDest;
	}
	
	public void setSourceDest(List<String[]> sourceDest) {
		this.sourceDest = sourceDest;
	}
	
	public Test(List<City> cities, List<String[]> sourceDest) {
		super();
		this.cities = cities;
		this.sourceDest = sourceDest;
	}
	
	public Test() {
		super();
	}
	
	public void addCity(City c)
	{
		cities.add(c);
	}
	
	public void addSourceAndDestunation(String[] sd)
	{
		sourceDest.add(sd);
	}
}