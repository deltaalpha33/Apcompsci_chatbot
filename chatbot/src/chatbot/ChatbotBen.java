package chatbot;

public class ChatbotBen implements Topic 
{

	private String[] keywords;
	private String goodbyeKeyword;
	private String response;
	private boolean firstTime = true;
	
	public ChatbotBen() 
	{
		String[] temp = {"pizza", "burger", "fries", "club sandwich", "fried chicken", "burgers", "french fries"};
		keywords = temp;
		goodbyeKeyword = "bye";
		response = "";
	}
	
	public void talk(String response) 
	{
		ChatbotMain.print("I'll need you to get (plus food, list of ingredients, and cost)");
		response = ChatbotMain.getInput();
		if (response.toLowerCase().equals("no") || ChatbotMain.findKeyword(response.toLowerCase(), "don't want", 0) > -1)
		{
			ChatbotMain.print("If you don't want to do this, we'll have to start all over.");
			ChatbotMain.chatbot.getBen().talk("");
		}
		while(!(response.toLowerCase().equals(goodbyeKeyword))) 
		{
			if(firstTime)
			{
				ChatbotMain.print("Let me ask you - what other kinds of fast food do you like?");
				firstTime = false;
				response = ChatbotMain.getInput();
			}
			else 
			{
				ChatbotMain.print("Yeah. That's pretty cool. But there are things I like even more. Tell me something else");
				response = ChatbotMain.getInput();
			}
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
}