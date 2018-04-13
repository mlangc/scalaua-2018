import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.tuning.CrossValidator
import org.apache.spark.ml.tuning.ParamGridBuilder

import at.irian.bibi.mining.classifier.spark.ml.SparkMlModule
import at.irian.bibi.mining.classifier.spark.ml.TrainTestSplit

object DemonstrateHyperParamTuning extends SparkMlModule {
  import preconfiguredTransformers._
  import preconfiguredEstimators._

  val trainingData = TrainTestSplit.forTraining(labeledDataLoader.load())

  val pipeline = new Pipeline()
    .setStages(Array(termsExtractor, countVectorizer, idf, propertyType))

  val paramGrid = new ParamGridBuilder()
    .addGrid(termsExtractor.stemmingEnabled, Array(true, false))
    .addGrid(termsExtractor.nGrams, Array(1, 2, 3))
    .build()

  val crossValidator = new CrossValidator()
    .setEstimator(pipeline)
    .setNumFolds(5)
    .setEvaluator(evaluator)

  crossValidator.fit()




}
