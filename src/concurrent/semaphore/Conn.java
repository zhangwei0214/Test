package concurrent.semaphore;

public class Conn {
	private String name;
	
	public Conn(String name) {
		super();
		this.name = name;
	}

	public void select(){
		try {
			
			Thread.sleep(5000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
