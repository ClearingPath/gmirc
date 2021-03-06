/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author waxrain
 */
import com.google.common.util.concurrent.SettableFuture;
import gMIRC.ResCode;
import gMIRC.UpdateString;
import gMIRC.UserChannel;
import gMIRC.UserMessage;
import gMIRC.Username;
import gMIRC.grpcMIRCGrpc;

import io.grpc.ChannelImpl;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class gMIRC_client {

  // default grpc stuff
  private static final Logger logger = Logger.getLogger(gMIRC_client.class.getName());
  private final ChannelImpl channel;
  private static grpcMIRCGrpc.grpcMIRCBlockingStub blockingStub;

  // from previous chat ver.
  public static String username = "";
  public static boolean update = true;
  public static JSONArray allMsg = new JSONArray();

  public gMIRC_client(String host, int port) {
    channel = NettyChannelBuilder.forAddress(host, port).negotiationType(NegotiationType.PLAINTEXT).build();
    blockingStub = grpcMIRCGrpc.newBlockingStub(channel);
  }

  public void shutdown() throws InterruptedException {
    channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
  }

  private static void perform() {
    //private static void perform () throws TException {
    boolean exit = false;
    Scanner input = new Scanner(System.in);

    generateUname();
    Username usernameMsg = Username.newBuilder().setUsername(username).build();
    UserChannel userchannelMsg;
    UserMessage usermessageMsg;
    
    //auto-regis
    ResCode res = ResCode.newBuilder().setResponseCode(0).build();

    res = blockingStub.regUser(usernameMsg);

    if (res.getResponseCode() == 0) {
      System.out.println("# Registered user: " + username);
    } else {
      System.out.println("!!!: Unidentified error on register!");

      generateUname();
      System.out.println("# Re-registering user with new username: " + username);
      usernameMsg = Username.newBuilder().setUsername(username).build();
      res = blockingStub.regUser(usernameMsg);
      if (res.getResponseCode() == 0) {
	System.out.println("# Registered user: " + username);
      }
    }

    while (!exit) {
      System.out.print("> ");
      String command = input.nextLine();

      String resSplit[] = command.split(" ", 2);
      String commandWord = resSplit[0].toUpperCase();
      
      String remSplit = "";
      switch (commandWord) {
	case "/NICK":
            if (resSplit.length == 1){
                System.out.print("!!!: Enter username: ");
                remSplit = input.nextLine();
            } else {
                remSplit = resSplit[1];
            }
	  System.out.println("# Registering user: " + remSplit );
	  usernameMsg = Username.newBuilder().setUsername(remSplit ).build();
	  res = blockingStub.regUser(usernameMsg);

	  if (res.getResponseCode() == 0) {
	    System.out.println("# Registered user: " + remSplit );
	    username = remSplit ;
	  } else if (res.getResponseCode() == 2) {
	    System.out.println("# Login as user: " +remSplit );
	    username = remSplit ;
	  } else {
	    System.out.println("!!!: Unidentified error on register!");
	  }
	  break;
	  
	case "/JOIN":
            if (resSplit.length == 1){
                System.out.print("!!!: Enter channel name: ");
                remSplit = input.nextLine();
            }else {
                remSplit = resSplit[1];
            }
            
	  System.out.println("# Checking channel: " + remSplit);
	  userchannelMsg = UserChannel.newBuilder().setUsername(username).setChannelname(remSplit).build();
	  res = blockingStub.join(userchannelMsg);
	  
	  if (res.getResponseCode() == 0 || res.getResponseCode() == 2) {
	    System.out.println("# Joined channel: " + remSplit);
	  } else {
	    if (res.getResponseCode() == 1) {
	      System.out.println("!!!: Channel " + remSplit + " already joined!");
	    } else {
	      System.out.println("!!!: code #" + res + " on channel join");
	    }
	  }

	  break;
	case "/LEAVE":
            if (resSplit.length == 1){
                System.out.print("!!!: Enter channel name: ");
                remSplit = input.nextLine();
            }else {
                remSplit = resSplit[1];
            }
	  if (username.isEmpty()) {
	    System.out.println("!!!: Unregistered user");
	  } else {
	    System.out.println("# " + username + " exiting channel " + remSplit);
	    
	    userchannelMsg = UserChannel.newBuilder().setUsername(username).setChannelname(remSplit).build();
	    res = blockingStub.leave(userchannelMsg);
	    if (res.getResponseCode() == 0) {
	      System.out.println("# Success");
	    } else {
	      System.out.println("!!!: Channel exit error!");
	    }
	  }
	  break;

	case "/EXIT":
	  System.out.println("# " + username + " closing...");

	  res = blockingStub.exit(usernameMsg);
	  if (res.getResponseCode() == 0) {
	    System.out.println("# Exit success");
	    username = "";
	    exit = true;
	    update = false;
            System.exit(0);
	  } else {
	    System.out.println("!!!: Channel error! Error code #" + res);
	  }
	  break;

	default:
	  if (resSplit[0].startsWith("@")) { // message to channel 
            if (resSplit.length == 1){
                System.out.print("!!!: Input message: ");
                remSplit = input.nextLine();
            }else {
                remSplit = resSplit[1];
            }
	    usermessageMsg = UserMessage.newBuilder().setUsername(username).setChannelname(resSplit[0].substring(1)).setMsg(remSplit).build();
	    
	    res = blockingStub.message(usermessageMsg);
	    
	    if (res.getResponseCode() == 0) {
	      System.out.println("# Msg to " + resSplit[0].substring(1) + " sent");
	    } else if (res.getResponseCode() == 2) {
	      System.out.println("!!!: Not member of channel " + resSplit[0].substring(1));
	    }
	  } else {
	    usermessageMsg = UserMessage.newBuilder().setUsername(username).setChannelname("*").setMsg(command).build();
	    res = blockingStub.message(usermessageMsg);
	    if (res.getResponseCode() == 0) {
	      System.out.println("# Msg to all channels sent");
	    } else {
	      System.err.println("!!!: Failed to send message to all channel. Error Code #"+ res.getResponseCode());
	    }

	  }
	  break;
      }
      showMsg();
    }

  }

  public static void updateMsg() {
    UpdateString tempRes = blockingStub.regularUpdates(Username.newBuilder().setUsername(username).build());
    String temp = tempRes.getNewMessages();
    temp = temp.replaceAll("\\{\"msg\":\\[\\]\\}", "");
    if (!temp.isEmpty() && temp.length() > 6) {

      try {
	JSONParser J = new JSONParser();

	JSONObject jeson = new JSONObject();
	jeson = (JSONObject) J.parse(temp);
	allMsg.add(jeson);

      } catch (Exception E) {
	E.printStackTrace();
      }
    }
  }

  public static void showMsg() {
    try {
      synchronized (allMsg) {

	if (!allMsg.isEmpty() && allMsg.size() > 0) {
	  for (int i = 0; i < allMsg.size(); i++) {
	    JSONObject temp = (JSONObject) allMsg.get(i);
	    JSONArray tempArr = (JSONArray) temp.get("msg");

	    for (int j = 0; j < tempArr.size(); j++) {
	      temp = (JSONObject) tempArr.get(j);
	      SimpleDateFormat formatDate = new SimpleDateFormat("yy-MM-dd HH:mm:ss");

	      Date sendDat = new Date();
	      sendDat.setTime((long) temp.get("timestamp"));

	      System.out.println(">> [" + temp.get("channel").toString()
		      + "] [" + temp.get("username")
		      + "] " + temp.get("message")
		      + " || " + formatDate.format(sendDat));
	    }
	  }
	}
	allMsg.clear();
      }
    } catch (Exception E) {
      E.printStackTrace();
    }
  }

  public static void main(String args[]) {
    gMIRC_client client = new gMIRC_client("localhost", 2121);

    try {

      Runnable updateThread;
      updateThread = new Runnable() {
	public void run() {
	  try {
	    while (update) {
	      Thread.sleep(3000);

	      updateMsg();
	    }
	  } catch (Exception E) {
	    E.printStackTrace();
	  }
	}
      };

      new Thread(updateThread).start();
      
      Runnable perform;
      perform = new Runnable() {
          public void run(){
              perform();
          }
      };
      
      new Thread(perform).start();
	    //perform(transport, client);
    } catch (Exception E) {
      E.printStackTrace();
    }

  }

  public static void generateUname() {
    String commonUsername[] = {"Earthshaker", "Sven", "Tiny", "Kunkka", "Beastmaster", "DragonKnight", "Axe", "Pudge", "SandKing", "Slardar", "Tidehunter", "WraithKing", "Bloodseeker", "Windranger", "StormSpirit", "Lina", "ShadowFiend", "AntiMage", "PhantomAssassin"};
    String uname;

    int randIndex = (int) Math.round(Math.random() * (commonUsername.length - 1));
    int randEnd = (int) (Math.random() * 999);
    uname = commonUsername[randIndex] + randEnd;
    System.out.println("# Generated new username: " + uname);

    username = uname;
  }

}
