package chatbot;

public class KitchenUtensil implements Namable{
	private int priority = 0;
	private String specialAction = "";
	private String name;
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public String getSpecialAction() {
		return specialAction;
	}
	public void setSpecialAction(String specialAction) {
		this.specialAction = specialAction;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public KitchenUtensil(String name){
		this.name = name;
	}
}
