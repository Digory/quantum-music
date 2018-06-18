package quantum;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;

public class DrawPanel extends JPanel{
    private final List<Integer> decimals;

    public DrawPanel(List<Integer> decimals) {
        this.decimals = decimals;
    }
    
    private String getMusicNotes(){
        /*
        This method is for figuring out what the note version (you know: "C#"
        or whatever) of the MIDI decimal value is; so that we can display this
        to the user.
        */
        StringBuilder musicNotes = new StringBuilder();
        for (Integer elements : decimals){
            int note = elements %= 12;
            switch(note){
                case 0: musicNotes.append("C ");
                break;
                case 1: musicNotes.append("C# ");
                break;
                case 2: musicNotes.append("D ");
                break;
                case 3: musicNotes.append("D# ");
                break;
                case 4: musicNotes.append("E ");
                break;
                case 5: musicNotes.append("F ");
                break;
                case 6: musicNotes.append("F# ");
                break;
                case 7: musicNotes.append("G ");
                break;
                case 8: musicNotes.append("G# ");
                break;
                case 9: musicNotes.append("A ");
                break;
                case 10: musicNotes.append("A# ");
                break;
                case 11: musicNotes.append("B ");
                break;
        }}
        return musicNotes.toString();
    }
    
    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString(getMusicNotes(), 0, 155);       
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(450,300);
    }
    
}
