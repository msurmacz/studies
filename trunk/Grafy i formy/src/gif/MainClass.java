/**
 * 
 */
package gif;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;

/**
 * @author Micha³ Surmacz
 *
 */
public class MainClass {

	private static JButton printFormButton;
	private static JButton printGramMatrixButton;
	private static JButton isConnectedButton;
	private static JButton componentsButton;
	private static JButton visualizationButton;
	private static JButton posetLoadButton;
	private static JButton plainLoadButton;
	private static JPanel bottomPanel;
	private static JPanel topPanel;
	private static JLabel topLabel;
	private static JTextArea textArea;
	private static JScrollPane scrollPane;
	private static JSplitPane splitPane;
	private static JFrame frame;
	private static Listener listener;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	private static void createAndShowGUI() {
		
		frame = new JFrame("Grafy i formy");
		
		splitPane = new JSplitPane();
		
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		
		topLabel = new JLabel("Wczytaj plik:");
		
		topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		bottomPanel = new JPanel();
		
		plainLoadButton = new JButton("zwyk³y");
		posetLoadButton = new JButton("poset");
		printFormButton = new JButton("Wypisz formê");
		printGramMatrixButton = new JButton("Macierz Grama");
		isConnectedButton = new JButton("SprawdŸ spójnoœæ");
		componentsButton = new JButton("Spójne sk³adowe");
		visualizationButton = new JButton("Wizualizacja");
		
		listener = new Listener(textArea);
		
		//akcje
		plainLoadButton.setActionCommand("plain");
		posetLoadButton.setActionCommand("poset");
		printFormButton.setActionCommand("printForm");
		printGramMatrixButton.setActionCommand("printGram");
		isConnectedButton.setActionCommand("isConnected");
		componentsButton.setActionCommand("components");
		visualizationButton.setActionCommand("visualization");
		
		plainLoadButton.addActionListener(listener);
		posetLoadButton.addActionListener(listener);
		printFormButton.addActionListener(listener);
		printGramMatrixButton.addActionListener(listener);
		isConnectedButton.addActionListener(listener);
		componentsButton.addActionListener(listener);
		visualizationButton.addActionListener(listener);

		//ustawianie w³aœciwoœci
		
		enableButtons(false);
		visualizationButton.setEnabled(false);
		
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
//		scrollPane.setVerticalScrollBarPolicy(
//				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(350, 400));

		splitPane.setResizeWeight(0.5);
		splitPane.setOneTouchExpandable(true);
		splitPane.setContinuousLayout(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//dodawanie komponentów
		
		topPanel.add(topLabel);
		topPanel.add(plainLoadButton);
		topPanel.add(posetLoadButton);
		
		splitPane.setLeftComponent(scrollPane);
		splitPane.setRightComponent(null);
		
		bottomPanel.add(printFormButton);
		bottomPanel.add(printGramMatrixButton);
		bottomPanel.add(isConnectedButton);
		bottomPanel.add(componentsButton);
		bottomPanel.add(visualizationButton);
		
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		frame.getContentPane().add(topPanel, BorderLayout.PAGE_START);
		frame.getContentPane().add(bottomPanel, BorderLayout.PAGE_END);
		
		
		//wyœwietlenie okna
		
		frame.pack();
	    frame.setVisible(true);
	}
	
	public static void enableButtons(boolean b) {
		printFormButton.setEnabled(b);
		printGramMatrixButton.setEnabled(b);
		isConnectedButton.setEnabled(b);
		componentsButton.setEnabled(b);
//		visualizationButton.setEnabled(b);
	}

}
