import java.util.Iterator;
import java.util.NoSuchElementException;

////////////////////////////////////////////////////////////////
class Link {
	public int iData; // data item
	public double dData; // data item
	public Link next; // next link in list Node next
// -------------------------------------------------------------

	public Link(int id, double dd) // constructor
	{
		iData = id; // initialize data
		dData = dd; // ('next' is automatically
	} // set to null)
// -------------------------------------------------------------

	public void displayLink() // display ourself
	{
		System.out.print("{" + iData + ", " + dData + "} ");
	}
} // end class Link
////////////////////////////////////////////////////////////////

class LinkList {
	private Link first; // ref to first link on list

// -------------------------------------------------------------
	public LinkList() // constructor
	{
		first = null; // no links on list yet
	}

// -------------------------------------------------------------
	public boolean isEmpty() // true if list is empty
	{
		return (first == null);
	}

// -------------------------------------------------------------
// insert at start of list
	public void insertFirst(int id, double dd) {
		Link newNode = new Link(id, dd);
		newNode.next = first;
		first = newNode;
	}

// -------------------------------------------------------------
	public Link deleteFirst() {
		if (first == null)
			throw new NoSuchElementException();
		Link obj = new Link(first.iData, first.dData);
		first = first.next;
		return obj;
	}

// -------------------------------------------------------------
	// create iterator
	// inner class implementing an iterator for this list
	private class Itr implements Iterator<Link> {
		private Link current = first;

		public boolean hasNext() {
			return current != null;
		}

		public Link next() {
			Link currentnew = new Link(current.iData, current.dData);
			Link tmp = currentnew;
			current = current.next;
			return tmp;
		}
	}

	/*
	 * Returns an iterator over elements of this list.
	 * 
	 * @return an iterator over elements of this list
	 */
	public Iterator<Link> iterator() {
		return new Itr();
	}

	public void displayList() {
		Iterator<Link> itr = iterator();
		while (itr.hasNext()) {
			Link element = itr.next();
			System.out.println(element.iData + "," + element.dData);
		}
	}

	// -------------------------------------------------------------

// -------------------------------------------------------------
	public void displaytheLastNODEiData() {
		Link current = first;
		Link next = current.next;

		while (next != null) {
			current = next;
			next = current.next;
		}
		System.out.println(current.iData);
	}

// ------------------------------------------------------------- 
	// returns the minimum iData in the list
	public int findMin() {
		Iterator<Link> itr = iterator();
		int min = itr.next().iData;
		while (itr.hasNext()) {
			Link element = itr.next();
			if (element.iData < min) {
				min = element.iData;
			}

		}
		return min;
	}

// ------------------------------------------------------------- 

// ------------------------------------------------------------- 
	// this method will delete any node with iData == a.
	// if there are many, the method will have to delete all of them.
	public void DeleteElementwithiData(int a) {
		Link current = first;
		Link next = new Link(0, 0.0);

		while (current != null) {
			while (current.next != null) {
				if (first.iData == a) {//handle the first element
					first = first.next;
				}
				next = current.next;//from the second element
				if (next.iData == a) {
					current.next = next.next;// skip the duplicate
				}

				else {
					current = current.next; // if not, point to next one normally
				}
			}
			current = current.next;// move to next node
		}
		displayList();

	}

// ------------------------------------------------------------- 
	// this method will scan through the list and remove
	// duplicates. (if the list is: 2->3->2->4->3->5->90 the method should
	// keep on copy of each duplicate, so the list become:
	// 2->3->4->5-90
	// if there are no duplicates then the method does not need do anything
	public void removeDuplicates() {//needs two iterations
		  Link outter = first;
		  Link inner = new Link(0, 0.0);
		  Link next = new Link(0, 0.0);

		  while (outter != null) {
			   inner = outter;//use this to iterate through the list
		   while (inner.next != null) {
		    next = inner.next;
		    if (outter.iData == next.iData) {
		     inner.next = next.next;// skip the duplicate
		    } else {
		     inner = inner.next; // if not, point to next one normally (inner loop)
		    }
		   }
		   outter = outter.next;// move to next node, move the outer loop
		  }
		  displayList();
		 }

	

// -------------------------------------------------------------
	// write an algorithm that I will sort the list in-place
	// do not use arrays.
	public void sortList() {
		Link current = first;
		Link next = new Link(0, 0.0);
		Link temp = new Link(0, 0.0);

		while (current.next != null) {
			next = current.next;
			// next.displayLink();
			// System.out.println("");
			while (next != null) {
				if (current.iData > next.iData) {
					/// swap elements
					temp.iData = current.iData;
					temp.dData = current.dData;
					current.iData = next.iData;
					current.dData = next.dData;
					next.iData = temp.iData;
					next.dData = temp.dData;
				}
				next = next.next;
			}
			current = current.next;
		}
		displayList();
	}

} // end class LinkList
////////////////////////////////////////////////////////////////

class Quiz3 {
	public static void main(String[] args) {
		LinkList theList = new LinkList(); // make new list
		// this code is for you to use to test you code
		// feel free to modify it.

		// insert four items
		theList.insertFirst(5, 5.99);
		theList.insertFirst(22, 2.99);
		theList.insertFirst(11, 4.99);
		theList.insertFirst(88, 6.99);
		theList.insertFirst(11, 4.99);
		theList.insertFirst(88, 8.99);
		theList.insertFirst(11, 4.99);
		theList.insertFirst(7, 1.99);
		theList.insertFirst(5, 5.99);
		
		

		
		// ... add more elements if you want..

		// CALL and TEST YOUR METHODS HERE
		System.out.println("Display List");
		theList.displayList();
		System.out.println("----------------------");
		
		System.out.println("Display List After Delete First");
		theList.deleteFirst();
	    theList.displayList();
		System.out.println("----------------------");
		
		System.out.println("Display the Min of the List");
		System.out.println(theList.findMin());
		System.out.println("----------------------");
		
		System.out.println("Display the Last Node of the List");
		theList.displaytheLastNODEiData();
		System.out.println("----------------------");
		
		System.out.println("Remove Duplicates");
		theList.removeDuplicates();
		System.out.println("----------------------");
		
		System.out.println("Remove Element with Specific iData");
		theList.DeleteElementwithiData(11);
		System.out.println("----------------------");
		
		System.out.println("Sorted According iData");
		theList.sortList();;

	} // end main()
} // end class LinkListApp
////////////////////////////////////////////////////////////////
