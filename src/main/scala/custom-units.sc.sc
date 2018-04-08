import java.net.URI

import akka.http.scaladsl.model.ContentTypes

import at.irian.bibi.domain.listings.ListingSource
import at.irian.bibi.mining.classifier.ClassificationInput
import at.irian.bibi.mining.features.FeaturesExtractor

val featuresExtractor = new FeaturesExtractor(
  FeaturesExtractor.DefaultCfg.copy(useStems = false, nGrams = 2))

val input = ClassificationInput(
  text = "This flat is 45 m² and costs 350,000 €",
  uri = URI.create("http://foo.bar"), source = ListingSource.AeternaImmo,
  contentType = ContentTypes.`text/plain(UTF-8)`, listingId = None)

val terms = featuresExtractor.toTerms(input)
