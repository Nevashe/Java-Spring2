package com.geekbrains.rabbitmq.console.producer;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.Scanner;


public class BlogSenderApp {
    private static final String EXCHANGE_NAME = "it_blog";


    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                String msg = scanner.nextLine();
                String[] msgArray = msg.split(" ",2);
                if (msgArray[0].equals("bye")){
                    scanner.close();
                    return;
                }
                if(msgArray.length>1){
                    channel.basicPublish(EXCHANGE_NAME, msgArray[0], null, msgArray[1].getBytes("UTF-8"));
                    System.out.println(" [x] Topic '" + msgArray[0] + "'");
                    System.out.println(" [x] Sent '" + msgArray[1] + "'");
                    System.out.println(" [x] ________________________'");
                }
            }
        }
    }
}