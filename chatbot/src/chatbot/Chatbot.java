package chatbot;

public class Chatbot {
	private String username;
	private boolean chatting;
	private Topic dimitris;
	private Topic achilles;
	private Topic ben;

	public Chatbot() {
		dimitris = new ChatbotDimitris();
		achilles = new ChatbotAchilles();
		ben = new ChatbotBen();
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
		
		while(chatting) {
			ChatbotMain.print("What would you like to talk about?");
			String response = ChatbotMain.getInput();
			if(ben.isTriggered(response)) {
				this.chatting = false;
				ben.talk(response);
			}
			if(dimitris.isTriggered(response)) {
				this.chatting = false;
				dimitris.talk(response);
			}
			
			if(achilles.isTriggered(response)) {
				this.chatting = false;
				achilles.talk(response);
			}
			
			ChatbotMain.print("I'm sorry. I don't understand. I never said I was perfect.");

		}
	}
}
