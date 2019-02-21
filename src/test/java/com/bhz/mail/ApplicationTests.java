package com.bhz.mail;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.bhz.mail.entity.MstDict;
import com.bhz.mail.mapper.MstDictMapper;
import com.bhz.mail.service.MstDictService;
import com.github.pagehelper.PageHelper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
	@Resource(name = "masterDataSource")
	private DataSource masterDataSource;

	@Resource(name = "slaveDataSource")
	private DataSource slaveDataSource;

	@Test
	public void contextLoads()throws Exception {
		Connection c1=masterDataSource.getConnection("root","root");
		System.err.println("c1+"+c1.getMetaData().getURL());
		Connection c2=slaveDataSource.getConnection("root","root");
		System.err.println("c2+"+c2.getMetaData().getURL());
	}
	
	@Autowired
	private MstDictMapper mstDictMapper;
	
	//遍历数据库
	@Test
	public void test1() throws Exception{
		//分页查询
		PageHelper.startPage(2,2);
		List<MstDict>list=mstDictMapper.selectAll();
		for (MstDict list1:list) {
			System.err.println(list1.getId()+" "+list1.getCode()+" "+list1.getName()+" "+list1.getStatus());
		}
	}
	
	@Autowired
	private MstDictService mstDictService;
	
	//只读测试
	@Test
	public void test2() throws Exception{
		List<MstDict>list=mstDictService.findByStatus("1");
		for (MstDict list1:list) {
			System.err.println(list1.getId()+" "+list1.getCode()+" "+list1.getName()+" "+list1.getStatus());
		}
	}
	
	

}
