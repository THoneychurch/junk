/**
 * The Pizza Order class represents the main ordering window as
 * the stage before the checkout window.
 *  
 * @author Tristan Honeychurch
 * @version 1.0, (May 15, 2015 - June 04 2015)
 */

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

class PizzaOrder extends JFrame implements ComponentListener, MouseListener, ActionListener{

	private static final long serialVersionUID = 1L; // set serialVersionUID
	private Pizza pizza = new Pizza(); // create new 'Pizza' class 'pizza'
	private PizzaOrder_Checkout checkout = new PizzaOrder_Checkout(pizza, this); // create new PizzaOrder_Checkout class 'checkout' with the 'pizza' class and 'this' class in the parameters
	private PizzaOrder_Help help = new PizzaOrder_Help("basics"); // create new PizzaOrder_Help class 'help'
	
	private JPanel pnl_header, pnl_content, pnl_size, pnl_sizeList, pnl_topping, pnl_toppingList, pnl_payment, pnl_paymentOption, pnl_paymentDetails, pnl_footer; // declare JPanel's
	private JLabel lbl_sizeS, lbl_sizeM, lbl_sizeL, lbl_total; // Declare JLabel's
	private JTextField txt_firstName, txt_streetAddress, txt_phoneNo; // Declare JTextField's
	private JCheckBox[] topping = new JCheckBox[18]; // Declare JCheckBox Array 'topping'
	private JRadioButton opt_pickup, opt_delivery; // declare JRadioButton's
	private JButton btn_addPizza, btn_help, btn_clear, btn_checkout; // Declare JButton's

	private String[] toppingList = pizza.getToppingList(); // declare String Array 'toppingList' with values from 'pizza' class
 	private double[] toppingPrice = pizza.getToppingPrice(); // declare Double Array 'toppingPrice' with values from 'pizza' class
	private int order_limit = pizza.getOrderLimit(); // declare 'order_limit' from 'pizza' Class
	public String[][] order =pizza.getOrder(); // declare multidimensional array 'order' used for the order collection from 'pizza' class
	public double[][] amount = pizza.getAmount(); // declare multidimensional array 'amount' used for the order price collection from 'pizza' class

	private int order_no = 0; // declare 'order_no' used for the pizza number
 	private double total = 0.00; // declare 'total' double used for the order total
 	private boolean delivery_option = false; // declare 'deliver_option' used to identify if delivery option selected
 	
	ButtonGroup payment_option = new ButtonGroup(); // declare ButtonGrounp 'payment_option'

	ImageIcon title = new ImageIcon(getClass().getClassLoader().getResource("images/title.png")); // declare 'title' ImageIcon with image
	ImageIcon size_small = new ImageIcon(getClass().getClassLoader().getResource("images/size_small.png")); // declare 'small_size' ImageIcon with image
	ImageIcon size_medium = new ImageIcon(getClass().getClassLoader().getResource("images/size_medium.png")); // declare 'medium_size' ImageIcon with image
	ImageIcon size_large = new ImageIcon(getClass().getClassLoader().getResource("images/size_large.png")); // declare 'large' ImageIcon with image
	ImageIcon payment_notice = new ImageIcon(getClass().getClassLoader().getResource("images/paymentNote.png")); // declare 'large' ImageIcon with image
	
	Container base = getContentPane(); // declare Container 'base' from ContentPane

