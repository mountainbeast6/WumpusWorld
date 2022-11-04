package com.miller;

public class myDoublyLinkedList<T> {
    private DoublyLinkedNode<T> first;
    private DoublyLinkedNode<T> last;
    int size=0;
    public myDoublyLinkedList(){
        first=null;
        last=null;
    }
    public void joinWith(int start, int end, myDoublyLinkedList<T> bridge){
        getNode(start).setNext(bridge.getNode(0));
        getNode(end).setPrev(bridge.getNode(bridge.size));
        size+=bridge.size;
    }
    public void addFirst(T data){
        DoublyLinkedNode<T>temp = new DoublyLinkedNode<>(data);
        if(first==null&&last==null){
            first =temp;
            last=temp;
        }
        else {
            temp.setNext(first);
            first.setPrev(temp);
            first=temp;
        }
        size++;
    }
    public void addLast(T data){
        DoublyLinkedNode<T>temp = new DoublyLinkedNode<>(data);
        if(first==null&&last==null){
            first=temp;
        }
        else{
            last.setNext(temp);
            temp.setPrev(last);
        }
        last=temp;
        size++;
    }
    public T removeFirst(){
        DoublyLinkedNode<T>temp1 = first;
        if (first==null){
            return null;
        }
        if (first==last){
            first=null;
            last=null;
            return temp1.getData();
        }
        first=first.getNext();
        size--;
        return temp1.getData();
    }
    public T removeLast(){
        if(last!=null){
            T send =last.getData();
            if(first==last) {
                first=null;
                last=null;
            }
            else{
                DoublyLinkedNode<T>temp = last.getPrev();
                temp.setNext(null);
                last=temp;
            }
            size--;
            return send;
        }
        return null;
    }
    public void addIndex(int loc, T data){
        DoublyLinkedNode<T>temp1 = first;
        DoublyLinkedNode<T>temp2 = temp1.getNext();
        DoublyLinkedNode<T>temp3 = new DoublyLinkedNode<>(data);
        if(loc==1) {
            addFirst(data);
        }
        else if(loc==size+1) {
            addLast(data);
        }
        else {
            for (int i = 0; i < loc - 2; i++) {
                temp1 = temp2;
                temp2 = temp2.getNext();
            }
            temp1.setNext(temp3);
            temp3.setNext(temp2);
            temp2.setPrev(temp3);
            temp3.setPrev(temp1);
        }
        size++;
    }
    public T removeInt(int loc){
        if(size==loc){
            return removeLast();

        }
        else if(loc>size){
            throw new NullPointerException("Too big of a number dumbass");
        }
        else if (loc<=0){
            throw new NullPointerException("Too small of a number dumbass");
        }
        else if(loc==1){
            return(removeFirst());
        }
        else if(size<3){
            DoublyLinkedNode<T> temp1 = first.getNext();
            first.setNext(last);
            return temp1.getData();
        }
        else {
            DoublyLinkedNode<T> temp1 = first;
            DoublyLinkedNode<T> temp2 = temp1.getNext();
            for (int i = 0; i < loc - 2; i++) {
                temp1 = temp2;
                temp2 = temp2.getNext();
            }
            temp1.setNext(temp2.getNext());
            temp2.getNext().setPrev(temp1);
            size--;
            return temp2.getData();
        }
    }

    public T get(int loc){
        DoublyLinkedNode<T> temp =first;
        for (int i =0; i<loc; i++){
            temp=temp.getNext();
        }
        return temp.getData();
    }
    private DoublyLinkedNode<T> getNode(int loc){
        DoublyLinkedNode<T> temp =first;
        for (int i =0; i<loc; i++){
            temp=temp.getNext();
        }
        return temp;
    }
    public String toString() {
        String out ="";
        DoublyLinkedNode<T>current=first;
        while(current!=null){
            out+=current.getData()+"=>";
            current= current.getNext();
        }
        out+="null";
        return out;
    }
}