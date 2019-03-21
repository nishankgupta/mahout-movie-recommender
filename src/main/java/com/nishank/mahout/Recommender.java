package com.nishank.mahout;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Recommender {

    public static void main(String[] args) throws IOException, TasteException {

        try {
            //Creating data model
            DataModel datamodel = new FileDataModel(new File("src/main/resources/ratings.csv")); //data

            UserSimilarity usersimilarity = new PearsonCorrelationSimilarity(datamodel);

            //Creating UserNeighbourHHood object.
            UserNeighborhood userneighborhood = new ThresholdUserNeighborhood(1, usersimilarity, datamodel);
            //userneighborhood = new NearestNUserNeighborhood(4, usersimilarity, datamodel);

            //Create UserRecomender
            UserBasedRecommender recommender = new GenericUserBasedRecommender(datamodel, userneighborhood, usersimilarity);

            List<RecommendedItem> recommendations = recommender.recommend(9, 5);

            for (RecommendedItem recommendation : recommendations) {
                System.out.println(recommendation);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