	public PizzaOrder(){;
		
		this.addComponentListener(this); // add ComponentListner handler to 'this' class
		base.add(pnl_content = new JPanel(new GridBagLayout()), BorderLayout.NORTH); // Add 'pnl_content' to base Container with BorderLayout
			
		GridBagConstraints grid = new GridBagConstraints(); // declare 'grid' GridBagConstraints
		grid.insets = new Insets(0, 10, 9, 10); // set 'grid' insets
		grid.weightx = 1; // set 'grid' X weight value
		grid.weighty = 0; // set 'grid' Y weight value
		grid.fill = GridBagConstraints.HORIZONTAL; // set 'grid' fill value

		// HEADER 
		grid.gridy = 1; // set 'grid' Y cell value
		pnl_content.add(pnl_header = new JPanel(), grid); // add 'pnl_header' to 'pnl_content' with 'grid' layout properties
		pnl_header.add(new JLabel(title, JLabel.CENTER)); // add 'lbl_title' JLabel to 'pnl_header' with JLabel properies
		
		// PIZZA SIZE
		grid.gridy = 2; // set 'grid' Y cell value
		pnl_content.add(pnl_size = new JPanel(), grid); // add 'pnl_size' to 'pnl_content' with 'grid' layout properties
		pnl_size.setBorder(BorderFactory.createTitledBorder("Select Size")); // create border with text
		pnl_size.add(pnl_sizeList = new JPanel()); // add 'pnl_sizeList' to 'pnl_size'
		
		pnl_sizeList.setLayout(new GridLayout(1, 3, 50, 50)); // set new GridLayout properties to 'pnl_sizeList'
		pnl_sizeList.add(lbl_sizeS = new JLabel(size_small)); // add 'lbl_sizeS' JLabel to 'pnl_sizeList'
		lbl_sizeS.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'lbl_sizeS'
		lbl_sizeS.setToolTipText("Click To Select SMALL Sized Pizza"); // set JLabel tooltip text
		lbl_sizeS.addMouseListener(this); // add MouseListener to 'lbl_sizeS'
		
		pnl_sizeList.add(lbl_sizeM = new JLabel(size_medium)); // add 'lbl_sizeM' JLabel to 'pnl_sizeList'
		lbl_sizeM.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'lbl_sizeM'
		lbl_sizeM.setToolTipText("Click To Select MEDIUM Sized Pizza"); // set JLabel tooltip text
		lbl_sizeM.addMouseListener(this); // add MouseListener to 'lbl_sizeM'
		
		pnl_sizeList.add(lbl_sizeL = new JLabel(size_large)); // add 'lbl_sizeL' JLabel to 'pnl_sizeList'
		lbl_sizeL.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'lbl_sizeL'
		lbl_sizeL.setToolTipText("Click To Select LARGE Sized Pizza"); // set JLabel tooltip text
		lbl_sizeL.addMouseListener(this); // add MouseListener to 'lbl_sizeL'
		
		// PIZZA TOPPINGS
		grid.gridy = 3; // set 'grid' Y cell value 
		pnl_content.add(pnl_topping = new JPanel(), grid); // add 'pnl_topping' to 'pnl_content' with 'grid' layout properties
		pnl_topping.setBorder(BorderFactory.createTitledBorder("Select Topping")); // create border with text
		pnl_topping.add(pnl_toppingList = new JPanel()); // add 'pnl_toppingList' to 'pnl_topping'
		
		pnl_toppingList.setLayout(new GridLayout(6, 3, 30, 10)); // set new GridLayout properties to 'pnl_toppingList'
		
		for(int i = 0; i < toppingList.length; i++){ // LOOP 0 to 'toppingList' length - Adding topping checkboxes to panel
			String price = Double.toString(toppingPrice[i]); // parse 'toppingPrice[i]' DOUBLE to 'price' STRING
 			topping[i] = new JCheckBox(toppingList[i] + " ($" + price + ")"); // declare JCheckBox 'topping' with 'toppingList[i]' & 'price' values
			pnl_toppingList.add(topping[i]); // add 'topping' JCheckBox to 'pnl_toppingList'
			topping[i].setFont(new Font("Verdana", Font.PLAIN,10)); // set font of 'topping' JCheckBox
			topping[i].setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'topping'
			topping[i].addActionListener(this); // add ActionListener of 'topping[i]'
			topping[i].setEnabled(false); // set Enabled of 'topping' to false
		}
		
		// PAYMENT
		grid.gridy = 4; // set 'grid' Y cell value
		pnl_content.add(pnl_payment = new JPanel(), grid); // add 'pnl_payment' to 'pnl_content' with 'grid' layout properties
		pnl_payment.setBorder(BorderFactory.createTitledBorder("Payment Options")); // create border with text
		pnl_payment.setPreferredSize(new Dimension(0, 155)); // set Preferred Size of 'pnl_payment' to prevent elements being cut off from the bottom
		
		pnl_payment.add(pnl_paymentOption = new JPanel()); // add 'pnl_paymentOption' to 'pnl_payment'
		pnl_paymentOption.setLayout(new GridLayout(1, 2, 20, 0)); // set new GridLayout properties to 'pnl_paymentOption'
		pnl_paymentOption.add(opt_pickup = new JRadioButton("Pickup", false)); // add 'opt_pickup' JRadioButton to 'pnl_paymentOption'
		pnl_paymentOption.add(opt_delivery= new JRadioButton("Delivery (Additional $1.00)", false)); // add 'opt_delivery' JRadioButton to 'pnl_paymentOption'
		payment_option.add(opt_pickup); // add 'opt_pickup' JRadioButton to 'payment_option' ButtonGroup
		payment_option.add(opt_delivery); // add 'opt_delivery' JRadioButton to 'payment_option' ButtonGroup 
		opt_pickup.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'opt_pickup'
		opt_pickup.addActionListener(this); // add ActionListener to 'opt_pickup'
		opt_pickup.setEnabled(false); // setting 'opt_pickup' enabled FALSE
		opt_delivery.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'opt_delivery'
		opt_delivery.addActionListener(this); // add ActionListener to 'opt_pickup'
		opt_delivery.setEnabled(false); // set Enabled to 'opt_delvery' FALSE

		pnl_payment.add(new JLabel(payment_notice)); // add 'pnl_paymentNotice' with 'payment_notice' JLabel to 'pnl_payment'
		
		pnl_payment.add(pnl_paymentDetails = new JPanel()); // add 'pnl_paymentDetails' to 'pnl_payment'
		pnl_paymentDetails.setLayout(new GridLayout(3, 2, 10, 5)); // set new GridLayout properties to 'pnl_paymentDetails'

		pnl_paymentDetails.add(new JLabel("First Name: ", JLabel.RIGHT)); // add new JLabel to 'pnl_paymentDetails' with Jlabel properties
		pnl_paymentDetails.add(txt_firstName = new JTextField(20)); // add new JTextfield to 'pnl_paymentDetails' with JTextfield properties
		txt_firstName.setFont(new Font("Verdana", Font.PLAIN, 12)); // add new font to 'txt_firstName'
		txt_firstName.setEditable(false); // setting 'txt_firstName' editable FALSE
		txt_firstName.getDocument().addDocumentListener(new DocumentListener() { // add DocumentListener handler to 'txt_firstName'
			public void changedUpdate(DocumentEvent e) {} // when 'txt_firstName' text get auto updated
			public void removeUpdate(DocumentEvent e) { // when 'txt_firstName' text gets removed
				pizza.setName(txt_firstName.getText());	 // call 'pizza' class function
			}
			public void insertUpdate(DocumentEvent e) { // when 'txt_firstName' text gets inserted
				pizza.setName(txt_firstName.getText());	// call 'pizza' class function		 
			}
		});

		pnl_paymentDetails.add(new JLabel("Delivery Address: ", JLabel.RIGHT)); // add new JLabel to 'pnl_paymentDetails' with JLabel properties
		pnl_paymentDetails.add(txt_streetAddress = new JTextField(20)); // add new JTextfield to 'pnl_paymentDetails' with JLabel properties
		txt_streetAddress.setFont(new Font("Verdana", Font.PLAIN, 12)); // add new font to 'txt_streetAddress'
		txt_streetAddress.setEditable(false); // set 'txt_streetAddress' editable FALSE
		txt_streetAddress.addMouseListener(this); // add MouseListener handler to 'txt_streetAddress'
		txt_streetAddress.addFocusListener(new FocusListener() { // add FocusListener handler to 'txt_streetAddress'
			public void focusGained(FocusEvent e) { // when mouse click focus gained on to 'txt_streetAddress'
				if(txt_streetAddress.getText().contains("e.g. 123 Fake St, Your Suburb, 1234")){ 
			    	txt_streetAddress.setText(""); // remove text from 'txt_streetAddress'
			    }
			}
			public void focusLost(FocusEvent e) {} // when mouse focus lost from 'txt_streetAddress'
		}); 
		txt_streetAddress.getDocument().addDocumentListener(new DocumentListener() { // add DocumentListener handler to 'txt_streetAddress'
			public void changedUpdate(DocumentEvent e) {} // when 'txt_streetAddress' text gets auto updated
			public void removeUpdate(DocumentEvent e) { // when 'txt_streetAddress' text gets removed
				pizza.setAddress(txt_streetAddress.getText()); // call 'pizza' class function
			}
			public void insertUpdate(DocumentEvent e) { // when 'txt_streetAddress' text gets inserted
				pizza.setAddress(txt_streetAddress.getText()); // call 'pizza' class function				 
			}
		});

		pnl_paymentDetails.add(new JLabel("Phone No: ", JLabel.RIGHT)); // add new JLabel to 'pnl_paymentDetails' with JLabel properties
		pnl_paymentDetails.add(txt_phoneNo = new JTextField(20)); // add JTextField to 'pnl_paymentDetails' with JTextField properties
		txt_phoneNo.setFont(new Font("Verdana", Font.PLAIN, 12)); // add new font to 'txt_phoneNo'
		txt_phoneNo.setEditable(false); // set 'txt_phoneNo' editable FALSE
		txt_phoneNo.getDocument().addDocumentListener(new DocumentListener() { // add DocumentListener handler to 'txt_phoneNo'
			public void changedUpdate(DocumentEvent e) {} // when 'txt_phoneNo' text gets auto updated
			public void removeUpdate(DocumentEvent e) { // when 'txt_phoneNo' text gets removed
				pizza.setNumber(txt_phoneNo.getText());	// call 'pizza' class function
			}
			public void insertUpdate(DocumentEvent e) { // when 'txt_phoneNo' text gets inserted
				pizza.setNumber(txt_phoneNo.getText());	// call 'pizza' class function
			}
		});	

		// FOOTER
		grid.gridy = 5; // set 'grid' Y cell value
		pnl_content.add(pnl_footer = new JPanel(), grid); // add 'pnl_footer' to 'pnl_content' with 'grid' layout properties
		pnl_footer.setLayout(new GridLayout(1, 5, 5, 0)); // add new GridLayout properties to 'pnl_footer'
		pnl_footer.add(btn_addPizza = new JButton("Add Pizza")); // add 'btn_clear' to 'pnl_footer'
		pnl_footer.add(lbl_total = new JLabel("Total: $0.00")); // add new JLabel to 'pnl_footer'
		pnl_footer.add(btn_help = new JButton("Help")); // add new JButton 'btn_help' to 'pnl_footer'
		pnl_footer.add(btn_clear = new JButton("Clear")); // add 'btn_clear' to 'pnl_footer'
		pnl_footer.add(btn_checkout = new JButton("Checkout")); // add 'btn_proceed' to 'pnl_footer'
		btn_addPizza.setToolTipText("Click To Add Another Pizza To Current Order"); // set JLabel tooltip text
		btn_addPizza.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'btn_addPizza'
		btn_addPizza.addActionListener(this); // add ActionListener handler to 'btn_addPizza'
		btn_addPizza.setEnabled(false); // set 'btn_addPizza' enabled FALSE
		btn_help.setToolTipText("Click To Open The Help Window"); // set JLabel tooltip text
		btn_help.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'btn_help'
		btn_help.addActionListener(this); // add ActionListener handler to 'btn_help'
		btn_clear.setToolTipText("Click To Clear Current Pizza In Order"); // set JLabel tooltip text
		btn_clear.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'btn_clear'
		btn_clear.addActionListener(this); // add ActionListner handler to 'btn_clear'
		btn_checkout.setCursor(new Cursor(Cursor.HAND_CURSOR)); // set Cursor Type of 'btn_checkout'
		btn_checkout.setToolTipText("Click To Proceed To Checkout"); // set JLabel tooltip text
		btn_checkout.addActionListener(this); // add ActionEvent handler to 'btn_checkout'
		btn_checkout.setEnabled(false); // set 'btn_checkout' enabled FALSE
	}

