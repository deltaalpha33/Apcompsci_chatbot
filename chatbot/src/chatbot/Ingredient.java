package chatbot;

public class Ingredient {
	private String name;
	private int cost;
	
	Ingredient(String name, int cost){
		this.name = name;
		this.cost = cost;
	}
	
	String getName() {
		return this.name;
	}
	
	int getCost() {
		return this.cost;
	}
}
