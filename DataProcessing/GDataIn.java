package dataprocessing;
import java.io.*;
import java.util.Scanner;



	
	public class GDataIn extends DataIn {
		
		public GDataIn(){
			catalogueID = "G";
			HDid = "0";
			HRid= "0";
		}
		
	private final double MIN_MAG = 12;
		
		 public void processFile(String i, String o){
				int counter = 0;
				IN_FILE = i;
				OUT_FILE = o;

				try{
					FileReader reader = new FileReader(IN_FILE);
					FileWriter out = new FileWriter(OUT_FILE);
					Scanner  in = new Scanner(reader);

					try{				
						while (in.hasNextLine()){
							String line = in.nextLine();
							String [] tokens = line.split("[|]+");
							System.out.println("token length: " +tokens.length);
							System.out.println(" before if " + tokens[11]);

							if (Double.parseDouble(tokens[6]) > MIN_MAG){
								id = (tokens[2].trim());
								mag = Double.parseDouble(tokens[6]);
								ra = Double.parseDouble(tokens[0]);
								dec = Double.parseDouble(tokens[1]);
								par = Double.parseDouble(tokens[9]);
								if (tokens[7].trim().equals(""))colourInd = 0;
								else
								colourInd = Double.parseDouble(tokens[7]);
								if (tokens[5].trim().equals(""))specType = "DEFAULT";
								else
								specType = tokens[5].trim();
								ccdmID = "0";							
								
								String output = String.format("%s, %f, %f, %f, %f, %f, %s,%s,%s,%s,%s,", id, mag, ra, dec, par, colourInd, specType, ccdmID, HDid, HRid, catalogueID);
								out.write(output + '\n');
								counter ++;	
							}
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
				
				
			}
	

	}


