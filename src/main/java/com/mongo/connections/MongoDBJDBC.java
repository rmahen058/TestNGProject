package com.mongo.connections;

import java.net.UnknownHostException;
import java.util.List;

import org.testng.Reporter;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.swapi.dto.Films;
import com.swapi.dto.Planet;
import com.swapi.dto.Residents;

/**
 * This class is to perform CRUD operations of MongoDB
 * 
 * @author rakes
 *
 */
public class MongoDBJDBC {
	public static boolean reporterFlag = true;
	public static MongoClient mongoClient;
	public static DB db;
	public static DBCollection dbColl;
	public static BasicDBObject doc;
	public static DBCursor dbCur;
	public static String collectionName = "planets";

	public static void createMongoDB(String dbName) throws UnknownHostException {

		mongoClient = new MongoClient("localhost", 27017);
		db = mongoClient.getDB("swapi");
		if (db.getCollection(collectionName) == null) {
			dbColl = db.createCollection("planets", new BasicDBObject());
			Reporter.log("Collections created successfully: 'planets'", reporterFlag);
		}

		dbColl = db.getCollection(collectionName);
	}

	public static void insertToMongoDB(List<Planet> lstPlanets) {
		int iCount = 1;
		BasicDBObject docPlanet;
		BasicDBObject docResident = new BasicDBObject();
		BasicDBObject docFilms;

		for (Planet objPlanet : lstPlanets) {
			// This outer loop to insert Planet attributes
			docPlanet = new BasicDBObject();
			docPlanet.put("ID", iCount);
			docPlanet.put("PlanetId", objPlanet.getiPlanetId());
			docPlanet.put("PlanetName", objPlanet.getStrPlanetName());
			docPlanet.put("PlanetPopulation", objPlanet.getlPopulation());
			docPlanet.put("PlanetDiameter", objPlanet.getlDiameter());

			for (Residents objResidents : objPlanet.getObjResidents()) {
				// This 1st level loop to insert REsident attributes
				if (objResidents != null) {
					docResident = new BasicDBObject();
					docResident.append("ResidentName", objResidents.getStrName())
							.append("ResidentHeight", objResidents.getiHeigt())
							.append("ResidentMass", objResidents.getiMass());

					docPlanet.put("PlanetResidents", docResident);

					for (Films objFilms : objResidents.getObjFilm()) {
						// This inner most loop to insert Films
						if (objFilms != null) {
							docFilms = new BasicDBObject();
							docFilms.put("FilmTitle", objFilms.getStrTitle());

							docResident.put("ResidentFilms", docFilms);
						}
					}
				}
			}
			dbColl.insert(docPlanet);
			iCount++;
		}
	}

	public static void queryMongoDB() {
		dbCur = dbColl.find();
		int i = 1;
		while (dbCur.hasNext()) {
			Reporter.log("Document at Row" + i + " is: ", reporterFlag);
			System.out.println(dbCur.next());
			i++;
		}
	}

	public static void dropMongoDB() {
		db.dropDatabase();
	}
}