	// CLICK EVENTS
	public void actionPerformed(ActionEvent e){ 
		
		// Clear Button
		if(e.getSource() == btn_clear){ // IF Source is 'btn_clear'

			unlockSelection(false); // call unlockSelection function
			for(int i = 0; i <= toppingList.length; i++){ 
				order[order_no][i] = null; // clear current order from array 'order' at 'order_no'
				pizza.setOrder(order, amount); // call 'pizza' class function
				total -= amount[order_no][i]; // subtract current 'ammount[order_no][i]' from 'total' 
				amount[order_no][i] = 0; // clear current order amount from array 'amount' at 'order_no'	
			}
			pizza.setTotal(total); // set 'pizza' class function
			checkout.updateList(order, amount); // update checkout receipt
			lbl_total.setText("Total: $" + String.format("%.2f", total)); // set text of 'lbl_total'	
		}
		
		// Button Group
		if(e.getSource() == opt_pickup || e.getSource() == opt_delivery){ // IF source is 'opt_pickup' or 'opt_delivery'
			txt_firstName.setEditable(true); // set Editable of 'txt_firstName' TRUE
			txt_phoneNo.setEditable(true); // set Editable of 'txt_phoneNo' TRUE				
			
			if(opt_delivery.isSelected()){ // IF 'opt_delivery' isSelected
				if(delivery_option == false){ // IF delivery option hasn't been chosen
					addTotal(1.00); // add to total
				}
				delivery_option = true; // set 'delivery_option' TRUE
				txt_streetAddress.setEditable(true); // set Editable of 'txt_streetAddress' TRUE
				txt_streetAddress.setText("e.g. 123 Fake St, Your Suburb, 1234"); // set 'txt_streetAddress' text 
				pizza.setAddress("unknown"); // set 'pizza' class function
				txt_streetAddress.setFont(new Font("Verdana", Font.ITALIC, 11)); // add new font to 'txt_streetAddress'
				checkout.updateList(order, amount); // update checkout receipt	
			}
			else{ 
				if(delivery_option == true){ // IF delivery option has been chosen
					minusTotal(1.00); // minus from total 
				}
				delivery_option = false; // set 'delivery_option' FALSE
				txt_streetAddress.setEditable(false); // set Editable of 'txt_streetAddress' TRUE
				txt_streetAddress.setText(""); // set 'txt_streetAddress' text
				txt_streetAddress.setFont(new Font("Verdana", Font.PLAIN, 12)); // set new font to 'txt_streetAddress'
				pizza.setAddress(null); // set 'pizza' class function
				checkout.updateList(order, amount); // update checkout receipt	
			}
		}
		
		// Help
		if(e.getSource() == btn_help){
			help.setSize(600, 400); // set 'help' class size
			help.setTitle("Pizza Order Help"); // set 'help' class titel
			help.setVisible(true); // set 'help' visible TRUE
			help.setLocationRelativeTo(this); // set 'help' location relative to 'this' class
			help.setResizable(false); // set 'help' resizable FALSE
			help.validate(); // validate 'help' class 
		}

		// Checkout
		if(e.getSource() == btn_checkout){
			checkout.setSize(300, 480); // set 'checkout' class size
			checkout.setTitle("Checkout"); // set 'checkout' class title
			checkout.setVisible(true); // set 'checkout' visible TRUE
			checkout.setResizable(false); // set 'checkout' resizable FALSE
			checkout.validate(); // validat 'checkout' class
		}
		
		// Add Pizza
		else if(e.getSource() == btn_addPizza){
			if(order_no == order_limit - 1){ // IF pizza limit exceeds
				JOptionPane.showMessageDialog(null, "Unfortunately You Have Exceeded The Limit Of " + order_limit + " Pizzas", "Pizza Limit Reached", JOptionPane.PLAIN_MESSAGE); // display message dialog
				btn_addPizza.setEnabled(false); // set 'btn_addPizza' enabled FALSE
 			}
			else{
				order_no++; // add to 'order_no'
				unlockSelection(false); // set unlockSelection function FALSE
			}
		}
		
		// CheckBoxes
		else{
			for(int i = 0; i < topping.length; i++){ // LOOP 0 to 'toppingList' length
				if(e.getSource() == topping[i]){
					if(topping[i].isSelected()){ // IF current CheckBox 'topping[i]' isSelected
						addTotal(toppingPrice[i]); // add to total 'toppingPrice[i]'
						order[order_no][i + 1] = toppingList[i]; // add to array 'order'
						pizza.setOrder(order, amount); // set 'pizza' class function
						amount[order_no][i + 1] = toppingPrice[i]; // add to array 'amount'		
						checkout.updateList(order, amount); // update checkout receipt				
					}
					else{
						order[order_no][i + 1] = null; // clear array 'order' from current topping 'i' + 1
						pizza.setOrder(order, amount); // set 'pizza' class function
						amount[order_no][i + 1] = 0; // clear array 'amount' from current topping 'i' + 1
						minusTotal(toppingPrice[i]); // minus from total 'toppingPrice[i]'
						checkout.updateList(order, amount); // update checkout receipt
					}
				}
			}
		}
	}
	
