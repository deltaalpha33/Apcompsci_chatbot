package chatbot;

public class ChatbotAchilles implements Topic{
	
	private String[] keywords;
	private String goodbyeKeyword;
	private String secretKeyword;
	private String response;
	private String tools;
	
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
		ChatbotMain.print("How are you going to cook your material?");
		
		for(int i = 0; i < tools.length(); i++){
			if(ChatbotMain.response.toLowerCase().equals(tools)){
				ChatbotMain.print("Nice you are correct.");
			}
		
		}
		
		
		String[] tools = {"oven", "knife", "spoon", "pan", "skillet"};
		String[] insults1 = {"are you sure??", "what the f....", "youre joking right"};
		String[] insults2 = {"don't be so stupid", "are you freaking serious?!", "haha you are mad dumb"};
		String[] insults3 = {"you are a disgrace, you don't know the essentials of cooking", "haha kill yourself", "jesus crist, stop now you are embarrasing yourself"};
		
		int wrongResponseCount = 0;
		
		
		for(int i = 0; i < tools.length; i++){
			if(response.toLowerCase().equals(tools[i].toLowerCase())){
			ChatbotMain.print("Very Good, You know what tools are needed for cooking!");
			
			}
			else{
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
	
	INGREDIENT_NAMES 
	TOOLS_NAMES
	
	Chatbot.Ingredients[] = ingredients[];
	
	
	
	CookingMaterials(get it from Ben) = tool[];
	
	
	ChatbotMain.print("How are you going to cook your material?");
	
	for(int i = 0; i < tools.length(); i++){
		if(ChatbotMain.findKeyword(response.toLowerCase().equals(tools[i]))){
			ChatbotMain.print("Nice you are correct.");
		}
	
	}
	
	

*/
	
	
	
}