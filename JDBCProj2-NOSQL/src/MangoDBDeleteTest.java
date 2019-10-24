import java.util.List;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MangoDBDeleteTest {

	public static void main(String[] args) {
		MongoClient client=null;
		MongoDatabase db=null;
		MongoCollection<Document> collection=null;
		List<Document> list=null;
		//create MangoDBClient obj
		client=new MongoClient("localhost",27017);
		//  get Logical DB
		db=client.getDatabase("MDB1");
		 // Get collection
	     collection=  db.getCollection("Student");
	     //update Document
	     Bson filter = new Document("qlfy", "B.E");
		  collection.deleteOne(filter);
		  System.out.println("Record deleted");
		
	}//main
}//class