	// MOUSE CLICK EVENTS
	public void mouseClicked(MouseEvent e) { 
		
		if(order[order_no][0] == null){ 	

			unlockSelection(true); // call unlockSelection function
			
 			if(e.getSource() == lbl_sizeS){
				double cost = 4.00; // Cost of item
				addTotal(cost); // add to total
				order[order_no][0] = "lbl_sizeS"; // add to array 'order'
				pizza.setOrder(order, amount); // set 'pizza' class function
				amount[order_no][0] = cost; // add to array 'amount'
				checkout.updateList(order, amount); // update checkout receipt
			}
			else if(e.getSource() == lbl_sizeM){
				double cost = 6.00; // Cost of item
				addTotal(cost); // add to total
				order[order_no][0] = "lbl_sizeM"; // add to array 'order'
				pizza.setOrder(order, amount); // set 'pizza' class function
				amount[order_no][0] = cost; // add to array 'amount'
				checkout.updateList(order, amount); // update checkout receipt
			}
			else{
				double cost = 8.00; // Cost of item
				addTotal(cost); // add to total
				order[order_no][0] = "lbl_sizeL"; // add to array 'order'
				pizza.setOrder(order, amount); // set 'pizza' class function
				amount[order_no][0] = cost; // add to array 'amount'
				checkout.updateList(order, amount); // update checkout receipt
			}
		}
		
		else if(e.getSource() == txt_streetAddress){
			if(txt_streetAddress.getText().contains("e.g. 123 Fake St, Your Suburb, 1234")){
				txt_streetAddress.setText(""); // clear 'txt_streetText' Text Field
				txt_streetAddress.setFont(new Font("Verdana", Font.PLAIN, 11)); // add new font to 'txt_streetAddress'
			}
		}
	}
	
