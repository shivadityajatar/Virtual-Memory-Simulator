package maj06mar11;
class Fifo extends BasicModule
{
    public Fifo(int x)
    {
        super(x," Fifo       ");
        System.out.println("-------------------------------------FIFO-------------------------------------------");
        BasicFn();
    }
    @Override
    public void tlbAdd(int calledPg,int indexToAdd)
    {
        tlb.tlbList.arr[indexToAdd]=calledPg; //add that page at the end of tlb
        //tlb.tlbList.addAfter(indexToAdd,calledPg); // fokat complex nhi krte
        System.out.print("\t index to add : "+indexToAdd+"\t\tSequnces :: ");
        System.out.print("\tTLB :: ");
        for(int i=0;i<Tlb.tlbSize/Maj06mar11.pgSize;i++)
        {
            System.out.print(" "+tlb.tlbList.arr[i]);
        }
        System.out.println();
    }
    @Override
    public int replacementAlgo()
    {   //if replacement algo is called => full, therefore add at rear only, after removing front.
        tlb.tlbList.removeX(tlb.tlbList.arr[0]);
        return Tlb.tlbSize/Maj06mar11.pgSize-1; // 32/4=8
    }
}