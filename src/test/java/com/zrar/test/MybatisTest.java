package com.zrar.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.User;

public class MybatisTest {
	ClassPathXmlApplicationContext ctx;
    @Before
    public void init(){
        ctx=new ClassPathXmlApplicationContext(
            "spring-mybatis.xml");
    }
    @Test
    public void testDatasource() 
        throws Exception{
        DataSource ds=ctx.getBean( "dataSource", DataSource.class);
        Connection conn=ds.getConnection();
        String sql = "select 'Hello World!' as s";
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(sql);
        while(rs.next()){
            System.out.println( rs.getString("s"));
        } 
        conn.close();
    }
    
    @Test
	public void testAddUser(){
	    //mapper扫描器将Mapper接口UserDao创建
	    //为Bean对象
	    UserDao dao = ctx.getBean(
	        "userDao", UserDao.class);
	    User user = new User(
	        "123", "tom", "123", "", "Tom");
	    dao.addUser(user);
	}
    
    @Test
    public void testFindUserByName(){
    	UserDao dao = ctx.getBean("userDao", UserDao.class);
	    User user=dao.findUserByName("tom");
	    System.out.println(user);

    }

}
