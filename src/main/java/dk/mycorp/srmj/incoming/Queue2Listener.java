package dk.mycorp.srmj.incoming;

import io.smallrye.reactive.messaging.jms.IncomingJmsMessageMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.Optional;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class Queue2Listener {
    private static final Logger LOGGER = LoggerFactory.getLogger(Queue2Listener.class);

    @Incoming("QUEUE2")
    public void receive(String incoming, Optional<IncomingJmsMessageMetadata> optionalMetadata) {
        try {
            LOGGER.info("Finally received: {}", incoming);
        } catch (Exception e) {
            LOGGER.info("Unable to process: {}", incoming, e);
        }
    }
}
