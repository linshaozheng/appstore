package com.linsz.demo.service.impl;

import com.linsz.demo.model.AyMood;
import com.linsz.demo.mq.AyMoodProducer;
import com.linsz.demo.repository.AyMoodRepository;
import com.linsz.demo.service.AyMoodService;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;


@Service
public class AyMoodServiceImpl implements AyMoodService {

    @Resource
    private AyMoodRepository ayMoodRepository;
    @Override
    public AyMood save(AyMood ayMood) {
        return ayMoodRepository.save(ayMood);
    }
    //队列
    private static Destination destination = new ActiveMQQueue("ay.queue.asyn.save");

    @Resource
    private AyMoodProducer ayMoodProducer;
    @Override
    public String asynSave(AyMood ayMood){
        //往队列ay.queue.asyn.save推送消息，消息内容为说说实体
        ayMoodProducer.sendMessage(destination, ayMood);
        return "success";
    }
}
