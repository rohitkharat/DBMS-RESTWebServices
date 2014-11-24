package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import dto.BookingDetails;
import dto.Bookings;
import dto.Concert;
import dto.FeedObjects;
import dto.Game;
import dto.GameEvent;
import dto.Movie;
import dto.MovieEvent;
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
	
	public HashMap<String, ArrayList> getBookings(Connection connection, String username) throws SQLException
	{
		ArrayList<BookingDetails> movieBookingsList = new ArrayList<BookingDetails>();
		ArrayList<BookingDetails> concertBookingsList = new ArrayList<BookingDetails>();
		ArrayList<BookingDetails> gameBookingsList = new ArrayList<BookingDetails>();
		
		HashMap<String, ArrayList> bookingsLists = new HashMap<String, ArrayList>();

		try
		{
			PreparedStatement ps = connection.prepareStatement("select * from bookings B, event E, movie M, hosted H where B.event_id=E.event_id and E.event_id=M.event_id and E.event_id=H.event_id and B.username = '" + username + "'");
			System.out.println("query fired = " + ps.toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				BookingDetails booking = new BookingDetails();
				booking.setBooking_id(rs.getInt("booking_id"));
				booking.setBooking_datetime(rs.getString("booking_datetime"));
				booking.setNo_of_tickets(rs.getInt("no_of_tickets"));
				booking.setUsername(username);
				booking.setTransaction_id(rs.getInt("transaction_id"));
				booking.setEvent_id(rs.getInt("event_id"));
				
				Movie movie = new Movie();
				movie.setMovie_name(rs.getString("movie_name"));
				
				booking.setMovie(movie);
				
				movieBookingsList.add(booking);
			}
			
			ps = connection.prepareStatement("select * from bookings B, event E, concert M, hosted H where B.event_id=E.event_id and E.event_id=M.event_id and E.event_id=H.event_id and B.username = '" + username + "'");
			System.out.println("query fired = " + ps.toString());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BookingDetails booking = new BookingDetails();
				booking.setBooking_id(rs.getInt("booking_id"));
				booking.setBooking_datetime(rs.getString("booking_datetime"));
				booking.setNo_of_tickets(rs.getInt("no_of_tickets"));
				booking.setUsername(username);
				booking.setTransaction_id(rs.getInt("transaction_id"));
				booking.setEvent_id(rs.getInt("event_id"));
				
				Concert concert = new Concert();
				concert.setType(rs.getString("type"));
				concert.setArtist(rs.getString("artist"));
				
				booking.setConcert(concert);

				concertBookingsList.add(booking);
			}
			
			ps = connection.prepareStatement("select * from bookings B, event E, game M, hosted H where B.event_id=E.event_id and E.event_id=M.event_id and E.event_id=H.event_id and B.username = '" + username + "'");
			System.out.println("query fired = " + ps.toString());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BookingDetails booking = new BookingDetails();
				booking.setBooking_id(rs.getInt("booking_id"));
				booking.setBooking_datetime(rs.getString("booking_datetime"));
				booking.setNo_of_tickets(rs.getInt("no_of_tickets"));
				booking.setUsername(username);
				booking.setTransaction_id(rs.getInt("transaction_id"));
				booking.setEvent_id(rs.getInt("event_id"));
				
				Game game = new Game();
				game.setGame_name(rs.getString("game_name"));
				game.setTeams(rs.getString("teams"));

				gameBookingsList.add(booking);
			}
			
			bookingsLists.put("Movies", movieBookingsList);
			bookingsLists.put("Concerts", concertBookingsList);
			bookingsLists.put("Games", gameBookingsList);

		}
		catch(Exception e)
		{
			throw e;
		}
		
		return bookingsLists;
		
	}
	
	public HashMap<String, ArrayList> getAllBookings(Connection connection) throws SQLException
	{
		ArrayList<BookingDetails> movieBookingsList = new ArrayList<BookingDetails>();
		ArrayList<BookingDetails> concertBookingsList = new ArrayList<BookingDetails>();
		ArrayList<BookingDetails> gameBookingsList = new ArrayList<BookingDetails>();
		
		HashMap<String, ArrayList> bookingsLists = new HashMap<String, ArrayList>();

		try
		{
			PreparedStatement ps = connection.prepareStatement("select * from bookings B, event E, movie M, hosted H where B.event_id=E.event_id and E.event_id=M.event_id and E.event_id=H.event_id");
			System.out.println("query fired = " + ps.toString());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				BookingDetails booking = new BookingDetails();
				booking.setBooking_id(rs.getInt("booking_id"));
				booking.setBooking_datetime(rs.getString("booking_datetime"));
				booking.setNo_of_tickets(rs.getInt("no_of_tickets"));
				booking.setUsername(rs.getString("username"));
				booking.setTransaction_id(rs.getInt("transaction_id"));
				booking.setEvent_id(rs.getInt("event_id"));
				
				Movie movie = new Movie();
				movie.setMovie_name(rs.getString("movie_name"));
				
				booking.setMovie(movie);
				
				movieBookingsList.add(booking);
			}
			
			ps = connection.prepareStatement("select * from bookings B, event E, concert M, hosted H where B.event_id=E.event_id and E.event_id=M.event_id and E.event_id=H.event_id");
			System.out.println("query fired = " + ps.toString());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BookingDetails booking = new BookingDetails();
				booking.setBooking_id(rs.getInt("booking_id"));
				booking.setBooking_datetime(rs.getString("booking_datetime"));
				booking.setNo_of_tickets(rs.getInt("no_of_tickets"));
				booking.setUsername(rs.getString("username"));
				booking.setTransaction_id(rs.getInt("transaction_id"));
				booking.setEvent_id(rs.getInt("event_id"));
				
				Concert concert = new Concert();
				concert.setType(rs.getString("type"));
				concert.setArtist(rs.getString("artist"));
				
				booking.setConcert(concert);

				concertBookingsList.add(booking);
			}
			
			ps = connection.prepareStatement("select * from bookings B, event E, game M, hosted H where B.event_id=E.event_id and E.event_id=M.event_id and E.event_id=H.event_id");
			System.out.println("query fired = " + ps.toString());
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				BookingDetails booking = new BookingDetails();
				booking.setBooking_id(rs.getInt("booking_id"));
				booking.setBooking_datetime(rs.getString("booking_datetime"));
				booking.setNo_of_tickets(rs.getInt("no_of_tickets"));
				booking.setUsername(rs.getString("username"));
				booking.setTransaction_id(rs.getInt("transaction_id"));
				booking.setEvent_id(rs.getInt("event_id"));
				
				Game game = new Game();
				game.setGame_name(rs.getString("game_name"));
				game.setTeams(rs.getString("teams"));

				gameBookingsList.add(booking);
			}
			
			bookingsLists.put("Movies", movieBookingsList);
			bookingsLists.put("Concerts", concertBookingsList);
			bookingsLists.put("Games", gameBookingsList);

		}
		catch(Exception e)
		{
			throw e;
		}
		
		return bookingsLists;
		
	}
	
	public void updateMovie(Connection connection, MovieEvent movieEvent) throws Exception {
		
	    try 
	    {	    	
	    	String query = "UPDATE event SET language='"+movieEvent.getLanguage()+"' , ticket_cost="+movieEvent.getTicket_cost()+" WHERE event_id="+movieEvent.getEvent_id();
	        PreparedStatement ps1 = connection.prepareStatement(query);
			System.out.println("query fired = " + ps1.toString());

	        int affectedRows1 = ps1.executeUpdate();

	        if (affectedRows1 == 0) {
	            throw new SQLException("Update Event failed, no rows affected.");
	        }
	        
	        String query2 = "UPDATE movie SET movie_name='"+movieEvent.getMovie_name()+"' , cast='"+movieEvent.getCast()+"' , release_date='" +movieEvent.getRelease_date()+"' , rating="+movieEvent.getRating() +" WHERE movie_id="+movieEvent.getMovie_id();
	        PreparedStatement ps2 = connection.prepareStatement(query2);
			System.out.println("query fired = " + ps2.toString());
	        
	        int affectedRows2 = ps2.executeUpdate();

	        if (affectedRows2 == 0) {
	            throw new SQLException("Update Movie failed, no rows affected.");
	        }

	    } 
	    catch(Exception e) {
			throw e;
	    }
	    
    	}
	
