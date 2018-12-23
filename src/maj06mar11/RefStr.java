package maj06mar11;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
public class RefStr 
{

    Scanner sc;
    String fileName;
    static int count=0;
    static int pid[]=new int[10000];
    static int pg[]=new int[10000];
    static int dirtyBit[]=new int[10000];
    RefStr(int i) 
    {
        if(i==1) randomly();
        else if(i==0) fromFile();
        else if (i==2)
        {
            pid = Input.pid;
            pg = Input.pgNo;
            dirtyBit = Input.dirt;
            Maj06mar11.startPolicy=1;
            count = Input.count;
            Stats.calledPgs = count;
        }
    }
    void randomly()//INCOMPLETE MODULE
    {
        /*int x=0,y=0;
        Random ran=new Random();
        pid[x]=ran.nextInt(Input.count);            
        int index = Input.getIndexofPid(0);
        // pg[y]=ran.nextInt(Input.size[index]/Maj04feb23.pgSize);
        // increment count in STATS of calledPages too! 
        */
        
    }
    void fromFile()
    {
        System.out.println("\nEnter name of file : ");
        sc=new Scanner(System.in);
        fileName=sc.nextLine();
        System.out.println("\nREADING FROM FILE... ");
        try
        {
            try
            {    
                sc = new Scanner(new File(Input.directoryWin+fileName));
            }
            catch (FileNotFoundException ex) 
            {
                System.out.println("ERROR : File not found!!\n opening default file...");
                try
                {    
                    sc = new Scanner(new File(Input.directoryWin+"ref_str.txt"));
                }
                catch(FileNotFoundException ex2)
                {
                    System.out.println("Tring for linux Systems..");
                    try
                    {    
                        sc = new Scanner(new File(Input.directorylinux+fileName));
                    }
                    catch (FileNotFoundException ex3) 
                    {
                        System.out.println("ERROR : File not found!!\n opening default file...");
                        sc = new Scanner(new File(Input.directorylinux+"pid---size.txt"));
                    }  
                }
            }    
            int x = 0;
            while(sc.hasNextInt())
            {
                pid[x] = sc.nextInt();
                pg[x]=sc.nextInt();
                dirtyBit[x++]=sc.nextInt();
                count++;
                Stats.calledPgs++;
                System.out.println(x-1+". "+pid[x-1]+" "+pg[x-1]+" "+dirtyBit[x-1]);
            }
        }
        catch (FileNotFoundException ex) 
        {
            System.out.println("\n ERROR : CANNOT FIND FILE!");
        }
        Maj06mar11.startPolicy=1;
    }
}
