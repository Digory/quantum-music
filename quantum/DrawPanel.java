package quantum;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

public class DrawPanel extends JPanel implements ControllerEventListener{

    private final List<Integer> decimals;
    private int location = -1;

    public DrawPanel(List<Integer> decimals) {
        this.decimals = decimals;
    }

    private String getMusicNote() {
        /*
        This method is for figuring out what the note name (you know: "C#" or
        whatever) of the MIDI decimal value is; so that we can display this to 
        the user.
         */
        String[] notes = new String[]{"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
        return notes[decimals.get(location)%12];
    }

    @Override
    public void paintComponent(Graphics g) {
        if (location == -1){
            return; 
            /*
            ^ So that it doesn't paint the first note before the play button is
            pushed.
            */
        }
        g.setColor(Color.CYAN);
        g.fillOval((30*location), 135, 30, 30);
        g.setFont(new Font("default", Font.BOLD, 15));
        g.setColor(Color.BLACK);
        g.drawString(getMusicNote(), (30*location+10), 155);
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
