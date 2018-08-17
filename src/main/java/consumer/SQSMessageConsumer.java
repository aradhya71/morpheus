package consumer;

import com.amazon.sqs.javamessaging.SQSConnection;
import listener.SQSListener;
import model.SQSConfiguration;
import util.SQSConnectionInitializer;

import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

/**
 * @description: SQS Message Consumer
 */
public class SQSMessageConsumer {

    private static SQSMessageConsumer instance = null;

    public static SQSMessageConsumer getInstance () {
        if (instance == null) {
            instance = new SQSMessageConsumer();
        }
        return instance;
    }

    public static void startConsumer () {

        SQSConnection connection = SQSConnectionInitializer.getConnection();

        try {
            Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
            MessageConsumer consumer = session.createConsumer(session.createQueue(SQSConfiguration.getInstance().getQueueName()));

            SQSListener listener = new SQSListener();
            consumer.setMessageListener(listener);
            connection.start();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}















