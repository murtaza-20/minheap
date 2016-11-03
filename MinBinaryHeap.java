import java.util.*;

public class MinBinaryHeap {
    public static void main (String[] args) {
//        Event e = new Arrival (1.30);
//        e.print();
        MinBinaryHeapMain minHeap = new MinBinaryHeapMain ();
        System.out.println ("Heap Empty? " + minHeap.isEmpty());
        
        boolean res;
        
        minHeap.buildHeap ();
        
        res = minHeap.insert (new Arrival (3434.34));
        
        if (res)
            System.out.println ("Event inserted successfully.");
        else
            System.out.println ("Can’t insert into the heap use build heap first or just add.");

        
        res = minHeap.add (new Arrival (10.30));
        
        if (res)
            System.out.println ("Event added successfully.");
        else
            System.out.println ("Can’t add into the heap use insert.");
        
        minHeap.buildHeap ();
        
        res = minHeap.add (new EndOfService (30.27));
        
        if (res)
            System.out.println ("Event added successfully.");
        else
            System.out.println ("Can’t add into the heap use insert.");
    }
    
    private static class MinBinaryHeapMain {
        List<Event> arr = null;
        static boolean heapBuilt;
        
        public MinBinaryHeapMain () {
            arr = new ArrayList<Event>();
            heapBuilt = false;
        }
        
        public boolean add (Event event) {
            if (!heapBuilt) {
                arr.add (event);
                return true;
            } else 
                return false;
        }
        
        public boolean insert (Event event) {
            if (heapBuilt) {
                return true;
            } else {
                return false;
            }
        }
        
        public boolean isEmpty () {
            return arr.isEmpty();
        }
        
        public void buildHeap () {
            heapBuilt = true;
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
        super.timeOfEvent = 0.00;
    }
    
    public Arrival (double timeOfEvent) {
        super.timeOfEvent = timeOfEvent;
    }
    
    public void print () {
        System.out.printf ("Arrival Event at time %.2f\n",super.getTimeOfEvent());
    }
}

class Termination extends Event {
    public Termination () {
        super.timeOfEvent = 0.00;
    }
    
    public Termination (double timeOfEvent) {
        super.timeOfEvent = timeOfEvent;
    }
    
    public void print () {
        System.out.printf ("Termination Event at time %.2f\n",super.getTimeOfEvent());
    }
}

class EndOfService extends Event {
    public EndOfService () {
        super.timeOfEvent = 0.00;
    }
    
    public EndOfService (double timeOfEvent) {
        super.timeOfEvent = timeOfEvent;
    }
    
    public void print () {
        System.out.printf ("EndOfService Event at time %.2f\n",super.getTimeOfEvent());
    }
}