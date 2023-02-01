package cywrite;

/**
 *
 * CYWRITE - SIMPLE TEXT EDITOR
 * @author Cyril John Magayaga
 * @latestReleaseDate February 2, 2023
 * @latestVersionNumber v1.0-preview1
 * 
 */
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.text.*;
/**
 *
 * CYWRITE - SIMPLE TEXT EDITOR
 * @author Cyril John Magayaga
 * @latestReleaseDate August 20, 2022
 * @latestVersionNumber v1.0-preview0
 * 
 */
public class CyWrite 
        extends JFrame 
        implements ActionListener {
	// Text component
	JTextArea t;

	// Frame
	JFrame f;

	// Constructor
	CyWrite(){
		// Create a frame
		f = new JFrame("CyWrite");

		try {
			// Set metal look and feel
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			// Set theme to ocean
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
		}
		catch (Exception e) {
		}

		// Text component
		t = new JTextArea();

		// Create a menubar
		JMenuBar mb = new JMenuBar();

		// Create amenu for menu
		JMenu m1 = new JMenu("File");

		// Create menu items
		JMenuItem mi1 = new JMenuItem("New");
		JMenuItem mi2 = new JMenuItem("Open");
		JMenuItem mi3 = new JMenuItem("Save");
		JMenuItem mi9 = new JMenuItem("Print");
                JMenuItem mi10 = new JMenuItem("Exit");

		// Add action listener
		mi1.addActionListener(this);
		mi2.addActionListener(this);
		mi3.addActionListener(this);
		mi9.addActionListener(this);
                mi10.addActionListener(this);

		m1.add(mi1);
		m1.add(mi2);
		m1.add(mi3);
		m1.add(mi9);
                m1.add(mi10);

		// Create amenu for menu
		JMenu m2 = new JMenu("Edit");

		// Create menu items
		JMenuItem mi4 = new JMenuItem("Cut");
		JMenuItem mi5 = new JMenuItem("Copy");
		JMenuItem mi6 = new JMenuItem("Paste");

		// Add action listener
		mi4.addActionListener(this);
		mi5.addActionListener(this);
		mi6.addActionListener(this);
                
		m2.add(mi4);
		m2.add(mi5);
		m2.add(mi6);
                
                // Create amenu for menu
                JMenu m3 = new JMenu("Help");
                
                JMenuItem mi17 = new JMenuItem("About");
                
                // Add action listener
                mi17.addActionListener(this);
                
                m3.add(mi17);
                
		mb.add(m1);
		mb.add(m2);
                mb.add(m3);

		f.setJMenuBar(mb);
		f.add(t);
		f.setSize(800, 575);
		f.show();
	}

/**
 *
 * CYWRITE - SIMPLE TEXT EDITOR
 * @author Cyril John Magayaga
 * @latestReleaseDate August 20, 2022
 * @latestVersionNumber v1.0-preview0
 * 
 */
        
	// If a button is pressed
	public void actionPerformed(ActionEvent e){
		String s = e.getActionCommand();

		if (s.equals("Cut")) {
			t.cut();
		}
		else if (s.equals("Copy")) {
			t.copy();
		}
		else if (s.equals("Paste")) {
			t.paste();
		}
		else if (s.equals("Save")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("f:");

			// Invoke the showsSaveDialog function to show the save dialog
			int r = j.showSaveDialog(null);

			if (r == JFileChooser.APPROVE_OPTION) {

				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// Create a file writer
					FileWriter wr = new FileWriter(fi, false);

					// Create buffered writer to write
					BufferedWriter w = new BufferedWriter(wr);

					// Write
					w.write(t.getText());

					w.flush();
					w.close();
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(f, "the user cancelled the operation");
		}
		else if (s.equals("Print")) {
			try {
				// print the file
				t.print();
			}
			catch (Exception evt) {
				JOptionPane.showMessageDialog(f, evt.getMessage());
			}
		}
		else if (s.equals("Open")) {
			// Create an object of JFileChooser class
			JFileChooser j = new JFileChooser("f:");

			// Invoke the showsOpenDialog function to show the save dialog
			int r = j.showOpenDialog(null);

			// If the user selects a file
			if (r == JFileChooser.APPROVE_OPTION) {
				// Set the label to the path of the selected directory
				File fi = new File(j.getSelectedFile().getAbsolutePath());

				try {
					// String
					String s1 = "", sl = "";

					// File reader
					FileReader fr = new FileReader(fi);

					// Buffered reader
					BufferedReader br = new BufferedReader(fr);

					// Initialize sl
					sl = br.readLine();

					// Take the input from the file
					while ((s1 = br.readLine()) != null) {
						sl = sl + "\n" + s1;
					}

					// Set the text
					t.setText(sl);
				}
				catch (Exception evt) {
					JOptionPane.showMessageDialog(f, evt.getMessage());
				}
			}
			// If the user cancelled the operation
			else
				JOptionPane.showMessageDialog(f, "the user cancelled the operation");
		}
		else if (s.equals("New")) {
			t.setText("");
		}
		else if (s.equals("Exit")) {
			f.setVisible(false);
		}
                else if (s.equals("About")) {
			JOptionPane.showMessageDialog(f, "CyWrite is simple text editor created and developed by Cyril John Magayaga.\n v1.0 was current first released on September 5, 2022.");
		}
	}

    /**
     *
     * 	Proprietary software,
     *  CyWrite. - Simple Text Editor created and developed by Cyril John Magayaga.
     * 
     *  It is written in Java programming language, like Microsoft Notepad, 
     *  Microsoft WordPad, GNU Emacs, Apple TextEdit, Vim, and more text editors.
     */
	// Main class
	public static void main(String args[]){
		CyWrite e = new CyWrite();
	}
}
