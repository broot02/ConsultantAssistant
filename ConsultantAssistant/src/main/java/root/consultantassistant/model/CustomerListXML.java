package root.consultantassistant.model;

import java.io.File;
import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CustomerListXML {

	private ArrayList<Customer> customerList;
	private String filePath;

	public CustomerListXML(final String filePath) {
		customerList = new ArrayList<>();
		this.filePath = filePath;
		File file = new File(filePath);
		if(file.exists()){
			readXMLFile();
		}
		Customer.id = customerList.get(customerList.size()-1).getId();
	}

	/*
	 * Adds a <code>Customer</code> to the list of customers. Returns whether or
	 * not the Customer was added successfully. No two customers can possess the same name, and same id. With that said
	 * if that does occur, the customer will not be added to the list.
	 * 
	 * @param customer - the <code>Customer</code> to be added to the list
	 * 
	 * @return if the <code>Customer</code> is successfully added to the customer list return true, otherwise false is returned
	 */
	public boolean addCustomer(final Customer customer) {
		boolean isCurrent = false;
		for(int i = 0; i < customerList.size();i++){
			if(customerList.get(i).equals(customer)){
				isCurrent = true;
				break;
			}
		}
		if(!isCurrent){
			return customerList.add(customer);
		}
		return false;
	}
	
	/**
	 * Removes a <code>Customer</code> from the list of customers. Returns whether
	 * @param customer
	 * @return
	 */
	public boolean removeCustomer(final Customer customer) {
		return customerList.remove(customer);
	}

	public ArrayList<Customer> getCustomerList() {
		return customerList;
	}

	public void setFilePath(final String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public void writeToFile() {

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();

			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			// Create the root element
			Element rootElement = doc.createElement("Customers");
			doc.appendChild(rootElement);
			customerList.forEach((customer) -> {

				// Create the customer and id attribute
					Element customerId = doc.createElement("Customer");
					rootElement.appendChild(customerId);

					Attr attribute = doc.createAttribute("id");
					attribute.setValue(String.valueOf(customer.getId()));
					customerId.setAttributeNode(attribute);

					// Create the customer first name element
					Element firstName = doc.createElement("firstname");
					firstName.appendChild(doc.createTextNode(customer
							.getFirstName()));
					customerId.appendChild(firstName);

					// Create the customer last name element
					Element lastName = doc.createElement("lastname");
					lastName.appendChild(doc.createTextNode(customer
							.getLastName()));
					customerId.appendChild(lastName);

					// Create the customer phone number element
					Element phoneNumber = doc.createElement("phonenumber");
					phoneNumber.appendChild(doc.createTextNode(customer
							.getPhoneNumber()));
					customerId.appendChild(phoneNumber);

					// Create the customer address element
					Element address = doc.createElement("address");
					address.appendChild(doc.createTextNode(customer
							.getAddress()));
					customerId.appendChild(address);

					// Create the customer city element
					Element city = doc.createElement("city");
					city.appendChild(doc.createTextNode(customer.getCity()));
					customerId.appendChild(city);

					// Create the customer zip element
					Element zip = doc.createElement("zip");
					zip.appendChild(doc.createTextNode(Integer.toString(customer.getZipCode())));
					customerId.appendChild(zip);

					// Create the customer state element
					Element state = doc.createElement("state");
					state.appendChild(doc.createTextNode(customer.getState()));
					customerId.appendChild(state);

					// Create the customer email element
					Element email = doc.createElement("email");
					email.appendChild(doc.createTextNode(customer.getEmail()));
					customerId.appendChild(email);

					// Create the customer mailing list element
					Element mailingList = doc.createElement("mailinglist");
					mailingList.appendChild(doc.createTextNode(String
							.valueOf(customer.isMailingList())));
					customerId.appendChild(mailingList);
				});
			// Prepare to write to file
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource dom = new DOMSource(doc);
			StreamResult stream = new StreamResult(filePath);
			transformer.transform(dom, stream);

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}

	}
	
	public void readXMLFile() {

		try {
			File schemaFile = new File("CustomerList.xsd");
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator(); 
			Source XMLFile = new StreamSource(new File(filePath));
			validator.validate(XMLFile);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new File(filePath));

			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Customer");

			for (int i = 0; i < nodeList.getLength(); i++) {
				Node node = nodeList.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element customerNode = (Element) node;
					Customer customer = new Customer();
					customer.setId(Integer.valueOf(customerNode
							.getAttribute("id")));
					customer.setFirstName(customerNode
							.getElementsByTagName("firstname").item(0)
							.getTextContent());
					customer.setLastName(customerNode
							.getElementsByTagName("lastname").item(0)
							.getTextContent());
					customer.setPhoneNumber(customerNode
							.getElementsByTagName("phonenumber").item(0)
							.getTextContent());
					customer.setAddress(customerNode
							.getElementsByTagName("address").item(0)
							.getTextContent());
					customer.setCity(customerNode.getElementsByTagName("city")
							.item(0).getTextContent());
					customer.setZipCode(Integer.parseInt(customerNode
							.getElementsByTagName("zip").item(0)
							.getTextContent()));
					customer.setState(customerNode
							.getElementsByTagName("state").item(0)
							.getTextContent());
					customer.setEmail(customerNode
							.getElementsByTagName("email").item(0)
							.getTextContent());
					customer.setMailingList((customerNode
							.getElementsByTagName("mailinglist").item(0)
							.getTextContent().equals("true") ? true : false));
					customerList.add(customer);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() {
		return customerList.toString();
	}

}
