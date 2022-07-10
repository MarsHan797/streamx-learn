package com.mars;

import com.streamxhub.streamx.flink.core.StreamEnvConfig;
import com.streamxhub.streamx.flink.core.java.source.KafkaSource;
import com.streamxhub.streamx.flink.core.scala.StreamingContext;
import com.streamxhub.streamx.flink.core.scala.source.KafkaRecord;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.Types;


public class StreamXKafka {

    //$KAFKA_HOME/bin/kafka-topics.sh --create --topic streamx-learn --zookeeper bigdata:2181 --replication-factor 1 --partitions 1

    // $KAFKA_HOME/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic streamx-learn

    //  --conf /Volumes/Code/IdeaProjects/streamx-learn/assembly/conf/application.yml
    public static void main(String[] args) {
        // 配置
        StreamEnvConfig javaConfig = new StreamEnvConfig(args, null);
        // 创建StreamingContext对象, 是一个核心类
        StreamingContext ctx = new StreamingContext(javaConfig);
        // 消费kafka数据
        new KafkaSource<String>(ctx)
                .getDataStream()
                .map(KafkaRecord::value)
                .returns(Types.STRING)
                .print();

// 启动任务
        ctx.start();

    }
}
