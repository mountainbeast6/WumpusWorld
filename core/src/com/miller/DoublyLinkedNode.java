package com.miller;

public class DoublyLinkedNode<T> {
    private T data;
    private DoublyLinkedNode next;
    private DoublyLinkedNode prev;
    public DoublyLinkedNode(T data) {
        this.data=data;
        next=null;
        prev=null;
    }
    public void setData(T data){
        this.data =data;
    }
    public T getData(){
        return data;
    }

    public void setNext(DoublyLinkedNode<T> next){
        this.next=next;
    }
    public void setPrev(DoublyLinkedNode<T> prev){
        this.prev=prev;
    }

    public DoublyLinkedNode getNext() {
        return next;
    }

    public DoublyLinkedNode getPrev() {
        return prev;
    }
}
