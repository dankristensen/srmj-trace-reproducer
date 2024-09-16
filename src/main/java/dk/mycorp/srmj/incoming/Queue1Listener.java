package dk.mycorp.srmj.incoming;

import dk.mycorp.srmj.service.Service2;
import io.smallrye.reactive.messaging.jms.IncomingJmsMessageMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class Queue1Listener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Queue1Listener.class);

    private final Service2 service2;

    public Queue1Listener(Service2 service2) {
        this.service2 = service2;
    }

    @Incoming("QUEUE1")
    public void receive(String incoming, Optional<IncomingJmsMessageMetadata> optionalMetadata) {
        try {
            LOGGER.info("Received: {}", incoming);
            service2.process(incoming);
        } catch (Exception e) {
            LOGGER.info("Unable to process: {}", incoming, e);
        }
    }
}
