package quantum;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements ControllerEventListener {

    private final List<Integer> MIDICodes;
    private final List<String> binaryStrings;
    private Graphics graphics;
    private int location;

    public DrawPanel(List<Integer> MIDICodes, List<String> binaryStrings) {
        this.MIDICodes = MIDICodes;
        this.binaryStrings = binaryStrings;
        location = -1;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        this.graphics = graphics;
        /*
        We don't paint the first note until the user has clicked the button.
        If the user wishes to play the sequence again we cover over the note 
        graphics that were played last time.
        */
        if (location == -1) {
            return;
        }
        if (location == 0) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 130, 960, 50);
        }
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 100, 40);
        graphics.fillRect(435, 200, 100, 40);
        paintNoteGraphics();
        paintBinaryGraphics();
    }

    private void paintNoteGraphics() {
        graphics.setColor(Color.CYAN);
        graphics.fillOval((30 * location), 135, 30, 30);
        graphics.setFont(new Font("default", Font.BOLD, 15));
        graphics.setColor(Color.BLACK);
        graphics.drawString(findMusicNote(), (30 * location + 10), 155);
    }

    private String findMusicNote() {
        /*
        This method is for figuring out what the note name (you know: "C#" or
        whatever) of the MIDI decimal value is; so that we can display this to 
        the user.
        */
        String[] notes = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        return notes[MIDICodes.get(location) % 12];
    }

    private void paintBinaryGraphics() {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("default", Font.BOLD, 30));
        graphics.drawString(binaryStrings.get(location), 435, 230);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(960, 300);
    }

    @Override
    public void controlChange(ShortMessage event) {
        location = event.getData2();
        repaint();
    }

}
