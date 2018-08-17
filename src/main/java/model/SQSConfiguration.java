package model;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;

/**
 * @description: This class defines all the required configurations to setup amazon queue
 */
public class SQSConfiguration {

    public static SQSConfiguration instance =  null;
    public static final String DEFAULT_QUEUE_NAME = "MyQueue";
    public static final Region DEFAULT_REGION = Region.getRegion(Regions.DEFAULT_REGION);


    private String queueName = DEFAULT_QUEUE_NAME;
    private Region region = DEFAULT_REGION;

    //TODO: need to check how are we going to assign default credentials and where to store it (EC2)
    private AWSCredentialsProvider awsCredentialsProvider = new DefaultAWSCredentialsProviderChain();

    public static SQSConfiguration getInstance () {
        if (instance == null) {
            instance = new SQSConfiguration();
        }
        return instance;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public AWSCredentialsProvider getAwsCredentialsProvider() {
        return awsCredentialsProvider;
    }

    public void setAwsCredentialsProvider(AWSCredentialsProvider awsCredentialsProvider) {
        //Need to set all the credentials going through this documentation
        //https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/setup-credentials.html
        awsCredentialsProvider.getCredentials();
        this.awsCredentialsProvider = awsCredentialsProvider;
    }
}
