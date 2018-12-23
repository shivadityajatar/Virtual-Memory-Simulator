package maj06mar11;
public class Lru extends BasicModule
{
    static int sequence[],calledNo=-1;
    public Lru(int x) //x=0: singly linked list, x=1:doubly linked list 
    {
      super(x," LRU        ");
      sequence=new int[100];
      System.out.println("-------------------------------------LRU-------------------------------------------");
      BasicFn();
    }
    public Lru(int x, String algoName) 
    {
        super(x,algoName);
        sequence=new int[100];
        System.out.println("--------------------------AUGMENTED LRU-------------------------------------------");
        BasicFn();
    }
    public void tlbAdd(int calledPg,int indexToAdd)
    {
        tlb.tlbList.arr[indexToAdd]=calledPg; //add that page at the end of tlb
        calledNo++;
        sequence[indexToAdd]=calledNo;
        System.out.print("\t index to add : "+indexToAdd+"\t\tSequnces :: ");
        for(int i=0;i<Tlb.tlbSize/Maj06mar11.pgSize;i++)
        {
            System.out.print(" "+sequence[i]);
        }
        System.out.print("\tTLB :: ");
        for(int i=0;i<Tlb.tlbSize/Maj06mar11.pgSize;i++)
        {
            System.out.print(" "+tlb.tlbList.arr[i]);
        }
        System.out.println();
    }
    @Override
    public int replacementAlgo()
    {
        int IndexOfMinVal=0,minValue = sequence[0];
        for(int i=0;i<Tlb.tlbSize/Maj06mar11.pgSize;i++)
        {
            if(sequence[i] < minValue)
            {
                minValue = sequence[i];   
                IndexOfMinVal = i;
            }
        }
        return IndexOfMinVal;
    }
}