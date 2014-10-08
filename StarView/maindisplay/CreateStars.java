package maindisplay;
import java.io.FileReader;
import commonnames.*;
import java.io.IOException;
import java.util.Scanner;

//class to create star objects from input text file
public class CreateStars {
	
	private String INPUT_TEXT_FILE;// = "HTGtest.txt";//"processed_upto_20mas.txt";
	private String id;
	private double mag;
	private double ra;
	private double dec;
	private double par;
	private double colourInd;
	private String specType = "Default";
	private String ccdmID = "NA";
	private String HDid;
	private String HRid;
	private String catalogueID;
	private StarManager manager;
		
	public CreateStars(int s, String i){
		manager = new StarManager(s);
		INPUT_TEXT_FILE = i;
		createStars();
	}
	
	//create star objects from input file and places in StarManager array
	public void createStars(){
		
		try{
			FileReader reader = new FileReader(INPUT_TEXT_FILE);
			Scanner  in = new Scanner(reader);
			try{
				while (in.hasNextLine()){
					String line = in.nextLine();
					String [] tokens = line.split("[,]+");
					id = (tokens[0].trim());
					mag = Double.parseDouble(tokens[1]);
					ra = Double.parseDouble(tokens[2]);
					dec = Double.parseDouble(tokens[3]);
					par = Double.parseDouble(tokens[4]);
					colourInd = Double.parseDouble(tokens[5]);
					specType = tokens[6].trim();
					ccdmID = tokens[7].trim();
					HDid = tokens[8].trim();
					HRid = tokens[9].trim();
					catalogueID = tokens[10].trim();
					
					Star newStar = new Star(catalogueID, id, manager.getStarViewID(), mag, ra, dec, par, colourInd, specType, ccdmID, HDid, HRid);
					manager.addStar(newStar);	
			}
			}
			finally{
				if((in)!=null)in.close();
			}
		}
		catch (IOException e){
			System.out.println("exception - input text file not found");
		}
		setNames();
	}
	
	//returns array of star objects
	public StarManager getManager(){
		return manager;
	}
	
	//method to set names of star objects
	public void setNames(){
		CommonNameManager nameManager = new CommonNameManager(manager.getStarArray());
		nameManager.processNameFile();
		nameManager.setNames();
	}
}
