package task2.citys;

import java.io.Serializable;

/*
 * Edge of graph
 * @cost - value between city
 * @from - Start point
 * @to - destination
 */
public class Edges implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cost;
	private int from;
	private int to;
	public Edges(int cost, int from, int to) {
		super();
		this.cost = cost;
		this.from = from;
		this.to = to;
	}

	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int from) {
		this.from = from;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int to) {
		this.to = to;
	}

	@Override
	public String toString() {
		return "Edges [cost=" + cost + ", from=" + from + ", to=" + to + "]";
	}
}
