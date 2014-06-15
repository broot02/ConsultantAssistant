package root.consultantassistant.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;

public class Order implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3261278709786404836L;

	public enum PaymentType {
		CASH, CHECK, CREDIT_CARD
	};

	static int orderNum = 1;
	private Consultant consultant;
	private PaymentType payment;
	private Customer customer;
	private CreditCard creditCard;
	private LocalDate salesDate;
	private List<OrderLine> orderLine;
	private double subTotal;
	private double salesTax;
	private double total;
	private boolean paid;

	public Order() {
		orderLine = new ArrayList<OrderLine>();
	}

	public Order(Customer customer) {
		customer = new Customer();
		setConsultant(new Consultant());
		setSalesDate(new LocalDate());
		orderLine = new ArrayList<OrderLine>();
	}

	public PaymentType getPayment() {
		return payment;
	}

	public void setPayment(final PaymentType payment) {
		this.payment = payment;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(final Customer customer) {
		this.customer = customer;
	}

	public Consultant getConsultant() {
		return consultant;
	}

	public void setConsultant(final Consultant consultant) {
		this.consultant = consultant;
	}

	public LocalDate getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(final LocalDate salesDate) {
		this.salesDate = salesDate;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(final double subTotal) {
		this.subTotal = subTotal;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(final double salesTax) {
		this.salesTax = salesTax;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(final double total) {
		this.total = total;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(final boolean paid) {
		this.paid = paid;
	}

	public boolean addOrderLine(final int quantity, final String description,
			final double unitPrice) {
		//reset subtotal
		setSubTotal(0);
		if (orderLine.add(new OrderLine(quantity, description, unitPrice))) {
			calcTotals();
			return true;
		}
		return false;
	}

	public void setOrderLine(final List<OrderLine> orderLine) {
		//reset totals
		setSubTotal(0);
		setSalesTax(0);
		setTotal(0);
		this.orderLine = orderLine;
		calcTotals();
	}

	public boolean removeOrderLine(final int itemNum) {
		if (orderLine.remove(itemNum - 1) != null) {
			return true;
		}
		return false;
	}

	public OrderLine getOrderLine(final int itemNum) {
		return orderLine.get(itemNum - 1);
	}
	
	public void calcTotals(){
		for(OrderLine order:orderLine){
			subTotal += order.getTotalPrice();
		}
		calcSalesTax(consultant.getState());
	}
	/**
	 * This method will get the State of where the order was placed, and apply the appropriate tax rate for that State.
	 */
	private void calcSalesTax(final String state) {
		switch (state) {
		case "Alabama":
		case "Hawaii":
		case "Georgia":
		case "Louisiana":
		case "New York":
		case "South Dakota":
		case "Wyoming":
			findSalesTax(.04);
			break;
		case "Alaska":
		case "Delaware":
		case "Montana":
		case "New Hampshire":
		case "Oregon":
			findSalesTax(0);
			break;
		case "Arizona":
			findSalesTax(.056);
			break;
		case "Arkansas":
		case "Washington":
			findSalesTax(.065);
			break;
		case "California":
			findSalesTax(.075);
			break;
		case "Colorado":
			findSalesTax(.029);
			break;
		case "Connecticut":
			findSalesTax(.0635);
			break;
		case "District of Columbia":
			findSalesTax(.0575);
			break;
		case "Florida":
		case "Idaho":
		case "Iowa":
		case "Kentucky":
		case "Maryland":
		case "Michigan":
		case "Pennsylvania":
		case "South Carolina":
		case "Vermont":
		case "West Virginia":
			findSalesTax(.06);
			break;
		case "Illinois":
		case "Massachusetts":
		case "Texas":
			findSalesTax(.0625);
			break;
		case "Indiana":
		case "Mississippi":
		case "New Jersey":
		case "Rhode Island":
		case "Tennessee":
			findSalesTax(.07);
			break;
		case "Kansas":
			findSalesTax(.0615);
			break;
		case "Maine":
		case "Nebraska":
			findSalesTax(.0550);
			break;
		case "Minnesota":
			findSalesTax(.06875);
			break;
		case "Missouri":
			findSalesTax(.04225);
			break;
		case "Nevada":
			findSalesTax(.0685);
			break;
		case "New Mexico":
			findSalesTax(.05125);
			break;
		case "North Carolina":
			findSalesTax(.0475);
			break;
		case "North Dakota":
			findSalesTax(.05);
			break;
		case "Ohio":
			findSalesTax(.0575);
			break;
		case "Oklahoma":
			findSalesTax(0.045);
			break;
		case "Utah":
			findSalesTax(.047);
			break;
		case "Virginia":
			findSalesTax(0.043);
			break;
		default:
			findSalesTax(0);
			break;

		}

	}

	private void findSalesTax(final double taxRate) {
		setSalesTax(0);
		setSalesTax(getSubTotal() * taxRate);
		setTotal(salesTax + getSubTotal());
	}

}
