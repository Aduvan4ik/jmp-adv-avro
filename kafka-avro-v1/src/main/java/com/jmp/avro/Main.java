package com.jmp.avro;

import com.jmp.avro.v1.consumer.KafkaConsumerV1;
import com.jmp.avro.v1.producer.KafkaProducerV1;

public class Main {

    private static final String TOPIC_NAME = "book-kafka-avro";

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equalsIgnoreCase("c")) {
            KafkaConsumerV1 consumerV1 = new KafkaConsumerV1();
            consumerV1.consume(TOPIC_NAME);
        } else {
            KafkaProducerV1 producerV1 = new KafkaProducerV1();
            producerV1.produce(TOPIC_NAME);
        }

    }
}
