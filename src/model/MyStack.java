package model;

public class MyStack {
	private Node[] value;
	public MyStack(){
		
	}
	public MyStack(Node[]value){
		this.value=value;
	}
	//lay 1 node ra stack
	public Node pop(){
		Node result=null;
		if ((value!=null) && (value.length>0)){
			result=value[value.length-1];
			//loai bo node cuoi cung;
			Node[]tmpNode=new Node[value.length-1];
			for (int i=0;i<value.length-1;i++)
				tmpNode[i]=value[i];
			this.value=tmpNode;
		}
		return result;
	}
	//them 1 node vao stact;
	public void push(Node node){
		if (value==null){// stack trong
			value=new Node[1];
			value[0]=node;
		}else{//ngan xep da co du lieu
			Node[] tmpNode=new Node[value.length+1];
			for (int i=0;i<value.length;i++)
				tmpNode[i]=value[i];
			tmpNode[value.length]=node;
			this.value=tmpNode;
		}
	}
}
