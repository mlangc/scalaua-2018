import org.apache.spark.ml.classification.LogisticRegression
import org.apache.spark.ml.evaluation.BinaryClassificationEvaluator
import org.apache.spark.ml.tuning.ParamGridBuilder
import org.apache.spark.ml.tuning.TrainValidationSplit

import at.irian.bibi.mining.classifier.spark.ml.SparkMlModule

object DemonstrateHyperParamTuning extends SparkMlModule {
  val trainingData = labeledDataLoader.load()

  /*---snip---*/
  val logReg = new LogisticRegression()

  // Assemble params to evaluate:
  val paramGrid = new ParamGridBuilder()
    .addGrid(logReg.regParam, Array(1e-8, 1e-6, 1e-4))
    .addGrid(logReg.elasticNetParam, Array(0.0, 0.5, 1.0))
    .build()

  // Configure train validation split:
  val trainValidationSplit = new TrainValidationSplit()
    .setEstimator(logReg)
    .setEstimatorParamMaps(paramGrid)
    .setEvaluator(new BinaryClassificationEvaluator())

  // Choose best set of params:
  val tunedModel = trainValidationSplit.fit(trainingData)
  /*---snip---*/
}
