package com.linsz.demo.service.impl;

import com.linsz.demo.model.AyRole;
import com.linsz.demo.repository.AyRoleRepository;
import com.linsz.demo.service.AyRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AyRoleServiceImpl implements AyRoleService {

    @Resource
    private AyRoleRepository ayRoleRepository;

    @Override
    public AyRole find(String id){
        return ayRoleRepository.findById(id).get();
    }
}

