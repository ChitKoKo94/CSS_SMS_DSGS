package sa.project.css.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.InputStreamResource;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfigurationClass {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootStrapServers;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", bootStrapServers);
       // props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

//    @Bean
//    public ConsumerFactory<String, Test> consumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put("bootstrap.servers", bootStrapServers);
//        // props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JavaDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers", bootStrapServers);
        //configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        //configProps.put("key.serializer", StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public ProducerFactory<String, File> producerFactoryFile() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers", bootStrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put("value.serializer", JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public KafkaTemplate<String, File> kafkaTemplateForFile() {
        return new KafkaTemplate<>(producerFactoryFile());
    }

}
