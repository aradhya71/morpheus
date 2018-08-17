package listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;

public class SQSListener implements MessageListener {
    private static final Logger log = LoggerFactory.getLogger(SQSListener.class);

    @Override
    public void onMessage(Message message) {
        //on getting the message we need to update the message to clever tap
        //clevertap logic here

        log.info("Getting Messages from SQS {}", message);

    }
}
