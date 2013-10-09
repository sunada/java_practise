class Queue{
    int head;
    int rear;
    int size;
    int q[];

    public Queue(){
        head = 0;
        rear = 0;
        size = 0;
        q = new int[0];
    }

    public Queue(int size){
        head = 0;
        rear = 0;
        this.size = size;
        q = new int[size];
    }

    public boolean is_full(){
        return (rear + 1) % size == head ? true : false;
    }

    public boolean is_empty(){
        return rear == head ? true : false;
    }

    public void push(int data) throws Exception{
        if (is_full()){
            throw new Exception("QueueOverflow");
        }else{
            q[rear] = data;
            rear = (rear + 1) % size;
        }
    }

    public int pop() throws Exception{
        if (is_empty()){
            throw new Exception("QueueEmpty");
        }else{
            int res = q[head];
            head = (head + 1) % size;
            return res;
        }
    }
    
    public void print(){
        if (is_empty()){
            System.out.println("The queue is empty!");
            return;
        }
        for (int i = head; i != rear; i = (i + 1) % size ){
            //System.out.print(i);
            System.out.print(" ");
            System.out.print(q[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        Queue que = new Queue();
        System.out.print("que = new Queue(): ");
        que.print();

        que = new Queue(10);
        System.out.print("que = new Queue(10): ");
        que.print();

        try{
            for (int i = 0; i <= 10; i++){
                que.push(i);
            }
        }catch(Exception e){
            que.print();
            System.out.println(e);
        }
        
        try{
            que.pop();
            que.pop();
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("after two que.pop()");
        que.print();

        try{
            que.push(10);
            que.push(11);
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println("after two que.push()");
        que.print();
        System.out.println();

        try{
            for (int i = 0; i <= 10; i++){
                //System.out.print(i);
                System.out.print(" ");
                System.out.print(que.pop());
            }
        }catch(Exception e){
            que.print();
            System.out.println(e);
        }
    }
}

