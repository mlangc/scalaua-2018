import org.apache.spark.ml.classification.LinearSVC
import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.apache.spark.ml.tuning.TrainValidationSplit

import at.irian.bibi.mining.classifier.spark.ml.DelegatingEstimator
import at.irian.bibi.mining.classifier.spark.ml.SparkMlModule
import at.irian.bibi.mining.classifier.spark.ml.TrainTestSplit

object DemonstrateAlgSelection extends SparkMlModule {
  val trainingData = TrainTestSplit.forTraining(labeledDataLoader.load())

  /*---snip---*/
  val logRegression = new LogisticRegression()
  val svmEstimator = new LinearSVC()

  // See https://stackoverflow.com/questions/48971317/how-to-use-crossvalidator-to-choose-between-different-models
  val delegatingEstimator = new DelegatingEstimator(
    logRegression, svmEstimator)

  val paramGrid = new ParamGridBuilder()
    .addGrid(delegatingEstimator.selectedEstimator, Array(0, 1))
    .build()

  val trainValidation = new TrainValidationSplit()
    .setEstimatorParamMaps(paramGrid)
    .setEstimator(delegatingEstimator)
    .setEvaluator(new BinaryClassificationEvaluator())

  val model = trainValidation.fit(trainingData)
  /*---snip---*/
}
