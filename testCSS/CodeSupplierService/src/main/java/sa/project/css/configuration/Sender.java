package sa.project.css.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.SerializationUtils;

import java.io.*;
import java.util.ArrayList;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaTemplate<String, File> kafkaTemplateForObjects;

//    public void send(String topic, String message){
////        kafkaTemplate.send(topic, message);
//        kafkaTemplateForObjects.send("DSGS_Creation", new Test());
//    }

    public void send(String topic, File srcCode) {
        kafkaTemplateForObjects.send(topic, srcCode);
    }

}