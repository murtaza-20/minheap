package datastructure;

import java.util.*;
import java.io.*;
import java.text.*;

public class BinaryHeap {
	public static void main (String[] args) throws IOException {
		BinaryHeapMain binHeap = new BinaryHeapMain ();
		Scanner scan  = new Scanner (System.in);
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));

		do {
			System.out.println ("1. Add Event");
			System.out.println ("2. Insert");
			System.out.println ("3. Print Array");
			System.out.println ("4. Build Heap");
			System.out.println ("5. Delete Min");
			System.out.println ("6. Quit");
			System.out.print ("Please enter your choice : ");
			int ch = scan.nextInt ();

			switch (ch) {
				case 1:
					if (!binHeap.isHeapBuilt()) {
						System.out.print ("Please enter the event name. (Arrival, Termination, EndOfService) : ");
						String eventName = br.readLine ();
						if (eventName.equalsIgnoreCase("arrival")) {
							Event event = new Arrival ();
							System.out.print ("Please enter time of event : ");
							event.setTimeOfEvent (scan.nextDouble());
							binHeap.add(event);
						} else if (eventName.equalsIgnoreCase("termination")) {
							Event event = new Termination ();
							System.out.print ("Please enter time of event : ");
							event.setTimeOfEvent (scan.nextDouble());
							binHeap.add(event);
						} else if (eventName.equalsIgnoreCase("endofservice")) {
							Event event = new EndOfService ();
							System.out.print ("Please enter time of event : ");
							event.setTimeOfEvent (scan.nextDouble());
							binHeap.add(event);
						} else {
							System.out.println ("Please enter proper name of the event. You have entered incorrect event type.");
						}
					} else {
 						System.out.println ("Can’t add into the heap use insert.");
					}
					break;
				case 2:
					if (binHeap.isHeapBuilt()) {
						System.out.print ("Please enter the event name. (Arrival, Termination, EndOfService) : ");
						String eventName = br.readLine ();
						if (eventName.equalsIgnoreCase("arrival")) {
							Event event = new Arrival ();
							System.out.print ("Please enter time of event : ");
							event.setTimeOfEvent (scan.nextDouble());
							binHeap.insert(event);
						} else if (eventName.equalsIgnoreCase("termination")) {
							Event event = new Termination ();
							System.out.print ("Please enter time of event : ");
							event.setTimeOfEvent (scan.nextDouble());
							binHeap.insert(event);
						} else if (eventName.equalsIgnoreCase("endofservice")) {
							Event event = new EndOfService ();
							System.out.print ("Please enter time of event : ");
							event.setTimeOfEvent (scan.nextDouble());
							binHeap.insert(event);
						} else {
							System.out.println ("Please enter proper name of the event. You have entered incorrect event type.");
						}
					} else {
						System.out.println ("Can’t insert into the heap use build heap first or just add.");
					}
					break;
				case 3:
					binHeap.printList();
					break;
				case 4:
					binHeap.buildHeap();
					break;
				case 5:
					break;
				case 6:
					scan.close();
					System.exit(0);
					break;
			}

		} while (true);
	}
}

class BinaryHeapMain {
	List<Event> heapList, tempList;
	int pos;
	boolean heapBuilt;

	public BinaryHeapMain () {
		heapBuilt = false;
		pos = 0;
		heapList = new ArrayList<Event>();
		tempList = new ArrayList<Event>();
	}

	public boolean isHeapBuilt () {
		return this.heapBuilt;
	}

	public void insert (Event event) {
		if (pos == 0) {
			heapList.add(pos, new Arrival());
			heapList.add(pos+1, event);
			pos = 2;
		} else {
			heapList.add(pos++, event);
			bubbleUp(pos - 1);
		}
	}

	public void add (Event event) {
		heapList.add (event);
		
	}

	public Event deleteMin () {
		return null;
	}

