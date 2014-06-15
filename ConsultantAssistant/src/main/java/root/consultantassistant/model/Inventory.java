package root.consultantassistant.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	private List<String> description;
	private List<Integer> quantity;


	public Inventory(){
		description = new ArrayList<String>();
		quantity = new ArrayList<Integer>();
	}
	public List<String> getDescription() {
		return description;
	}

	public void setDescription(List<String> description) {
		this.description = description;
	}

	public List<Integer> getQuantity() {
		return quantity;
	}

	public void setQuantity(List<Integer> quantity) {
		this.quantity = quantity;
	}


	public void addItem(String description, int quantity){
		if(this.description.contains(description)){
			int loc = this.description.indexOf(description);
			this.quantity.set(loc, this.quantity.get(loc) + quantity);
		}
		else{
			this.description.add(description);
			this.quantity.add(quantity);
		}
	}
	
	public boolean removeItem(String description, int quantity){
		if(this.description.contains(description)){
			int loc = this.description.indexOf(description);
			if(this.quantity.get(loc) >= quantity){
				this.quantity.set(loc, this.quantity.get(loc) - quantity);
				if(this.quantity.get(loc) == 0){//Quantity equals zero remove the item(description, quantity) from the inventory
					this.quantity.remove(loc);
					this.description.remove(loc);
					return true;
				}
				else{
					return true;
				}
			}
		}
		return false;
	}
	

}
