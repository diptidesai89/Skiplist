import java.util.Iterator;
import java.util.NoSuchElementException;


public class SkipListIterator<E> implements Iterator<E> {

	private SkipListNode nextNode;
	public SkipListIterator(SkipListNode head)
	{
		nextNode=head;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		
		if(nextNode.next[0] !=null) return true;
		else
			
		return false;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		if (!hasNext()) 
		{
			return null;
		}
        E res = (E) nextNode.data;
         nextNode = nextNode.next[0];
         return res;
		
	}
	
	
	

}
