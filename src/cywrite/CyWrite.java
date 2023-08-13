import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.plaf.metal.*;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.CannotRedoException;
import javax.swing.text.*;
import javax.swing.tree.*;

public class CyWrite extends JFrame implements ActionListener {
    // Text component
    JTextArea t;
	private UndoManager undoManager;
	float fontSize = 12.0f;
    // Constructor
    CyWrite() {
        // Create a frame
        super("CyWrite"); // Set frame title

        // Text component
        t = new JTextArea();
		undoManager = new UndoManager();
        t.getDocument().addUndoableEditListener(undoManager);

        // Create a menubar
        JMenuBar mb = new JMenuBar();

        // Create a menu for File
        JMenu m1 = new JMenu("File");
		JMenu m2 = new JMenu("Edit");
        JMenu m3 = new JMenu("View");
		JMenu m4 = new JMenu("Help");

        // Create menu items
        JMenuItem mi1 = new JMenuItem("New");
        JMenuItem mi2 = new JMenuItem("Open");
        JMenuItem mi3 = new JMenuItem("Save");
        JMenuItem mi9 = new JMenuItem("Print");
        JMenuItem mi10 = new JMenuItem("Exit");

		JMenuItem mi18 = new JMenuItem("Undo");
		JMenuItem mi19 = new JMenuItem("Redo");
        JMenuItem mi4 = new JMenuItem("Cut");
        JMenuItem mi5 = new JMenuItem("Copy");
        JMenuItem mi6 = new JMenuItem("Paste");

		JMenuItem mi20 = new JMenuItem("Zoom In");
		JMenuItem mi21 = new JMenuItem("Zoom Out");

		JMenuItem mi17 = new JMenuItem("About");

        // Add action listener
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi9.addActionListener(this);
        mi10.addActionListener(this);

        // Add action listener
		mi18.addActionListener(this);
		mi19.addActionListener(this);
        mi4.addActionListener(this);
        mi5.addActionListener(this);
        mi6.addActionListener(this);

		mi20.addActionListener(this);
		mi21.addActionListener(this);

        // Add action listener
        mi17.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi9);
        m1.add(mi10);

		m2.add(mi18);
		m2.add(mi19);
        m2.add(mi4);
        m2.add(mi5);
        m2.add(mi6);

		m3.add(mi20);
		m3.add(mi21);

        m4.add(mi17);

        mb.add(m1);
        mb.add(m2);
		mb.add(m3);
        mb.add(m4);

        setJMenuBar(mb);
        add(t);
        setSize(800, 575);
        setVisible(true);
    }

    // If a button is pressed
    public void actionPerformed(ActionEvent e) {
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
            JFileChooser j = new JFileChooser("f:");

            int r = j.showSaveDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    FileWriter wr = new FileWriter(fi, false);

                    BufferedWriter w = new BufferedWriter(wr);

                    w.write(t.getText());

                    w.flush();
                    w.close();
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(this, "The user cancelled the operation");
        }
		
		else if (s.equals("Print")) {
            try {
                t.print();
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(this, evt.getMessage());
            }
        }
		
		else if (s.equals("Open")) {
            JFileChooser j = new JFileChooser("f:");

            int r = j.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try {
                    String s1 = "", sl = "";
                    FileReader fr = new FileReader(fi);
                    BufferedReader br = new BufferedReader(fr);

                    sl = br.readLine();

                    while ((s1 = br.readLine()) != null) {
                        sl = sl + "\n" + s1;
                    }

                    t.setText(sl);
                } catch (Exception evt) {
                    JOptionPane.showMessageDialog(this, evt.getMessage());
                }
            } else
                JOptionPane.showMessageDialog(this, "The user cancelled the operation");
        }
		
		else if (s.equals("New")) {
            t.setText("");
        }
		
		else if (s.equals("Exit")) {
            setVisible(false);
        }
		
		else if (s.equals("About")) {
            JOptionPane.showMessageDialog(this,
                    "CyWrite is a simple text editor created and developed by Cyril John Magayaga.\n"
                            + "v1.0 was first released on September 5, 2022.");
        }
		
		else if (s.equals("Undo")) {
            try {
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
            } catch (CannotUndoException cue) {
            }
        }

		else if (s.equals("Redo")) {
            try {
                if (undoManager.canRedo()) {
                    undoManager.redo();
                }
            } catch (CannotRedoException cre) {
            }
        }

		else if (s.equals("Zoom In")) {
            fontSize += 1.0f;
            t.setFont(t.getFont().deriveFont(fontSize));
        }

        else if (s.equals("Zoom Out")) {
            fontSize -= 1.0f;
            t.setFont(t.getFont().deriveFont(fontSize));
        }
    }

    public static void main(String args[]) {
        new CyWrite();
    }
}
