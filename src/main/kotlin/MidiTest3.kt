import java.lang.Exception
import javax.sound.midi.*
import java.util.*
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    //println("Enter the number of notes to be played: ")
    //val input: Scanner = Scanner(System.`in`)
    val numOfNotes = 1000 //input.nextInt()

    val player = MidiTest3()
    player.setUpPlayer(numOfNotes)
}

class MidiTest3 {
    private lateinit var midiSynth: Synthesizer

    fun setUpPlayer(numOfNotes: Int)
    {
        try {
            midiSynth = MidiSystem.getSynthesizer().apply { open() }

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
                val rand = Random().nextInt(10)
                i += rand

                // Add Note On event
                track.add(makeEvent(ShortMessage.NOTE_ON, 1, i, 100, i))

                val instrumentNumber = 106
                    val instrumentsArray = midiSynth.defaultSoundbank.instruments

                val instrument = instrumentsArray[instrumentNumber]
                track.add(makeEvent(ShortMessage.PROGRAM_CHANGE, 1, instrumentNumber, 100, i))
                println("now playing: ${instrument.name}")

                // Add Note Off event
                track.add(makeEvent(ShortMessage.NOTE_OFF, 1, i, 100, i + 2))
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
                if (!sequencer.isRunning) {
                    sequencer.close()
                    exitProcess(1)
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
            val shortMessage = ShortMessage()
            shortMessage.setMessage(command, channel, note, velocity)

            // A midi event is comprised of a short message(representing
            // a note) and the tick at which that note has to be played
            event = MidiEvent(shortMessage, tick.toLong())
        }
        catch (ex: Exception) {
            ex.printStackTrace()
        }
        return event
    }
}
