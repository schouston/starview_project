package maindisplay;
import javax.swing.*;
import javax.swing.UIManager;

//main class to run the StarView Program
//lines 15 to 40 from http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
public class Main {

	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable(){

			public void run(){

				try {
					for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (Exception e) {
					try {
						UIManager.setLookAndFeel(
								UIManager.getSystemLookAndFeelClassName());
					} 
					catch (UnsupportedLookAndFeelException e1) {
						// handle exception
					}
					catch (ClassNotFoundException e1) {
						// handle exception
					}
					catch (InstantiationException e1) {
						// handle exception
					}
					catch (IllegalAccessException e1) {
						// handle exception
					}
				}
				CreateStars stars = new CreateStars(17197,"sv_database.txt");
				StarManager man = stars.getManager();
				StarController controller = new StarController(man);
			}


		}
				);
	}
}

