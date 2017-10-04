package chatbot;

public class ChatbotDimitris implements Topic {
	private String[] greetings = {"hi", "hello"};
	private String[] finishedKeywords = {"done", "finished"};
	private String[] positiveKeywords = {"thank", "thanks", "great", "perfect"};
	private String response = "";
	private boolean finished = false;
	private Chatbot chatbot;
	
	public ChatbotDimitris(Chatbot chatbot) {
		this.chatbot = chatbot;
	}

	@Override
	public void talk(String response) {
		ChatbotMain.print("Hello this is "  + Chatbot.name + ". Welcome to my interactive terminal. Let us begin with selectin the foods you wish to eat");
		this.response = ChatbotMain.getInput();
		this.finished = this.findKeywords(this.finishedKeywords);
		while(!this.finished) {
			ChatbotMain.print("have you selected all of your foods?");
			this.finished = this.findKeywords(this.finishedKeywords) || this.findKeywords(this.positiveKeywords);
		}
		//acces variables from other classes
		ChatbotMain.print("YOU HAVE SELECTED ALL YOUR INGREDIENTS :)");
	}
	
	public boolean isTriggered(String response) {
		this.response = response;
		return this.findKeywords(this.greetings);	
	}
	
	public boolean findKeywords(String[] keywords) {
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(this.response, keywords[i], 0) > -1) {
				return true;
			}
		}
		return false;
	}
	

}
