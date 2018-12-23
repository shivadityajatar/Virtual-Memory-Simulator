package maj06mar11;
public class List 
{
    public static final int ListSize=1000;
    int arr[];
    public int size=-1;
    public List()
    {
        arr=new int[ListSize];
        size=0;
    }
    public void shiftDown(int pos)
    {
        if(size==ListSize)
            System.out.println("List full!!");
        else
        {
            size++;
            for(int i=size-1;i>pos;i--)
                arr[i]=arr[i-1];   
        }
    }
    public void removeX(int x)
    {
        int index=getIndex(x);
        for(int i=index;i<size;i++)
            arr[i]=arr[i+1];
        size--;
    }
    public int getIndex(int x)
    {
       int index=-1;
        for(int i=0;i<size;i++)
        {
            if(arr[i]==x)
                index=i;
        }
        return index;
    }
    void addAfter(int pos,int val)
    {
        shiftDown(pos);
        arr[pos]=val;
    }
}