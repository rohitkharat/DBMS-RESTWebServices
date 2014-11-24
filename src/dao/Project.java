package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Bookings;
import dto.FeedObjects;
import dto.User;

public class Project {
	
	public ArrayList<FeedObjects> GetFeeds(Connection connection) throws Exception
	{
		ArrayList<FeedObjects> feedData = new ArrayList<FeedObjects>();
		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM website ORDER BY id DESC");
			ResultSet rs = ps.executeQuery();
		while(rs.next())
		{
			FeedObjects feedObject = new FeedObjects();
			feedObject.setId(rs.getInt("id"));
			feedObject.setTitle(rs.getString("title"));
			feedObject.setDescription(rs.getString("description"));
			feedObject.setUrl(rs.getString("url"));
			feedData.add(feedObject);
		}
			return feedData;
		}
		catch(Exception e)
		{
			throw e;
		}
	}
	
	public User authenticateUser(Connection connection, String username, String pwd) throws Exception
	{
		User user = new User();
		boolean isAuthenticUser = false;

		try
		{
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM attendee where username = '"+username+"' AND password = '"+pwd+"'");
			System.out.println("query fired = " + ps.toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				isAuthenticUser = true;
				user.setUsername(rs.getString("username"));
				user.setUserType("attendee");
			}
			
			 ps = connection.prepareStatement("SELECT * FROM event_organizer where username = '"+username+"' AND password = '"+pwd+"'");
			 rs = ps.executeQuery();
			
			while(rs.next())
			{
				isAuthenticUser = true;
				user.setUsername(rs.getString("username"));
				user.setUserType("event_organizer");
			}
			
			 ps = connection.prepareStatement("SELECT * FROM system_admin where username = '"+username+"' AND password = '"+pwd+"'");
			 rs = ps.executeQuery();
			
			while(rs.next())
			{
				isAuthenticUser = true;
				user.setUsername(rs.getString("username"));
				user.setUserType("system_admin");
			}
		}
		catch (Exception e)
		{
			throw e;
		}
		
		if(isAuthenticUser)
			return user;
		else
			return null;
		
	}
	
	public ArrayList<Bookings> getBookings(Connection connection, String username) throws SQLException
	{
		ArrayList<Bookings> bookingsList = new ArrayList<Bookings>();

		try
		{
			PreparedStatement ps = connection.prepareStatement("select * from bookings B, event E, movie M, hosted H where B.event_id=E.event_id and E.event_id=M.event_id and E.event_id=H.event_id and B.username = '" + username + "'");
			System.out.println("query fired = " + ps.toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				Bookings booking = new Bookings();
				booking.setBooking_id(rs.getInt("booking_id"));
				booking.setBooking_datetime(rs.getString("booking_datetime"));
				booking.setNo_of_tickets(rs.getInt("no_of_tickets"));
				booking.setUsername(username);
				booking.setTransaction_id(rs.getInt("transaction_id"));
				booking.setEvent_id(rs.getInt("event_id"));
				bookingsList.add(booking);
			}		

		}
		catch(Exception e)
		{
			throw e;
		}
		
		return bookingsList;
		
	}
	
	

}
