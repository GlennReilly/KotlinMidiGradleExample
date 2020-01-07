import org.jfugue.pattern.Pattern
import org.jfugue.player.Player
import org.staccato.ReplacementMapPreprocessor
import java.util.*


fun main(args: Array<String>) {

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

    //val player = Player()
    //player.play("C D E F G A B")

/*
    val noteString = Note.getToneString(60)
    println("noteString: $noteString")
    val p1 = Pattern("V0 I[Piano] Eq Ch. | Eq Ch. | Dq Eq Dq Cq")
    val p2 = Pattern("V1 I[Flute] Rw     | Rw     | GmajQQQ  CmajQ")
    val player = Player()
    player.play(p1, p2)}*/

/*    val rhythm = Rhythm()
            .addLayer("O..oO...O..oOO..") // This is Layer 0
            .addLayer("..S...S...S...S.")
            .addLayer("````````````````")
            .addLayer("...............+") // This is Layer 3
            .addOneTimeAltLayer(3, 3, "...+...+...+...+") // Replace Layer 3 with this string on the 4th (count from 0) measure
            .setLength(4) // Set the length of the rhythm to 4 measures

    Player().play(rhythm.pattern.repeat(2)) // Play 2 instances of the 4-measure-long rhythm*/


/*    val rhythm = Rhythm()
            .addLayer("O..oO...O..oOO..")
            .addLayer("..S...S...S...S.")
            .addLayer("````````````````")
            .addLayer("...............+")
    Player().play(rhythm.pattern.repeat(2))*/

/*    val cp = ChordProgression("I IV V")

    val player = Player()
    player.play(cp)*/


/*    player.play(cp.eachChordAs("$0q $1q $2q Rq"))

    player.play(cp.allChordsAs("$0q $0q $0q $0q $1q $1q $2q $0q"))

    player.play(cp.allChordsAs("$0 $0 $0 $0 $1 $1 $2 $0").eachChordAs("V0 $0s $1s $2s Rs V1 $!q"))*/
}