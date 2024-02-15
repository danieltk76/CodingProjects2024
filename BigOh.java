import java.util.Random;

public class BigOh 
{
    private static final double MILLISECONDS_PER_SECOND = 1000.0;
    private static final int NUM_TRIALS = 5;
    private Random rand;

    public BigOh()
    {
        this.rand = new Random();

    }

    public BigOh(Random rand)
    {
        this.rand = rand;

    }
    
    public int runAlgorithm(int choice, int numElements)
    {

        switch (choice)
        {
            case 1:
                return algorithms.MysteryAlgorithms.alg1(numElements, rand);
                
            case 2:
                return algorithms.MysteryAlgorithms.alg2(numElements, rand);
               
            case 3: 
                return algorithms.MysteryAlgorithms.alg3(numElements, rand);
                
            case 4:
                return algorithms.MysteryAlgorithms.alg4(numElements, rand);
                
            case 5: 
                return algorithms.MysteryAlgorithms.alg5(numElements, rand);
                
            case 6:
                return algorithms.MysteryAlgorithms.alg6(numElements, rand);
                

            default:
                return -1;
        }
       

    }

    public double bigOhFunc(int choice, double n)
    {
        
        switch (choice)
        {
            case 1:
                return Math.pow(n, 1);
            case 2:
                return Math.pow(n, 3);
            case 3:
                return Math.pow(n, 2);
            case 4:
                return Math.pow(n, 2);
            case 5: 
                return Math.pow(n, 5);
            case 6:
                return Math.pow(n, 4);

            default:
                return -1;

    
        }

        
    }

    public double timeAlgorithm(int choice, int n)
    {
        System.gc();
        long startTime = System.currentTimeMillis();
        runAlgorithm(choice, n);
        long finishTime = System.currentTimeMillis();
        
        long totalTimeMillis = finishTime - startTime;

        return totalTimeMillis / MILLISECONDS_PER_SECOND;

        

    }

    public double robustTimeAlgorithm(int choice, int n)
    {
        double smallestTime = Double.MAX_VALUE;

        for (int i = 0; i < NUM_TRIALS; i++)
        {
            double currentTime = timeAlgorithm(choice, n);
            if (currentTime < smallestTime)
            {
                smallestTime = currentTime;
            }
        }
        return smallestTime;
        

    }

    public double estimateTiming(int choice, int n1, double t1, int n2)
    {
        double complexityN1 = bigOhFunc(choice, n1);

        double complexityN2 = bigOhFunc(choice, n2);

        double scaleFactor = complexityN2 / complexityN1;

        double estimaedTime = t1 * scaleFactor;

        return estimaedTime;

        

    }

    public double percentError(double correct, double estimate)
    {
        double difference = (estimate - correct);

        double percentError = (difference / correct);
        
        return percentError;
        
        

    }

    public double computePercentError(int choice, int n1, int n2)
    {

        double bestTimeN1 = robustTimeAlgorithm(choice, n1);

        double estimatedTimeN2 = estimateTiming(choice, n1, bestTimeN1, n2);

        double actualTimeN2 = robustTimeAlgorithm(choice, n2);

        return percentError(actualTimeN2, estimatedTimeN2);
    }
}
