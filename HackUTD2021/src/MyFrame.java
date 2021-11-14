import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import org.json.JSONException;

public class MyFrame extends JFrame implements ActionListener {
	
	JComboBox firstAddressBox = null;
	JComboBox secondAddressBox = null;
	Button selectButton = null;

	
	MyFrame(){
		//default close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//setting graphic overlay
		ImageIcon backgroundImage = new ImageIcon("background.png");
		JLabel background = new JLabel();
		background.setIcon(backgroundImage);
		background.setBounds(0, 0, 2000, 674);
		
		this.getContentPane().setBackground(new Color(0xeeeeee)); 	//setting background to color of bug
		
		//size of box
		this.setSize(2000,1000); 
		
		//layout of the combo boxes
		this.setLayout(null);
		
		//Array of all the IATAs 
		String[] airportOriginIATA = {"WRO","FEZ","MAN","SOF","TGD","DUB","LON","LON","LON","KTW","POZ","WRO","KRK","GDN","BZG","PFO","MIL","PFO","LPP","TUF","PFO","POZ","ROM","OSL","VNO","MLA","MIL","KTW","SCV","GDN"};
		String[] airportDestIATA = {"GDN","VLC","ACE","PFO","ZAG","EDI","NAP","SOF","MIL","IEV","ODS","BRU","ODS","GOT","IEV","BLQ","CTA","MIL","BUD","MRS","TLV","OSL","RAK","RIX","HRK","IEV","PMO","ODS","MIL","STO"};
		
		
		// First Combo Box //
		JLabel FirstComboLable = new JLabel("Select Airport One");
		FirstComboLable.setBounds(200,600,200,200);
		
		
		firstAddressBox = new JComboBox(airportOriginIATA);	 		
		firstAddressBox.addActionListener(this);			//make it so that we can react to changes in the box
		firstAddressBox.setEditable(true);                 	//can edit the drop down menu or select
		firstAddressBox.setBounds(200, 720, 160, 40);			//setting position
		
		//__________________//
		
		// Second Combo Box //
		JLabel SecondComboLable = new JLabel("Select Airport Two");
		SecondComboLable.setBounds(400,600,200,200);
		
		secondAddressBox = new JComboBox(airportDestIATA);	 		
		secondAddressBox.addActionListener(this);			//make it so that we can react to changes in the box
		secondAddressBox.setEditable(true);                 //can edit the drop down menu or select
		secondAddressBox.setBounds(400,720, 160, 40);					//setting position
		//__________________//
				
		// Button //
		selectButton = new Button("Select");
		selectButton.setBounds(600, 720, 160, 40);
		selectButton.addActionListener(this);
		// ______ //
		
		//adding everything to the frame
		this.add(background);
		this.add(firstAddressBox);
		this.add(SecondComboLable);
			this.add(secondAddressBox);
		this.add(FirstComboLable);
		this.add(selectButton);
		this.setVisible(true) ;
	}

	//what to do when someone changes something in a box
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==selectButton) {
			//use a flight generator object to run the api with the selected airports
			FlightGenerator flightGenObject = new FlightGenerator();
			try {
				ArrayList<String> firstDest = flightGenObject.generateDestinations((String)firstAddressBox.getSelectedItem());
				ArrayList<String> secondDest = flightGenObject.generateDestinations((String)secondAddressBox.getSelectedItem());
				
				//combine in first
				firstDest.addAll(secondDest);
				boolean found = false;
				for(int i = 0; i<firstDest.size()-1;i++) {
					for(int j = i+1; j<firstDest.size();j++) {
						if(firstDest.get(i).equals(firstDest.get(j))) {
							JLabel result = new JLabel(firstDest.get(i));
							result.setBounds(1000,800,160,40);
							System.out.println(firstDest.get(i));
							found = true;
							break;
						}
					if(found)
						break;
					}
					if(found)
					break;
				}

				
				
				
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}
	

	
	
}
