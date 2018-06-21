package quantum;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

    private final List<Integer> decimals;

    public DrawPanel(List<Integer> decimals) {
        this.decimals = decimals;
    }

    private String getMusicNotes() {
        /*
        This method is for figuring out what the note name (you know: "C#" or
        whatever) of the MIDI decimal value is; so that we can display this to 
        the user.
         */
        StringBuilder musicNotes = new StringBuilder();
        String[] notes = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        for (int i = 0; i < decimals.size(); i++) {
            int note = decimals.get(i);
            note %= 12;
            if (i < decimals.size() - 1) {
                musicNotes.append(notes[note] + ", ");
            } else {
                musicNotes.append(notes[note]);
            }
        }
        return musicNotes.toString();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(0, 135, 830, 30);
        g.setFont(new Font("default", Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString(getMusicNotes(), 65, 155);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(830, 300);
    }

}
