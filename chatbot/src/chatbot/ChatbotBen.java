package chatbot;

public class ChatbotBen implements Topic 
{
	private static final int INGREDIENT_NAMES = 0;
	private static final int TOOLS_NAMES = 1;
	private static final int BOTH_NAMES = 2;
	
	private String[] keywords;
	private String goodbyeKeyword;
	private String response;
	private String[] noKeywords = {"rather not", "don't want", "not really"};
	private Chatbot info;
	private Food[] food;
	private int requestCount;
	String[] requestTerms = {"show me", "tell me"};
	String[] requestTypes = {"ingredient", "tools", "utensils"};
	
	public ChatbotBen(Chatbot chatbot) 
	{
		String[] temp = {"ingredients", "components", "cost"};
		info = chatbot;
		keywords = temp;
		goodbyeKeyword = "bye";
		response = "";
		requestCount = 0;
	}

	public void talk(String response) 
	{
		food = info.getFoodList();
		ChatbotMain.print("So you wanna make " + food[0].getName() + ", huh? You're gonna need to get some ingredients first. It'll cost you " + getTotalCost(food[0].getIngredients()) + ". You're also going to need some cooking tools to make it. Feel free to ask for the ingredients and tools at any time.");
		response = ChatbotMain.getInput();
		if (response.toLowerCase().equals("no") || !(interested(response.toLowerCase(), this.noKeywords)))
		{
			ChatbotMain.print("If you don't want to do this, we'll have to start all over.");
			ChatbotMain.chatbot.getBen().talk("");
		}
		if (typeOfRequest(response.toLowerCase()).length() > 0)
		{
			requestCount += 1;
			// just to test:
			ChatbotMain.print(typeOfRequest(response.toLowerCase()));
		}
		while(!(response.toLowerCase().equals(goodbyeKeyword))) 
		{
				ChatbotMain.print("Tell me what the deal is.");
				response = ChatbotMain.getInput();
		}
		ChatbotMain.print("Well, it was nice talking to you, " + ChatbotMain.chatbot.getUsername() + "!");
		return;
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
		for (int i = 0; i < noKeywords.length; i += 1)
		{
			if (ChatbotMain.findKeyword(s.toLowerCase(), noKeywords[i], 0) > -1)
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
	public String typeOfRequest(String s)
	{
		for (int i = 0; i < requestTerms.length; i += 1)
		{
			if (ChatbotMain.findKeyword(s.toLowerCase(), requestTerms[i], 0) > -1)
			{
				for (int o = 0; i < requestTypes.length; o += 1)
				{
					if (ChatbotMain.findKeyword(s.toLowerCase(), requestTypes[o], 0) > -1)
					{
						
						return requestTypes[o];
					}
				}
			}
		}
		return "";
	}
	public void printNames(int namesToPrint)
	{
		if (namesToPrint == INGREDIENT_NAMES)
		{
			for (int i = 0; i < food[0].getIngredients().length; i += 1)
			{
				ChatbotMain.print(food[0].getIngredients()[i].getName());
			}
		}
		else if (namesToPrint == TOOLS_NAMES)
		{
			for (int i = 0; i < food[0].getCookingTools().length; i += 1)
			{
				ChatbotMain.print(food[0].getCookingTools()[i].getName());
			}
		}
		else if (namesToPrint == BOTH_NAMES)
		{
			printNames(INGREDIENT_NAMES);
			printNames(TOOLS_NAMES);
		}
	}
}