package chatbot;

public class ChatbotAchilles implements Topic{
	
	private String[] keywords;
	private String goodbyeKeyword;
	private String secretKeyword;
	private String response;
	
	
	//private boolean chatting;
	//private String[] recipe foods
	
	public ChatbotAchilles() {
		String[] temp = {"stuff", "things", "whatever", "nothing"};
		keywords = temp;
		goodbyeKeyword = "bye";
		secretKeyword = "pug";
		response = "";
		
	}
	
	public void talk(String response) {
		ChatbotMain.print("Hey! So you want to talk about generic boring things, huh? I love talking about that.");
		response = ChatbotMain.getInput();
		while(!response.toLowerCase().equals(goodbyeKeyword)) {
			if(ChatbotMain.findKeyword(response.toLowerCase(), secretKeyword, 0) > -1) {
				ChatbotMain.print("I can't even. I love pugs so much. Wow. You are so cool.");
				response = ChatbotMain.getInput();
			}else {
				ChatbotMain.print("Yeah. That's pretty cool. But there are things I like even more. Tell me something else");
				response = ChatbotMain.getInput();
			}
		}
		//access variables from other classes
		ChatbotMain.print("Well, it was nice talking to you, " + ChatbotMain.chatbot.getUsername() + "!");
		ChatbotMain.chatbot.getAchilles().talk("");
	}
	
	public boolean isTriggered(String response) {
		for(int i = 0; i < keywords.length; i++) {
			//IMPORTANT(on the rubric)
			if(ChatbotMain.findKeyword(response.toLowerCase(), keywords[i], 0) > -1) {
				return true;
			}
		}
		return false;
	}
	
	
/*
	public void startTalking(String response) {
		ChatbotMain.print("Hmm, that is interesting.");
		chatting = true;
		String lastResponse = "";
		while(chatting) {
			response = ChatbotMain.getInput();
			if(lastResponse.toLowerCase().equals(response.toLowerCase())) {
				ChatbotMain.print("You already said that.");
				
			}
		}
	}
	
	String[] tools = {"oven", "knife", "spoon", "pan", skillet"...};
	String[] insults1 = {"are you sure??", "what the f....", "youre joking right"};
	String[] insults2 = {"don't be so stupid", "are you freaking serious?!", "haha you are mad dumb"};
	String[] insults3 = {"you are a disgrace, you don't know the essentials of cooking", "haha kill yourself", "jesus crist, stop now you are embarrasing yourself"};
	
	int wrongResponseCount = 0;
	
	
	for(int i = 0; i < tools.length(); i++){
		if(response.toLowerCase().equals(tools[i].toLowerCase())){
		ChatbotMain.print("Very Good, You know what tools are needed for cooking!");
		
		}
		else{
			wrongResponseCount++;
				if(wrongResponseCount =< 3){
					ChatbotMain.print(insults1[Math.random() * insults1.length()]);
				}
				else if(wrongResponseCount =< 6 && wrongResponseCount > 3){
					ChatbotMain.print(insults2[Math.random() * insults2.length()]);
				}
				else if(wrongResponseCount =< 9 && wrongResponseCount > 6){
					ChatbotMain.print(insults3[Math.random() * insults3.length()]);
				}
	
			}
	}
*/
	
	
	
}