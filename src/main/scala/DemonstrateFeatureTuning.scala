import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.apache.spark.ml.tuning.TrainValidationSplit

import at.irian.bibi.mining.classifier.spark.ml.SparkMlModule
import at.irian.bibi.mining.classifier.spark.ml.TrainTestSplit

object DemonstrateFeatureTuning extends SparkMlModule {
  import preconfiguredEstimators._
  import preconfiguredTransformers._

  val trainingData = TrainTestSplit.forTraining(labeledDataLoader.load())

  /*---snip---*/
  val pipeline = new Pipeline()
    .setStages(Array(termsExtractor, countVectorizer, idf, propertyType))

  // Assemble params to try:
  val paramGrid = new ParamGridBuilder()
    .addGrid(termsExtractor.stemmingEnabled, Array(true, false))
    .addGrid(termsExtractor.nGrams, Array(1, 2, 3))
    .build()

  // Configure train validation:
  val trainValidation = new TrainValidationSplit()
    .setEstimatorParamMaps(paramGrid)
    .setEstimator(pipeline)
    .setEvaluator(new MulticlassClassificationEvaluator())

  // Find best combination of params:
  val tunedPipeline = trainValidation.fit(trainingData)
  /*---snip---*/
}
