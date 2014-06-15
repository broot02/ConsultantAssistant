package root.consultant.assistant.Test.Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import root.consultantassistant.model.Consultant;
import root.consultantassistant.model.CreditCard;
import root.consultantassistant.model.Customer;
import root.consultantassistant.model.Order;
import root.consultantassistant.model.OrderLine;
import root.consultantassistant.model.CreditCard.CreditCardType;
import root.consultantassistant.model.Order.PaymentType;

public class OrderTest {
	private Consultant consultant;
	private PaymentType payment;
	private Customer customer;
	private CreditCard creditCard;
	private LocalDate salesDate;
	private List<OrderLine> orderLine;
	private double subTotal = 0;
	private double salesTax = 0;
	private double total = 0;
	private boolean paid;
	
	@Before
	public void setUp() throws Exception {
		consultant = new Consultant();
		consultant.setAddress("407 N. Gould St.");
		consultant.setCity("Owosso");
		consultant.setEmail("rootbrad86@gmail.com");
		consultant.setFirstName("Teri");
		consultant.setLastName("root");
		consultant.setUserId("troot");
		consultant.setPassword("password99");
		consultant.setPhoneNumber("(999)999-9999");
		consultant.setState("Michigan");
		consultant.setZip(48867);
		customer = new Customer();
		customer.setAddress("111 W King Rd");
		customer.setCity("Owosso");
		customer.setEmail("rootbrad86@gmail.com");
		customer.setFirstName("Brad");
		customer.setLastName("Root");
		customer.setMailingList(false);
		customer.setPhoneNumber("(999)999-9999");
		customer.setState("Michigan");
		customer.setZipCode(48867);
		creditCard = new CreditCard();
		creditCard.setCardType(CreditCardType.MasterCard);
		creditCard.setExpirationMonth(10);
		creditCard.setExpirationYear(2015);
		creditCard.setCreditCardNumber("5555555555554444");
		creditCard.setSecurityCode("1234");
		salesDate = new LocalDate();
		orderLine = new ArrayList<>();
		
	} 

	@Test
	public void testGetPayment() {
		Order order = new Order();
		order.setPayment(PaymentType.CHECK);
		assertEquals("The payment type returned should be of type check", PaymentType.CHECK, order.getPayment());
	}
	@Test
	public void testSetPayment() {
		Order order = new Order();
		order.setPayment(PaymentType.CHECK);
		assertEquals("The payment type returned should be of type check", PaymentType.CHECK, order.getPayment());
		order.setPayment(PaymentType.CASH);
		assertEquals("The payment type returned should be of type cash", PaymentType.CASH, order.getPayment());
		order.setPayment(PaymentType.CREDIT_CARD);
		assertEquals("The payment type returned should be of type credit card", PaymentType.CREDIT_CARD, order.getPayment());
	}
	@Test
	public void testGetCustomer() {
		Order order = new Order();
		order.setCustomer(customer);
		assertEquals("The customer returned should be the customer variable.",customer,order.getCustomer());
	}
	@Test
	public void testSetCustomer() {
		Order order = new Order();
		order.setCustomer(customer);
		assertEquals("The customer should be set to the customer variable.",customer,order.getCustomer());
	}
	
	@Test
	public void testGetConsultant() {
		Order order = new Order();
		order.setConsultant(consultant);
		assertEquals("The consultant returned be the consultant variable.", consultant, order.getConsultant());
	}
	
	@Test
	public void testSetConsultant() {
		Order order = new Order();
		order.setConsultant(consultant);
		assertEquals("The consultant be set to the consultant variable.", consultant, order.getConsultant());
	}
	
	@Test
	public void testGetSalesDate() {
		Order order = new Order();
		order.setSalesDate(salesDate);
		assertEquals("The sales date should be set to todays date.", salesDate.toString(), order.getSalesDate().toString());
	}
	
