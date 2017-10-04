package chatbot;

public class ChatbotDimitris implements Topic {
	private String[] greetings = {"hi", "hello"};
	private String[] triggerWords = {"choose", "select"};
	private String[] finishedKeywords = {"done", "finished"};
	private String[] affirmativeKeywords = {"yes", "yeah"};
	private String response = "";
	private boolean finished = false;
	private Chatbot chatbot;
	
	public ChatbotDimitris(Chatbot chatbot) {
		this.chatbot = chatbot;
	}

	@Override
	public void talk(String response) {
		
		while(!this.finished) {
			ChatbotMain.print("What food would you like to select?");
			this.response = ChatbotMain.getInput();
			
			ChatbotMain.print("have you selected all of your foods?");
			this.response = ChatbotMain.getInput();
			
			this.finished = this.findKeywords(this.finishedKeywords) || this.findKeywords(this.affirmativeKeywords);
		}
		ChatbotMain.print("YOU HAVE SELECTED ALL YOUR INGREDIENTS :)");
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
	

}
