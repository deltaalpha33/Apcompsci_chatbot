package chatbot;

public class Ingredient implements Nameable{
	private String name;
	private float cost;
	
	public Ingredient(String name, float cost){
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
