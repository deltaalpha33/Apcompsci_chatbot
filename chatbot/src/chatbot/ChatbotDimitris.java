package chatbot;

public class ChatbotDimitris implements Topic {
	private String[] keywords;
	private String goodbyeKeyword;
	private String secretKeyword;
	private String response;
	
	public ChatbotDimitris() {
		String[] temp = {"stuff", "things", "whatever", "nothing"};
		this.keywords = temp;
		this.goodbyeKeyword = "bye";
		this.secretKeyword = "pug";
		this.response = "";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void talk(String response) {
		ChatbotMain.print("Hey! So you want to talk about generic boring things, huh? I love talking about that.");
		response = ChatbotMain.getInput();
		while(ChatbotMain.findKeyword(response, goodbyeKeyword, 0) != -1) {
			if(ChatbotMain.findKeyword(response, secretKeyword, 0) >= 0) {
				ChatbotMain.print("I can't even. I love pugs so much. Wow. You are so cool.");
				response = ChatbotMain.getInput();
			}
			else{
				ChatbotMain.print("Yeah. That's pretty cool. But there are things even more. Tell me something.else");
				response = ChatbotMain.getInput();
			}
		}
		//acces variables from other classes
		ChatbotMain.print("thanks for chatting");
		ChatbotMain.chatbot.startChatting();
	}
	
	public boolean isTriggered(String response) {
		for(int i = 0 ; i < this.keywords.length; i++) {
			//Important on Rubric
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0) {
				return true;
			}
		}
		return false;
	
	}
	

}
