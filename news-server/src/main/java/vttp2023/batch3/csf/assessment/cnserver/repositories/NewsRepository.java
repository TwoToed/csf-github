package vttp2023.batch3.csf.assessment.cnserver.repositories;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Repository
public class NewsRepository {

	// TODO: Task 1 
	// Write the native Mongo query in the comment above the method
		@Autowired
		private MongoTemplate template;
    	public ObjectId insertNews (String title, String description, String image, String tags){
			tags = tags.replace ("[", "");
			tags = tags.replace ("]", "");
			String[] listoftags = tags.split(",");
			JsonArrayBuilder tagsarray = Json.createArrayBuilder();
			for (String tag : listoftags){
				tagsarray.add(tag);
			}
		JsonObject news = Json.createObjectBuilder()
				.add("postDate", System.currentTimeMillis())
				.add("title", title)
				.add("description", description)
				.add("image", image)
				.add("tags", tagsarray)
				.build();
        Document newNews = Document.parse(news.toString());
        System.out.println(news.toString() + " adding success");
        Document inserting = template.insert(newNews, "news");
        ObjectId _id = (ObjectId)inserting.get( "_id" );
		return _id;
    }

	// TODO: Task 2 
	// Write the native Mongo query in the comment above the method
		public List<Document> getTags(int min) {
		AggregationOperation unwindTags = Aggregation.unwind("tags");
		GroupOperation groupByTags = Aggregation.group("tags")
		.push("tags").as("tag")
		.count().as("count");
		ProjectionOperation projectTags =
			Aggregation.project("tag","count").andExclude("_id");
		SortOperation sortByCount = Aggregation.sort(
			Sort.by(Direction.DESC, "count")
		);
		SortOperation sortByTag = Aggregation.sort(
			Sort.by(Direction.ASC, "tag")
		);
		LimitOperation limitBy10 = Aggregation.limit(10);
		Aggregation pipeline = Aggregation.newAggregation(unwindTags,
		groupByTags, projectTags, sortByCount, sortByTag, limitBy10);
		AggregationResults<Document> results = template.aggregate(
		pipeline,"news" ,Document.class);
		System.out.println(results.getMappedResults());
		System.out.println("HELLO ARE YOU WORKING MONGO");
		return results.getMappedResults();
	}

	// TODO: Task 3
	// Write the native Mongo query in the comment above the method


}
