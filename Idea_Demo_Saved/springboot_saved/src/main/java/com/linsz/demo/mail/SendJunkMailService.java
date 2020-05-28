package com.linsz.demo.mail;

import com.linsz.demo.model.AyUser;

import java.util.List;

public interface SendJunkMailService {
    boolean sendJunkMail(List<AyUser>ayUsers);
}
