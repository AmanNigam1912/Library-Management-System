package com.neu.Library.dao;



import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import com.neu.Library.exception.LibraryUserException;
import com.neu.Library.pojo.Authority;
import com.neu.Library.pojo.LibraryUser;
import com.neu.Library.pojo.LibraryUserAccount;

public class LibraryUserDao extends DAO{
	
	public LibraryUserDao() {
		
	}
	
	
	public LibraryUser signUp(LibraryUser libUser) throws LibraryUserException {
		try {
			begin();
			System.out.println("inside LibraryUserDao");
			getSession().save(libUser);
			System.out.println("after save LibraryUserDao");
			commit();
			return libUser;
		} catch (HibernateException e) {
			rollback();
			throw new LibraryUserException("Exception while creating user: " + e.getMessage());
		}
	}
	
	public Authority getAuthorityByRole(String role) throws LibraryUserException {
		try {
			Query q = getSession().createQuery("from Authority WHERE role LIKE :value");
			q.setString("value", role);
			Authority authority = (Authority) q.uniqueResult();
			return authority;

		} catch (HibernateException e) {
			rollback();
			throw new LibraryUserException("Cannot find User role.");
		}
	}
	
	public LibraryUserAccount account(LibraryUserAccount libUserAcc) throws LibraryUserException{
		try {
			begin();
			getSession().save(libUserAcc);
			commit();
			return libUserAcc;
		}catch (HibernateException e) {
			rollback();
			throw new LibraryUserException("Exception while creating user: " + e.getMessage());
		}		
	}
	
	public LibraryUser checkLogin(String username, String password) throws LibraryUserException{
		try {
			begin();
			System.out.println("inside checkLogin");
			Query q = getSession().createQuery("from LibraryUser where username= :username and password= :password");
			q.setString("username", username);
			q.setString("password", password);
			LibraryUser libUser = (LibraryUser)q.uniqueResult();
			commit();
			System.out.println("fetched data in checkLogin");
			if(libUser!=null)
				return libUser;
			else
				return null;
		}catch (Exception e) {
			rollback();
			throw new LibraryUserException("Exception while login: " + e.getMessage());
		}
	}
	
	public boolean validateUsername(String username)  {
		
		System.out.println("username inside validateUsername: "+username);
		Query q = getSession().createQuery("from LibraryUser where username= :username");
		q.setString("username", username);
		List<LibraryUser> libUser = q.list();
		for (LibraryUser lib: libUser)
			System.out.println("username from list: "+lib);
		if(libUser.isEmpty()) {
			System.out.println("Username does not exist");
			return false;
		}
		else
			return true;
		
	
	}
	
	public LibraryUserAccount getLibUserAcc(LibraryUser lu ) throws LibraryUserException{
		Criteria crit = getSession().createCriteria(LibraryUserAccount.class);
		crit.add(Restrictions.eq("userLibraryId", lu.getLibUserAcc().getUserLibraryId()));
		LibraryUserAccount results = (LibraryUserAccount)crit.uniqueResult();
		return results;
	}
	
	public LibraryUser getLibUser(Long lua) throws LibraryUserException{
		begin();
		System.out.println("inside getLibUser");
		Query q = getSession().createQuery("from LibraryUser where libUserAcc.userLibraryId = :u");
		q.setLong("u", lua);
		LibraryUser libUser = (LibraryUser)q.uniqueResult();
		commit();
		System.out.println("coming out of getLibUser");
		return libUser;
	}
	
	public void updateLibraryMember(LibraryUserAccount libUserAcc) throws LibraryUserException{
		try{
			begin();
			getSession().save(libUserAcc);
			commit();
		}catch(HibernateException e){
			rollback();
			throw new LibraryUserException("Exception while updating user occurence: " + e.getMessage());
		}
		
	}
	
}
