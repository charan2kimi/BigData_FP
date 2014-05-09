package com.charan.myPackage;



import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.RMSRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.neighborhood.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.apache.mahout.common.RandomUtils;

import java.io.*;
import java.util.*;

class MahoutReco {
	static int  user=92652;
public static void main(String[] args) throws Exception {
	File file=new File("/home/charan/Downloads/Books2.csv");
DataModel model = new FileDataModel(file);

usersimi(model);
usersimiLogLike(model);
usersimiEucledian(model);

usersimiThresholdPearson(model);
usersimiThresholdEucledian(model);

itemsimi(model);
itemsimiLogLike(model);
itemsimiEucledian(model);

//Evaluators

userSimiEvaluatorNearest8020(model);

userSimiEvaluatorNearestPearson(model);
userSimiEvaluatorNearestEucledian(model);
userSimiEvaluatorNearestLogLikelihood(model);

userSimiEvaluatorThreshold(model);
userSimiEvaluatorThresholdEucledian(model);
userSimiEvaluatorThresholdLog(model);

itemSimiEvaluatorPearsonCorrelation(model);
itemSimiEvaluatorLogLikelihood(model);
itemSimiEvaluatorEuclideanDistance(model);


}

public static void usersimi(DataModel model) throws TasteException{

	RecommenderEvaluator evaluator =
			new AverageAbsoluteDifferenceRecommenderEvaluator();

	
	UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
	UserNeighborhood neighborhood =
	new NearestNUserNeighborhood(6, similarity, model);
	Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); 

	List<RecommendedItem> recommendations =
	recommender.recommend(user, 20); 
	System.out.println("\n User based recommendations Nearest Pearson:\n");
	for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	}
}
public static void itemsimi(DataModel model) throws TasteException{
	ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
	
	Recommender recommender = new GenericItemBasedRecommender(model, similarity); 

	List<RecommendedItem> recommendations =
	recommender.recommend(user, 20); 
System.out.println("\n Item based recommendations Pearson:\n");
	for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	}
}

