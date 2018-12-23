package maj06mar11;
class Tlb 
{
    List tlbList,statusbits,pgList;
    static int tlbSize=32;//32Bytes or 8 pg ki TLB
    int remainingTlb=32;
    Tlb(int i) 
    {
        if(i==0)//LinkedList
        {
            tlbList=new List();
        }
        else if(i==1)// Doubly circular
        {
            tlbList=new DoublyCircularList();
        }
        statusbits=new List();
    }    
} 