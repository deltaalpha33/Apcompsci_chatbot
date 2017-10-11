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
	private int finishCount;
	private int normalCount;
	private int fakeBoughtCount;
	private boolean normalResponse;
	private String[] requestTerms = {"show me", "tell me", "need to know", "what are the", "do i need", "do i have to", "want to know", "give me"};
	private String[] requestTypes = {"ingredient", "tools", "utensils", "ingredients", "both", "everything"};
	private String[] requestResponses = {"You better get going", "Why do you keep asking?", "Maybe if you stopped asking and went out and got stuff, you would be done by now.", "Do you have amnesia or something?"};
	private String[] normalResponses = {"Want to ask anything?", "What's up?", "You need anything?", "What's the deal?", "You got a question or something?", "Need anything?"};
	private String[] finishedTerms = {"finished", "got", "found", "bought", "purchased"};
	private String[] finishedResponses = {"Another task done, nice job.", "You're a go-getter.", "Great work.", "You're on the right track.", "That wasn't too bad, was it?", "Gotcha"};
	private String[] alreadyFinishedResponses = {"You already bought that.", "You already got that.", "You didn't need to get it again...", "You only needed to buy it once."};
	private String[] alreadyCheckTerms = {"what do i", "tell me what i", "what have i", "already have", "do i have", "do i still need"};
	private String[] jibberishResponses = {"What's that supposed to mean?", "I don't get it.", "I don't get what you're trying to say.", "Didn't catch that.", "Huh?", "I don't get what that's supposed to mean.", "I missed the meaning of that.", "I don't get it."};
	private String[] unfinishedItems;
	private String[] finishedItems;
	
	public ChatbotBen(Chatbot chatbot) 
	{
		String[] temp = {"ingredients", "components", "cost", "tools", "utensils"};
		this.info = chatbot;
		this.keywords = temp;
		this.goodbyeKeyword = "bye";
		this.response = "";
		this.requestCount = 0;
		this.finishCount = 0;
		this.normalCount = 0;
		this.fakeBoughtCount = 0;
		this.normalResponse = true;
		this.finishedItems = new String[(info.getFoodList()[0].getIngredients().length) + (info.getFoodList()[0].getCookingTools().length)];
		this.unfinishedItems = new String[finishedItems.length];
		for (int i = 0; i < info.getFoodList()[0].getIngredients().length; i += 1)
		{
			this.unfinishedItems[i] = info.getFoodList()[0].getIngredients()[i].getName();
		}
		for (int i = info.getFoodList()[0].getIngredients().length; i < unfinishedItems.length; i += 1)
		{
			this.unfinishedItems[i] = info.getFoodList()[0].getCookingTools()[i - 1].getName();
		}
	}

	public void talk(String response) 
	{
		food = info.getFoodList();
		ChatbotMain.print("So you wanna make " + food[0].getName() + ". You're gonna need to get some ingredients first. It'll cost you " + getTotalCost(food[0].getIngredients()) + ". You're also going to need some cooking tools to make it. Feel free to ask for the ingredients and tools at any time.");
		response = ChatbotMain.getInput();
		while(!(response.toLowerCase().equals(goodbyeKeyword))) 
		{
			this.normalResponse = true;
			boolean alreadyCheck = false;
			boolean jibberish = true;
			if (response.toLowerCase().equals("sorry"))
			{
				ChatbotMain.print("No problem.");
				jibberish = false;
			}
			if (typeOfRequest(response.toLowerCase()).length() > 0)
			{
				requestCount += 1;
				if (requestCount > 5)
				{
					int rnd = (int)(Math.random() * (requestResponses.length));
					ChatbotMain.print(requestResponses[rnd]);
				}
				if (detectResponse(response.toLowerCase()) == INGREDIENT_NAMES)
				{
					ChatbotMain.print("The ingredients you still need are:");
					this.printNames(INGREDIENT_NAMES);
				}
				else if (detectResponse(response.toLowerCase()) == TOOLS_NAMES)
				{
					ChatbotMain.print("For cooking utensils, you still need the following:");
					this.printNames(TOOLS_NAMES);
				}
				else if (detectResponse(response.toLowerCase()) == BOTH_NAMES)
				{
					ChatbotMain.print("Here's everything you still need:");
					this.printNames(BOTH_NAMES);
				}
				else
				{
					ChatbotMain.print("Be more specific. What do you want to know, again?");
					response = ChatbotMain.getInput();
					continue;
				}
				jibberish = false;
			}
			if (detectAlreadyCheck(response.toLowerCase()))
			{
				ChatbotMain.print("So far, you have:");
				alreadyCheck = true;
				
				boolean foundAnything = false;
				for (int i = 0; i < finishedItems.length; i += 1)
				{
					try
					{
						ChatbotMain.print(finishedItems[i]);
						foundAnything = true;
					}
					catch (Exception e)
					{
						
					}
				}
				if (!foundAnything)
				{
					ChatbotMain.print("...Nothing. You better get going!");
					this.normalResponse = false;
				}
				jibberish = false;
			}
			if (typeOfFinish(response.toLowerCase()).length() > 0)
			{
				boolean repeatFinish = false;
				jibberish = false;
				if (typeOfFinish(response.toLowerCase()).equals("what") && !alreadyCheck)
				{
					ChatbotMain.print("That's not something that you needed to buy.");
					this.fakeBoughtCount += 1;
					this.normalResponse = false;
					
					if (fakeBoughtCount > 5)
					{
						ChatbotMain.print("How about you go and get something you're actually supposed to get?");
					}
				}
				else
				{
					for (int i = 0; i < (splitFinish(typeOfFinish(response.toLowerCase()))).length; i += 1)
					{
						for (int o = 0; o < this.finishedItems.length; o += 1)
						{
							if (this.unfinishedItems[o].equals(splitFinish(typeOfFinish(response.toLowerCase()))[i]))
	                        {
	                        	try
	                        	{
	                        		if (this.finishedItems[o].equals(splitFinish(typeOfFinish(response.toLowerCase()))[i]))
	                        		{
	                        			int rnd = (int)(Math.random() * (alreadyFinishedResponses.length));
	                        			ChatbotMain.print(alreadyFinishedResponses[rnd]);
	                        			repeatFinish = true;
	                        			continue;
	                        		}
	                        	}
	                        	catch (Exception e)
	                        	{
	                        		
	                        	}
								this.finishedItems[o] = splitFinish(typeOfFinish(response.toLowerCase()))[i];
	                        }
						}
					}				
					try
					{
						int everythingFinished = 0;
						for (int i = 0; i < finishedItems.length; i += 1)
						{
							if (finishedItems[i].length() > 0)
							{
								everythingFinished += 1;
							}
							if (everythingFinished == finishedItems.length)
							{
								info.setFoodPurchased(true);
								ChatbotMain.print("Looks like you have everything you need.");
								return;
							}
						}
					}
					catch (Exception e)
					{
						
					}
					if (!repeatFinish)
					{
						int rnd = (int)(Math.random() * (finishedResponses.length));
						ChatbotMain.print(finishedResponses[rnd]);
					}
					finishCount += 1;
				}
			}
			if (response.toLowerCase().equals("no") || !(interested(response.toLowerCase(), this.noKeywords)))
			{
				ChatbotMain.print("If you don't want to do this, we'll have to start all over.");
				ChatbotMain.chatbot.getBen().talk("");
				jibberish = false;
			}
			
			if (jibberish)
			{
				int rnd = (int)(Math.random() * (jibberishResponses.length));
				ChatbotMain.print(jibberishResponses[rnd]);
				normalResponse = false;
			}
			if (normalCount % 7 == 0 || ChatbotMain.findKeyword(response.toLowerCase(), "help", 0) > -1)
			{
				ChatbotMain.print("If you don't know what to say, try asking what you have left to get or what you already have.");
				normalResponse = false;
			}
			if (normalResponse)
			{
				int rnd = (int)(Math.random() * (normalResponses.length));
				ChatbotMain.print(normalResponses[rnd]);
			}
			response = ChatbotMain.getInput();
			normalCount += 1;
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
	public String typeOfFinish(String s)
	{
		String finalString = "";
		boolean foundAnything = false;
		for (int i = 0; i < finishedTerms.length; i += 1)
		{
			if (ChatbotMain.findKeyword(s.toLowerCase(), finishedTerms[i], 0) > -1)
			{
				for (int o = 0; o < food[0].getIngredients().length; o += 1)
				{
					if(ChatbotMain.findKeyword(s.toLowerCase(), food[0].getIngredients()[o].getName(), 0) > -1) 
					{
						finalString += (food[0].getIngredients()[o].getName()) + "|";
						foundAnything = true;
					}
				}
				for (int p = 0; p < food[0].getCookingTools().length; p += 1)
				{
					if(ChatbotMain.findKeyword(s.toLowerCase(), food[0].getCookingTools()[p].getName(), 0) > -1) 
					{
						finalString += (food[0].getCookingTools()[p].getName()) + "|";
						foundAnything = true;
					}
				}
				if (!foundAnything)
				{
					return "what";
				}
			}
		}
		return finalString;
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
	public int detectResponse(String s)
	{
		if (typeOfRequest(s).equals("ingredient") || typeOfRequest(s).equals("ingredients"))
		{
			return INGREDIENT_NAMES;
		}
		else if (typeOfRequest(s).equals("tools") || typeOfRequest(s).equals("utensils"))
		{
			return TOOLS_NAMES;
		}
		else if (typeOfRequest(s).equals("both") || typeOfRequest(s).equals("everything"))
		{
			return BOTH_NAMES;
		}
		else
		{
			return -1;
		}
	}
	public String[] splitFinish(String s)
	{
		int wordLength = 0;
		int arrayIndex = 0;
		String[] finalString = new String[s.length()];
		
		for (int i = 0; i < s.length(); i += 1)
		{
			if (s.substring(i, i + 1).equals("|"))
			{
				finalString[arrayIndex] = s.substring(i - wordLength, i);
				arrayIndex += 1;
				wordLength = 0;
			}
			else
			{
				wordLength += 1;
			}
		}
		return finalString;
	}
	public boolean detectAlreadyCheck(String s)
	{
		for (int i = 0; i < alreadyCheckTerms.length; i += 1)
		{
			if (ChatbotMain.findKeyword(s, alreadyCheckTerms[i], 0) > -1)
			{
				return true;
			}
		}
		return false;
	}
}