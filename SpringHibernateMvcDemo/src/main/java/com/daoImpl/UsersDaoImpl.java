package com.daoImpl;
import java.util.List;

//import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.entities.*;
@Repository
@Transactional
public class UsersDaoImpl implements UserDao{
    @Autowired
	SessionFactory session;
    
    
    public boolean saveOrUpdate(Users users){
    	
    	session.getCurrentSession().saveOrUpdate(users);
		return true;
    	
    }
    
    public List<Users> list(){
    	System.out.println("Here, it is working ");
		return session.getCurrentSession().createQuery("from Users").list();	
    }
    
    public boolean delete(Users users){
    	try{
    		
    		System.out.println("Its working in delete");
    		session.getCurrentSession().delete(users);
    		
    	}catch(Exception ex){
    		
    		return false;	
    	}
    	
    	return true;	
	}
    
   
	
	
}
