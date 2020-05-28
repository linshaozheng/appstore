package com.linsz.demo.service.impl;

import com.linsz.demo.model.AyUserAttachmentRel;
import com.linsz.demo.repository.AyUserAttachmentRelRepository;
import com.linsz.demo.service.AyUserAttachmentRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AyUserAttachmentRelServiceImpl implements AyUserAttachmentRelService {

    @Resource
    private AyUserAttachmentRelRepository ayUserAttachmentRelRepository;

    public AyUserAttachmentRel save(AyUserAttachmentRel ayUserAttachmentRel){
        return ayUserAttachmentRelRepository.save(ayUserAttachmentRel);
    }
}
