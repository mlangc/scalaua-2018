val tokens = "The dog ate the cat"
  .split("""\s+""").map(_.toLowerCase)

val n = 2

/*---snip---*/
val nGrams = 1.to(n).flatMap(w => {
  tokens.sliding(w, 1).map(_.mkString(" "))
}).groupBy(identity)
  .mapValues(_.size)
/*---snip---*/

nGrams.toSeq
  .sortBy(-_._2)
  .map(g => s"'${g._1}' -> ${g._2}")
  .mkString(", ")
