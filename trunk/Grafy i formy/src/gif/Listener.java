/**
 * 
 */
package gif;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;

/**
 * @author Micha³ Surmacz
 *
 */
public class Listener implements ActionListener {
	private Form form;
	private FormReader reader;
	private JTextArea textArea;
	private JFileChooser fc;

	public Listener(JTextArea ta) {
		super();
		this.textArea = ta;
		reader = new FormReader();
		fc = new JFileChooser();
		fc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
				"Pliki txt", "txt"));
//		do testów 
		fc.setCurrentDirectory(new File("D:\\Pulpit-prznios³em_¿eby_zrobiæ_miejsce_na_C\\michu\\Formy-przyk³ady"));
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("plain")) {
			open("plain");
			
		} else if (e.getActionCommand().equals("poset")) {
			open("poset");
			
		} else if (e.getActionCommand().equals("printForm")) {
			textArea.append("Wypisanie formy:\n" + form + "\n");
			
		} else if (e.getActionCommand().equals("printGram")) {
			textArea.append("Macierz Grama:\n");
			for (double[] a : form.getGramMatrix()) {
				for (double w : a)
					textArea.append(w + "\t");
				textArea.append("\n");				
			}
//				textArea.append(java.util.Arrays.toString(a) + "\n");
			
		} else if (e.getActionCommand().equals("isConnected")) {
			textArea.append(form.printIsConnected() + "\n");
			
		} else if (e.getActionCommand().equals("components")) {
			textArea.append("Spójne sk³adowe:\n");
			for(String s : form.getComponentStrings())
				textArea.append(s + "\n");
			
		} else if (e.getActionCommand().equals("visualization")) {
			
		}
		textArea.append("\n");
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}
	
	private void open(String s) {
		int returnVal = fc.showDialog(textArea, "Wczytaj formê");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File f = fc.getSelectedFile();
			try {
				if (s == "plain")
					form = new Form(reader.openPlain(f));
				else if (s == "poset")
					form = new Form(reader.openPoset(f));
				textArea.append("Wczyta³em plik: " + f.getName() + "\n");
				textArea.append("Wczytana forma:\n" + form.toString() + "\n");
				MainClass.enableButtons(true);
			} catch (java.io.IOException ex) {
				JOptionPane.showMessageDialog(null,
					    "Wyst¹pi³ b³¹d przy otwieraniu pliku.",
					    "I/O Error",
					    JOptionPane.ERROR_MESSAGE);
			} catch (java.util.InputMismatchException ex) {
				System.err.println(ex.getLocalizedMessage() + "\n");
				ex.printStackTrace();
				
				JOptionPane.showMessageDialog(null,
					    "Wyst¹pi³ b³¹d przy parsowaniu pliku.",
					    "Parse Error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
