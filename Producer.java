
public class Producer implements Runnable {
	
	private int id;
	private BoundedBuffer buffer;

	/**
	 * Constructs a producer 
	 * @param id
	 */
	public Producer(BoundedBuffer bb, int id) {
		this.id = id;
		this.buffer = bb;
	}
	
	/**
	 * Each producer produces 10000 items
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			int item = id * 100 + i;
			buffer.put(Integer.valueOf(item));
			System.out.println("Producer " + id + " put " + item + " (buffersize = " + buffer.size() + ")");				
		}
	}

}