	/**
	 * Function used to lock/unlock elements such as Toppings and Payment details.
	 * 
	 * @param b Boolean of locked or unlocked (true or false)
	 */
	// UNLOCK ORDERING ELEMENTS
	public void unlockSelection(boolean b){

		if(b == false){ // IF boolean is FALSE
			payment_option.clearSelection(); // clear selection of 'payment_option' button group
			delivery_option = false; // set the customer 'delivery_option' selected choice to FALSE
			txt_firstName.setEditable(false); // set Editable of 'txt_firstName' FALSE
			txt_streetAddress.setEditable(false); // set Editable of 'txt_streetAddress' FALSE
			txt_phoneNo.setEditable(false); // set Editable of 'txt_phoneNo' FALSE
			lbl_sizeS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_small.png"))); // set ImageIcon of 'lbl_sizeS'
			lbl_sizeM.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_medium.png"))); // set ImageIcon of 'lbl_sizeM'	
			lbl_sizeL.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_large.png"))); // set ImageIcon of 'lbl_sizeL'
			if(pizza.getAddress() != null){
				pizza.setAddress(null); // set 'pizza' class function
				minusTotal(1.00); // set minusTotal function
			}			
			pizza.setTotal(total); // set 'pizza' class function
			checkout.updateList(order, amount); // update checkout receipt
		}

		for(int i = 0; i < topping.length; i++){ // LOOP 0 to 'toppingList' length
			topping[i].setEnabled(b); // set Enabled to 'toppingList[i]' CheckBox FALSE
			
			if(b == false){ // IF boolean is FALSE
				topping[i].setSelected(false); // set Selected of 'toppingList[i]' CheckBox FALSE
			}
		}

		btn_checkout.setEnabled(b);// set 'btn_checkout' enabled 'b'
		opt_pickup.setEnabled(b); // set 'opt_pickup' enabled 'b'
		opt_delivery.setEnabled(b); // set 'opt_delivery' enabled 'b'
		btn_addPizza.setEnabled(b); // set 'btn_addPizza' enabled 'b'

		if(order_no > 0){ // IF current pizza number is over 0
			btn_checkout.setEnabled(true); // set 'btn_checkout' enabled TRUE
		}
	}
	
