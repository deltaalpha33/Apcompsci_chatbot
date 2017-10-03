package chatbot;

public class Chatbot {
	public static final Food[] foods = {};
	public static final Ingredient[] ingredients = {};
	public static final String name = "FoodB0t";
	private String username;
	private boolean chatting;
	private Topic dimitris;
	private Topic achilles;
	private Topic ben;
	private Food[] foodList;
	private boolean foodSelected = false;
	
	public boolean isFoodPurchased() {
		return this.foodPurchased;
	}
	
	public Food[] getFoodList() {
		return this.foodList;
	}

	public void setFoodList(Food[] foodList) {
		this.foodList = foodList;
	}

	public boolean isFoodSelected() {
		return this.foodSelected;
	}

	public void setFoodSelected(boolean foodSelected) {
		this.foodSelected = foodSelected;
	}


	public void setFoodPurchased(boolean foodPurchased) {
		this.foodPurchased = foodPurchased;
	}
	private boolean foodPurchased = false;

	public Chatbot() {
		Ingredient tempingredient = new Ingredient("meatballs", (float)6.99);
		KitchenUtensil temputensil = new KitchenUtensil("Spatula");
		Ingredient[] tempilist = {tempingredient};
		KitchenUtensil[] tempklist = {temputensil};
		Food tempfood = new Food("pasta", tempilist, tempklist);
		Food[] tempflist = {tempfood};
		this.setFoodList(tempflist);
		ben = new ChatbotBen(this);
		dimitris = new ChatbotDimitris(this);
		achilles = new ChatbotAchilles();
		username = "Unknown User";
		chatting = true;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public Topic getDimitris() {
		return this.dimitris;
	}
	public Topic getAchilles() {
		return this.achilles;
	}
	public Topic getBen() {
		return this.ben;
	}
	public void startChatting() {
		//whenever you print or get input, use these methods
		ChatbotMain.print("Hi! I am an intelligent machine that can respond to your input. Tell me your name.");
		username = ChatbotMain.getInput();
		
		while(true) {
			ChatbotMain.print("What would you like to talk about?");
			String response = ChatbotMain.getInput();
			
			if(dimitris.isTriggered(response)) {
				dimitris.talk(response);
			}else {
				if(ben.isTriggered(response)) {
					ben.talk(response);
				}else {
					if(achilles.isTriggered(response)) {
						achilles.talk(response);
					}
					else {
						ChatbotMain.print("I'm sorry. I don't understand. I never said I was perfect.");
					}
				}
			}
			

		}
	}
}
