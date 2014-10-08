package dataprocessing;
import java.io.*;

import java.util.*;

public class HDataIn extends DataIn{

	private CrossRefID[] crossrefArray;							//array to hold CrossRefID objects
	private int SIZE = 4308;									//size of cross ref array from file idcrossref.txt
	private int crCounter = 0; 									//cross ref counter

	public HDataIn(){
		catalogueID = "H";										//sets all catalogueID to H
	}


	//method to process file in expected Hipparcos Catalogue format and produce out put file in StarView expected format
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
					String [] tokens = line.split("[|]+");								//split each line by |

					if(!tokens[11].trim().equals("")){
						if(Double.parseDouble(tokens[11]) >= 20){

							id = (tokens[1].trim());

							for (int i = 0; i < crossrefArray.length; i++){							//compare to cross ref array to find matching hipp id

								if (tokens[1].trim().equals(crossrefArray[i].getHIP())){			//if match found, set HD and HR ids

									if(!crossrefArray[i].getHD().equals(""))
										HDid = crossrefArray[i].getHD();

									if(!crossrefArray[i].getHR().equals(""))
										HRid = crossrefArray[i].getHR();
								}
							}
							mag = Double.parseDouble(tokens[5]);
							ra = Double.parseDouble(tokens[8]);
							dec = Double.parseDouble(tokens[9]);
							par = Double.parseDouble(tokens[11]);

							if (tokens[37].trim().equals(""))colourInd = 0;
							else
								colourInd = Double.parseDouble(tokens[37]);
							if (tokens[76].trim().equals(""))specType = "DEFAULT";
							else
								specType = tokens[76].trim();
							if (tokens[55].trim().equals(""))ccdmID = "0";
							else if (tokens[58].trim().equals("1"))ccdmID = "1";				//unresolved system
							else
								ccdmID = tokens[55];			

							String output = String.format("%s, %f, %f, %f, %f, %f, %s,%s,%s,%s,%s,", id, mag, ra, dec, par, colourInd, specType, ccdmID, HDid, HRid, catalogueID);
							out.write(output + '\n');
							//counter ++;

							HDid = "0";
							HRid= "0";
						}
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
		System.out.println("DONE!");
	}

	//method to produce cross reference array from cross reference input text file
	public void processCrossRefFile(String s){

		String HD = "HD ";
		String HR = "HR ";
		String HIP;
		CrossRefID[] array = new CrossRefID[SIZE];

		String fileName = s;
		try{
			FileReader reader = new FileReader(fileName);
			Scanner in = new Scanner(reader);

			try{
				while (in.hasNextLine()){
					String line = in.nextLine();
					String [] tokens = line.split("[|]+");
					HD += tokens[0].trim();
					HR += tokens[1].trim();
					if (tokens.length == 3){
						HIP = tokens[2].trim();
						CrossRefID ref = new CrossRefID(HD, HR, HIP);
						array[crCounter] = ref;
						crCounter ++;}

					HD = "HD ";
					HR = "HR ";
				}
			}
			finally{
				if((in)!=null)in.close();
			}
		}
		catch (IOException e){
			System.out.println("cross referencing file exception");
		}

		crossrefArray = new CrossRefID[crCounter];
		for (int i = 0; i < crossrefArray.length; i ++){
			crossrefArray[i] = array[i];
		}
	}

	public CrossRefID[] getCrossRefArray(){return crossrefArray;}

}
