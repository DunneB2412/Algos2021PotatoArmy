package com.potatoes_are_great;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class GUI implements ActionListener {
	
	//global variables, ones that will change via actions e.g. screens
	JFrame mainMenu, shortPath, busName, arrival;

	
	//buttons go here
	JButton shortestPathB, busNameB, arrivalsB, quitB, goBackB1, goBackB2, goBackB3;
	
	//User interface is created here
	//i.e. screens, buttons, settings etc
	public GUI() 
	{
		mainMenu = new JFrame();
		shortPath = new JFrame();
		busName = new JFrame();
		arrival = new JFrame();
		
		JPanel panel = new JPanel();
		JPanel shortPathScreen = new JPanel();
		JPanel busNameScreen = new JPanel();
		JPanel arrivalTimeScreen = new JPanel();
		
		createButtons();
		createShortPath(shortPathScreen);
		createBusName(busNameScreen);
		createArrivalTime(arrivalTimeScreen);
		
		//settings for panel. Add buttons and stuff here as well.
		panel.setLayout(new GridLayout(0, 1));
	
		//adding components (buttons)
		panel.add(shortestPathB);
		panel.add(busNameB);
		panel.add(arrivalsB);
		panel.add(quitB);
		
		
		//settings for frame. Layout, size, location etc
		
		mainMenu.add(panel, BorderLayout.CENTER);
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenu.setTitle("Menu");
		mainMenu.setSize(600, 600);
		mainMenu.setVisible(true);
		//mainMenu.setResizable(false);
		//mainMenu.pack();
		mainMenu.setLocationRelativeTo(null);
	
		
		
	}
	
	public void createArrivalTime(JPanel panel)
	{
		//create extra components here
		
		JLabel hrsText = new JLabel("Hours");
		JLabel minText = new JLabel("Minutes");
		JLabel secText = new JLabel("Seconds");
		
		JFormattedTextField userHours = new JFormattedTextField(createFormatter("##"));
		userHours.setToolTipText("Press Enter to run");
		userHours.setPreferredSize(new Dimension(30, 30));
		
		JFormattedTextField userMinutes = new JFormattedTextField(createFormatter("##"));
		userMinutes.setToolTipText("Press Enter to run");
		userMinutes.setPreferredSize(new Dimension(30, 30));
		
		JFormattedTextField userSeconds = new JFormattedTextField(createFormatter("##"));
		userSeconds.setToolTipText("Press Enter to run");
		userSeconds.setPreferredSize(new Dimension(30, 30));
		
		//An area where we can display results
		JTextArea systemDisplay = new JTextArea("System will output here");
		systemDisplay.setEditable(false);
		systemDisplay.setLineWrap(true);
		//Allows user to scroll through them 
		JScrollPane areaScrollPane = new JScrollPane(systemDisplay);
		areaScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaScrollPane.setPreferredSize(new Dimension(500, 500));
		
		panel.setLayout(new FlowLayout());
		//add components here 
		
		panel.add(hrsText);
		panel.add(userHours);
		panel.add(minText);
		panel.add(userMinutes);
		panel.add(secText);
		panel.add(userSeconds);
		panel.add(areaScrollPane);
		panel.add(goBackB3);
		
		
		//screen settings 
		arrival.add(panel, BorderLayout.CENTER);
		arrival.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		arrival.setTitle("Search for Bus Names");
		arrival.setSize(600, 600);
		arrival.setVisible(false);
		//arrival.setResizable(false);
		//mainMenu.pack();
		arrival.setLocationRelativeTo(null);
	}
	
	public void createBusName(JPanel panel)
	{
		//create extra components here
		
		JTextField userText = new JTextField("Please enter the bus name here", 30);
		userText.setToolTipText("press Enter to run");
		
		//An area where we can display results
		JTextArea systemDisplay = new JTextArea("System will output here");
		systemDisplay.setEditable(false);
		systemDisplay.setLineWrap(true);
		//Allows user to scroll through them 
		JScrollPane areaScrollPane = new JScrollPane(systemDisplay);
		areaScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaScrollPane.setPreferredSize(new Dimension(500, 500));

		
		//panel settings, stuff on the window
		panel.setLayout(new FlowLayout());
		//add components here 
		panel.add(userText);
		panel.add(areaScrollPane);
		panel.add(goBackB2);
				
		//window settings 
		busName.add(panel, BorderLayout.CENTER);
		busName.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		busName.setTitle("Search for Bus Names");
		busName.setSize(600, 600);
		busName.setVisible(false);
		busName.setResizable(false);
		//mainMenu.pack();
		busName.setLocationRelativeTo(null);
	}
	
	public void createShortPath(JPanel panel)
	{
		
		//create extra components 
		JTextField busStop1 = new JTextField("Please enter bus stop 1 here", 15);
		busStop1.setToolTipText("press Enter to run");
		
		JTextField busStop2 = new JTextField("Please enter bus stop 2 here", 15);
		busStop2.setToolTipText("press Enter to run");
		
		//An area where we can display results
		JTextArea systemDisplay = new JTextArea("System will output here");
		systemDisplay.setEditable(false);
		systemDisplay.setLineWrap(true);
		//Allows user to scroll through them 
		JScrollPane areaScrollPane = new JScrollPane(systemDisplay);
		areaScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaScrollPane.setPreferredSize(new Dimension(500, 500));

		
		
		
		panel.setLayout(new FlowLayout());
		//add components here
		panel.add(busStop1);
		panel.add(busStop2);
	//	panel.add(systemDisplay);
		panel.add(areaScrollPane);
		panel.add(goBackB1);
		
		//screen settings 
		shortPath.add(panel, BorderLayout.CENTER);
		shortPath.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shortPath.setTitle("Shortest Path");
		shortPath.setSize(600, 600);
		shortPath.setVisible(false);
		shortPath.setResizable(false);
		//shortPath.pack();
		shortPath.setLocationRelativeTo(null);
		
	}
	
	public void createButtons()
	{
		shortestPathB = new JButton("Shortest Path between Bus stops");
		shortestPathB.addActionListener(this);
		shortestPathB.setActionCommand("Short Path");
		
		busNameB = new JButton("Search for Bus Name");
		busNameB.addActionListener(this);
		busNameB.setActionCommand("Bus Name");
		
		arrivalsB = new JButton("Search for trips at an arrival time");
		arrivalsB.addActionListener(this);
		arrivalsB.setActionCommand("Arrival Time");
		
		quitB = new JButton("Close the application");
		quitB.addActionListener(this);
		quitB.setActionCommand("Quit");
		
		goBackB1 = new JButton("Go back to Main Menu");
		goBackB1.addActionListener(this);
		goBackB1.setActionCommand("Go Back");
		
		goBackB2 = new JButton("Go back to Main Menu");
		goBackB2.addActionListener(this);
		goBackB2.setActionCommand("Go Back");
		
		goBackB3 = new JButton("Go back to Main Menu");
		goBackB3.addActionListener(this);
		goBackB3.setActionCommand("Go Back");
	}
	
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		new GUI();
	}

	//here actions performed by user get responses from here
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(e.getActionCommand())
		{		
		case "Short Path":
			System.out.println("change screens");
			shortPath.setVisible(true);
			mainMenu.setVisible(false);
			break;
		case "Bus Name":
			System.out.println("Bus names");
			busName.setVisible(true);
			mainMenu.setVisible(false);
			break;
		case "Arrival Time":
			System.out.println("Arrival time");
			arrival.setVisible(true);
			mainMenu.setVisible(false);
			break;
		case "Quit":
			System.exit(0);
			break;
		case"Go Back":
			mainMenu.setVisible(true);
			shortPath.setVisible(false);
			busName.setVisible(false);
			arrival.setVisible(false);
			break;
		default: break;
		
		}
		
	}
	
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}

}
