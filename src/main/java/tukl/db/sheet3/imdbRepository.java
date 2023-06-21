package tukl.db.sheet3;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.ConvertOperators.ToDouble.toDouble;


import java.util.List;

@Repository
public class imdbRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public imdbRepository(MongoTemplate mt){
        this.mongoTemplate = mt;
    }

    public List<Document> getAllDocs(String collection){
        return mongoTemplate.findAll(Document.class, collection);
    }

    public List<Document> topRatedMoviesInGenre (String genre){
        AggregationOperation matchStage = match(Criteria.where("genre").is(genre));
        AggregationOperation sortStage = sort(Sort.Direction.DESC, "$ratingNumeric");
        AggregationOperation limitStage = limit(1);

        Aggregation aggregation = newAggregation(matchStage, sortStage, limitStage);

        AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "movie_details", Document.class);
        return results.getMappedResults();
    }
}
