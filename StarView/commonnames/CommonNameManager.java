package commonnames;
import java.io.FileReader;
import maindisplay.*;
import java.io.IOException;
import java.util.*;
import java.util.Scanner;

//class to manage hash map of common names with ref numbers as key
public class CommonNameManager {

	private final String IN_FILE = "common_names.txt";
	private HashMap<Integer, String> names;
	private Star[] starArray;

	public CommonNameManager(Star[] s){

		names = new HashMap<Integer, String>();
		starArray = s;
	}
	
	//parse common name file and enter into hash map
	public void processNameFile(){

		try{
			FileReader reader = new FileReader(IN_FILE);
			Scanner  in = new Scanner(reader);

			try{				
				while (in.hasNextLine()){
					String line = in.nextLine();
					String [] tokens = line.split("[,]");				
					String name1 = tokens[0].trim();
					int id1 = Integer.parseInt(tokens[1].trim());
					String name2 = tokens[2].trim();
					int id2 = Integer.parseInt(tokens[3].trim());
					CommonName nameObject1 = new CommonName(name1, id1);
					CommonName nameObject2 = new CommonName(name2, id2);
					this.putName(nameObject1);
					this.putName(nameObject2);						
				}
			}
			finally{
				if((in)!=null)in.close();
			}
		}
		catch (IOException e){
			System.out.println("exception - can not find common name file");
		}
	}

	public void putName(CommonName n){
		String name = n.getName();
		int id = n.getID();

		names.put(id, name);
	}

	public void setNames(){

		for (int i = 0; i < starArray.length; i++){

			Star star = starArray[i];
			
			if (star.getCatalogueID().equals("H")){

				if (names.containsKey(star.getHippID())){
					star.setName(names.get(star.getHippID()));
					star.setCommonNameBoole(true);
				}
				else 
					star.setName("HIP " + (star.getID()));
			}
			else if (star.getCatalogueID().equals("T")) star.setName("Ty " + (star.getID()));
			else star.setName(star.getID());
		
		}
	}
	
	public HashMap<Integer, String> getHashMap(){return names;}

}
