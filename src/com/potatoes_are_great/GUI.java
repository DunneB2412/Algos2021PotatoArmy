package com.potatoes_are_great;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

/**
 * @author seckersl
 */
public class GUI implements ActionListener {
	
	//global variables, ones that will change via actions e.g. screens
	JFrame mainMenu, shortPath, busName, arrival;
	
	//Text Fields
	JTextField busStop1, busStop2, userText;
	
	//Formatted Fields
	JFormattedTextField userHours, userMinutes, userSeconds;
	
	//Text Areas
	JTextArea systemDisplay1, systemDisplay2, systemDisplay3;
	
	//buttons go here
	JButton shortestPathB, busNameB, arrivalsB, quitB; 
	JButton goBackB1, goBackB2, goBackB3, runB1, runB2, runB3;
	
	//User interface is created here
	//i.e. screens, buttons, settings etc
	//main menu is here as well
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
		
		userHours = new JFormattedTextField(createFormatter("##"));
		userHours.setToolTipText("Format is 2 numbers i.e. 02 == 2");
		userHours.setPreferredSize(new Dimension(30, 30));
		
		userMinutes = new JFormattedTextField(createFormatter("##"));
		userMinutes.setToolTipText("Format is 2 numbers i.e. 02 == 2");
		userMinutes.setPreferredSize(new Dimension(30, 30));
		
		userSeconds = new JFormattedTextField(createFormatter("##"));
		userSeconds.setToolTipText("Format is 2 numbers i.e. 02 == 2");
		userSeconds.setPreferredSize(new Dimension(30, 30));
		
		//An area where we can display results
		systemDisplay3 = new JTextArea("System will output here");
		systemDisplay3.setEditable(false);
		systemDisplay3.setLineWrap(true);
		//Allows user to scroll through them 
		JScrollPane areaScrollPane = new JScrollPane(systemDisplay3);
		areaScrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaScrollPane.setPreferredSize(new Dimension(500, 500));
		
		panel.setLayout(new FlowLayout());
		//add components here 
		
		panel.add(hrsText);
		panel.add(userHours);
		panel.add(minText);
		panel.add(userMinutes);
		panel.add(secText);
		panel.add(userSeconds);
		panel.add(runB3);
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
		
		userText = new JTextField("Please enter the bus name here", 30);
		userText.setToolTipText("press Enter to run");
		
		//An area where we can display results
		systemDisplay2 = new JTextArea("System will output here");
		systemDisplay2.setEditable(false);
		systemDisplay2.setLineWrap(true);
		//Allows user to scroll through them 
		JScrollPane areaScrollPane = new JScrollPane(systemDisplay2);
		areaScrollPane.setVerticalScrollBarPolicy( 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaScrollPane.setPreferredSize(new Dimension(500, 500));


		//panel settings, stuff on the window
		panel.setLayout(new FlowLayout());
		//add components here 
		panel.add(userText);
		panel.add(runB2);
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
		busStop1 = new JTextField("Please enter bus stop 1 here", 18);
		busStop1.setToolTipText("press Enter to run");
		
		busStop2 = new JTextField("Please enter bus stop 2 here", 18);
		busStop2.setToolTipText("press Enter to run");
		
		//An area where we can display results
		systemDisplay1 = new JTextArea("System will output here");
		systemDisplay1.setEditable(false);
		systemDisplay1.setLineWrap(true);
		//Allows user to scroll through them 
		JScrollPane areaScrollPane = new JScrollPane(systemDisplay1);
		areaScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		areaScrollPane.setPreferredSize(new Dimension(500, 500));

		
		
		
		panel.setLayout(new FlowLayout());
		//add components here
		panel.add(busStop1);
		panel.add(busStop2);
		panel.add(runB1);
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
		
		runB1 = new JButton("Enter");
		runB1.addActionListener(this);
		runB1.setActionCommand("Shortest Path");
		
		runB2 = new JButton("Enter");
		runB2.addActionListener(this);
		runB2.setActionCommand("Bus Name Search");
		
		runB3 = new JButton("Enter");
		runB3.addActionListener(this);
		runB3.setActionCommand("Arrival Times");
		
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
		//for switching screens
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
		
		//seperate block for the complicated programs
		if(e.getActionCommand() == "Shortest Path")
		{
			String stop1 = busStop1.getText();
			String stop2 = busStop2.getText();
			systemDisplay1.setText(stop1+" is nowhere near "+stop2);
		}
		else if(e.getActionCommand() == "Bus Name Search")
		{
			String input = userText.getText();
			systemDisplay2.setText(input);
			
			System.out.println("bus names are not here");
		}
		else if(e.getActionCommand() == "Arrival Times")
		{
			//postfix of T represents that they're in text form
			
			String hoursT = userHours.getText();
			String minutesT = userMinutes.getText();
			String secondsT = userSeconds.getText();
			
			int hours = 0;
			int minutes = 0;
			int seconds = 0;
	        try
	        {
	            hours = Integer.parseInt(hoursT); 
	            minutes = Integer.parseInt(minutesT);
	            seconds = Integer.parseInt(secondsT);
	        }
	        catch (NumberFormatException ex) 
	        {
	            ex.printStackTrace();
	        }
			
			if(IsErrorArrivals(hours, minutes, seconds) == false)
			{
				systemDisplay3.setText(hours +":"+minutes+":"+seconds);
			}
			else 
			{
				systemDisplay3.setText("Please enter a valid range");

			}
			
		}
		
	}
	
	public boolean IsErrorArrivals(int hours, int minutes, int seconds)
	{
		if((hours > 23) || (hours <0)) return true;
		else if((minutes > 59) || (minutes < 0)) return true;
		else if((seconds > 59) || (seconds <0)) return true;
		else System.out.println("Time is good");
			
		return false;
	}
	
	//used to allow only numbers to be entered for arrival times
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
