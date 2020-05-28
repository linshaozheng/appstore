package com.linsz.demo.service.impl;

import com.linsz.demo.model.AyUserRoleRel;
import com.linsz.demo.repository.AyUserRoleRelRepository;
import com.linsz.demo.service.AyUserRoleRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AyUserRoleServiceImpl implements AyUserRoleRelService {

    @Resource
    private AyUserRoleRelRepository ayUserRoleRelRepository;

    @Override
    public List<AyUserRoleRel> findByUserId(String userId) {
        return ayUserRoleRelRepository.findByUserId(userId);
    }
}

