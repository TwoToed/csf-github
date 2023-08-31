package vttp2023.batch3.csf.assessment.cnserver.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import vttp2023.batch3.csf.assessment.cnserver.repositories.ImageRepository;
import vttp2023.batch3.csf.assessment.cnserver.repositories.NewsRepository;

@RestController
public class NewsController {

	// TODO: Task 1
	@Autowired
	private ImageRepository s3Repo;

	@Autowired
	private NewsRepository mongoRepo;
	// TODO: Task 1
	@PostMapping(path="upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> upload(@RequestPart String title, @RequestPart MultipartFile file, @RequestPart String description, @RequestPart String tags) {
		System.out.println(title + file + description + tags);
		System.out.println("HELLO");
		String url = s3Repo.saveImage(file);
		System.out.printf(">>> saving to S3: %s\n", url);
		String _id = mongoRepo.insertNews(title, description, url, tags).toString();
		JsonObject resp = Json.createObjectBuilder()
			.add("newsId", _id)
			.build();
		mongoRepo.getTags(5);
		return ResponseEntity.ok(resp.toString());
	}

	// TODO: Task 2
	@GetMapping(path="tags")
	public ResponseEntity<String> toptags(){
		List<Document> result = mongoRepo.getTags(5);
		JsonArrayBuilder tagsarray = Json.createArrayBuilder();
		for (Document doc : result){
			tagsarray.add(doc.toJson());
		}

		JsonArray resp = tagsarray.build();
		return ResponseEntity.ok(resp.toString());
		
	}

	// TODO: Task 3

}
