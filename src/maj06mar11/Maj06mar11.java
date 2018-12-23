package maj06mar11;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Maj06mar11 
{
    // CONSTANTS
    public static int refSize=20,pgSize=4,noOfPgLimit=100000,inputFlag=-1,startPolicy=0;
    public static int flagFifo=0,flagLru=0,flagOptimal=0,flagElru=0,flagAugmented=0;
    public static int selectedAlgoCount=0, speed=0;
    public static void main(String[] args) throws IOException
    {
        firstFrame.frameStart();
        Stats s=new Stats();
        while(inputFlag==-1)
        {
            try {
            Thread.sleep(100);
            } catch (InterruptedException ex) 
            {
                System.out.println("EXCEPTION IN WAITING..");
            }
        }
        Input i1=new Input(inputFlag);// selected from first frame//1=custom input, 0 for strGenerator
        RefStr rsi=new RefStr(inputFlag);//1 for inputRefStr , 0 for strGenerator, 2 for file reading
        while(startPolicy==0)
        {
            try {
            Thread.sleep(100);
            } catch (InterruptedException ex) 
            {
                System.out.println("EXCEPTION IN WAITING..");
            }
        }
        selectedAlgoCount=flagFifo+flagLru+flagOptimal+flagElru+flagAugmented;
        if(speed>500||speed<50) speed=100;
        if(flagFifo==1) {   Fifo fifo=new Fifo(0);}
        if(flagLru==1)  {  Lru lru=new Lru(0); }
        if(flagOptimal==1){    Optimal op=new Optimal(0); }
        //Hlru elru=new Hlru(0);
        if(flagElru==1){    Hlru hy = new Hlru(0);}
        if(flagAugmented==1){  Ehlru ehlru=new Ehlru(0); }
        System.out.println("\n\nSTATS :: \n\tNo of Pgs called = "+Stats.calledPgs+"\n\tNO\tNAME    \tNO OF PG HIT\t   HIT RATIO\tNo of PageMiss \tMISS RATIO");
        /*for(int i=0;i<=Stats.algoNo;i++)
            System.out.println("\t"+i+1+". \t"+Stats.algos[i]+"\t\t"+Stats.pgHit[i]+"\t\t"+(double)Stats.pgHit[i]/(double)Stats.calledPgs+"\t\t"+Stats.pgMiss[i]+"\t\t"+(double)Stats.pgMiss[i]/(double)Stats.calledPgs);        
        */
        while(Stats.showStatsFrame==0)//abhi k liye 3, set to 5 after hlru and ehlru//updated with count
        {
            try 
            {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Maj06mar11.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        StatsFrame.statsFrameMain();
    }
}