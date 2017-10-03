package chatbot;

public class ChatbotBen implements Topic 
{

	private String[] keywords;
	private String goodbyeKeyword;
	private String response;
	private String[] noKeywords;
	private Chatbot info;
	private Food[] food;
	
	public ChatbotBen(Chatbot chatbot) 
	{
		String[] temp = {"ingredients", "components", "cost"};
		String[] noKeywords = {"rather not", "don't want", "not really"};
		info = chatbot;
		food = info.getFoodList();
		keywords = temp;
		goodbyeKeyword = "bye";
		response = "";
	}
	// 
	public void talk(String response) 
	{
		ChatbotMain.print("So you wanna make " + food[0].getName() + ", huh? You're gonna need to get some ingredients first. " + food[0].getIngredients() + ". It'll cost you " + getTotalCost(food[0].getIngredients()) + ". You're also going to need " + food[0].getCookingTools() + "to make it");
		response = ChatbotMain.getInput();
		if (response.toLowerCase().equals("no") || !(interested(response.toLowerCase(), this.noKeywords)))
		{
			ChatbotMain.print("If you don't want to do this, we'll have to start all over.");
			ChatbotMain.chatbot.getBen().talk("");
		}
		while(!(response.toLowerCase().equals(goodbyeKeyword))) 
		{
				ChatbotMain.print("Yeah. That's pretty cool. But there are things I like even more. Tell me something else");
				response = ChatbotMain.getInput();
		}
		ChatbotMain.print("Well, it was nice talking to you, " + ChatbotMain.chatbot.getUsername() + "!");
		ChatbotMain.chatbot.getBen().talk("");
	}
	
	public boolean isTriggered(String response) 
	{
		for(int i = 0; i < keywords.length; i++) 
		{
			//IMPORTANT(on the rubric)
			if(ChatbotMain.findKeyword(response.toLowerCase(), keywords[i], 0) > -1) 
			{
				return true;
			}
		}
		return false;
	}
	public boolean interested(String s, String[] noKeywords)
	{
		for (int i = 0; i < s.length(); i += 1)
		{
			if (ChatbotMain.findKeyword(response.toLowerCase(), noKeywords[i], 0) > -1)
			{
				return false;
			}
		}
		return true;
	}
	public float getTotalCost(Ingredient[] ingredientList)
	{
		float finalCost = 0;
		
		for (int i = 0; i < ingredientList.length; i += 1)
		{
			finalCost += ingredientList[i].getCost();
		}
		
		return finalCost;
	}
}