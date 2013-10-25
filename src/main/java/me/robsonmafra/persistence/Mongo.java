package me.robsonmafra.persistence;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

public class Mongo {
	
	public static DB getDatabase() {
		DB db = getMongo().getDB("twitter_stream");
		return db;
	}

	public static MongoClient getMongo() {
        MongoClient mongoClient = null;
		try {
			mongoClient = new MongoClient();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
		return mongoClient;        
	}

}
