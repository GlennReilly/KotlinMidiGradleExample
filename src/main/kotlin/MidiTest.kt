import javax.sound.midi.MidiSystem
import javax.sound.midi.MidiUnavailableException
import javax.sound.midi.Soundbank
import javax.sound.midi.Synthesizer
import javax.sound.sampled.spi.AudioFileReader


lateinit var midiSynth: Synthesizer

fun main(args: Array<String>) {
    try {
        midiSynth = MidiSystem.getSynthesizer().apply { open() }

        (0..128).forEach {
            println("it: $it")
            PlayPhrase(it)
        }

    } catch (ex: MidiUnavailableException) {
        println("Exception: ${ex.message}")
    } finally {
        midiSynth.close()
    }

    //val audioFileReader = AudioFileReader()
}

private fun PlayPhrase(instrumentNumber: Int) {
    playNote(
            instrumentNumber,
            0,
            noteNumber = 60,
            noteVelocity = 100,
            holdNoteTime = 800
    )

    playNote(
            instrumentNumber,
            0,
            noteNumber = 69,
            noteVelocity = 50,
            holdNoteTime = 1333
    )

    playNote(
            instrumentNumber,
            0,
            noteNumber = 43,
            noteVelocity = 89,
            holdNoteTime = 970
    )

    playNote(
            instrumentNumber,
            0,
            noteNumber = 37,
            noteVelocity = 134,
            holdNoteTime = 2733
    )
}

fun playNote(instrumentNumber: Int, midiChannelNumber: Int = 0, noteNumber: Int, noteVelocity: Int, holdNoteTime: Long) {
    val instrumentsArray = midiSynth.defaultSoundbank.instruments
    val instrument = instrumentsArray[instrumentNumber]
    val midiChannels = midiSynth.channels
    midiSynth.loadInstrument(instrument)
    midiChannels[midiChannelNumber].programChange(instrument.patch.program)
    midiChannels[midiChannelNumber].noteOn(noteNumber, noteVelocity)
    println("now playing instrument: ${instrument.name}")
    try {
        Thread.sleep(holdNoteTime)
    } catch (ex: MidiUnavailableException) {
        println("Exception: ${ex.message}")
    }
    midiChannels[0].noteOff(noteNumber)

/*    val soundbank: Soundbank = SoundFon
    soundbank.*/
}


