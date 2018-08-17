package util;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnection;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import model.SQSConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;

/**
 * @author : Aradhya
 * @description : Singleton class to initialize SQS connection
 */

public class SQSConnectionInitializer {

    private static final Logger log = LoggerFactory.getLogger(SQSConnectionInitializer.class);

    private static  SQSConnectionInitializer instance = null;

    public static SQSConnection getConnection() {
        return connection;
    }

    public static void setConnection(SQSConnection connection) {
        SQSConnectionInitializer.connection = connection;
    }

    public static SQSConnection connection = null;

    public static SQSConnectionInitializer getInstance () {
        if (instance == null) {
            //initializing instance and building the connection
            instance = new SQSConnectionInitializer();
            //AWSCredentialsProvider awsCredentialsProvider

            //Provider configuration is defining the number of messages to prefetch
            //TODO: do we need to override number of messages being fetched???

            log.info("Starting to initialize the connection factory");
            SQSConnectionFactory connectionFactory = new SQSConnectionFactory(new ProviderConfiguration(),
                    AmazonSQSClientBuilder.standard().
                        withRegion(Regions.AP_SOUTH_1).
                            withCredentials(SQSConfiguration.getInstance().getAwsCredentialsProvider()));

            log.debug("Connection factory initialized {}", connectionFactory);

            //Trying to create connection from the sqs connection factory
            try {
                log.info("Trying to establish connection with the SQS through params {}", connectionFactory);
                connection = connectionFactory.createConnection();

                log.debug("Successfully established connection with SQS {}", connection);
            } catch (JMSException e) {

                log.error("Unable to get a resource from the connection factory {}",e);
                e.printStackTrace();
            }

        }

        return instance;
    }
}
