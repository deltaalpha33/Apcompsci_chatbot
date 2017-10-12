package chatbot;

public class ChatbotAchilles implements Topic{
	
	private String[] keywords;
	private String goodbyeKeyword;
	private String secretKeyword;
	private String response;
	private String[] tools;
	private boolean firstCheck;
	
	//private boolean chatting;
	//private String[] recipe foods
	
	public ChatbotAchilles(Chatbot info) {
		String[] temp = {"cooking", "how to cook", "ready to cook", "prepare food", "make food", "cook","I want to cook"};
		keywords = temp;
		goodbyeKeyword = "bye";
		secretKeyword = "pug";
		this.response = "";
		this.tools = new String[info.getFoodList()[0].getCookingTools().length];
		for(int i = 0; i < tools.length;i++) {
			tools[i] = info.getFoodList()[0].getCookingTools()[i].getName();
		}
		firstCheck = true;
	}
	
	public void talk(String response) {
		ChatbotMain.print("How are you going to cook your material?");
		
		for(int i = 0; i < this.tools.length; i++){
			if(response.toLowerCase().equals(tools[i])){
				ChatbotMain.print("Nice you are correct.");
			}
		
		}
		
		
		String[] insults1 = {"are you sure??", "what the ...", "you're joking right?"};
		String[] insults2 = {"don't be so stupid", "are you freaking serious?!", "haha you are dumb"};
		String[] insults3 = {"you don't know the essentials of cooking", "shut up", "stop now you are embarrasing yourself"};
		
		int wrongResponseCount = 0;
		
		
		while(!(response.equals(goodbyeKeyword))){
			for(int i = 0; i < tools.length;i++) {
				if(ChatbotMain.findKeyword(response.toLowerCase(), this.tools[i].toLowerCase(), 0) > -1){
					ChatbotMain.print("Very Good, You know what tools are needed for cooking!");
					response = ChatbotMain.getInput();
					}
					else if(!firstCheck){
						wrongResponseCount++;
							if(wrongResponseCount <= 3){
								ChatbotMain.print(insults1[(int) (Math.random() * insults1.length)]);
							}
							else if(wrongResponseCount <= 6 && wrongResponseCount > 3){
								ChatbotMain.print(insults2[(int) (Math.random() * insults2.length)]);
							}
							else if(wrongResponseCount <= 9 && wrongResponseCount > 6){
								ChatbotMain.print(insults3[(int) (Math.random() * insults3.length)]);
							}
							response = ChatbotMain.getInput();
							
						}
				firstCheck = false;
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
	
	

	

	
}