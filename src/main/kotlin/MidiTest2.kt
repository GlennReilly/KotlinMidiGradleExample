import java.lang.Exception
import javax.sound.midi.*
import java.util.*

fun main(args: Array<String>) {
    println("Enter the number of notes to be played: ")
    val input: Scanner = Scanner(System.`in`)
    val numOfNotes = input.nextInt()

    val player = MidiTest2()
    player.setUpPlayer(numOfNotes)
}

class MidiTest2 {

    fun setUpPlayer(numOfNotes: Int)
    {
        try {
            // A static method of MidiSystem that returns
            // a sequencer instance.
            val sequencer = MidiSystem.getSequencer()
            sequencer.open()

            // Creating a sequence.
            val sequence = Sequence(Sequence.PPQ, 4)

            // PPQ(Pulse per ticks) is used to specify timing
            // type and 4 is the timing resolution.

            // Creating a track on our sequence upon which
            // MIDI events would be placed
            val track = sequence.createTrack()

            // Adding some events to the track
            var i = 5
            while (i < (4 * numOfNotes) + 5)
            {
                i += 4

                // Add Note On event
                track.add(makeEvent(144, 1, i, 100, i))

                // Add Note Off event
                track.add(makeEvent(128, 1, i, 100, i + 2))
            }

            // Setting our sequence so that the sequencer can
            // run it on synthesizer
            sequencer.sequence = sequence

            // Specifies the beat rate in beats per minute.
            sequencer.tempoInBPM = 220F

            // Sequencer starts to play notes
            sequencer.start()

            while (true) {

                // Exit the program when sequencer has stopped playing.
                if (!sequencer.isRunning()) {
                    sequencer.close()
                    System.exit(1)
                }
            }
        }
        catch (ex: Exception) {

            ex.printStackTrace()
        }
    }

    fun makeEvent(command: Int, channel: Int, note: Int, velocity: Int, tick: Int): MidiEvent? {
        var event: MidiEvent? = null

        try {
            // ShortMessage stores a note as command type, channel,
            // instrument it has to be played on and its speed.
            val a: ShortMessage = ShortMessage()
            a.setMessage(command, channel, note, velocity)

            // A midi event is comprised of a short message(representing
            // a note) and the tick at which that note has to be played
            event = MidiEvent(a, tick.toLong())
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
        return event
    }
}
