package multithread;

import java.util.Random;

public class WorkerThreadTest {

    /**
     * @param args
     * Thread Worker设计模式实例
     * reference: http://www.cnblogs.com/gaotianle/archive/2013/09/08/3308010.html
     */
    public static void main(String[] args) {
    	//set worker's number to 5
        Channel channel = new Channel(5);
        channel.startWorkers();
        
        //simulate job comes
        new WorkerClientThread("Alice", channel).start();
        new WorkerClientThread("Bobby", channel).start();
        new WorkerClientThread("Chris", channel).start();
    }

}

class WorkerClientThread extends Thread{
    private final Channel channel;
    private static final Random random = new Random();
    public WorkerClientThread(String name,Channel channel){
        super(name);
        
        this.channel=channel;
    }
    @Override
    public void run() {
        try{
            for(int i=0;true; i++){
                WorkerRequest request = new WorkerRequest(getName(),i);
                channel.put(request);
                Thread.sleep(random.nextInt(1000));
            }
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}

class WorkerRequest{
    private final String name;
    private final int number;
    private static final Random random = new Random();
    
    public WorkerRequest(String name,int number){
        this.name=name;
        this.number=number;
    }
    
    public void execute(){
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "[Request from " + name + " No." + number + "]";
    }
}

class Channel{
    private static final int MAX_REQUEST=100;
    private final WorkerRequest[] requestQueue;
    private int tail;
    private int head;
    private int count;
    
    private final WorkerThread[] threadPool;
    
    public Channel(int threads){
        this.requestQueue=new WorkerRequest[MAX_REQUEST];
        this.head = 0;
        this.tail=0;
        this.count=0;
        
        threadPool=new WorkerThread[threads];
        
        for(int i=0;i < threadPool.length;i++){
            threadPool[i] = new WorkerThread("Worker-" + i, this);
        }
    }
    
    public void startWorkers(){
        for(int i=0;i<threadPool.length;i++){
            threadPool[i].start();
        }
    }
    
    public synchronized void put(WorkerRequest request){
        while(count>=requestQueue.length){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        requestQueue[tail]=request;
        tail=(tail+1)%requestQueue.length;
        count++;
        notify();
    }
    
    public synchronized WorkerRequest take(){
    	// if there is no job in the channel, wait() unit there is a job
        while(count<=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        WorkerRequest request = requestQueue[head];
        
        head=(head+1)%requestQueue.length;
        count--;
        notify();
        return request;
    }
}

class WorkerThread extends Thread{
    private final Channel channel;
    public WorkerThread(String name, Channel channel){
        super(name);
        this.channel=channel;
    }
    @Override
    public void run() {
    	//get work from channel, and call work.execute to do the job
        while(true){
            WorkerRequest request = channel.take();
            request.execute();
        }
    }
}