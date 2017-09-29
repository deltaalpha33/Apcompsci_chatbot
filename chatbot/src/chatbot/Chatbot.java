package chatbot;

public class Chatbot {
	private String username;
	private boolean chatting;
	private Topic Dimitris;

	public Chatbot() {
		Dimitris = new ChatbotDimitris();
		username = "Unknown User";
		chatting = true;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public Topic getDimitris() {
		return this.Dimitris;
	}
	public void startChatting() {
		//whenever you print or get input, use these methods
		ChatbotMain.print("Hi! I am an itelligent machine that can respond to your input. Tell me your name.");
		username = ChatbotMain.getInput();
		
		while(chatting) {
			ChatbotMain.print("What would you like to talk about?");
			String response = ChatbotMain.getInput();
			if(Dimitris.isTriggered(response)) {
				this.chatting = false;
				Dimitris.talk(response);
			}
			else {
				ChatbotMain.print("I'm sorry. I don't understand. I never said I was perfect.");
			}
		}
	}
}
