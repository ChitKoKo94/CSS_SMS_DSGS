package sa.project.css.configuration;

import com.netflix.discovery.converters.Auto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import sa.project.css.service.CodeSupplierService;

import java.io.File;
import java.io.IOException;

@Service
public class Receiver {
    private static final Logger LOG = LoggerFactory.getLogger(Receiver.class);

    @Autowired
    private CodeSupplierService codeSupplierService;

    @Autowired
    private KafkaTemplate<String, File> kafkaTemplateForFile;

//    @KafkaListener(topics = "DSGS_Creation", groupId = "default")
//    public void receive(@Payload Test message) {
//        LOG.info("recvd msg from DSGS_Creation: " + message);
//    }
    //codeSupplierService.getSSCode(topics);

    @KafkaListener(topics = "DSGS_RETRIEVAL", groupId = "default")
    public void receive(@Payload String message) {
        LOG.info("The topic(DSGS_Creation) requests the service : " + message);

        // assume message is type of src code DSGS request for now
        // change logic later when message format changes
        File zippedSourceCode = retrieveSrcCode(message);
//        kafkaTemplateForFile.send("CSS_RESPONSE", zippedSourceCode);
    }

    private File retrieveSrcCode(String serviceName) {
        try {
            switch (serviceName.toLowerCase()) {
                case "rs" ->
                        codeSupplierService.getRSCode();
                case "cds" ->
                        codeSupplierService.getCDSCode(""); // refactor later if got time, as topic names are not necessary
                case "ss" ->
                        codeSupplierService.getSSCode(""); // refactor later if got time, as topic names are not necessary
                case "dis" ->
                        codeSupplierService.getDISCode("", ""); // interval is the value from client-message, empty for now
            }
        }
        catch (IOException e) {
            LOG.error("retrieveSrcCode in Receiver class");
            throw new RuntimeException(e);
        }
        catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return null;
    }



}
