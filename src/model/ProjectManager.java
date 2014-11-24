package model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import dao.Database;
import dao.Project;
import dto.Bookings;
import dto.FeedObjects;
import dto.GameEvent;
import dto.MovieEvent;
import dto.User;

public class ProjectManager {
	private static Connection dbConnection;
	
	public ArrayList<FeedObjects> GetFeeds()throws Exception {
		ArrayList<FeedObjects> feeds = null;
		try {
		Project project= new Project();
		feeds=project.GetFeeds(getDBConnection());
		}
		catch (Exception e) {
		throw e;
		}
		return feeds;
		}
	
	private static Connection getDBConnection() throws Exception {
		try {
			Database database= new Database();
			if(dbConnection == null)
				dbConnection = database.Get_Connection();
			
			return dbConnection;
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public User authenticateUser(String username, String pwd) throws Exception
	{
		User user = new User();
		try
		{
			Project project= new Project();
			user = project.authenticateUser(getDBConnection(), username, pwd);
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return user;
	}
	
	public HashMap<String, ArrayList> getBookings(String username) throws Exception
	{
		HashMap<String, ArrayList> bookingsList = null;
		
		try
		{
			Project project = new Project();
			bookingsList = project.getBookings(getDBConnection(), username);
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return bookingsList;
	}
	
	public HashMap<String, ArrayList> getAllBookings() throws Exception
	{
		HashMap<String, ArrayList> bookingsList = null;
		
		try
		{
			Project project = new Project();
			bookingsList = project.getAllBookings(getDBConnection());
		}
		catch(Exception e)
		{
			throw e;
		}
		
		return bookingsList;
	}
	
	public void updateMovie(MovieEvent movieEvent) throws Exception {
		
		try 
		{
			Connection connection = getDBConnection();
			Project project= new Project();
			//movieEventCreated = project.createMovie(connection, movieEvent);
			project.updateMovie(connection, movieEvent);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	public void updateGame(GameEvent gameEvent) throws Exception {
		
		try 
		{
			Connection connection = getDBConnection();
			Project project= new Project();
			//movieEventCreated = project.createMovie(connection, movieEvent);
			project.updateGame(connection, gameEvent);
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	 

}
