public class Book {
	private String title;
	private String ISBN;
	private boolean available;
	private MyQueue<String> reservedQueue;
	
	public Book() {
		this.available = true;
		reservedQueue = new MyQueue<String>();
	}
	
	public Book(String title, String ISBN) {
		this.title = title;
		this.ISBN = ISBN;
		this.available = true;
		reservedQueue = new MyQueue<String>();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	public boolean isAvailable() {
		return available;
	}
	
	public void setReservedQueue(MyQueue<String> queue) {
		reservedQueue = queue;
	}
	
	public MyQueue<String> getReservedQueue() {
		return reservedQueue;
	}
	
}
