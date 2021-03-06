package com.zrar.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.zrar.note.dao.NoteDao;
import com.zrar.note.dao.NotebookDao;
import com.zrar.note.dao.TtDao;
import com.zrar.note.dao.UserDao;
import com.zrar.note.entity.Notebook;
import com.zrar.note.entity.User;
import com.zrar.note.service.UserService;
import com.zrar.note.util.Constant;
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
    	Map<String, Object> map1 = new HashMap<String, Object>();
    	map1.put("userId", userId);
    	map1.put("statusId", Constant.NOTEBOOK_STATUS_ID_1);
    	List<Map<String, Object>> list = dao.findNotebooksByUserId(map1);
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
    
    @Test
    public void testFindNotesByNotebookId(){
    	String notebookId = "01e24d89-15ab-4b6a-bf6f-2e5ad10b2041";
    	NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
    	//List<Map<String, Object>> list = dao.findNotesByNotebookId(notebookId);
//    	for(Map<String, Object> map : list){
//    		System.out.println(map);
//    	}
    }
    
    @Test
    public void testGetNoteByNoteId(){
    	String noteId = "60480071-f989-4945-9b1c-0d2aba07ae96";
    	NoteDao dao = ctx.getBean("noteDao",NoteDao.class);
    	Map<String,Object> map = dao.getNoteByNoteId(noteId);
    	System.out.println(map);
    }
    
    @Test
    public void saveNote(){
    	String noteId = "003ec2a1-f975-4322-8e4d-dfd206d6ac0c";
    	NoteDao noteDao = ctx.getBean("noteDao",NoteDao.class);
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("noteId", noteId);
    	map.put("noteTitle", "�Ұ�java");
    	map.put("noteBody", "���ر�ϲ��java��ϲ���������԰�");
    	noteDao.updateNote(map);
    	noteDao.findNoteByNoteId(noteId);
    }
    
    @Test
    public void testAddNotebook(){
    	NotebookDao dao = ctx.getBean("notebookDao",NotebookDao.class);
    	Notebook notebook = new Notebook();
    	notebook.setId("1");
    	notebook.setUserId("123");
    	notebook.setName("test");
    	int i = dao.insertNotebook(notebook);
    	System.out.println(i);
    }
    
    @Test
    public void testFindNotebooksByParam(){
        String id="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
        String table="cn_notebook";
        int start = 0;
        int rows = 6;
        Map<String, Object> param=
            new HashMap<String, Object>();
        param.put("userId", id);
        param.put("tableName", table);
        param.put("start", start);
        param.put("rows", rows);
        NotebookDao dao=ctx.getBean(
            "notebookDao", NotebookDao.class);
        List<Map<String, Object>> list=
            dao.findNotebooksByParam(param);
        for (Map<String, Object> map : list) {
            System.out.println(map); 
        }
    }
    
    @Test
    public void testGetClass(){
    	int a = 2;
    	TtDao dao = ctx.getBean("ttDao",TtDao.class);
    	//System.out.println(dao.getClass(a));
    	
    	
    	//System.out.println(dao.getClass2(a));
    	
    	//System.out.println(dao.getClass3(a));
    	
    	System.out.println(dao.getClass4(a));
    }
    
    
    
    
}
