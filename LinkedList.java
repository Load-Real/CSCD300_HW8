//Circular Doubly Linked
public class LinkedList {

	private class Node {
		private Object data;   //Assume data implemented Comparable
		private Node next, prev;
		private Node(Object data, Node pref, Node next)
		{
			this.data = data;
			this.prev = pref;
			this.next = next;
		}
	}

	private Node head;
	private int size;

	public LinkedList() {
		this.head = new Node(null, null, null);
		this.head.next = this.head;
		this.head.prev=this.head;
		this.size = 0;
	}

	public boolean isEmpty() {
		return this.head == this.head.next;
	} 
   
   public int getSize() {
      return this.size;
   }
   
   public Object getFirst() {
      return this.head.next.data;
   }
   
   //O(1) Time Complexity
	public void addFirst(Object data) {
		Node nn = new Node(data, this.head, this.head.next);
		this.head.next.prev = nn;
		this.head.next = nn;
		this.size ++;
	}

   //O(1) Time Complexity
	public void addLast(Object data) {
      Node newNode = new Node(data, this.head.prev, this.head);
      this.head.prev.next = newNode;
      this.head.prev = newNode;
      this.size++;
	}
   
   //O(1) Time Complexity
   public void removeFirst()
	{
		if (this.size <= 0) throw new IllegalArgumentException("LinkedList is Empty!");
		
      Node nodeToRemove = this.head.next;
	
	   nodeToRemove.next.prev = nodeToRemove.prev;
      this.head.next = nodeToRemove.next;
	
	   this.size--;
	}
   
   public void InsertionSort() {
		Node lastSorted, sortedWalker;
      Comparable<Object> fud; //First Unsorted Data
      for (lastSorted = this.head.next; lastSorted != this.head.prev; lastSorted = lastSorted.next) {
         fud = (Comparable<Object>)lastSorted.next.data;
         for (sortedWalker = lastSorted; sortedWalker != this.head && 
         ((Comparable<Object>)sortedWalker.data).compareTo(fud) > 0;
         sortedWalker = sortedWalker.prev) {
            sortedWalker.next.data = sortedWalker.data;
         }
         sortedWalker.next.data = fud;
      }
	}
   
   public LinkedList merge(LinkedList A, LinkedList B) {
      LinkedList mergedList = new LinkedList();
      mergedList.size = A.getSize() + B.getSize();
      
      while (!(A.isEmpty()) && !(B.isEmpty())) {
         Comparable<Object> fa = (Comparable<Object>)A.getFirst();
         Comparable<Object> fb = (Comparable<Object>)B.getFirst();
         if ((fa.compareTo(fb)) < 0) {
            A.removeFirst();
            mergedList.addLast(fa);
         }
         else {
            B.removeFirst();
            mergedList.addLast(fb);
         }
      }
      
      while (!(A.isEmpty())) {
         Object fa = A.getFirst();
         mergedList.addLast(fa);
         A.removeFirst();
      }
      
      while (!(B.isEmpty())) {
         Object fb = B.getFirst();
         mergedList.addLast(fb);
         B.removeFirst();
      }
      return mergedList;
   }
   
   public void MergeSort() {
      Queue q = new Queue();
      for (Node cur = this.head.next; cur != this.head; cur = cur.next) {
         LinkedList newList = new LinkedList();
         newList.addFirst(cur.data);
         q.enqueue(newList);
      }
      while(q.size() > 1) {
         LinkedList sublist1 = (LinkedList) q.dequeue();
         LinkedList sublist2 = (LinkedList) q.dequeue();
         LinkedList mergedList = merge(sublist1, sublist2);
         q.enqueue(mergedList);
      }
      LinkedList finalList = (LinkedList) q.dequeue();
      this.head = finalList.head;
   }
   
   public boolean isSorted() {
      for (Node cur = this.head.next; cur.next != this.head; cur = cur.next) {
         if (((Comparable)cur.data).compareTo((Comparable)cur.next.data) > 0) {
            return false;
         }
      }
      return true;
   }
   
   @Override
	public String toString() {
		String result = "{";
	    for (Node node = this.head.next; node != this.head; node = node.next) {
	    		if(node.next != this.head)
	    			result += node.data + "->";
	    		else
	    			result += node.data;
	    }
	    return result + "}";
	  }
}