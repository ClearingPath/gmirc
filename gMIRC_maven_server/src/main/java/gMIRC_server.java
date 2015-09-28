/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import gMIRC.grpcMIRCGrpc;
import gMIRC.grpcMIRCGrpc.grpcMIRC;
import io.grpc.ServerImpl;
import io.grpc.netty.NettyServerBuilder;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonathan
 */
public class gMIRC_server {

  private static final int port = 2121;
  private static ServerImpl server;

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try {

      Runnable simple = new Runnable() {
	public void run() {
	  simple();
	}
      };

      new Thread(simple).start();

      Runnable cleaner = new Runnable() {
	public void run() {
	  try {
	    Thread.sleep(100);
	    while (true) {
	      System.out.println("cleaner running...");
	      cleaner();
	      Thread.sleep(1000 * 60 * 5);
	    }
	  } catch (InterruptedException ex) {
	    Logger.getLogger(gMIRC_server.class.getName()).log(Level.SEVERE, null, ex);
	  }
	}
      };

      new Thread(cleaner).start();

      Runnable ultClean = new Runnable() {
	public void run() {
	  try {
	    Thread.sleep(500);
	    while (true) {
	      UltimateClean();
	      Thread.sleep(1000 * 60 * 60);
	    }
	  } catch (InterruptedException ex) {
	    Logger.getLogger(gMIRC_server.class.getName()).log(Level.SEVERE, null, ex);
	  }
	}
      };

      new Thread(ultClean).start();
    } catch (Exception x) {
      x.printStackTrace();
    }
  }

  public static void simple() {
    try {

      System.out.println("Starting simple server...");

      server = NettyServerBuilder.forPort(port)
	      .addService(grpcMIRCGrpc.bindService(new gMIRC_handler()))
	      .build().start();
      
      Runtime.getRuntime().addShutdownHook(new Thread() {
	@Override
	public void run() {
	  // Use stderr here since the logger may have been reset by its JVM shutdown hook.
	  System.err.println("*** shutting down gRPC server since JVM is shutting down");
	  gMIRC_server.stop();
	  System.err.println("*** server shut down");
	}
      });
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void cleaner() {
    try {
      boolean hard_clean = false;
      MongoClient mongoClient = new MongoClient();
      DB db = mongoClient.getDB("mirc");
      DBCollection coll = db.getCollection("activeUser");

      DBCursor cursor = coll.find();
      try {
	Date now = new Date();
	long timestamp_now = now.getTime();
	long treshold = timestamp_now - (1000 * 60 * 5); //5 minutes
	while (cursor.hasNext()) {
	  hard_clean = true;
	  BasicDBObject temp = (BasicDBObject) cursor.next();
	  Date time_temp = (Date) temp.get("timestamp");
	  long timestamp_temp = time_temp.getTime();
	  if (timestamp_temp < treshold) {
	    String target = temp.getString("username");
	    gMIRC_handler.SoftDelete(target);
	  }
	}
	HardClean();
      } finally {
	cursor.close();
      }

    } catch (UnknownHostException ex) {
      Logger.getLogger(gMIRC_server.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public static void HardClean() {
    try {
      MongoClient mongoClient = new MongoClient();
      DB db = mongoClient.getDB("mirc");
      DBCollection coll[] = new DBCollection[4];
      coll[0] = db.getCollection("channelCollection");
      coll[1] = db.getCollection("inbox");
      coll[2] = db.getCollection("activeUser");
      coll[3] = db.getCollection("passiveUser");

      DBCursor cursor = coll[3].find();

      try {
	while (cursor.hasNext()) {
	  BasicDBObject temp = (BasicDBObject) cursor.next();
	  String username = temp.getString("username");
	  BasicDBObject query = new BasicDBObject("username", username);
	  System.out.println("cleaning " + username);
	  for (int i = 0; i < 4; i++) {
	    DBCursor cursor2 = coll[i].find(query);

	    try {
	      while (cursor2.hasNext()) {
		DBObject temp2 = cursor2.next();
		coll[i].remove(temp2);
	      }
	    } finally {
	      cursor2.close();
	    }
	  }
	}
      } finally {
	cursor.close();
      }

    } catch (UnknownHostException ex) {
      Logger.getLogger(gMIRC_server.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  /**
   * ONLY IF NO USER IS IN THE ACTIVE OR PASIVE COLLECTION;getting server to
   * clean state
   */
  private static void UltimateClean() {
    try {
      MongoClient mongoClient = new MongoClient();
      DB db = mongoClient.getDB("mirc");
      DBCollection coll[] = new DBCollection[4];
      coll[0] = db.getCollection("activeUser");
      coll[1] = db.getCollection("passiveUser");
      coll[2] = db.getCollection("channelCollection");
      coll[3] = db.getCollection("inbox");

      DBCursor cursor[] = new DBCursor[4];
      cursor[0] = coll[0].find();
      cursor[1] = coll[1].find();
      cursor[2] = coll[2].find();
      cursor[3] = coll[3].find();
      try {
	if (!cursor[0].hasNext() && !cursor[1].hasNext() && cursor[2].hasNext() && cursor[3].hasNext()) {
	  System.out.println("SYSTEM RESTARTING with ULTIMATE CLEANING !");
	  for (int i = 2; i <= 3; i++) {
	    coll[i].drop();
	  }
	  System.out.println("RESTART COMPLETE!");
	}
      } finally {
	cursor[0].close();
	cursor[1].close();
      }
    } catch (UnknownHostException ex) {
      Logger.getLogger(gMIRC_server.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  private static void stop() {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    if (server != null) {
      server.shutdown();
    }
  }
}
