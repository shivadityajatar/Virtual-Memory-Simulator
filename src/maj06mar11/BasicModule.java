package maj06mar11;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicModule 
{
    MM mm;
    Tlb tlb;
    int refCount=RefStr.count,algoRef[] = new int[500000];
    String strAlgoRef[] = new String[500000];
    public String StrRefStr[];
    int x=0,callPg;
    static int runningFlag=0;
    public BasicModule(int x,String algo)
    {
        while(runningFlag==1)
        {
            try 
            {
                Thread.sleep(100);
            }
            catch (InterruptedException ex) 
            {
                Logger.getLogger(BasicModule.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        runningFlag=1;
        mm=new MM();
        tlb=new Tlb(x);
        //StrRefStr = new String[500000];
        for(int i=0;i<=refCount;i++)
        {
            algoRef[i]= RefStr.pid[i]*Maj06mar11.noOfPgLimit+RefStr.pg[i];//pid=204,pg=1600:: calledPg= 20401600
            strAlgoRef[i]=Integer.toString(algoRef[i]);
        }
        Stats.algoNo++;
        Stats.algos[Stats.algoNo]=algo;
        BasicFrame bframe=new BasicFrame();
        bframe.basicFrameMain(algo);
    }
    public void tlbAdd(int calledPg,int indexToAdd)
    {
        tlb.tlbList.arr[indexToAdd]=calledPg; //add that page at the end of tlb
        //tlb.tlbList.addAfter(indexToAdd, calledPg); //fokat conplex nhi krte
    }
    public int replacementAlgo() 
    {
        int i=0;
        return((i++)%(Tlb.tlbSize/Maj06mar11.pgSize));
    }
    public void BasicFn()
    {
        for(int i=0;i<=refCount;i++)
        {
            int calledPg=algoRef[0];
            callPg=calledPg;
            BasicFrame.blink(false);
            BasicFrame.setPgCount(i+1);
            BasicFrame.setPgNo(calledPg);
            BasicFrame.setAction("searching");
            try {
                Thread.sleep(Maj06mar11.speed);
            } catch (InterruptedException ex) {
               System.out.println(" THREAD SLEEP EXCEPTION ");
            }
            if(tlb.tlbList.getIndex(calledPg)==-1) //PAGE MISS
            {
                BasicFrame.setAction("Page Miss");
                 try {
                      Thread.sleep(20);
                    } catch (InterruptedException ex) {
                    System.out.println(" THREAD SLEEP EXCEPTION ");
                 }
                Stats.pgMiss[Stats.algoNo]++;
                int indexToAdd;
                if(tlb.remainingTlb>0) //space available in tlb
                {
                    BasicFrame.setAction("Adding in TLB");
                    indexToAdd=tlb.tlbList.size/Maj06mar11.pgSize;
                    tlb.tlbList.size+=Maj06mar11.pgSize;
                    tlb.remainingTlb-=Maj06mar11.pgSize; //reserve a page in tlb
                }
                else // PAGE REPLACEMENT ALGO HERE
                {
                    BasicFrame.setAction("Replacing");
                    indexToAdd=replacementAlgo();
                    System.out.print("\nREPL:");
                    BasicFrame.blink(true);
                    BasicFrame.setNoofPgreplaced(Stats.pgMiss[Stats.algoNo]);
                    try {
                      Thread.sleep(Maj06mar11.speed);
                    } catch (InterruptedException ex) {
                    System.out.println(" THREAD SLEEP EXCEPTION ");
                    }
                }
                tlbAdd(calledPg,indexToAdd);
            }
            else// PAGE HIT
            {
                BasicFrame.setAction("page HIT");
                System.out.print("\n HIT:");
                Stats.pgHit[Stats.algoNo]++;
                int indexToAdd=tlb.tlbList.getIndex(calledPg);
                tlbAdd(calledPg,indexToAdd);
            }
            popAlgoRef();
        }
        BasicFrame.blink(false);
        BasicFrame.NextButton();
        BasicFrame.setPgNo(RefStr.pid[RefStr.count]*Maj06mar11.noOfPgLimit+RefStr.pg[RefStr.count]);//RefStr.pid[i]*Maj06mar11.noOfPgLimit+RefStr.pg[i]
        BasicFrame.setAction("Finished");
        System.out.println("=================================================================================");
    }
    public void popAlgoRef() 
    {
        for(int i=0;i<refCount-1;i++)
            algoRef[i]=algoRef[i+1];
        //refCount--;
    }
}