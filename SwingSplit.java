package com.navdeep;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class SwingSplit extends JPanel implements ActionListener {
	static private final String newline = "\n";

	JButton openButton, saveButton;

	JTextArea log;

	JFileChooser fc;

	public SwingSplit() {
		super(new BorderLayout());

		// Create the log first, because the action listeners
		// need to refer to it.
		log = new JTextArea(5, 20);
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// Create a file chooser
		fc = new JFileChooser();

		openButton = new JButton("Click here to use File Divider ...",
				createImageIcon("dividers.gif"));
		openButton.addActionListener(this);


		JPanel buttonPanel = new JPanel(); // use FlowLayout
		buttonPanel.add(openButton);
		add(buttonPanel, BorderLayout.PAGE_START);
		add(logScrollPane, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == openButton) {
			int returnVal = fc.showOpenDialog(SwingSplit.this);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				log.append("Opening: " + file.getName() + "." + newline);
				split zzz = new split();
				zzz.runn(file.getName());

			} else {
				log.append("Open command cancelled by user." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());

			// Handle save button action.
		} else if (e.getSource() == saveButton) {
			int returnVal = fc.showSaveDialog(SwingSplit.this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				log.append("Saving: " + file.getName() + "." + newline);
			} else {
				log.append("Save command cancelled by user." + newline);
			}
			log.setCaretPosition(log.getDocument().getLength());
		}
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = SwingSplit.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	private static void createAndShowGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("File_Divider");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JComponent newContentPane = new SwingSplit();
		newContentPane.setOpaque(true); 
		frame.setContentPane(newContentPane);

		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
