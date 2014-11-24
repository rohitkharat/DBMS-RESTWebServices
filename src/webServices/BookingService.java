package webServices;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.ProjectManager;

import com.google.gson.Gson;

import dto.Bookings;
import dto.FeedObjects;
import dto.GameEvent;
import dto.MovieEvent;
import dto.User;

@Path("/BookingService")
public class BookingService {

	@GET
	@Path("/GetFeeds")
	@Produces("application/json")
	public String feed()
	{
		System.out.println("first");
		String feeds = null;
		try 
		{
			ArrayList<FeedObjects> feedData = null;
			ProjectManager projectManager= new ProjectManager();
			feedData = projectManager.GetFeeds();
			Gson gson = new Gson();
			System.out.println("Checking");
			System.out.println(gson.toJson(feedData));
			feeds = gson.toJson(feedData);
		}
		
		catch (Exception e)
		{
			System.out.println("Exception Error"+e); //Console 
		}
		return feeds;
	}
	
	@GET
	@Path("/multipleParamTest/{name}/{age}/{gender}")
	@Produces("application/json")
	public String multipleParamTest(@PathParam("name") String name,
			@PathParam("age") int age, 
			@PathParam("gender") String gender)
	{
		System.out.println("Multiple Attempt");
		String feeds = null;
		try 
		{
			System.out.println("Name: " + name);
			System.out.println("Age: " + age);
			System.out.println("Gender: " + gender);
		}
		
		catch (Exception e)
		{
			System.out.println("Exception Error"+e); //Console 
		}
		return feeds;
	}
    
	//1
    @GET
    @Path("/login/{username}/{password}")
    @Produces("application/json")
    public String authenticateUser(@PathParam("username") String username,
                                    @PathParam("password") String password)
    {
        String jsonString = null;
        User user = new User();
        try
        {
            System.out.println("User Name sent: " + username);
            System.out.println("password sent: " + password);
            
			ProjectManager projectManager= new ProjectManager();
			user = projectManager.authenticateUser(username, password);
			Gson gson = new Gson();
			jsonString = gson.toJson(user);
			System.out.println(jsonString);
			
			if(user!=null)
			{
				System.out.println("found user: " + user.getUsername() + " of type: " + user.getUserType());
			}
			else
				System.out.println("no user found");

        }
        
        catch (Exception e)
        {
            System.out.println("Exception "+e); //Console 
        }
        return jsonString;
    }
    
    //5
    @GET
    @Path("/bookings/{username}")
    @Produces("application/json")
    public String getBookings(@PathParam("username") String username)
    {
        String jsonString = null;
        try
        {
            System.out.println("User Name sent: " + username);
			ProjectManager projectManager= new ProjectManager();
						
			HashMap<String, ArrayList> bookingsList = new HashMap<String, ArrayList>();
			bookingsList = projectManager.getBookings(username);
			Gson gson = new Gson();
			jsonString = gson.toJson(bookingsList);
			System.out.println(jsonString);
        }
        
        catch (Exception e)
        {
            System.out.println("Exception "+e); //Console 
        }
        
        return jsonString;
    }
    
    //19
    @GET
    @Path("/allBookings/")
    @Produces("application/json")
    public String getAllBookings()
    {
        String jsonString = null;
        try
        {
			ProjectManager projectManager= new ProjectManager();						
			HashMap<String, ArrayList> bookingsList = new HashMap<String, ArrayList>();
			bookingsList = projectManager.getAllBookings();
			Gson gson = new Gson();
			jsonString = gson.toJson(bookingsList);
			System.out.println(jsonString);
        }
        
        catch (Exception e)
        {
            System.out.println("Exception "+e);  
        }
        
        return jsonString;
    }
    
    @GET
	@Path("/updateMovie/{event_id}/{movie_id}/{language}/{ticket_cost}/{username}/{movie_name}/{cast}/{release_date}/{rating}")
	@Produces("application/json")
	public void updateMovie(@PathParam("event_id") int event_id,
			@PathParam("movie_id") int movie_id,
			@PathParam("language") String language,
			@PathParam("ticket_cost") float ticket_cost, 
			@PathParam("username") String username,
			@PathParam("movie_name") String movie_name,
			@PathParam("cast") String cast, 
			@PathParam("release_date") String release_date,
			@PathParam("rating") int rating) 
    {
		System.out.println("updating Movie");
	
		try 
		{
			MovieEvent movieEvent = new MovieEvent();
			movieEvent.setEvent_id(event_id);
			movieEvent.setMovie_id(movie_id);
			movieEvent.setLanguage(language);
			movieEvent.setMovie_name(movie_name);
			movieEvent.setRating(rating);
			movieEvent.setRelease_date(release_date);
			movieEvent.setTicket_cost(ticket_cost);
			movieEvent.setUsername(username);
			movieEvent.setCast(cast);

			System.out.println("Language: " + language);
			System.out.println("ticket_cost: " + ticket_cost);
			System.out.println("username: " + username);
			System.out.println("movie_name: " + movie_name);
			System.out.println("cast: " + cast);
			System.out.println("release_date: " + release_date);
			System.out.println("rating: " + rating);
			ProjectManager projectManager= new ProjectManager();
			projectManager.updateMovie(movieEvent);
		}
		
		catch (Exception e) {
			System.out.println("Exception Error"+e); //Console 
		}
	}
    
    @GET
	@Path("/updateGame/{event_id}/{game_id}/{language}/{ticket_cost}/{username}/{game_name}/{teams}")
	@Produces("application/json")
	public void updateGame(@PathParam("event_id") int event_id,
			@PathParam("game_id") int game_id,
			@PathParam("language") String language,
			@PathParam("ticket_cost") float ticket_cost, 
			@PathParam("username") String username,
			@PathParam("game_name") String game_name,
			@PathParam("teams") String teams) 
    {
		System.out.println("updating Game");
	
		try 
		{
			GameEvent gameEvent = new GameEvent();
			gameEvent.setEvent_id(event_id);
			gameEvent.setGame_id(game_id);
			gameEvent.setLanguage(language);
			gameEvent.setGame_name(game_name);
			gameEvent.setTeams(teams);
			gameEvent.setTicket_cost(ticket_cost);
			gameEvent.setUsername(username);
			
			System.out.println("Language: " + language);
			System.out.println("ticket_cost: " + ticket_cost);
			System.out.println("username: " + username);
			System.out.println("game_name: " + game_name);
			System.out.println("teams: " + teams);
			
			ProjectManager projectManager= new ProjectManager();
			projectManager.updateGame(gameEvent);
		}
		
		catch (Exception e) {
			System.out.println("Exception Error"+e); //Console 
		}
	}
    
    

}