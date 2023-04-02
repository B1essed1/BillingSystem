/*
package shakh.billingsystem.configurations;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import shakh.billingsystem.models.ProductOrderDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {


    @Bean
    public ConsumerFactory<String,Object> consumerFactory (){
        Map props = new HashMap();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG,brokerId);
        props.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
        props.put(JsonDeserializer.REMOVE_TYPE_INFO_HEADERS,"true");
       // props.put(JsonDeserializer.TYPE_MAPPINGS, "unload:com.example.billingsystempublisher.DTOs.Unload, unload:com.example.kafkaconsumer.DTOs.Unload");
        return new DefaultKafkaConsumerFactory<>(props,new StringDeserializer(),new JsonDeserializer<>());

    }
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, ProductOrderDto>> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, ProductOrderDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


}
*/
