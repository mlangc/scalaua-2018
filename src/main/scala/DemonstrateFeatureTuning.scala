import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.tuning.CrossValidator
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.apache.spark.ml.tuning.TrainValidationSplit

import at.irian.bibi.mining.classifier.spark.ml.SparkMlModule
import at.irian.bibi.mining.classifier.spark.ml.TrainTestSplit

object DemonstrateFeatureTuning extends SparkMlModule {
  import preconfiguredTransformers._
  import preconfiguredEstimators._

  val trainingData = TrainTestSplit.forTraining(labeledDataLoader.load())

  /*---snip---*/
  val pipeline = new Pipeline()
    .setStages(Array(termsExtractor, countVectorizer, idf, propertyType))

  val paramGrid = new ParamGridBuilder()
    .addGrid(termsExtractor.stemmingEnabled, Array(true, false))
    .addGrid(termsExtractor.nGrams, Array(1, 2, 3))
    .build()

  val trainValidation = new TrainValidationSplit()
    .setEstimatorParamMaps(paramGrid)
    .setEstimator(pipeline)
    .setEvaluator(new MulticlassClassificationEvaluator())

  val tunedPipeline = trainValidation.fit(trainingData)
  /*---snip---*/
}
