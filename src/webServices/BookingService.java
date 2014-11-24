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

}