	public void buildHeap () {
		tempList = new ArrayList<Event>(heapList);
		heapList = new ArrayList<>();

		for (Iterator<Event> it = tempList.iterator(); it.hasNext();) {
			Event event = it.next();
			if (pos == 0) {
				heapList.add(pos, null);
				heapList.add(pos+1, event);
				pos = 2;
			} else {
				heapList.add(pos++, event);
				bubbleUp (pos - 1);
			}
		}
		heapBuilt = true;
	}

	public void bubbleUp () {
		int p = pos - 1;
		try {
			while (p > 0 && heapList.get(p/2).getTimeOfEvent() > heapList.get(p).getTimeOfEvent()) {
				Event temp = heapList.get(p);
				heapList.set(p, heapList.get(p/2));
				heapList.set(p/2, temp);
				p = p / 2;
			}
		} catch (NullPointerException | IndexOutOfBoundsException ne) {
			
		}
	}
	
	
	public void bubbleUp (int position) {
		try {
			int leftChildInd = getLeftChildPosition(position);
			int rightChildInd = getRightChildPosition(position);
			if (leftChildInd < pos && rightChildInd < pos && heapList.get(position).getTimeOfEvent() > heapList.get(leftChildInd).getTimeOfEvent() || 
				heapList.get(position).getTimeOfEvent() > heapList.get(rightChildInd).getTimeOfEvent()) {
				if (heapList.get(getLeftChildPosition(position)).getTimeOfEvent() < heapList.get(getRightChildPosition(position)).getTimeOfEvent()) {
					swap (position, getLeftChildPosition(position));
					bubbleUp(getLeftChildPosition(position));
				} else {
					swap(position, getRightChildPosition(position));
					bubbleUp(getRightChildPosition(position));
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//test comment
	
	public int getLeftChildPosition (int position) {
		return position * 2;
	}
	
	public int getRightChildPosition (int position) {
		return position * 2 + 1;
	}
	
	public void swap (int position1, int position2) {
		Event temp = heapList.get(position1);
		heapList.set(position1, heapList.get(position2));
		heapList.set(position2, temp);
	}
	

	public void printList () {
		try {
			DecimalFormat df = new DecimalFormat ("00.00");
			for (int i = 1; i < pos; i++) {
				Event event = heapList.get(i);
				System.out.print (event.toString() + ":" + df.format(event.getTimeOfEvent()) + "; ");
			}
			System.out.println ();
		} catch (NullPointerException ne) {
			
		}
	}
}

abstract class Event {
	double timeOfEvent;

	public Event () {
		timeOfEvent = 0.00;
	}

	public Event (double timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}

	public void setTimeOfEvent (double timeOfEvent) {
		this.timeOfEvent = timeOfEvent;
	}

	public double getTimeOfEvent () {
		return this.timeOfEvent;
	}

	public abstract void print ();
}

class Arrival extends Event {
	public Arrival () {
		super();
	}

	public Arrival (double timeOfEvent) {
		super(timeOfEvent);
	}

	public void print () {
		DecimalFormat df = new DecimalFormat ("00.00");
		System.out.println ("Arrival Event at time " + df.format(super.getTimeOfEvent()));
	}
	
	public String toString () {
		return "Arrival";
	}
}

class Termination extends Event {
	public Termination () {
		super();
	}

	public Termination (double timeOfEvent) {
		super (timeOfEvent);
	}

	public void print () {
		DecimalFormat df = new DecimalFormat ("00.00");
		System.out.println ("Termination Event at time " + df.format(super.getTimeOfEvent()));	
	}
	
	public String toString () {
		return "Termination";
	}
}


class EndOfService extends Event {
	public EndOfService () {
		super();
	}

	public EndOfService (double timeOfEvent) {
		super (timeOfEvent);
	}

	public void print () {
		DecimalFormat df = new DecimalFormat ("00.00");
		System.out.println ("EndOfService Event at time " + df.format(super.getTimeOfEvent()));
	}
	
	public String toString () {
		return "EndOfService";
	}
}