import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

class Solution {
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        Arrays.sort(tasks);
        Arrays.sort(workers);
        int low=0 ,high=Math.min(tasks.length, workers.length);
        while(low<high){
            int mid=(low+high+1)/2;
            if(canAssign(tasks,workers,pills,strength,mid)){
                low=mid;
            }
            else{
                high=mid-1;
            }

        }
        return low;
    }


    private boolean canAssign(int[] tasks, int[] workers, int pills, int strength, int taskcount) {
        Deque <Integer> b=new ArrayDeque<>();
        int w=workers.length-1;
        for(int i=taskcount-1;i>=0;i--)
        {
            int task=tasks[i];
            if(!b.isEmpty()&&b.peekFirst()>=task){
                b.pollFirst();
            }
            else if(w>=0&&workers[w]>=task){
                w--;
            }
                else{
                   while (w>=0&&workers[w]+strength>=task){
                    b.addLast(workers[w--]);
                   }
                    if(b.isEmpty()|| pills==0){
                        return false;
                    }
                    b.pollLast();
                    pills--;
                }
            
            }
            return true;
          
    }
}