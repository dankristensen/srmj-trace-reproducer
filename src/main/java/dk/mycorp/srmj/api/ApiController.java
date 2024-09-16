package dk.mycorp.srmj.api;

import dk.mycorp.srmj.service.StartJobService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/trigger")
public class ApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    StartJobService startJobService;

    public ApiController(StartJobService startJobService) {
        this.startJobService = startJobService;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/test")
    public Response uploadTest() {
        try {
            LOGGER.info("Hit service");
            startJobService.startJob(true);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
