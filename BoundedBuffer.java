/**
 * A synchronized bounded buffer class.
 * @author David
 */
public class BoundedBuffer<E> {
	
	// instance variables (shared)
	private E[] buffer;
	private int current_size;

	/**
	 * Constructor for a bounded buffer
	 * @param capacity Fixed capacity of the bounded buffer
	 */
	public BoundedBuffer(int capacity) {
		//initialize shared memory
		buffer = (E[]) new Object[capacity];	
		current_size = 0;
	}

	/**
	 * Synchronously retrieves and removes the tail element
	 * in the buffer. Waits if buffer is empty.
	 * @return the element at the tail
	 */
	public synchronized E get() {
		while (current_size == 0) {
			try {
				// must wait if the buffer is empty
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		E item = buffer[current_size-1];
		current_size--;
		notifyAll();	// wake everyone up who was waiting!
		return item;
	}

	/**
	 * Places the given element in the tail of the buffer.
	 * Waits if the buffer is full.
	 */
	public synchronized void put(E data) {

		while (current_size == buffer.length) {
			try {
				// must wait if the buffer is full
				wait();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}

		buffer[current_size] = data;
		current_size++;

		// Wake up all threads who might be waiting.
		notifyAll();
	}

	/**
	 * @return the current size of the buffer
	 */
	public synchronized int size() {
		return current_size;
	}

}
