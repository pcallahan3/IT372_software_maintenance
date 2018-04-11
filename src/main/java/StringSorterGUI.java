import java.io.IOException;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Dimension;

public class StringSorterGUI {
	private static class ComboFileChooser extends JPanel {
		private JTextField tf;

		ComboFileChooser(String label) {
			JLabel l=new JLabel(label);
			add(l);
			tf=new JTextField(20);
			add(tf);
			JButton b=new JButton("...");
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFileChooser chooser=new JFileChooser(".");
					int returnVal = chooser.showOpenDialog(null);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						try {
							tf.setText(chooser.getSelectedFile().getCanonicalPath());
						}
						catch (IOException ioe) {}
					}
				}
			});
			add(b);
			this.setVisible(true);
		}

		String getFileName() {
			return tf.getText();
		}
	}

	private static class StringSorterDialog extends JFrame {
		private ComboFileChooser inFile, outFile;

		StringSorterDialog() {
			super("String Sorter");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			inFile=new ComboFileChooser("Input file:");
			outFile=new ComboFileChooser("Output file:");
			JButton sortBtn=new JButton("Sort");
			sortBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StringSorter ss=new StringSorter();
					try {
						ss.sort(inFile.getFileName(), outFile.getFileName());
					} catch (IOException ioe) {

					}
				}
			});
			// use a up top to bottom layout
			getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.PAGE_AXIS));

			getContentPane().add(Box.createRigidArea(new Dimension(10,10)));
			getContentPane().add(inFile);
			getContentPane().add(Box.createRigidArea(new Dimension(10,10)));
			getContentPane().add(outFile);
			getContentPane().add(Box.createRigidArea(new Dimension(10,10)));
			getContentPane().add(sortBtn);
			getContentPane().add(Box.createRigidArea(new Dimension(10,10)));
			pack();
		}

	}
	public static void main(String args[]) throws IOException {
		StringSorter ss=new StringSorter();
		StringSorterDialog d=new StringSorterDialog();
		d.setVisible(true);
	}
}