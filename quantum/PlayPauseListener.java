package quantum;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class PlayPauseListener implements ActionListener, MetaEventListener {

    private final Sequencer sequencer;
    private final Sequence sequence;
    private long currentSequencePosition;

    public PlayPauseListener(Sequencer sequencer, Sequence sequence) {
        this.sequencer = sequencer;
        this.sequence = sequence;
        sequencer.addMetaEventListener(this);        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            sequencer.setSequence(sequence);
        } catch (InvalidMidiDataException ex) {
            Logger.getLogger(PlayPauseListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (sequencer.isRunning()) {
            currentSequencePosition = sequencer.getTickPosition();
            sequencer.stop();
        } else {
            sequencer.setTickPosition(currentSequencePosition);
            sequencer.start();
        }
    }
    
    @Override
    public void meta(MetaMessage event) {
        /*
        If the user clicks the button after the sequence has finished, this
        makes sure the sequence starts again from the beginning. 
        */
        if (event.getType() == 47) {
            currentSequencePosition = 0;
        }
    } 

} 
