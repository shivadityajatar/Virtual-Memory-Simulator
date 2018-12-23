package maj06mar11;
public class Stats 
{
    static String algos[];
    static int pgHit[],pgMiss[],calledPgs=0,algoNo=-1,showStatsFrame=0;
    static double hitRatios[],missRatios[];
    public Stats()
    {
        algos=new String[100];
        pgHit=new int[100];
        pgMiss=new int[100];
        hitRatios=new double[100];
        missRatios=new double[100];
    }
}