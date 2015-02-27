public class SkipListNode<T> {

	
	T data;
	SkipListNode<T> [] next;
	//int[]width; // to store the size.


	public SkipListNode (T data,int length)
	{
		this.data= data;
		next= new SkipListNode[length+1];
	//	width=new int [length+1];
		//next= new SkipListNode[length+1];
		

	}


	public SkipListNode(int maxLevel) {
		// TODO Auto-generated constructor stub
		next= new SkipListNode[maxLevel];
		this.data=null;
		

		
	}
}
