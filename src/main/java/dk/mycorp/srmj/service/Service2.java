package dk.mycorp.srmj.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class Service2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(Service2.class);
    private final Emitter<String> queue2;

    public Service2(@Channel("QUEUE2_OUT") Emitter<String> queue2) {
        this.queue2 = queue2;
    }

    public void process(String incoming) {
        LOGGER.info("Sending to queue2");
        queue2.send(incoming);
    }
}
