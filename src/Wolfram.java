public class Wolfram 
{
    private int [] rule;

    Wolfram()
    {
        this.rule = to8BitBinaryArray(1);
    }

    Wolfram(int rule)
    {
        this.rule = to8BitBinaryArray(rule);
    }

    public void  setRule(int newRule)
    {
        rule = to8BitBinaryArray(newRule);
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
        if(left == 1 && mid ==1 && right ==1) return rule[7];
        if(left == 1 && mid ==1 && right ==0) return rule[6];
        if(left == 1 && mid ==0 && right ==1) return rule[5];
        if(left == 1 && mid ==0 && right ==0) return rule[4];
        if(left == 0 && mid ==1 && right ==1) return rule[3];
        if(left == 0 && mid ==1 && right ==0) return rule[2];
        if(left == 0 && mid ==0 && right ==1) return rule[1];
        if(left == 0 && mid ==0 && right ==0) return rule[0];
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
}


