
import acm.graphics.*;
import acm.program.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class BoxDiagram extends GraphicsProgram {

	// practice instance variable
	private JButton hi;

	private JTextField input;
	private JLabel textFieldLabel;

	private JButton clear;
	private JButton remove;
	private JButton add;

	private GPoint whereMouseWasPressed;
	private GObject theObject;

	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;

	// keeps track of all the boxes and their associated strings.
	private HashMap<String, GObject> boxes = new HashMap<>();

	public void init() {

		// practice();
		demonstration();

	}

	public void demonstration() {

		initializeButtonsAndLabel();

		addButtonsAndTextField();

		addActionListeners();
		addMouseListeners();

	}

	public void practice() {

		// create a new JButton with "Hi" on it.
		hi = new JButton("Hi");
		// 14 is the amount of characters the text field will have room for
		input = new JTextField(14);
		// JText field object now listens for actions and it listens for actions
		// on itself by passing in 'this' as an argument.

		JLabel textFieldLabel = new JLabel("Name:");
		input.addActionListener(this);
		// GRect testRect = new GRect(200, 300, 100, 100);

		add(hi, SOUTH);
		add(textFieldLabel, SOUTH);
		add(input, SOUTH);

		addActionListeners();
	}

	public void actionPerformed(ActionEvent e) {

		handleAnActionEvent(e.getSource());

		/*
		 * // if a button was licked // this will return it's name String cmd =
		 * e.getActionCommand();
		 * 
		 * // this returns a reference to the object itself Object theEvent =
		 * e.getSource();
		 * 
		 * // redundant statements if(/*cmd.equals("Hi") || theEvent == hi) {
		 * println("You clicked the \"Hi\" button"); }
		 * 
		 * if(theEvent == input) { // do something with the input from the text field }
		 * 
		 */

	}

	public GCompound getBox(String nameOfBox) {
		// create GCompound object
		GCompound box = new GCompound();
		// create a GRect object to act as the box outline.
		GRect outline = new GRect(BOX_WIDTH, BOX_HEIGHT);
		// create a GLabel with nameOfBox as its content
		GLabel label = new GLabel(nameOfBox);
		// add the GRect object to the GCompound object and center it within the
		// GCompound object.
		box.add(outline, -BOX_WIDTH / 2, -BOX_HEIGHT / 2);

		box.add(label, -label.getWidth() / 2, label.getAscent() / 2);
		System.out.println(label.getWidth());
		System.out.println(label.getAscent());

		// add the String key and it's pair, the box object to the hashmap.
		boxes.put(nameOfBox, box);

		return box;
	}

	public void initializeButtonsAndLabel() {
		textFieldLabel = new JLabel("Name:");
		input = new JTextField(14);
		// allow the text field to generate event objects
		input.addActionListener(this);

		add = new JButton("Add");
		remove = new JButton("Remove");
		clear = new JButton("Clear");

	}

	public void addButtonsAndTextField() {
		add(textFieldLabel, SOUTH);
		add(input, SOUTH);
		add(add, SOUTH);
		add(remove, SOUTH);
		add(clear, SOUTH);

	}

	public void handleAnActionEvent(Object e) {
		// if the add button was clicked
		if (e == add) {

			String nameOfBox = input.getText();
			GCompound theBox = getBox(nameOfBox);

			// add the box to the center of the canvas
			add(theBox, getWidth() / 2, getHeight() / 2);

		}
		// if the remove button was clicked
		if (e == remove) {
			String nameOfBox = input.getText();

			if (boxes.containsKey(nameOfBox)) {
				System.out.println(nameOfBox);
				GObject theBox = boxes.get(nameOfBox);
				remove(theBox);

				// update contents of the HashMap
				boxes.remove(nameOfBox);
			}
		}

		if (e == clear) {
			Iterator<String> it = boxes.keySet().iterator();
			while (it.hasNext()) {
				String nameOfBox = it.next();
				System.out.println(nameOfBox);
				GObject theBox = boxes.get(nameOfBox);
				System.out.println(theBox.toString());
				remove(theBox);

				// update contents of HashMap
				boxes.remove(nameOfBox);
			}
			boxes.clear();
		}
	}

	public void mouseClicked(MouseEvent e) {
		if (theObject != null) theObject.sendToFront();
	}

	public void mouseDragged(MouseEvent e) {
		if (theObject != null) {
			
			theObject.move(e.getX() - whereMouseWasPressed.getX(), e.getY() - whereMouseWasPressed.getY());
			whereMouseWasPressed = new GPoint(e.getPoint());
		}

	}

	public void mousePressed(MouseEvent e) {

		// get the point at whre the mouse was pressed
		whereMouseWasPressed = new GPoint(e.getX(), e.getY());

		// the current object is assigned whatever object is at the mouse pressed point
		theObject = getElementAt(whereMouseWasPressed);

	}
}
