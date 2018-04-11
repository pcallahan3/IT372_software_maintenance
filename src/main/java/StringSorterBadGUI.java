import java.io.IOException;
import javax.swing.JOptionPane;

public class StringSorterBadGUI {
	public static void main(String args[]) throws IOException {
		try {
			StringSorter ss=new StringSorter();
			String inputFileName=JOptionPane.showInputDialog("Please enter input file name");
			String outputFileName=JOptionPane.showInputDialog("Please enter output file name");
			ss.sort(inputFileName, outputFileName);
		} finally {
			System.exit(1);
		}
	}
}
