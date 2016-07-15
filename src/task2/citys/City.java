package task2.citys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * This class is vertex of graph with edges to another vertexes with their costs.
 */
class City implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int index; // Number of city
	private List<Edges> neighbours; 
	
	public City(String name, int index, List<Edges> neighbours) {
		super();
		this.index = index;
		this.name = name;
		this.neighbours = neighbours;
	}
	
	public City(String name, int index) {
		super();
		this.name = name;
		this.index = index;
		this.neighbours = new ArrayList<>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Edges> getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(List<Edges> neighbours) {
		this.neighbours = neighbours;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void addNeighbour(Edges e)
	{
		if(e != null)
			neighbours.add(e);
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", index=" + index + ", neighbours=" + neighbours + "]";
	}
}

