package com.linsz.demo.service;

import com.linsz.demo.model.AyMood;

public interface AyMoodService {
    AyMood save(AyMood ayMood);
    String asynSave(AyMood ayMood);
}
