package datastructure;

public class Queue {
	int front = 0;
	int rear = 0;
	Integer [] queue = new Integer[10];
	
	public boolean isEmpty() {
		return front == rear && queue[front] == null;
	}
	
	public boolean isFull() {
		return front == rear && queue[front] != null;
	}
	
	public int poll() {
		if(isEmpty()) {
			System.out.println("비었단다.");
			return 0;
		}
		int temp = queue[front];
		queue[front] = null;
		front = ++front % 10;
		return temp;
	}
	
	public void offer(int a) {
		if(isFull()) {
			System.out.println("꽉 찼단다.");
			return;
		}
		queue[rear] = a;
		rear = ++rear % 10;
	}
	
}
