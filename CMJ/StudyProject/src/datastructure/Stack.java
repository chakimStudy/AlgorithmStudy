package datastructure;

public class Stack {

	int maxsize = 10;
	int sp = -1;
	int[] stack = new int[maxsize];
	
	public void push(int a) {
		sp++;
		if(sp == maxsize) {
			int[] temp = new int[maxsize * 2];
			for(int i = 0; i < maxsize; i++) {
				temp[i] = stack[i];
			}
			maxsize = maxsize * 2;
			stack = temp;
			stack[sp] = a;
		}
		else {
			stack[sp] = a;
		}
	}
	public int pop() {
		return stack[sp--];
	}
	
	public boolean isEmpty() {
		if(sp == -1) {
			return true;
		}else {
			return false;
		}
	}
}
