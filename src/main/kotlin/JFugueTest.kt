import org.jfugue.pattern.Pattern
import org.jfugue.player.Player
import org.jfugue.rhythm.Rhythm
import org.jfugue.theory.ChordProgression
import org.jfugue.theory.Note
import org.staccato.ReplacementMapPreprocessor
import java.util.*


fun main(args: Array<String>) {
    //playFractalMusic()
    //playBasicExample()
    //playPattern()
    //playRhythm()
    //playRhythm2()
    //playChordProgression()
    //playChordProgression3()
    playRandomProgression()
}

private val keyToPlay = listOf("Dmaj", "Amin", "Dmin").shuffled().first()
private val bpm = listOf(120, 100, 90, 80, 70).shuffled().first()
private const val repeats = 4

val chordList = listOf("I", "ii", "iii", "IV", "V", "vi", "vii")

val randomChordProgression
    get() = chordList.shuffled().take(4)

val randomMelody
get() = listOf("C", "D", "E", "F", "G", "A", "B").shuffled().take(8)

val bassInstrument
    get() = listOf(
            "Fretless_Bass",
            "Synth_Bass_1",
            "Synth_Bass_2").shuffled().first()

val melodicInstrument
    get() = listOf("Glockenspiel",
            "Guitar_harmonics",
            "Trumpet",
            "Muted_Trumpet",
            "Oboe",
            "Shakuhachi",
            "Sitar",
            "Pan_Flute").shuffled().first()

val rhythmInstrument
    get() = listOf("Rock_Organ",
            "Voice_Oohs",
            "Choir_Aahs",
            "Brass_Section").shuffled().first()

private fun playRandomProgression() {
    val allChordsInKey  = ChordProgression(chordList.joinToString()).setKey(keyToPlay)
    println("allChordsInKey $keyToPlay: $allChordsInKey")

    val rhythm = Rhythm()
            .addLayer("O..oO...O..oOO..")
            .addLayer("..S...S...S...S.")
            .addLayer("````````````````")
            .addLayer("...............+")

    val chordProgression1 = Pattern(ChordProgression(randomChordProgression.joinToString())
            .eachChordAs("$!w"))
            .setTempo(bpm)
            .repeat(4)
            .setInstrument(rhythmInstrument)

    println("rhythmInstrument: $rhythmInstrument")
    
    val chordProgression2 = Pattern(ChordProgression(randomChordProgression.joinToString())
            .eachChordAs("$!w"))
            .setTempo(bpm)
            .repeat(2)

    val chordProgression3 = Pattern(ChordProgression(randomChordProgression.joinToString())
            .eachChordAs("$!w"))
            .setTempo(bpm)
            .repeat(2)

    val melody1 = Pattern(randomMelody.joinToString()).addToEachNoteToken("$!q")
            .setTempo(bpm)
            .repeat(2)
            .setVoice(2)
            .setInstrument(melodicInstrument)

    println("melodicInstrument: $melodicInstrument")

    val melody2 = Pattern(randomMelody.joinToString()).addToEachNoteToken("$!q")
            .setTempo(bpm)
            .repeat(1)
            .setVoice(2)

    val melody3 = Pattern(randomMelody.joinToString())
            .addToEachNoteToken("$!q")
            .setTempo(bpm)
            .repeat(2)
            .setVoice(2)

    val rest = Pattern("Rw Rw Rw Rw")
            .repeat(2)
            .setVoice(2)

    val bass1 = Pattern(randomMelody.joinToString(separator = " "))
            .addToEachNoteToken("2$!q")
            .setTempo(bpm)
            .repeat(40)
            .setVoice(3)
            .setInstrument(bassInstrument)

    println("bassInstrument: $bassInstrument")

    println("bpm: $bpm")

    println("chordProgression1: $chordProgression1")
    println("chordProgression2: $chordProgression2")
    println("chordProgression3: $chordProgression3")

    println("melody1: $melody1")
    println("melody2: $melody2")
    println("melody3: $melody3")

    println("bass1: $bass1")

    val player = Player()
    player.play(
            chordProgression1,
            chordProgression2,
            chordProgression1,
            chordProgression2,
            chordProgression3,
            chordProgression1,
            rest,
            melody1,
            melody2,
            melody1,
            melody3,
            rest,
            melody2,
            melody3,
            melody1,
            melody1,
            bass1,
            rhythm.pattern.repeat(40)
     )
}



