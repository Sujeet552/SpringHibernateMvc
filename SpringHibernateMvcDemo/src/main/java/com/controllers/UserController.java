package com.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.entities.Users;
import com.services.UserServices;
@Controller
@RequestMapping(value="users")
public class UserController {

	@Autowired
	UserServices userServices;
	
	
	@RequestMapping(value="/page",method=RequestMethod.GET)
	public ModelAndView getPage(){
		
		ModelAndView view = new ModelAndView("hello");
		return view;
	}
	
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getSaved(Users users){
		
		Map<String,Object> map=new HashMap<String,Object>();
		if(userServices.saveOrUpdate(users)){
		map.put("status", "200");
		map.put("message", "Your record have been saved successfully.");
		}
		return map;
				
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> getAll(Users users){
		
		Map<String,Object> map=new HashMap<String,Object>();		
		List<Users> list = userServices.list();
		System.out.println("*****Users******");
		
		for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getUser_name()+" "+list.get(i).getId());
        }
		   		    
		if(list != null){
		    map.put("status", "200");
		    map.put("message", "Data found.");
		    map.put("data",list);
		}else{
			
			map.put("status", "404");
			map.put("message", "Data not found.");	
		}
		return map;
				
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> delete(Users users){
		
		System.out.println("ID: "+users.getId());
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(userServices.delete(users)){
		map.put("status", "200");
		map.put("message", "Your record have been deleted successfully.");
		}
		return map;
				
	}
	
	
	
}