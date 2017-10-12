package chatbot;

import java.util.Arrays;

public class ChatbotDimitris implements Topic {
	private String[] greetings = {"hi", "hello"};
	private String[] triggerWords = {"choose", "select", "food"};
	private String[] finishedKeywords = {"done", "finished"};
	private String[] affirmativeKeywords = {"yes", "yeah"};
	private String response = "";
	private boolean finished = false;
	private Chatbot chatbot;
	private int annoyance = 0;
	private int anger = 0;
	
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
			ChatbotMain.print("What food would you like to select?");
			this.response = ChatbotMain.getInput();
				int foodIndex = this.extractNameable(this.foods, this.response);
				if(foodIndex != -1) {
					this.addSelectedFood(foodIndex);
				}
				else{
					String foodName = this.response;
					ChatbotMain.print("Food was not found, do you wish to add it?");
					this.response = ChatbotMain.getInput();
					if(this.findKeywords(this.affirmativeKeywords)) {
						Food newFood = new Food();
						newFood.setName(foodName);
						
						ChatbotMain.print("Nessisary Ingredients (separate with commas):");
						this.response = ChatbotMain.getInput();
						String currentString = "";
						for(int i = 0; i <= this.response.length(); i++) {
							
							if(i == this.response.length() || this.response.charAt(i) == ',') { //short circuits operator
								int ingredientIndex = this.extractNameable(this.ingredients, currentString);
								ChatbotMain.print(currentString);
								if(ingredientIndex == -1) {
									ChatbotMain.print("How much does " + currentString + " cost?");
									String tempCost = ChatbotMain.getInput();
									if(tempCost.equals("")) {
										ChatbotMain.print("ha ha, very funny");
										tempCost = "100.00";
									}
									float ingredientCost;
									try {
										ingredientCost = Float.parseFloat(tempCost);
									}
									catch(Exception e){
										ChatbotMain.print("because you are being difficult, the cost is now 100.00");
										ingredientCost = (float)100.00;
									}
										
									this.addIngredient(new Ingredient(currentString, ingredientCost), newFood);
								}
								else {
									this.addIngredient(this.ingredients[ingredientIndex], newFood); //ingredient already exists
								}
								ChatbotMain.print(currentString + " added to " + newFood.getName());
								currentString = "";

							}
							else {
								currentString+= this.response.charAt(i);
							}
							
							
						}
						
						this.addSelectedFood(newFood);
						
					}
					else {
						ChatbotMain.print("if you do not want to make a new food then select one of the default ones" + this.getNameString(this.foods));
					}
					
				
				
			}
			
			
			ChatbotMain.print("have you selected all of your foods?");
			this.response = ChatbotMain.getInput();
			
			this.finished = this.findKeywords(this.finishedKeywords) || this.findKeywords(this.affirmativeKeywords);
		}
		ChatbotMain.print("YOU HAVE SELECTED ALL YOUR INGREDIENTS :)");
		this.chatbot.setFoodSelected(true);
		this.chatbot.setFoodList(this.selectedFoods);
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
	
	public int extractNameable(Nameable[] nameableList, String str) {
		for(int i = 0; i < nameableList.length; i++) {
			if(nameableList[i].getName().equals(str.toLowerCase())){
				return i;
			}
		}
		return -1;
	}
	
	public void addSelectedFood(int foodIndex) {
		this.selectedFoods = Arrays.copyOf(this.selectedFoods,this.selectedFoods.length+1);
		this.selectedFoods[this.selectedFoods.length-1] = this.foods[foodIndex];
	}
	public void addSelectedFood(Food newFood) {
		this.selectedFoods = Arrays.copyOf(this.selectedFoods,this.selectedFoods.length+1);
		this.selectedFoods[this.selectedFoods.length-1] = newFood;
	}
	
	public void addIngredient(Ingredient ingredient, Food food) {
		Ingredient[] addArray = Arrays.copyOf(food.getIngredients(),food.getIngredients().length+1);
		addArray[addArray.length-1] = ingredient;
		food.setIngredients(addArray);
	}
	
	public String getNameString(Nameable[] nameArray) {
		String temp = "";
		for(int i = 0; i < nameArray.length; i++) {
			temp+= nameArray[i].getName();
		}
		return temp;
	}
	

}
