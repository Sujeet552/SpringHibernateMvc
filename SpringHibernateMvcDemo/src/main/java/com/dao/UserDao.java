package com.dao;
import com.entities.Users;

import java.util.List;
public interface UserDao {

	public List<Users> list();
	public boolean delete(Users users);
	public boolean saveOrUpdate(Users users);
	
}
