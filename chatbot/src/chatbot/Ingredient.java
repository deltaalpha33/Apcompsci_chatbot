package chatbot;

public class Ingredient {
	private String name;
	private int cost;
	
	public Ingredient(String name, int cost){
		this.name = name;
		this.cost = cost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getCost() {
		return this.cost;
	}
}