	// MOUSE ENTERING EVENTS
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == lbl_sizeS){
			lbl_sizeS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_small_selected.png"))); // set 'lbl_sizeS' to new ImageIcon
		}
		if(e.getSource() == lbl_sizeM){
			lbl_sizeM.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_medium_selected.png"))); // set 'lbl_sizeM' to new ImageIcon			
		}
		else if(e.getSource() == lbl_sizeL){
			lbl_sizeL.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_large_selected.png"))); // set 'lbl_sizeL' to new ImageIcon			
		}
	}
	
	// MOUSE EXITED EVENTS
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource() == lbl_sizeS && order[order_no][0] != "lbl_sizeS"){
			lbl_sizeS.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_small.png"))); // set 'lbl_sizeS' to new ImageIcon
		}
		if(e.getSource() == lbl_sizeM && order[order_no][0] != "lbl_sizeM"){
			lbl_sizeM.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_medium.png"))); // set 'lbl_sizeM' to new ImageIcon			
		}
		else if(e.getSource() == lbl_sizeL && order[order_no][0] != "lbl_sizeL"){
			lbl_sizeL.setIcon(new ImageIcon(getClass().getClassLoader().getResource("images/size_large.png"))); // set 'lbl_sizeL' to new ImageIcon			
		}
	}

	// COMPONENT MOVED EVENT
	public void componentMoved(ComponentEvent e){
        int x = this.getX() + this.getWidth() + 15; // set int 'x' the current window Y axis location + 15
        int y = this.getY() + 110; // set int 'y' the current windows Y axis location + 110
        checkout.setDialogLocation(x, y); // call 'checkout' function
    }
	
	/**
	 * Adding a cost to the order total.
	 * 
	 * @param p double to add to Total
	 */
	// ADD TO TOTAL
	public void addTotal(double p){
		total += p; // add price to 'total'
		lbl_total.setText("Total: $" + String.format("%.2f", total)); // set text of 'lbl_total'
		pizza.setTotal(total); // set 'pizza' class function
	}
	
	/**
	 * Subtracting a cost from the order Total.
	 * 
	 * @param p double to subtract from Total
	 */
	// SUBTRACT FROM TOTAL
	public void minusTotal(double p){
		total -= p; // add price to 'total'
		lbl_total.setText("Total: $" + String.format("%.2f", total)); // set text of 'lbl_total'
		pizza.setTotal(total); // set 'pizza' class function
	}
	
	/**
	 * Changing layout of order elements for a specific event.
	 * 
	 * @param param String of event being set.
	 */
	// EDITING ORDER WINDOW LAYOUT
	public void editOrder(String param){

		if(param == "reset"){
			for(int i = 0; i < order.length; i++){
				for(int n = 0; n < order[i].length; n++){
					order[i][n] = null; // clear current order from array 'order' at 'order_no'
					amount[i][n] = 0; // clear current order amount from array 'amount' at 'order_no'	
				}
			}
			unlockSelection(false);
			total = 0.00;
			order_no = 0;
			lbl_total.setText("Total: $" + String.format("%.2f", total)); // set text of 'lbl_total'
			btn_clear.setEnabled(true);
			pizza.setTotal(total); // set 'pizza' class function
			pizza.setOrder(order, amount); // set 'pizza' class function
			checkout.updateList(order, amount);
		}

		else if(param == "complete"){
			btn_addPizza.setEnabled(false);
			btn_clear.setEnabled(false);
			payment_option.clearSelection(); // clear selection of 'payment_option' button group
			txt_firstName.setEditable(false); // set Editable of 'txt_firstName' FALSE
			txt_streetAddress.setEditable(false); // set Editable of 'txt_streetAddress' FALSE
			txt_phoneNo.setEditable(false); // set Editable of 'txt_phoneNo' FALSE
			opt_pickup.setEnabled(false); // set Enabled of 'opt_pickup' FALSE
			opt_delivery.setEnabled(false); // set Enabled of 'opt_delivery' FALSE

			for(int i = 0; i < topping.length; i++){ // LOOP 0 to 'toppingList' length
				topping[i].setEnabled(false); // set Enabled to 'toppingList[i]' CheckBox FALSE
				topping[i].setSelected(false); // set Selected of 'toppingList[i]' CheckBox FALSE
			}
		}
	}

	public void mousePressed(MouseEvent e) {}

	public void mouseReleased(MouseEvent e) {}

	public void componentHidden(ComponentEvent e) {}

    public void componentResized(ComponentEvent e) {}

    public void componentShown(ComponentEvent e) {}
}