public static void userSimiEvaluatorNearestPearson(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood =
			new NearestNUserNeighborhood(6, similarity, model);
			return new GenericUserBasedRecommender(model, neighborhood, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n User based recommendation Using Nearest Neighbourhood Pearson Correlation:\n"+score);

}

public static void userSimiEvaluatorThreshold(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood =
			new ThresholdUserNeighborhood(0.5, similarity, model);
			return new GenericUserBasedRecommender(model, neighborhood, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n User based recommendation Using Threshold Neighbourhood and Pearspn Correlation:\n"+score);

}
public static void userSimiEvaluatorNearestEucledian(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
			UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
			UserNeighborhood neighborhood =
			new NearestNUserNeighborhood(6, similarity, model);
			return new GenericUserBasedRecommender(model, neighborhood, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n User based recommendation Using Nearest Neighbourhood Eucledian Distance:\n"+score);

}public static void userSimiEvaluatorNearestLogLikelihood(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
			UserSimilarity similarity = new LogLikelihoodSimilarity(model);
			UserNeighborhood neighborhood =
			new NearestNUserNeighborhood(6, similarity, model);
			return new GenericUserBasedRecommender(model, neighborhood, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n User based recommendation Using Nearest Neighbourhood LogLikelihood:\n"+score);

}


public static void userSimiEvaluatorThresholdEucledian(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
			UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
			UserNeighborhood neighborhood =
			new ThresholdUserNeighborhood(0.5, similarity, model);
			return new GenericUserBasedRecommender(model, neighborhood, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n User based recommendation Using Threshold Neighbourhood Euclidean Similarity:\n"+score);

}


public static void userSimiEvaluatorThresholdLog(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
			UserSimilarity similarity = new LogLikelihoodSimilarity(model);
			UserNeighborhood neighborhood =
			new ThresholdUserNeighborhood(0.5, similarity, model);
			return new GenericUserBasedRecommender(model, neighborhood, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n User based recommendation Using Threshold Neighbourhood Log-Likelihood Similarity :\n"+score);

}

public static void itemSimiEvaluatorPearsonCorrelation(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
				ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
				
				 

			return new GenericItemBasedRecommender(model, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n Item based recommendation Using Pearson Correlation Similarity :\n"+score);

}
public static void itemSimiEvaluatorEuclideanDistance(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
				ItemSimilarity similarity = new EuclideanDistanceSimilarity(model);
				
				 

			return new GenericItemBasedRecommender(model, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n Item based recommendation Using Euclidean Distance Similarity :\n"+score);

}
public static void itemSimiEvaluatorLogLikelihood(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
				ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
				
				 

			return new GenericItemBasedRecommender(model, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.90, 0.10);
			System.out.println("\n Item based recommendation Using Log-Likelihood Similarity :\n"+score);

}
/*
public static void clusterbasedSimiEvaluatorLogLikelihood(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
				UserSimilarity similarity = new LogLikelihoodSimilarity(model);
				FarthestNeighborClusterSimilarity clusterSimilarity =
				new FarthestNeighborClusterSimilarity(similarity);
				return new TreeClusteringRecommender(model, clusterSimilarity, 10);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.95, 0.10);
			System.out.println("\n Item based recommendation Using Log-Likelihood Similarity :\n"+score);

}
*/

public static void userSimiEvaluatorNearest8020(DataModel model) throws TasteException{
	RandomUtils.useTestSeed();

	RecommenderEvaluator evaluator =
			new RMSRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
			@Override
			public Recommender buildRecommender(DataModel model) throws TasteException {
			UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
			UserNeighborhood neighborhood =
			new NearestNUserNeighborhood(6, similarity, model);
			return new GenericUserBasedRecommender(model, neighborhood, similarity);
			}
			};
			double score = evaluator.evaluate(recommenderBuilder, null, model, 0.70, 0.10);
			System.out.println("\n User based recommendation Using Nearest Neighbourhood Pearson Correlation 80 20:\n"+score);

}
public static void usersimiLogLike(DataModel model) throws TasteException{

	RecommenderEvaluator evaluator =
			new AverageAbsoluteDifferenceRecommenderEvaluator();

	
	UserSimilarity similarity = new LogLikelihoodSimilarity(model);
	UserNeighborhood neighborhood =
	new NearestNUserNeighborhood(6, similarity, model);
	Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); 

	List<RecommendedItem> recommendations =
	recommender.recommend(user, 20); 
	System.out.println("\n User based recommendations Nearest Log Like:\n");
	for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	}
}
public static void usersimiEucledian(DataModel model) throws TasteException{

	RecommenderEvaluator evaluator =
			new AverageAbsoluteDifferenceRecommenderEvaluator();

	
	UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
	UserNeighborhood neighborhood =
	new NearestNUserNeighborhood(6, similarity, model);
	Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); 

	List<RecommendedItem> recommendations =
	recommender.recommend(user, 20); 
	System.out.println("\n User based recommendations Nearest Eucledian Similarity:\n");
	for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	}
}
public static void usersimiThresholdPearson(DataModel model) throws TasteException{

	RecommenderEvaluator evaluator =
			new AverageAbsoluteDifferenceRecommenderEvaluator();

	
	UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
	UserNeighborhood neighborhood =
	new ThresholdUserNeighborhood(0.6, similarity, model);
	Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); 

	List<RecommendedItem> recommendations =
	recommender.recommend(user,20); 
	System.out.println("\n User based recommendations Threshold Pearson:\n");
	for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	}
}
public static void usersimiThresholdEucledian(DataModel model) throws TasteException{

	RecommenderEvaluator evaluator =
			new AverageAbsoluteDifferenceRecommenderEvaluator();

	
	UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
	UserNeighborhood neighborhood =
	new ThresholdUserNeighborhood(0.6, similarity, model);
	Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity); 

	List<RecommendedItem> recommendations =
	recommender.recommend(user, 20); 
	System.out.println("\n User based recommendations Threshold Eucledian:\n");
	for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	}
}
public static void itemsimiLogLike(DataModel model) throws TasteException{
	ItemSimilarity similarity = new LogLikelihoodSimilarity(model);
	
	Recommender recommender = new GenericItemBasedRecommender(model, similarity); 

	List<RecommendedItem> recommendations =
	recommender.recommend(user, 20); 
System.out.println("\n Item based recommendations Log Likelihood:\n");
	for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	}
}
public static void itemsimiEucledian(DataModel model) throws TasteException{
	ItemSimilarity similarity = new EuclideanDistanceSimilarity(model);
	
	Recommender recommender = new GenericItemBasedRecommender(model, similarity); 

	List<RecommendedItem> recommendations =
	recommender.recommend(user, 20); 
System.out.println("\n Item based recommendations Euclidean Distance Similarity:\n");
	for (RecommendedItem recommendation : recommendations) {
	System.out.println(recommendation);
	}
}
}
