package quantum;

import java.awt.Container;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;

public class UserInterface implements Runnable{
private JFrame frame;
private List<Integer> decimals;

    public UserInterface(List<Integer> decimals){
        this.decimals = decimals;
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("Quantum Music");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);

    }

    private void createComponents(Container container) {
        DrawPanel panel = new DrawPanel(decimals);
        container.add(panel);

        Synthesizer midiSynth;
    try {
        midiSynth = MidiSystem.getSynthesizer();
        midiSynth.open();
        Instrument[] instr = midiSynth.getDefaultSoundbank().getInstruments();
        MidiChannel[] mChannels = midiSynth.getChannels();
        midiSynth.loadInstrument(instr[0]);

        for (Integer elements : decimals) {
            mChannels[0].noteOn(60 + elements, 100);
            try {           
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } catch (MidiUnavailableException ex) {
        Logger.getLogger(UserInterface.class.getName()).log(Level.SEVERE, null, ex);
    }

    }

}
