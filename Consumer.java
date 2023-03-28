
public class Consumer implements Runnable {
	
	private int id;
	private BoundedBuffer buffer;

	/**
	 * Constructs a consumer
	 * @param id
	 */
	public Consumer(BoundedBuffer bb, int id) {
		this.id = id;
		this.buffer = bb;
	}

	/**
	 * Each consumer gets 10000 items
	 */
	@Override
	public void run() {
		for (int i = 0; i < 10000; i++) {
			Integer item = (Integer) buffer.get();
			System.out.println("Consumer " + id + " got " + item + " (buffersize = " + buffer.size() + ")");
		}
	}
	
}
