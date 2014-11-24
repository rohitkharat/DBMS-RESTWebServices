package dto;

public class BookingDetails {
	  
	int booking_id;
	String booking_datetime;
	int no_of_tickets;
	String username;
	int transaction_id;
	int event_id;
	Movie movie;
	Concert concert;
	Game game;
	  
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Concert getConcert() {
		return concert;
	}
	public void setConcert(Concert concert) {
		this.concert = concert;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	public int getBooking_id() {
		return booking_id;
	}
	public void setBooking_id(int booking_id) {
		this.booking_id = booking_id;
	}
	public String getBooking_datetime() {
		return booking_datetime;
	}
	public void setBooking_datetime(String booking_datetime) {
		this.booking_datetime = booking_datetime;
	}
	public int getNo_of_tickets() {
		return no_of_tickets;
	}
	public void setNo_of_tickets(int no_of_tickets) {
		this.no_of_tickets = no_of_tickets;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
}