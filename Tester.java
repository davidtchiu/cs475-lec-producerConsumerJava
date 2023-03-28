public class Tester {
  private static final int NUM_THREADS = 40;	//create this many consumers and producers

	public static void main(String[] args) {
		BoundedBuffer buff = new BoundedBuffer(10);

		//allocate space for threads
		Thread[] producers = new Thread[NUM_THREADS];
		Thread[] consumers = new Thread[NUM_THREADS];

		//start the threads
		for (int i = 0; i < NUM_THREADS; i++) {
			producers[i] = new Thread(new Producer(buff, i));
			producers[i].start();

			consumers[i] = new Thread(new Consumer(buff, i));
			consumers[i].start();
		}

		//join the customer threads
		for (int i = 0; i < NUM_THREADS; i++) {
			try {
				producers[i].join();
				consumers[i].join();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Done!");
	}

}