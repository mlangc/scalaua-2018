import java.net.URI

import akka.http.scaladsl.model.ContentTypes

import at.irian.bibi.domain.listings.ListingSource
import at.irian.bibi.mining.classifier.ClassificationInput
import at.irian.bibi.mining.features.FeaturesExtractor
import at.irian.bibi.mining.gate.IntervalSelectionPolicyName

object DemonstrateTermExtraction {
  def main(args: Array[String]): Unit = {
    val featuresExtractor = new FeaturesExtractor(
      FeaturesExtractor.DefaultCfg.copy(
        useStems = true, nGrams = 1,
        splitWords = true,
        intervalSelectionPolicy = IntervalSelectionPolicyName.Tuned_2018_04_05))

    println(featuresExtractor.toTerms(mkInput("This flat is 45 m² and costs 350.000 €")))
    println(featuresExtractor.toTerms(mkInput("Dg-Wohnung")))
    println(featuresExtractor.toTerms(mkInput("küche küchen haus häuser")))
    println(featuresExtractor.toTerms(mkInput("dachgeschosstraum")))
  }

  private def mkInput(str: String): ClassificationInput = {
    ClassificationInput(
      text = str,
      uri = URI.create("http://foo.bar"), source = ListingSource.AeternaImmo,
      contentType = ContentTypes.`text/plain(UTF-8)`, listingId = None
    )
  }

}
