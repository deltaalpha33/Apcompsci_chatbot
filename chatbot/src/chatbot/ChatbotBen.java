package chatbot;

public class ChatbotBen implements Topic 
{

	private String[] keywords;
	private String goodbyeKeyword;
	private String secretKeyword;
	private String response;
	
	public ChatbotBen() 
	{
		String[] temp = {"pizza", "burger", "fries", "club sandwich", "fried chicken", "burgers", "french fries"};
		keywords = temp;
		goodbyeKeyword = "bye";
		secretKeyword = "pineapple";
		response = "";
		
	}
	
	public void talk(String response) 
	{
		ChatbotMain.print("So you like fast food, huh?");
		response = ChatbotMain.getInput();
		if (response.toLowerCase().equals("no") || !ChatbotMain.noNegations(response.toLowerCase(), 0))
		{
			ChatbotMain.print("Well, it was nice talking to you, " + ChatbotMain.chatbot.getUsername() + "!");
			ChatbotMain.chatbot.getBen().talk("");
		}
		while(!(response.toLowerCase().equals(goodbyeKeyword))) 
		{
			if(ChatbotMain.findKeyword(response.toLowerCase(), secretKeyword, 0) > -1) 
			{
				ChatbotMain.print("Pineapple on pizza is a crime against humanity. We're done here.");
				break;
			}
			else 
			{
				ChatbotMain.print("Yeah. That's pretty cool. But there are things I like even more. Tell me something else");
				response = ChatbotMain.getInput();
			}
		}
		//access variables from other classes
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