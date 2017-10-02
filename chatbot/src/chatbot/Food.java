package chatbot;

public class Food {
	private String name;
	private Ingredient[] ingredients;
	
	public Food(String name, Ingredient[] ingredients) {
		this.name = name;
		this.ingredients = ingredients;
	}
	public String getName() {
		return this.name;
	}
	
	public Ingredient[] getIngredients() {
		return this.ingredients;
	}
	
}
