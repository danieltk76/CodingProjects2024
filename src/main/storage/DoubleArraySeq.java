package storage;

/*
 * @author Daniel Knight
 * @version 2/25/2024
 * 
 * Class for DoubleArraySeq.
 * 
 */


public class DoubleArraySeq implements Cloneable
{
    public static final int DEFAULT_CAPACITY = 10;
    private double[] data;
    private int manyItems;
    private int currentIndex;


    /*
     * No-arg for DoubleArraySeq.
     */
    public DoubleArraySeq()
    {
        
        this(DEFAULT_CAPACITY);


    }

    /*
    * One-arg for DoubleArraySeq.
    * @param initialCapacity for DoubleArraySeq.
    */

    public DoubleArraySeq(int initialCapacity)
    {
        this.data = new double[initialCapacity];
        manyItems = 0;
        currentIndex = 0;
        if (currentIndex == -1)
        {
            currentIndex = manyItems;
        }



    }

    /*
     * AddAdter method for DoubleArraySeq.
     * @param elemet for addAfter method.
     */

    public void addAfter(double element)
    {

        if (manyItems == data.length)
        {
            ensureCapacity(2*manyItems + 1);

        }
        
        
        if (manyItems == 0 || currentIndex == -1)
        {
            data[0] = element;
            currentIndex = 0;
        }
        else if (currentIndex == manyItems) 
        {
            data[manyItems] = element;
            currentIndex = manyItems;
        }
        else
        {
            for (int i = manyItems; i > currentIndex + 1; i--)
            {
                data[i] = data[i - 1];
            }
            data[currentIndex + 1] = element;
            currentIndex = currentIndex + 1;

        }
        manyItems++;                             

    }

    /*
     * @param element for addBefore method.
     * Addbefore method for  DoubleArraySeq.
     */

    public void addBefore(double element) 
    {
            if (manyItems == data.length)
            {

                ensureCapacity(2 * manyItems + 1);
            }
        
            if (manyItems == 0 || currentIndex == -1)
            {

                data[0] = element;
                currentIndex = 0;
            }
            else if (currentIndex == manyItems) 
            {
                data[manyItems] = element;
                currentIndex = manyItems;
            }
            else
            {
                for (int i = manyItems; i > currentIndex; i--)
                {
                    data[i] = data[i - 1];
                }
                data[currentIndex] = element;
                
            }
            manyItems++;
        

        
    }

    /*
     * AddAll method for DoubleArraySeq.
     * @param addend for DoubleArraySeq.
     */

    public void addAll(DoubleArraySeq addend)
    {
       
        ensureCapacity(manyItems + addend.manyItems);

        for (int i = 0; i < addend.manyItems; i++)
        {
            data[manyItems + i] = addend.data[i];

        }

        manyItems += addend.manyItems;

    }

    /*
     * TrimToSize method for DoubleArraySeq.
     */

    public void trimToSize()
    {
        if (data.length != manyItems)
        {
            double[] newData = new double[manyItems];
            for (int i = 0; i < manyItems; i++)
            {
                newData[i] = data[i];

            }

            data = newData;
            

        }
    }

    /*
     * EnsureCapacity method for DoubleArraySeq.
     * @param for ensureCapacity method.
     * 
     */

    public void ensureCapacity(int minimumCapacity)
    {
        if (data.length < minimumCapacity)
        {
            double[] newData = new double[minimumCapacity];
            
            for (int i = 0; i < manyItems; i++)
            {
                newData[i] = data[i];

            }
            data = newData;

        }


    }

    /*
     * Start method for DoubleArraySeq.
     * 
     */
    public void start()
    {
        if (manyItems == 0)
        {
            currentIndex = -1;

        }
        else
        {
            currentIndex = 0;
        }

    }
    /*
     * 
     * Advance method for DoubleArraySeq.
     */

    public void advance()
    {
        if (currentIndex < -1 || currentIndex >= manyItems)
        {
            throw new IllegalStateException("no element to advance to");
        
        }
        else if (currentIndex < manyItems - 1)
        {
            currentIndex++;

        }
        else
        {
            currentIndex = manyItems;

        }

    
        
       


    }

    /*
     * GetCurrent method for DoubleArraySeq.
     * 
     * 
     */

    public double getCurrent()
    {
        if (currentIndex >= 0 && currentIndex < manyItems)
        {
            return data[currentIndex];
        }
        else
        {
            throw new IllegalStateException("No current element avalible");

        }
        
    }


    /*
     * RemoveCurrent method for DoubleArraySeq.
     * 
     */
    public void removeCurrent()
    {
        if (currentIndex < 0 || currentIndex >= manyItems)
        {
            throw new IllegalStateException("no element to remove.");

        }

        for (int i = currentIndex; i < manyItems - 1; i++)
        {
            data[i] = data[i + 1];
        }

        manyItems--;

        if (currentIndex >= manyItems)
        {
            currentIndex = manyItems - 1;
        }

    }

    /*
     * 
     * IsCurrent method for DoubleArraySeq.
     */
    public boolean isCurrent()
    {
        return currentIndex >= 0 && currentIndex < manyItems;

    }


    /*
     * 
     * GetCapacity method for DoubleArraySeq.
     */
    public int getCapacity()
    {
        return data.length;
    
    }


    /*
     * 
     * Size method for DoubleArraySeq.
     */
    public int size()
    {
        return manyItems;

    }


    /*
     * 
     * Clone method for DoubleArraySeq.
     */
    public DoubleArraySeq clone() 
    {

        try{
            DoubleArraySeq cloner = (DoubleArraySeq) super.clone();
            cloner.data = this.data.clone();
            return cloner;

        }
        catch (CloneNotSupportedException e)
        {
            throw new AssertionError();

        }
        

    }



    /*
     * ToString method for DoubleArraySeq.
     */
    public String toString()
    {

        String str = "<";
        for (int i = 0; i < manyItems; i++)
        {
            if (i > 0)
            {
                str += ", ";
            }
            if (i == currentIndex)
            {
                str += "[" + data[i] + "]";

            }
            else
            {
                str += data[i];
            }
            
        }
        str += ">";
        

        return str;
        
        

    }

    /*
     * Equals method for DoubleArraySeq.
     * @param other for method Equals.
     */
    public boolean equals(Object other)
    {
        if (!(other instanceof DoubleArraySeq))
        {
            return false;
        }

        DoubleArraySeq otherSeq = (DoubleArraySeq) other;
        if (this.manyItems != otherSeq.manyItems || this.currentIndex != otherSeq.currentIndex)
        {
            return false;
        }

        for (int i = 0; i < manyItems; i++)
        {
            if (this.data[i] != otherSeq.data[i])
            {
                return false;
            }
        }
        return true;
    }

    /*
     * 
     * Concatenation method for DoubleArraySeq.
     * @param s1 for method concatenation.
     * @param s2 for method concatenation.
     */
    public static DoubleArraySeq concatenation(DoubleArraySeq s1, DoubleArraySeq s2) 
    {

        DoubleArraySeq res = new DoubleArraySeq(s1.manyItems + s2.manyItems);
        
        for (int i = 0; i < s1.manyItems; i++)
        {
            res.data[i] = s1.data[i];

        }

        for (int i = 0; i < s2.manyItems; i++)
        {
            res.data[s1.manyItems + i] = s2.data[i];
        }

        res.manyItems = s1.manyItems + s2.manyItems;

        res.currentIndex = -1;
        
        
        
        return res;
    }




}