import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

public class MangoDBRetrieveTest {

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
	      //Retrieve documents
	   /*  System.out.println("All docs are...");
	     list=collection.find().into(new ArrayList<Document>());
	     for(Document doc:list) {
	    	 System.out.println(doc);
	     } */
	     //Retrieve docs having condition
	      BasicDBObject findCond=new BasicDBObject();
	      findCond.put("name","rajesh");
	       MongoIterable<Document> it=collection.find(findCond);
	       MongoCursor<Document> cursor=it.iterator();
	       while(cursor.hasNext()) {
	    	   System.out.println(cursor.next());
	       }
	      
		
	}//main
}//class
