package maj06mar11;
import java.util.Scanner;
import java.io.*;
public class Input 
{   
    Scanner sc;
    static int[] pid= new int[100];
    static int[] pgNo= new int[100];
    static int[] dirt= new int[100];
    static int[] size=new int[100];
    static int count;
    String fileName;
    static String strRefStr2;
    String strRefStr,mmRef;
    // ff=new firstFrame();
    public static String directoryWin="C:\\Users\\ansh\\Documents\\NetBeansProjects\\MajorProject\\home9april\\src\\maj06mar11\\";
    public static String directorylinux="/home/ashley/Anshul/Maj05Mar07/src/maj05mar07/";
    public Input(int i) throws IOException
    {
        strRefStr = new String();
        mmRef = new String();
         //   System.out.println(" AHAHAHHAHAHHAA");
        if(i==0)// INTEGRATE STRGENERATOR HERE
        {
            System.out.println("INTEGRATE STRGENERATOR HERE");
        }
        if(i==1)// INTEGRATED INPUT REFSTR MODULE HERE
        {
           // System.out.println(" AHAHAHHAHAHHAA");
            inputRefStr in= new inputRefStr();
            in.inputRefStrMain();
        }
        if(i==2)// add file chooser here!!
        {
            char[] s2 = new char[1000];
            int c=0;
            String chosenFile = FileChooser.chooseFile();
            System.out.println(chosenFile);
        /*    System.out.println("old : "+chosenFile);
            char[] charFile = chosenFile.toCharArray();
            for(int z=0;z<chosenFile.length();z++)
            {
                s2[c]=charFile[z];
                c++;
                if(charFile[z]=='\\')
                {
                    
                    s2[c]='\\';
                    c++;
                }
            }
            String s = new String(s2);
            System.out.println("new : "+s);
        */    fileRead(chosenFile);
        }
        System.out.println("In input lets seee::");
        for(int x=0;x<=count;x++)
        {
           //pid=204,pg=1600:: calledPg= 20401600
            strRefStr=strRefStr.concat(Integer.toString(pid[x]*Maj06mar11.noOfPgLimit+pgNo[x])+"\n");
        }
        
        //System.out.println(strRefStr);
        strRefStr2 = strRefStr;
        //System.out.println("ye sahi ho chuka");*/
        // BREAK pid SIZE into pages
    }
    static public int getIndexofPid(int value) 
    {
        int k=0;
        for(int i=0;i<pid.length;i++)
        {
            if(pid[i]==value)
            {
                k=i;
                break;
            }
        }
        return k;
    }
    public void fileRead(String fname)  
    {
        String sname = new String (fname);
        //RefList ne=new RefList();
        try
        {
            System.out.println("trying to read from file : "+sname);
            File f = new File(sname);
            Scanner sc = new Scanner(f);
            int i = 0;
            while(sc.hasNextInt())
            {
                pid[i] = sc.nextInt();
                pgNo[i] = sc.nextInt();
                dirt[i] = sc.nextInt();
                System.out.println(pid[i]+" "+pgNo[i]+" "+dirt[i]);
                count=i++;
            }
          
            /*int i = 0;
            String line = new String();
            String word[] = new String[3];
            FileReader fr=new FileReader("H:\\in lab\\home9april\\src\\maj06mar11\\ref_str.txt");    
            BufferedReader br = new BufferedReader(fr);
            while((line = br.readLine()) != null)
            {
                word = line.split("\\s");
                pid[i] = Integer.parseInt(word[0]);
                pgNo[i] = Integer.parseInt(word[1]);
                dirt[i] = Integer.parseInt(word[2]);
                System.out.println(pid[i]+" "+pgNo[i]+" "+dirt[i]);
                i++;
            }
            */
        }
        catch (Exception ex) 
        {
            //sc = new Scanner();
            System.out.println("ERROR : File not found!\n"+fname+"asd");
            System.exit(0);
        }
        System.out.println("DONE NEATLY");
    }
}