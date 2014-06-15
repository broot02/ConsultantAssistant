package root.consultantassistant.model;

import java.io.Serializable;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Brad Root Tracks data about an order line.
 */
public class OrderLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8077098222793620452L;
	private final SimpleIntegerProperty quantity;
	private final SimpleStringProperty description;
	private final SimpleDoubleProperty unitPrice;
	private final SimpleDoubleProperty totalPrice;

	public OrderLine(int quantity, String description, double unitPrice) {
		this.quantity = new SimpleIntegerProperty(quantity);
		this.description = new SimpleStringProperty(description);
		this.unitPrice = new SimpleDoubleProperty(unitPrice);
		totalPrice = new SimpleDoubleProperty(unitPrice * quantity);
	}

	public OrderLine(OrderLine orderLine){
		this.description = orderLine.description;
		this.quantity = orderLine.quantity;
		this.unitPrice = orderLine.unitPrice;
		this.totalPrice = orderLine.totalPrice;
	}
	
	public OrderLine() {
		description = new SimpleStringProperty();
		quantity = new SimpleIntegerProperty();
		unitPrice = new SimpleDoubleProperty();
		totalPrice = new SimpleDoubleProperty();
	}

	public int getQuantity() {
		return quantity.get();
	}

	public void setQuantity(int quantity) {
		this.quantity.set(quantity);
		setTotalPrice();
	}

	public String getDescription() {
		return description.get();
	}

	public void setDescription(String description) {
		this.description.set(description);
	}

	public double getUnitPrice() {
		return unitPrice.get();
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice.set(unitPrice);
		setTotalPrice();
	}
	
	public void setTotalPrice(){
		totalPrice.set(quantity.get()*unitPrice.get());
	}
	
	public double getTotalPrice(){
		return totalPrice.get();
	}

}