	@Test
	public void testSetSalesDate() {
		Order order = new Order();
		order.setSalesDate(salesDate);
		assertEquals("The sales date should be set to todays date.", salesDate.toString(), order.getSalesDate().toString());
		order.setSalesDate(new LocalDate().plusDays(4));
		assertEquals("The sales date should be set to four days from today", new LocalDate().plusDays(4).toString(),order.getSalesDate().toString());
	}
	
	@Test
	public void testGetSubTotal() {
		Order order = new Order();
		order.setConsultant(consultant);
		assertEquals("The subtotal should be 0", 0, order.getSubTotal(),0);
		order.addOrderLine(1,"Test 1", 1.50);
		assertEquals("The subtotal should be 1.50", 1.50, order.getSubTotal(),0);
	}

	@Test
	public void testSetSubTotal() {
		Order order = new Order();
		order.setSubTotal(1.5);
		assertEquals("The subtotal should be 1.50", 1.50, order.getSubTotal(),0);
	}
	
	@Test
	public void testGetSalesTax() {
		Order order = new Order();
		order.setConsultant(consultant);
		assertEquals("The sales tax should be 0", 0, order.getSalesTax(), 0);
		order.addOrderLine(1, "Test 1", 1.50);
		assertEquals("The sales tax should be .09", .09, order.getSalesTax(), .01);
		order.addOrderLine(3, "Test 2", 1.00);
		assertEquals("The sales tax should be .27", .27, order.getSalesTax(), .01);
	}
	
	@Test
	public void testSetSalesTax() {
		Order order = new Order();
		order.setSalesTax(.06);
		assertEquals("The sales tax should be .06", .06, order.getSalesTax(), 0);
	}
	
	@Test
	public void testGetTotal() {
		Order order = new Order();
		order.setConsultant(consultant);
		order.addOrderLine(1, "Test 1", 1.50);
		assertEquals("The total should be 1.59", 1.59, order.getTotal(), 0.01);
		order.addOrderLine(3, "Test 2", 1.00);
		assertEquals("The total should be 4.77", 4.77, order.getTotal(), 0.01);
		
	}

	@Test
	public void testSetTotal() {
		Order order = new Order();
		order.setConsultant(consultant);
		order.addOrderLine(1, "Test 1", 1.50);
		assertEquals("The total should be 1.59", 1.59, order.getTotal(), 0.01);
	}
	
	@Test
	public void testGetCreditCard() {
		Order order = new Order();
		order.setCreditCard(creditCard);
		assertEquals("The credit card returned should be the creditCard variable", creditCard, order.getCreditCard());
	}
	
	@Test
	public void testSetCreditCard() {
		Order order = new Order();
		order.setCreditCard(creditCard);
		assertEquals("The credit card should be set to the creditCard variable", creditCard, order.getCreditCard());
	}
	
	@Test
	public void testIsPaid() {
		Order order = new Order();
		order.setPaid(true);
		assertEquals("The order should have been paid.",true,order.isPaid());
		
	}
	
	@Test
	public void testSetPaid() {
		Order order = new Order();
		order.setPaid(true);
		assertEquals("The order should have been paid.",true,order.isPaid());
	}
	@Ignore
	@Test
	public void testAddOrderLine() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testSetOrderLine() {
		Order order = new Order();
		order.setConsultant(consultant);
		orderLine.add(new OrderLine(1,"Test1",2));
		orderLine.add(new OrderLine(2,"Test2",2.50));
		order.setOrderLine(orderLine);
		assertEquals("The total should be 7.42",7.42,order.getTotal(),.01);
		orderLine.clear();
		orderLine.add(new OrderLine(1,"Test1",2));
		orderLine.add(new OrderLine(2,"Test2",2.50));
		orderLine.add(new OrderLine(1,"Test3", 1));
		order.setOrderLine(orderLine);
		assertEquals("The total should be 8.48",8.48,order.getTotal(),.01);
		
		
	}
	@Ignore
	@Test
	public void testRemoveOrderLine() {
		fail("Not yet implemented");
	}
	@Ignore
	@Test
	public void testGetOrderLine() {
		fail("Not yet implemented");
	}

}
