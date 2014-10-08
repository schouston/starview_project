package dataprocessing;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//class to hold identifiers from Henry Draper, Harvard Revised and Hipparcos Catalogues
public class CrossRefID {
	
	private final String INPUT_TEXT_FILE = "idcrossref.txt";
	
	private String HDid;
	private String HRid;
	private String HIPid;
	
	public CrossRefID(String hd, String hr, String hip){
		HDid = hd;
		HRid = hr;
		HIPid = hip;
	}

	public void readInData(){
	try{

		FileReader reader = new FileReader(INPUT_TEXT_FILE);
		Scanner  in = new Scanner(reader);
		try{
			while (in.hasNextLine()){
				String line = in.nextLine();
				String [] tokens = line.split("[|]+");
				HDid = tokens[0].trim();
				HRid = tokens[1].trim();
				HIPid = tokens[2].trim();
		}
		}

		finally{
			if((in)!=null)in.close();
		}
	}
	catch (IOException e){
		System.out.println("exception - input text file not found");
	}
	}
	
	public String getHIP(){return HIPid;}
	public String getHR(){return HRid;}
	public String getHD(){return HDid;}
}