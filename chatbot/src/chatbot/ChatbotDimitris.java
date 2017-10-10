package chatbot;

import java.util.Arrays;

public class ChatbotDimitris implements Topic {
	private String[] greetings = {"hi", "hello"};
	private String[] triggerWords = {"choose", "select", "food"};
	private String[] finishedKeywords = {"done", "finished"};
	private String[] affirmativeKeywords = {"yes", "yeah"};
	private String[] needKeywords = {"need", "want", "would like"};
	private String response = "";
	private boolean finished = false;
	private Chatbot chatbot;
	
	private Ingredient[] ingredients= { new Ingredient("garlic", (float)0.30),
										new Ingredient("onion", (float)0.30),
										new Ingredient("chiken", (float)0.30),
										new Ingredient("tomato", (float)0.30),
										new Ingredient("lemon", (float)0.30),
										new Ingredient("beef", (float)0.30),
										new Ingredient("lettuce", (float)0.30),
										new Ingredient("pasta", (float)0.30)};
	private Food[] foods = { };
	private Food[] selectedFoods = {};
	
	public ChatbotDimitris(Chatbot chatbot) {
		this.chatbot = chatbot;
	}
	
	public void talk(String response) {
		
		while(!this.finished) {
			
			//Select Foods
			ChatbotMain.print("What food(s) would you like to select?");
			this.response = ChatbotMain.getInput();
			int foodIndex = 0;
			for(int i = foodIndex; i < this.response.length(); i = foodIndex) {
				foodIndex = this.extractNameable((Nameable[])this.foods, this.response, i);
				if(foodIndex == -1) {
					ChatbotMain.print("Food was not found, do you wish to add it?");
					this.response = ChatbotMain.getInput();
					if(this.findKeywords(this.affirmativeKeywords)) {
						Food newFood = new Food();
						ChatbotMain.print("Food Name:");
						newFood.setName(ChatbotMain.getInput());
						
						ChatbotMain.print("Nessisary Ingredients (separate with commas):");
						this.response = ChatbotMain.getInput();
						for(int wordStart = 0, j = 0; i <= this.response.length(); j++) {
							if(i == this.response.length()-1 || this.response.charAt(i) == ',') //short curcuits operator
								ChatbotMain.print("How much does it cost?");
								float ingredientCost = Float.parseFloat(ChatbotMain.getInput());
								this.addIngredient(new Ingredient(this.response.substring(wordStart, i), ingredientCost), newFood);
								wordStart = i;
							
						}
						newFood.setIngredients(ingredients);
						
						
					}
					
					break;
				}
				this.addSelectedFood(foodIndex);
				ChatbotMain.print(this.selectedFoods.toString());
				
			}
			
			
			ChatbotMain.print("have you selected all of your foods?");
			this.response = ChatbotMain.getInput();
			
			this.finished = this.findKeywords(this.finishedKeywords) || this.findKeywords(this.affirmativeKeywords);
		}
		ChatbotMain.print("YOU HAVE SELECTED ALL YOUR INGREDIENTS :)");
		this.chatbot.setFoodSelected(true);
	}
	
	public boolean isTriggered(String response) {
		this.response = response;
		return this.findKeywords(this.greetings) || this.findKeywords(this.triggerWords);	
	}
	
	public boolean findKeywords(String[] keywords) {
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(this.response, keywords[i], 0) > -1) {
				return true;
			}
		}
		return false;
	}
	
	public int extractNameable(Nameable[] nameableList, String str, int startPos) {
		for(int i = 0; i < chatbot.getFoodList().length; i++) {
			if(ChatbotMain.keywordIsIsolated(startPos, nameableList[i].getName(), str)){
				return i;
			}
		}
		return -1;
	}
	
	public void addSelectedFood(int foodIndex) {
		this.selectedFoods = Arrays.copyOf(this.selectedFoods,this.selectedFoods.length+1);
		this.selectedFoods[this.selectedFoods.length] = this.foods[foodIndex];
	}
	
	public void addIngredient(Ingredient ingredient, Food food) {
		Ingredient[] addArray = Arrays.copyOf(food.getIngredients(),food.getIngredients().length+1);
		addArray[addArray.length] = ingredient;
		food.setIngredients(addArray);
	}
	

}
