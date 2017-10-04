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
	private String[] requestTerms = {"show me", "tell me", "need to know", "what are the", "do i need", "do i have to", "want to know"};
	private String[] requestTypes = {"ingredient", "tools", "utensils", "ingredients", "both", "everything"};
	private String[] requestResponses = {"You better get going", "Why do you keep asking?", "Maybe if you stopped asking and went out and got stuff, you would be done by now.", "Do you have amnesia or something?"};
	
	public ChatbotBen(Chatbot chatbot) 
	{
		String[] temp = {"ingredients", "components", "cost", "tools", "utensils"};
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
		while(!(response.toLowerCase().equals(goodbyeKeyword))) 
		{
			if (typeOfRequest(response.toLowerCase()).length() > 0)
			{
				requestCount += 1;
				if (requestCount > 5)
				{
					int rnd = (int)(Math.random() * (5));
					ChatbotMain.print(requestResponses[rnd]);
				}
				if (typeOfRequest(response.toLowerCase()).equals("ingredient") || typeOfRequest(response.toLowerCase()).equals("ingredients"))
				{
					ChatbotMain.print("The ingredients you still need are:");
					this.printNames(0);
				}
				else if (typeOfRequest(response.toLowerCase()).equals("tools") || typeOfRequest(response.toLowerCase()).equals("utensils"))
				{
					ChatbotMain.print("For cooking utensils, you still need the following:");
					this.printNames(1);
				}
				else if (typeOfRequest(response.toLowerCase()).equals("both") || typeOfRequest(response.toLowerCase()).equals("everything"))
				{
					ChatbotMain.print("Here's everything you still need:");
					this.printNames(2);
				}
				else
				{
					ChatbotMain.print("Be more specific. What do you want to know, again?");
					response = ChatbotMain.getInput();
					continue;
				}
			}
			if (response.toLowerCase().equals("no") || !(interested(response.toLowerCase(), this.noKeywords)))
			{
				ChatbotMain.print("If you don't want to do this, we'll have to start all over.");
				ChatbotMain.chatbot.getBen().talk("");
			}
			
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
				for (int o = 0; o < requestTypes.length; o += 1)
				{
					if (ChatbotMain.findKeyword(s.toLowerCase(), requestTypes[o], 0) > -1)
					{
						
						return requestTypes[o];
					}
				}
				return "what";
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