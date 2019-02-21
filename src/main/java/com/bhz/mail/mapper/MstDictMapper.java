package com.bhz.mail.mapper;

import java.util.List;

import com.bhz.mail.config.database.BaseMapper;
import com.bhz.mail.entity.MstDict;

public interface MstDictMapper extends BaseMapper<MstDict> {

	List<MstDict> findByStatus(String status) throws Exception;
}