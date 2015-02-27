/**
 * author dsd140330
 * Skip list implementation
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;


public class SkipListImpl<T extends Comparable <T>> implements SkipList<T>{

	static int maxLevel,size;
	static SkipListNode head;
	static SkipListNode tail;
	
// constructor
	public SkipListImpl() {

		maxLevel=10; // initialize to some value.
		size=0;
        head = new SkipListNode<T>(null,maxLevel);// create head node
		tail = new SkipListNode<T>(null,maxLevel); // create tail with infinite value


		for(int i=0;i<maxLevel;i++)
		{
			head.next[i]=tail;			// all head pointing to tail initially.
		}

	}
	
	
	
	// find element.
	public static <T extends Comparable <T>> SearchNode<T> Find(T x)
	{
		SkipListNode<T > p;
		
		SkipListNode[] prev=new SkipListNode[maxLevel];
		p = head;

		for(int i=maxLevel-1;i>=0;i--)
		{
			if(p.next[i].data!=null)
			{
			T d =  p.next[i].data;
			while(d.compareTo(x)<0)
			{
				p=p.next[i];
			
				
				
				if(p.next[i].data== null) break;
				d = p.next[i].data;
			}
		}
			prev[i]=p;
		}
		
		
		if(p.next[0].data!=null && (p.next[0].data).compareTo(x)==0)
		{
			
			 return new SearchNode(p.next[0],prev);
		
		}
		else
		{
			 return new SearchNode(null,prev);

		}
	}
	
	public static  void resetLevel()
	{
		   int oldmaxL =maxLevel;
			maxLevel=maxLevel+10;
			@SuppressWarnings({ "unchecked", "rawtypes" })
			SkipListNode temp1 =new SkipListNode(null,maxLevel);
			@SuppressWarnings({ "unchecked", "rawtypes" })
			SkipListNode temp2 =new SkipListNode(null,maxLevel);
			for(int i=oldmaxL;i<maxLevel;i++)
			{
				temp1.next[i]=temp2;	
				
			}
			for(int i=0;i<oldmaxL;i++)
			{
				temp1.next[i]=head.next[i];
				
			}
			head=temp1;
			tail=temp2;
}
	
	//add element
	public  void add(T x) {
		// TODO Auto-generated method stub
		
       //int s = (int) (Math.log10(size)/Math.log10(2));
		
		//if(s>maxLevel-1) resetLevel();
		
		
		int level;
		SkipListNode<T > p;
		SkipListNode[]prev = null;

		SearchNode<T>searchnode = Find(x);
		p=searchnode.node;
		prev=searchnode.prev;

		if(p != null) { try {
			return;//throw new Exception("Value already exists");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		else
		{

			level =generateLevel();
			SkipListNode<T>n=new SkipListNode<T>(x, level);
			
			int i=0;
			do
			{
				n.next[i] = prev[i].next[i];
				prev[i].next[i]=n;
			
				i++;
				
				
			}while(i<level);
			size++;
			
		}
		
	}

	//choice
	static int generateLevel()
	{
		int l=0;
		int bit;
		Random r = new Random();
		while(l<maxLevel)
		{
			bit= r.nextInt(2);
			if(bit==0) 
				l++ ;
			else
				return l;
			
			
		}
		return l;
	}
	
	public static <T> void display()
	{
		SkipListNode<T> current =head;

		T dataValue=(T) current.next[0].data;
		System.out.println("Size of the list is:"+size);
		
      int i=0;
		while(i<size)
		{
			
			System.out.print(dataValue.toString()+"--");	
			current=current.next[0];
			dataValue=(T) current.next[0].data;
			if(dataValue == null) break;
			i++;
			
		}
		System.out.println("");

	}
	
	
	
	public static void main(String[] args) {

		Scanner sc = null;

		if (args.length > 0) {
			File file = new File(args[0]);
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			sc = new Scanner(System.in);
		}
		String operation = "";
		long operand = 0;
		int modValue = 997;
		long result = 0;
		Long returnValue = null;
		SkipListImpl<Long> skipList = new SkipListImpl<Long>();
		// Initialize the timer
		long startTime = System.currentTimeMillis();

		while (!((operation = sc.next()).equals("End"))) {
			switch (operation) {
			case "Add": {
				operand = sc.nextLong();
				skipList.add(operand);
				result = (result + 1) % modValue;
				break;
			}
			case "Ceiling": {
				operand = sc.nextLong();
				returnValue = skipList.ceiling(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "FindIndex": {
				operand = sc.nextLong();
				returnValue = skipList.ceiling(operand);
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "First": {
				returnValue = skipList.first();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Last": {
				returnValue = skipList.last();
				if (returnValue != null) {
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Floor": {
				operand = sc.nextLong();
				returnValue = skipList.floor(operand);
				if (returnValue != null) {
					System.out.println("floor:"+returnValue.toString());
					result = (result + returnValue) % modValue;
				}
				break;
			}
			case "Remove": {
				operand = sc.nextLong();
				if (skipList.remove(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}
			
			case "Contains": {
				operand = sc.nextLong();
				if (skipList.contains(operand)) {
					result = (result + 1) % modValue;
				}
				break;
			}
			
			
			case "Display":
			{
				Iterator<Long> l= skipList.iterator();
				while(l.hasNext())
				{
					System.out.println(l.next());
				}
				break;
			}

			}
		}

		// End Time
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;

		System.out.println(result + " " + elapsedTime);

	}
	
	
	
	
	@Override
	public T ceiling(T x) {
		// TODO Auto-generated method stub
		 T result;
		if(isEmpty()) {System.out.println("SkipList is emppty"); return null;}
		else
		{
		SearchNode<T>ceilingNode = Find(x);
		
		
		
		if(ceilingNode.node==null) { result= (T) ceilingNode.prev[0].next[0].data;}
		else
			result = ceilingNode.node.data;
		
}
		return result;
		}

	@Override
	public boolean contains(T x) {
		SearchNode<T>containsnode = Find(x);
		if(containsnode.node== null) return false;
		else
			return true;
		
		
	}

	@Override
	public T findIndex(int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T first() {
		// TODO Auto-generated method stub
		if(!isEmpty())
		return (T) (head.next[0].data);
		else 
			return null;
	}


	@Override
	public  T floor(T x)
	{

		if(isEmpty())
		{
			return null;
		}
		if(head.next[0].data==x)
			return null;
		SkipListNode<T> p = head;

		for(int i=maxLevel-1;i>=0;i--)
		{
			if(p.next[i].data!=null)
				while((p.next[i].data).compareTo(x)<0)
				{
					p=p.next[i];
					if(p.next[i].data==null)
						break;
				}
		}
		return p.data;
	}





	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		if(size==0)return true;
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return new SkipListIterator(head.next[0]);
	}

	@Override
	public T last() {

			{
				if(isEmpty())
				{
					System.out.println("The list is empty");
					return null;
				}
				else
				{
					SkipListNode p = head;
					for(int i=maxLevel-1;i>=0;i--)
					{
						
						while(p.next[i].data!=null)
							p=p.next[i];
								if(p.next[i].data==null)
									continue;
								
					}
//					System.out.println(p.data);
						return (T) p.data;
					}	
					
				}		
		}

	@Override
	public void rebuild() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean remove(T x) {
		// TODO Auto-generated method stub
		
		SearchNode<T>noderemove = Find(x);
		if(noderemove.node== null)
		{
			try {
		return false;	//throw new Exception("Value does not exist ");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
		else
		{
			size--;
			for(int i=0;i<maxLevel;i++)
			{
				
				if(noderemove.prev[i].next[i]== noderemove.node)
					noderemove.prev[i].next[i]=noderemove.node.next[i];
				
			}
			
			
		}
		
		return true;
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	
	
	
	
	
}
