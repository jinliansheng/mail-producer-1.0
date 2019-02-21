package com.bhz.mail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhz.mail.config.database.ReadOnlyConnection;
import com.bhz.mail.entity.MstDict;
import com.bhz.mail.mapper.MstDictMapper;

@Service
public class MstDictService {

	@Autowired
	private MstDictMapper mstDictMapper;
	
	@ReadOnlyConnection//只读注解
	public List<MstDict>findByStatus(String status)throws Exception{
		return this.mstDictMapper.findByStatus(status);
	}
}
