public class Wolfram 
{
    private int [] rulebits;
    private int rule;


    public Wolfram()
    {
        rule = 30;
        this.rulebits = to8BitBinaryArray(rule);
    }

    public  Wolfram(int rule)
    {
        this.rulebits = to8BitBinaryArray(rule);
        this.rule = rule;
    }

    public void  setRule(int newRule)
    {
        rulebits = to8BitBinaryArray(newRule);
        this.rule = newRule;
    }

    public int[] generate(int [] previousGen)
    {
        int [] newGen = new int[previousGen.length];
        for (int i = 1; i < previousGen.length-1;++i)
        {
            int left = previousGen[i-1];
            int mid = previousGen[i];
            int right = previousGen[i+1];
            newGen[i] = nextGenPos(left,mid,right);
        }
        return newGen;
    }
            
    private int nextGenPos(int left, int mid, int right) 
    {
        if(left == 1 && mid ==1 && right ==1) return rulebits[0];
        if(left == 1 && mid ==1 && right ==0) return rulebits[1];
        if(left == 1 && mid ==0 && right ==1) return rulebits[2];
        if(left == 1 && mid ==0 && right ==0) return rulebits[3];
        if(left == 0 && mid ==1 && right ==1) return rulebits[4];
        if(left == 0 && mid ==1 && right ==0) return rulebits[5];
        if(left == 0 && mid ==0 && right ==1) return rulebits[6];
        if(left == 0 && mid ==0 && right ==0) return rulebits[7];
        return 0;
    }

    private int[] to8BitBinaryArray(int number) 
    {
        if (number < 0 || number > 255) 
        {
            throw new IllegalArgumentException("Number must be in the range 0 to 255.");
        }

        int[] binaryArray = new int[8];
        for (int i = 7; i >= 0; i--) {
            binaryArray[i] = (number & 1); // Get the least significant bit
            number >>= 1;                 // Shift the number to the right
        }
        return binaryArray;
    }

    public int getRule() 
    {
        return rule;
    }
}


