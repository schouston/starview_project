package dataprocessing;

//main class to run data processing for StarView data base.
//comment out a particular catalogue if you do not want it processed
public class Main {

	
	public static void main(String[] args) {
	
		HDataIn hIn = new HDataIn();
		hIn.processCrossRefFile("idcrossref.txt");
		hIn.processFile("hip_main.dat", "hip_main_pro.txt");
		
		TDataIn tIn = new TDataIn();
		tIn.processFile("tyc_main.dat", "tyc_main_pro.txt");
		
		GDataIn gIn = new GDataIn();
		gIn.processFile("gliese_data_1.txt", "gliese_data_processed.txt");
	}

}

