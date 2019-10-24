import org.bson.Document;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MangoDBInsertTest {

	public static void main(String[] args) {
		MongoClient client=null;
		MongoDatabase db=null;
		MongoCollection<Document> collection=null;
		//create MangoDBClient obj
		client=new MongoClient("localhost",27017);
		//  get Logical DB
		db=client.getDatabase("MDB1");
		 // Get collection
	     collection=  db.getCollection("Student"); 
	      //prepare document
	      Document document = new Document("name", "rajesh") 
	      .append("id", 103)
	      .append("age", 56) 
	      .append("qlfy", "B.Tech") 
	      .append("hobies",new Document("hb1","RB")
   		                   .append("hb2","Singing")
   		                   .append("hb3","sleeping")
	    		  );
	      //insert document
	      collection.insertOne(document); 
	      System.out.println("Document inserted");
		
	}

}
