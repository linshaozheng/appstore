package com.linsz.demo.service;

import com.linsz.demo.model.AyUserRoleRel;

import java.util.List;

public interface AyUserRoleRelService {

    List<AyUserRoleRel> findByUserId(String userId);
}
