package task2.citys;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/*
 * Class realization of Dijkstra algorithm.
 */
public class Dijkstra
{
	// Graph of cities
	private List<City> cities;

	public Dijkstra(List<City> cities) {
		super();
		this.cities = cities;
	}

	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
	
	// Counting shortest path from SOURCE to DESTINATION
	public int findPathFromTo(String sourse, String destination) throws DijkstraCitiesException
	{
		if(cities == null)
			throw new DijkstraCitiesException("First set cities.");
		
		int sourseIndex = findIndexeOfCityByName(sourse); // First point of graph from which to start counting
		int destinationIndex = findIndexeOfCityByName(destination); // End point. 
		
		int p[] = new int [cities.size()]; // Vector of ways. It shows for some points of the route passed
		int d[] = new int [cities.size()]; // Vector of marks. It contain values of vertexes.
		
		for(int i=0; i < cities.size(); i++)
		{
			p[i] = 0;
			d[i] = Integer.MAX_VALUE; // All marks is infinite or maximal. Except first point she contain 0 - begin.
		}
		
		d[sourseIndex-1] = 0;
		
		// Copies for remove passed vertexes.
		List<City> copiesCity = makeCopyOfCities();
		
		//While have vertexes counting the marks
		while(copiesCity.size() > 0)
		{
			//The initial point of the path. Every step it's changing when moving to another vertex.
			City choisenCity = null;
			
			// The initial vertex calculation. the initial point of the path.
			choisenCity = getCityByIndex(sourseIndex, copiesCity);
			
			if(choisenCity == null)
				throw new DijkstraCitiesException("Incorrect source.");
			
			// Calculate route to neighbors
			for(Edges edge: choisenCity.getNeighbours())
			{
				// If value of mark of vertex was bigger than value of path.
				// Assign new value for mark
				if((edge.getCost() + d[edge.getFrom()-1]) < d[edge.getTo()-1])
				{
					d[edge.getTo()-1] = edge.getCost() + d[edge.getFrom()-1];
					p[edge.getTo()-1] = edge.getFrom();
				}
			}
			
			removeAllReferenceOnLastVertex(copiesCity, sourseIndex);
			
			// Find the next town which is less expensive. The edge with the lowest index.
			sourseIndex = findNextCitysIndexWithMinEdge(choisenCity);
			copiesCity.remove(choisenCity);
		}
	
		// The shortest path from source to destination.
		return d[destinationIndex-1];
	}
	
	/*
	 * Making copy of graph for safety removing traveled vertices (cities) of graph.
	 * Using serialization for cloning.
	 */
	@SuppressWarnings("unchecked")
	private List<City> makeCopyOfCities() 
	{
		List<City> copies = null;
			try
			{
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
		        ObjectOutputStream ous = new ObjectOutputStream(baos);
		        
		        ous.writeObject(cities);
		        ous.close();
		        
		        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		        ObjectInputStream ois = new ObjectInputStream(bais);
		       
		        copies = (List<City>) ois.readObject();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
        return copies;
	}
	
	private void removeAllReferenceOnLastVertex(List<City> cities, int sourseIndex)
	{
		for(City c: cities)
		{
			int index = 0;
			for(int i = 0; i < c.getNeighbours().size(); i++)
			{
				Edges e = c.getNeighbours().get(index);
					if(e.getTo() == sourseIndex)
					{
						c.getNeighbours().remove(e);
						index = 0;
					}
					else
						index++;
			}
		}
	}
	
	private int findNextCitysIndexWithMinEdge(City city)
	{
		int min = Integer.MAX_VALUE;
		int index  = 0;
		
		for(Edges e: city.getNeighbours())
		{
			if(e.getCost() < min)
			{
				min = e.getCost();
				index = e.getTo();
			}
		}
		
		return index;
	}
	
	private int findIndexeOfCityByName(String name)
	{
		for(City c: cities)
			if(c.getName().equalsIgnoreCase(name))
				return c.getIndex();
		
		return -1;
	}
	
	private City getCityByIndex(int index, List<City> copiesCity)
	{
		for(City c: copiesCity)
			if(c.getIndex() == index)
				return c;
		
		return null;
	}
}