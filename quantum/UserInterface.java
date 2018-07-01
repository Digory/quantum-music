package quantum;

import java.awt.Color;
import java.awt.Container;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.swing.JButton;
import javax.swing.JFrame;

public class UserInterface implements Runnable {

    private JFrame frame;
    private final List<Integer> MIDICodes;
    private final List<String> binaryStrings;
    private Sequence sequence;

    public UserInterface(List<Integer> MIDICodes, List<String> binaryStrings) {
        this.MIDICodes = MIDICodes;
        this.binaryStrings = binaryStrings;
    }

    @Override
    public void run() {
        frame = new JFrame("Quantum Music");
        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        DrawPanel panel = new DrawPanel(MIDICodes, binaryStrings);
        Sequencer sequencer = createSequencer(panel);
        JButton playPauseButton = new JButton("Play/Pause");
        playPauseButton.addActionListener(new PlayPauseListener(sequencer, sequence));
        panel.add(playPauseButton);
        container.add(panel);
    }

    private Sequencer createSequencer(DrawPanel panel) {
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequence = new Sequence(Sequence.PPQ, 3);
            Track track = sequence.createTrack();
            /* 
            We go through the decimal notes and add them to the track. The 
            panel is told the location (i.e. where we are in the list of notes)
            with use of the ControllerEventListener interface, so that it can 
            display the current note to the user.
            */
            int location = 0;
            for (int elements : MIDICodes) {
                track.add(new MidiEvent(new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 0, location), (3 * location)));
                track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, elements + 60, elements + 60), (3 * location)));
                track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, elements + 60, elements + 60), (3 * location) + 3));
                location++;
            }
            sequencer.open();
            sequencer.addControllerEventListener(panel, new int[]{0});
            return sequencer;
        } catch (InvalidMidiDataException | MidiUnavailableException ex) {
            Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
