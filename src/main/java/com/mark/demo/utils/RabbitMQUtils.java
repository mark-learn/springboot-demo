package com.mark.demo.utils;

//import com.lubansoft.center.process.task.api.constant.ProcessMQKeys;
//import com.lubansoft.center.process.task.api.model.mq.ProcessMQMessage;
//import org.springframework.amqp.core.AmqpAdmin;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Queue;

/**
 * @Description : rabbit配置
 * @Author : cxw
 * @Date : 2022/11/15 13:39
 * @Version : 1.0
 **/
@Configuration
public class RabbitMQUtils {

    private HashMap<String, Queue> queueHashMap=new HashMap<>();

//    @Autowired
//    private AmqpAdmin amqpAdmin;
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @Bean(name = "process_exchange" )
//    public TopicExchange topicExchange() {
//        return new TopicExchange(ProcessMQKeys.EXCHANGE);
//    }
//
//    /**
//     * 注册RabbitMq的组件信息
//     *
//     * @param queue    队列对象
//     * @param binding  队列与交换机绑定关系对象
//     */
//    private void registerModule(Queue queue, Binding binding) {
//        amqpAdmin.declareQueue(queue);
//        amqpAdmin.declareExchange(topicExchange());
//        amqpAdmin.declareBinding(binding);
//    }
//
//    /**
//     * 推送消息至businessType对应的queue
//     * @param businessType
//     * @param msg
//     */
//    public void pushTaskCenterMsg(String businessType, ProcessMQMessage msg){
//        String queueName=ProcessMQKeys.QUEUES_PREFIX+businessType;
//        String routingKey=ProcessMQKeys.KEY_PREFIX+businessType;
//        if(!queueHashMap.containsKey(queueName)){
//            //获取队列
//            Queue queue = getQueue(queueName);
//            //绑定关系
//            Binding binding = new Binding(queue.getName(), Binding.DestinationType.QUEUE,ProcessMQKeys.EXCHANGE, routingKey, null);
//            registerModule(queue,binding);
//        }
//        rabbitTemplate.convertAndSend(ProcessMQKeys.EXCHANGE,routingKey,msg);
//    }
//
//    private Queue getQueue(String queueName) {
//        if(queueHashMap.containsKey(queueName)){
//            return queueHashMap.get(queueName);
//        }else {
//            Queue queue = new Queue(queueName, true);
//            queueHashMap.put(queueName,queue);
//            return queue;
//        }
//    }
}