public void updateGame(Connection connection, GameEvent gameEvent) throws Exception {
		
	    try 
	    {	    	
	    	String query = "UPDATE event SET language=? , ticket_cost=? WHERE event_id=?";
	        PreparedStatement ps1 = connection.prepareStatement(query);
	        ps1.setString(1, gameEvent.getLanguage());
	        ps1.setFloat(2, gameEvent.getTicket_cost());
	        ps1.setInt(3, gameEvent.getEvent_id());

	        System.out.println("query fired = " + ps1.toString());

	        int affectedRows1 = ps1.executeUpdate();

	        if (affectedRows1 == 0) {
	            throw new SQLException("Update Event failed, no rows affected.");
	        }
	        
	        String query2 = "UPDATE game SET game_name=? , teams=? WHERE game_id=?";
	        PreparedStatement ps2 = connection.prepareStatement(query2);
	        ps2.setString(1, gameEvent.getGame_name());
	        ps2.setString(2, gameEvent.getTeams());
	        ps2.setInt(3, gameEvent.getGame_id());
	        
			System.out.println("query fired = " + ps2.toString());
	        
	        int affectedRows2 = ps2.executeUpdate();

	        if (affectedRows2 == 0) {
	            throw new SQLException("Update Game failed, no rows affected.");
	        }

	    } 
	    catch(Exception e) {
			throw e;
	    }
	    
    	}
		

}
