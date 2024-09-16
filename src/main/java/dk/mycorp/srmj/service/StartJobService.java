package dk.mycorp.srmj.service;

import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class StartJobService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StartJobService.class);

    private final MutinyEmitter<String> queue1;


    public StartJobService(@Channel("QUEUE1_OUT") MutinyEmitter<String> queue1) {
        this.queue1 = queue1;
    }

    public void startJob(boolean start) {
        if (start) {
            LOGGER.info("Starting job");
            queue1.sendAndAwait("start");
        }
    }
}
