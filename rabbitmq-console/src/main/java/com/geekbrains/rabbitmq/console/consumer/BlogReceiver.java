package com.geekbrains.rabbitmq.console.consumer;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class BlogReceiver {
    private static final String EXCHANGE_NAME = "it_blog";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

        String queueName = channel.queueDeclare().getQueue();
        System.out.println("My queue name: " + queueName);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Topic '" + delivery.getEnvelope().getRoutingKey() + "'");
            System.out.println(" [x] Msg '" + message + "'");
            System.out.println(" [x] _____________________");

        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        Scanner scanner = new Scanner(System.in);
        while (true){
            String msg = scanner.nextLine();
            String[] msgArray = msg.split(" ",2);
            if(msgArray.length>1 & msgArray[0].equals("set_topic")){
                channel.queueBind(queueName, EXCHANGE_NAME, msgArray[1]);
                System.out.println(" [x] You add topic '" + msgArray[1] + "'");
            }
            if(msgArray.length>1 & msgArray[0].equals("del_topic")){
                channel.queueUnbind(queueName, EXCHANGE_NAME, msgArray[1]);
                System.out.println(" [x] You delete topic '" + msgArray[1] + "'");
            }
            if (msg.equals("bye")) {
                scanner.close();
                channel.close();
                return;
            }
        }



    }
}
