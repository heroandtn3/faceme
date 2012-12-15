package model;


public class MyQueue {
	private Node[] value;
	public MyQueue(){
		
	}
	public MyQueue(Node[] value){
		this.value=value;
	}
	//lay 1 nut ra tu dau queue
	public Node remove(){
		Node result=null;
		if ((value!=null) && (value.length>0)){
			result=value[0];
			//loai bo node dau hang doi
			Node[] tmpNode=new Node[value.length-1];
			for (int i=0;i<value.length-1;i++)
				tmpNode[i]=value[i+1];
			this.value=tmpNode;
		}
		return result;
	}
	//them mot nut vao cuoi queue
	public void insert(Node node){
		if (value==null){
			value=new Node[1];//hang doi rong
			value[0]=node;
		}
		else{
			Node[] tmpNode=new Node[value.length+1];
			for (int i=0;i<value.length;i++)
				tmpNode[i]=value[i];
				tmpNode[value.length]=node;
				this.value=tmpNode;
		}
	}

}
