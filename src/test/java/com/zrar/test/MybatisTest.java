package com.zrar.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zrar.note.dao.NotebookDao;
import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.User;
import com.zrar.note.service.UserService;
import com.zrar.note.util.Util;

public class MybatisTest {
	ClassPathXmlApplicationContext ctx;
    @Before
    public void init(){
        ctx=new ClassPathXmlApplicationContext("spring-mybatis.xml","spring-service.xml");
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
	    //mapperɨ������Mapper�ӿ�UserDao����
	    //ΪBean����
	    UserDao dao = ctx.getBean("userDao", UserDao.class);
	    User user = new User("123", "tom", "123", "", "Tom");
	    dao.addUser(user);
	}
    
    @Test
    public void testFindUserByName(){
    	UserDao dao = ctx.getBean("userDao", UserDao.class);
	    User user=dao.findUserByName("tom");
	    System.out.println(user);

    }
    
    @Test
    public void testLogin(){
    	String name="tom";
        String pwd="123";
        UserService service = ctx.getBean("userService", UserService.class);
        User user=service.login(name, pwd);
        System.out.println(user);
    }
    
    @Test
    public void testMD5(){
    	String data = "123";
    	String newData = Util.salMD5(data);
    	System.out.println(newData);
    }

    @Test
    public void testFindNotebooksByUserId(){
    	String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
    	NotebookDao dao = ctx.getBean("notebookDao",NotebookDao.class);
    	List<Map<String, Object>> list = dao.findNotebooksByUserId(userId);
    	System.out.println(list);
    	for(Map<String, Object> map : list){
    		System.out.println(map);
    	}
    }
    
    @Test
    public void testFindUserByUserId(){
    	String userId = "39295a3d-cc9b-42b4-b206-a2e7fab7e77";
    	UserDao dao = ctx.getBean("userDao",UserDao.class);
    	int c = dao.findUserByUserId(userId);
    	System.err.println(c);
    }
    
    
    
    
    
}
