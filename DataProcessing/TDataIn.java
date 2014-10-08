package dataprocessing;
import java.io.*;
import java.util.*;

public class TDataIn extends DataIn{

	public TDataIn(){
		catalogueID = "T";
		HDid = "0";
		HRid= "0";

	}

	public void processFile(String s,String o){
		//int counter = 0;
		IN_FILE = s;
		OUT_FILE = o;

		try{
			FileReader reader = new FileReader(IN_FILE);

			FileWriter out = new FileWriter(OUT_FILE);
			Scanner  in = new Scanner(reader);

			try{				
				while (in.hasNextLine()){
					String line = in.nextLine();
					String [] tokens = line.split("[|]+");
					//System.out.println("token length: " +tokens.length);
					//System.out.println(" before if " + tokens[11]);

					if(!tokens[11].trim().equals("")){
						if(Double.parseDouble(tokens[11]) >= 175){
							if (tokens[31].trim().equals("")){
								id = (tokens[1].trim());
								mag = Double.parseDouble(tokens[5]);
								ra = Double.parseDouble(tokens[8]);
								dec = Double.parseDouble(tokens[9]);
								par = Double.parseDouble(tokens[11]);
								if (tokens[37].trim().equals(""))colourInd = 0;
								else
									colourInd = Double.parseDouble(tokens[37]);
								specType = "DEFAULT";
								if (tokens[51].trim().equals(""))ccdmID = "0";
								else
									ccdmID = tokens[51];
								if(!tokens[53].trim().equals("")) HDid = tokens[53];		

								String output = String.format("%s, %f, %f, %f, %f, %f, %s,%s,%s,%s,%s,", id, mag, ra, dec, par, colourInd, specType, ccdmID, HDid, HRid, catalogueID);
								out.write(output + '\n');
								//counter ++;

								HDid = "0";
								HRid= "0";
							}
						}}
				}
			}
			finally{
				if((in)!=null)in.close();
				if((out)!= null)out.close();
			}
		}
		catch (IOException e){
			System.out.println("input file exception");
		}
	
	System.out.println("ty done");
	}
}
	