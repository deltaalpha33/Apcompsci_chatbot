package chatbot;

public class Food implements Nameable{
	private String name;
	private Ingredient[] ingredients;
	private KitchenUtensil[] cookingTools;
	
	public Food() {
		//empty constructor for creating Food manually
	}
	
	public Food(String name, Ingredient[] ingredients, KitchenUtensil[] cookingTools) {
		this.name = name;
		this.ingredients = ingredients;
		this.cookingTools = cookingTools;
	}
	public String getName() {
		return this.name;
	}
	
	public Ingredient[] getIngredients() {
		return this.ingredients;
	}
	
	public KitchenUtensil[] getCookingTools(){
		return this.cookingTools;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setIngredients(Ingredient[] ingredients) {
		this.ingredients = ingredients;
	}
	public void setCookingTools(KitchenUtensil[] cookingTools) {
		this.cookingTools = cookingTools;
	}
	
}
