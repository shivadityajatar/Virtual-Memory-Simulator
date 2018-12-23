package maj06mar11;
public class Optimal extends BasicModule //replace pages whose use will occur farthest in future
{
    static int refStrCpy[],distance[],cpyCount=0;
    public Optimal(int x) 
    {
        super(x," OPTIMAL     ");
        optimalParent();
        System.out.println("-------------------------------------OPTIMAL-------------------------------------------");
    }
    public Optimal(int x, String algoName) 
    {
        super(x,algoName);
        optimalParent();
        System.out.println("-----------------------------------"+algoName+"-------------------------------------------");
    }
    void optimalParent()
    {
        refStrCpy=new int[100];
        distance=new int[100];
        copyRef();
        BasicFn();
    }
    void copyRef()
    {
        for (int i=0;i<RefStr.count;i++,cpyCount++)
            refStrCpy[i]=RefStr.pid[i]*Maj06mar11.noOfPgLimit+RefStr.pg[i];//pid=204,pg=1600:: calledPg= 20401600
        System.out.println(" RefStr count = "+RefStr.count+" cpyCount = "+cpyCount);
    }
    void calcDist() // tlb mein x,y,z h.. to (dist=refStr index dist) until next ref of x,y,z
    {
        for(int i=0;i<tlb.tlbList.size/Maj06mar11.pgSize;i++)// for each pg in tlb we'll see dist
        {
            distance[i]=0;
            for(int j=0;j<cpyCount;j++)
            {
                if(tlb.tlbList.arr[i]==refStrCpy[j]) //*** tlb ka X == refStrCpy mein kitna distance pe            
                    break;
                else
                    distance[i]++;
            }
        }
    }
    int indexOfMaxDist()
    {
        int index=0;
        for(int i=0;i<tlb.tlbList.size/Maj06mar11.pgSize;i++)
        {
            if(distance[i]>distance[index])
                index=i;
        }
        return index;
    }
    @Override
    public void tlbAdd(int calledPg,int indexToAdd)
    {
        tlb.tlbList.arr[indexToAdd]=calledPg;
        System.out.print("\t index to add : "+indexToAdd+"\n\tcpyRef :: ");
        for(int i=0;i<cpyCount;i++)
            System.out.print(" "+refStrCpy[i]);
        System.out.print("\n\tTLB :: ");
        for(int i=0;i<Tlb.tlbSize/Maj06mar11.pgSize;i++)
            System.out.print(" "+tlb.tlbList.arr[i]);
        System.out.print("\n\tDistances :: ");
        for(int i=0;i<Tlb.tlbSize/Maj06mar11.pgSize;i++)
            System.out.print(" "+distance[i]);
        System.out.println();
        
        try
        {
            Exception e = null;
            if(refStrCpy[0]==calledPg) // remove the pg from RefStr(cpy) as it'll no longer be needed
            {
                for(int i=0;i<cpyCount;i++)
                    refStrCpy[i]=refStrCpy[i+1];
                cpyCount--;
            }
            else
                throw e;
        }
        catch(Exception e)
        {
            System.out.println("\t\t\tHIS SHOULDNT HAPPEN!!");
        }
    }
    @Override
    public int replacementAlgo()
    {
        calcDist();
        return indexOfMaxDist();
    }
}