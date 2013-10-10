import java.util.*;

public class Stack{
    int top = -1;
    int size;
    int p[];

    public Stack(){
        size = 0;
        p = new int[0];
    }

    public Stack(int size){
        this.size = size;
        p = new int[size];
    }

    public void set_size(int new_size) throws Exception{
        if (top >= new_size){
            throw new Exception("OverflowError");
        }else if (new_size <= size){
            size = new_size;
        }else{
            int q[] = new int[new_size];
            for (int i = 0; i <= top; i++){
                q[i] = p[i];
            }
            p = q;
        }
        return;
    }

    public boolean is_full(){
        return (top == size - 1) ? true : false;
    }

    public boolean is_empty(){
        return (top == -1) ? true : false;
    }

    public void push(int data) throws Exception{
        if (is_full()){
            throw new Exception("OverflowError");
        }else{
            top += 1;
            p[top] = data;
        }
        return;
    }

    public int pop() throws Exception{
        if (is_empty()){
            throw new Exception("EmptystackError");
        }else{
            int res = p[top];
            top -= 1;
            return res;
        }
    }

    public void print(){
        if (is_empty()){
            System.out.println("The stakc is empty!");
            return;
        }
        for (int i = 0; i <= top; i++){
            System.out.print(p[i]);
            System.out.print(' ');
        }
        System.out.println();
        return;
    }
    
    public static void main(String[] args){
        Stack s = new Stack(10);
        s.print();
        int data = -1;
        try{
            for (int i = 0; i <= 10; i++){
                s.push(i);
            }
        }catch(Exception e){
            s.print();
            System.out.println(e);
        }
        
        try{
            s.set_size(5);
        }catch(Exception e){
            System.out.println(e);
        }
        s.print();

        try{
            s.set_size(15);
        }catch(Exception e){
            System.out.println(e);
        }

        s.print();

        try{
            for (int i = 0; i <= 10; i++){
                data = s.pop();
                System.out.print("data: ");
                System.out.println(data);
            }
        }catch(Exception e){
            s.print();
            System.out.println(e);
        }

        return;
    }
}

        

