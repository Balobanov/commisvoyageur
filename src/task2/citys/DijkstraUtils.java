package task2.citys;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Class "DijkstraUtils" serving for load file with source data and for perform the tests.
 */
public class DijkstraUtils
{
	// The List for tests. 
	private List<Test> tests = new ArrayList<>();
	
	// Load file and checking format 
	public void loadFileOfTest(String fileName) throws FileCitiesException
	{
		try(Scanner in = new Scanner(new FileReader(fileName)))
		{
			// First reads number of tests. If correct continue.
			int numberOfTest = in.nextInt();
				if(numberOfTest > 10 || numberOfTest <= 0)
					throw new FileCitiesException("Incorrect size of tests.");
				
			// Load all tests from file
			for(int i = 0; i < numberOfTest; i++)
				loatTestFromFile(in);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		runTests();
	}
	
	private  void loatTestFromFile(Scanner in) throws FileCitiesException
	{
		Test t = new Test();
		
		// Reads number of cities.
		int numberOfCities = in.nextInt();
			if(numberOfCities > 10000 || numberOfCities < 0)
				throw new FileCitiesException("Incorrect size of cities.");
		
		
		// Reads cities and their neighbours. And add them to Test.
		for(int i = 0; i < numberOfCities; i++)
		{
			City c = null;
			
			String cityName = in.next();
			
			if(cityName.length() > 10 || cityName.length() < 0)
				throw new FileCitiesException("Incorrect size of cities.");
			
			int numberOfNeighbours = in.nextInt();
			
			c = new City(cityName, i+1);
			for(int j = 0; j < numberOfNeighbours; j++)
			{
				int to = in.nextInt();
				int cost = in.nextInt();
				c.addNeighbour(new Edges(cost, i+1, to));
			}
			
			t.addCity(c);
		}
		
		
		int sourceDest = in.nextInt();
		for(int i = 0; i < sourceDest; i++)
		{
			String s = in.next(); // source
			String d = in.next(); // destination
			
			t.addSourceAndDestunation(new String[]{s,d});
		}
		
		// Add one test in list.
		tests.add(t);
	}
	
	// Performing all tests in list using the Dijkstra algorithm for finding shortest path.
	private void runTests()
	{
		
			try(PrintWriter pw = new PrintWriter(new FileWriter("output.txt")))
			{
				for(Test t: tests)
				{
					// Pass graph (cities with neighbours) for the algorithm
					Dijkstra d = new Dijkstra(t.getCities());
					
					for(String[] s: t.getSourceDest())
					{
						pw.println(d.findPathFromTo(s[0], s[1]));
					}
				
					pw.println();
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
	}
}
