import org.apache.spark.ml.Pipeline

import at.irian.bibi.mining.classifier.spark.ml.SparkMlModule

object SimpleMlPipeline extends SparkMlModule {
  import preconfiguredModels._
  import preconfiguredModels.featureRelated._
  import preconfiguredTransformers._

  val trainingData = labeledDataLoader.load()

  /*---snip---*/
  val featureExtraction = new Pipeline()
    .setStages(Array(termsExtractor, countVectorizer))

  val featureTranformations = new Pipeline()
    .setStages(Array(idf, termRelevance))

  val pipeline = new Pipeline()
    .setStages(Array(featureExtraction, featureTranformations,
      classifiers.propertyType,
      classifiers.propertySubType,
      classifiers.listingCategory))

  val pipelineModel = pipeline.fit(trainingData)
  /*---snip---*/
}
