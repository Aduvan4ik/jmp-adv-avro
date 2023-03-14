package com.jmp.avro.v1.producer;

import com.jmp.avro.Book;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class KafkaProducerV1 {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerV1.class);

    public void produce(String topic) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("acks", "all");
        properties.setProperty("retries", "10");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", "http://127.0.0.1:8081");

        Producer<String, Book> producer = new KafkaProducer<>(properties);

        Book book = Book.newBuilder()
                .setTitle("Clean code")
                .setAuthor("Robert C. Martin")
                .setTotalPages(235)
                .build();

        ProducerRecord<String, Book> producerRecord = new ProducerRecord<>(topic, book);
        logger.info("Kafka producer v1 is sending ...");
        producer.send(producerRecord, (metadata, exception) -> {
            if (exception == null) {
                logger.info("Metadata: {}", metadata.timestamp());
            } else {
                logger.error("Exception during sending topic: {}", exception.getMessage());
            }
        });
        producer.flush();
        producer.close();
    }
}