private fun playChordProgression3() {

    val randomChord2 = chordList.shuffled().first()

    val allChordsInKey  = ChordProgression(chordList.joinToString()).setKey(keyToPlay)
    println("allChordsInKey $keyToPlay: $allChordsInKey")
/*    val cp = ChordProgression("VII I V D#5s iii iv7 V ii vi $randomChord")
            .setKey(keyToPlay)
    println("cp.chords in $keyToPlay: ${cp.chords.asList().joinToString()}")*/

    val chords = "C#5MAJ D4MAJh A4MAJ D4MAJh F#4MIN A4MAJh A4MAJq E4MINhq"

    val melody = Pattern(
            "Rw     | Rw     | Rw     | Rq B5Q A5Q Rq C#QQ  | Rw  | Rq | A4QQ Rq F#4HH" +
                    "Rw     | Rw     | Rw     | Rq B4QQ Rq F#4QQ | Rw  | Rq | f#QQ Rq B4HH")
            .setVoice(1).setInstrument("Flute")

    val bassLine = Pattern("X[Volume]=11200 Rh A3QQ Rq C#4QQ | D4QQ Rq F#3HH")
            .setVoice(2).setInstrument("Acoustic_Bass")

    val rhythm = Rhythm()
            .addLayer("O..oO...O..oOO..")
            .addLayer("..S...S...S...S.")
            .addLayer("````````````````")
            .addLayer("...............+")

    //val x: Pattern = cp.setKey(keyToPlay).pattern.setTempo(bpm).repeat(repeats)
    val chordProgression = Pattern(chords).setTempo(bpm)

    println("chords: $chords")

    val player = Player()
    player.play(
            chordProgression.repeat(20),
            melody.repeat(repeats)
            //,
            //bassLine.repeat(20),
            //rhythm.pattern.repeat(20)
    )
}

private fun playChordProgression2() {
    val cp = ChordProgression("VII I V II III IV VII")
    val rhythm = Rhythm()
            .addLayer("O..oO...O..oOO..")
            .addLayer("..S...S...S...S.")
            .addLayer("````````````````")
            .addLayer("...............+")

    val player = Player()
    player.play(cp.setKey(keyToPlay).pattern.setTempo(bpm), rhythm.pattern.repeat(2))
}

private fun playChordProgression() {
    val cp = ChordProgression("I IV V")
    val player = Player()
    player.play(cp)

    /*
    player.play(cp.eachChordAs("$0q $1q $2q Rq"))
    player.play(cp.allChordsAs("$0q $0q $0q $0q $1q $1q $2q $0q"))
    player.play(cp.allChordsAs("$0 $0 $0 $0 $1 $1 $2 $0").eachChordAs("V0 $0s $1s $2s Rs V1 $!q"))
    */
}

private fun playRhythm2() {
    val rhythm = Rhythm()
            .addLayer("O..oO...O..oOO..")
            .addLayer("..S...S...S...S.")
            .addLayer("````````````````")
            .addLayer("...............+")
    Player().play(rhythm.pattern.repeat(2))
}

private fun playRhythm() {
    val rhythm = Rhythm()
            .addLayer("O..oO...O..oOO..") // This is Layer 0
            .addLayer("..S...S...S...S.")
            .addLayer("````````````````")
            .addLayer("...............+") // This is Layer 3
            .addOneTimeAltLayer(3, 3, "...+...+...+...+") // Replace Layer 3 with this string on the 4th (count from 0) measure
            .setLength(4) // Set the length of the rhythm to 4 measures

    Player().play(rhythm.pattern.repeat(2)) // Play 2 instances of the 4-measure-long rhythm
}

private fun playPattern() {
    val noteString = Note.getToneString(60)
    println("noteString: $noteString")
    val p1 = Pattern("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq")
    val p2 = Pattern("V1 I[Flute] Rw     | Rw     | GmajQQQ  CmajQ")
    val player = Player()
    player.play(p1, p2)
}

private fun playBasicExample() {
    val player = Player()
    player.play("C D E F G A B")
}

private fun playFractalMusic() {
    // Specify the transformation rules for this Lindenmayer system
    val rules: MutableMap<String, String> = object : HashMap<String, String>() {
        init {
            put("Cmajw", "Cmajw Fmajw")
            put("Fmajw", "Rw Bbmajw")
            put("Bbmajw", "Rw Fmajw")
            put("C5q", "C5q G5q E6q C6q")
            put("E6q", "G6q D6q F6i C6i D6q")
            put("G6i+D6i", "Rq Rq G6i+D6i G6i+D6i Rq")
            put("axiom", "axiom V0 I[Flute] Rq C5q V1 I[Tubular_Bells] Rq Rq Rq G6i+D6i V2 I[Piano] Cmajw E6q " +
                    "V3 I[Warm] E6q G6i+D6i V4 I[Voice] C5q E6q")
        }
    }

    // Set up the ReplacementMapPreprocessor to iterate 3 times
    // and not require brackets around replacements
    // Set up the ReplacementMapPreprocessor to iterate 3 times
// and not require brackets around replacements
    val rmp = ReplacementMapPreprocessor.getInstance()
    rmp.setReplacementMap(rules)
    rmp.iterations = 4
    rmp.setRequireAngleBrackets(false)

    // Create a Pattern that contains the L-System axiom
    // Create a Pattern that contains the L-System axiom
    val axiom = Pattern("T120 " + "V0 I[Flute] Rq C5q "
            + "V1 I[Tubular_Bells] Rq Rq Rq G6i+D6i "
            + "V2 I[Piano] Cmajw E6q "
            + "V3 I[Warm] E6q G6i+D6i "
            + "V4 I[Voice] C5q E6q")

    val player = Player()
    println(rmp.preprocess(axiom.toString(), null))
    player.play(axiom)
}