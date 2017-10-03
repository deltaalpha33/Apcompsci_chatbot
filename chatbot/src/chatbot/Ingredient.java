package chatbot;

public class Ingredient {
	private String name;
	private float cost;
	
	public Ingredient(String name, int cost){
		this.name = name;
		this.cost = cost;
	}
	
	public String getName() {
		return this.name;
	}
	
	public float getCost() {
		return this.cost;
	}
}
