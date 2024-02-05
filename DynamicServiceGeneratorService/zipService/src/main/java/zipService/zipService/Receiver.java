package zipService.zipService;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Set;


@Service
public class Receiver {

//    @Autowired
//    Sender sender;

    @Autowired
    SupplierServiceClient supplierServiceClient;

    @Autowired
    private Sender sender;

    @KafkaListener(topics = "DSGS_CREATION", groupId = "default")
    public void receive(String message) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputMessageWrapper inputMessageWrapper = mapper.readValue(message, InputMessageWrapper.class);
            String serviceName = inputMessageWrapper.getServiceName();
            Set<String> topics = inputMessageWrapper.getTopics();
            String interval = inputMessageWrapper.getInterval();
            sender.send("DSGS_RETRIEVAL", serviceName.toLowerCase());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "CSS_RESPONSE", groupId = "default")
    public void receive(Object file) {
        System.out.println("CODE FILE OBJ FROM CSS " + (file.getClass()));
        //sender.send("DSGS_EXECUTION", file);
    }

//    @KafkaListener(topics = "CSS_RESPONSE", groupId = "default")
//    public void receive(Object message) {
//        try {
//
//            ObjectMapper mapper = new ObjectMapper();
//            System.out.println("got a message from client: " + message);
//
//            //converting the message to an object
//            InputMessageWrapper inputMessageWrapper = mapper.readValue(message, InputMessageWrapper.class);
//
//            String serviceName = inputMessageWrapper.getServiceName();
//            Set<String> topics = inputMessageWrapper.getTopics();
//            String interval = inputMessageWrapper.getInterval();
//
//            // receiving file from css
//            byte[] zipFile = getSourceCode(serviceName, topics, interval);
//
////          System.out.println("zipFile: "+zipFile);
//
//            // sending message to unzip service
//            RequestWrapper requestWrapper = new RequestWrapper(zipFile, serviceName, topics);
//            System.out.println("sending to kafka " + requestWrapper);
//
//            // convert file to string before sending to kafka
//            String jsonInString = mapper.writeValueAsString(requestWrapper);
//            sender.send("filedownloaded", jsonInString);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }
}

