package dk.mycorp.srmj.helper;

import com.ibm.mq.jakarta.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.jakarta.jms.JmsConstants;
import com.ibm.msg.client.jakarta.wmq.common.CommonConstants;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import java.util.concurrent.TimeUnit;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class MQConnectionProvider {
    @ConfigProperty(name = "mq.host")
    String mqHostName;
    @ConfigProperty(name = "mq.port")
    Integer mqPort;
    @ConfigProperty(name = "mq.queuemanager")
    String mqQueueManager;
    @ConfigProperty(name = "mq.channel")
    String mqChannel;

    @Produces
    ConnectionFactory factory() throws JMSException {
        MQQueueConnectionFactory mqFactory = new MQQueueConnectionFactory();
        mqFactory.setHostName(mqHostName);
        mqFactory.setPort(mqPort);
        mqFactory.setQueueManager(mqQueueManager);
        mqFactory.setChannel(mqChannel);
        mqFactory.setClientReconnectTimeout((int) TimeUnit.MINUTES.toSeconds(1));
        mqFactory.setClientReconnectOptions(CommonConstants.WMQ_CLIENT_RECONNECT);
        mqFactory.setTransportType(CommonConstants.WMQ_CM_CLIENT);
        mqFactory.setIntProperty(JmsConstants.ACKNOWLEDGE_MODE, JmsConstants.CLIENT_ACKNOWLEDGE);
        return mqFactory;
    }
}
