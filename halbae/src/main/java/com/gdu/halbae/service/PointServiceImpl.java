package com.gdu.halbae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gdu.halbae.mapper.PointMapper;

@Service
public class PointServiceImpl implements PointService {
	
    @Autowired
    private PointMapper pointMapper;

    @Override
    public int getUserPoint(int userNo) {
        return pointMapper.getUserPoint(userNo);
    }
    
}