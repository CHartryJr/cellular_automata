 /**
  *@author Carl Hartry Jr
  *@apiNote just used to see and under stand wolfranm Celluar Automata
  */

public class App 
{
    private static char pixel = '-';
    private static int rule = 30; //must be a 8 bit unsigned int
    private static final int ZOOM = 50,HEIGHT =100 ,X_PADDING =200;
    


    public static void main(String[] args) throws Exception 
    {
        Wolfram ca = new Wolfram();
        ca.setRule(rule);
        char[] arrStarr = starArr(ZOOM);

        for(int i = 0;i < HEIGHT;++i) 
        {
            printCharArr(arrStarr);
            int [] temp = ca.generate(denormalize(arrStarr));
            arrStarr = normalize(temp);
        }
        
    }
    /*
     * prodiuces a starting array for programm
     */
    static char [] starArr(int length)
    {
        char [] temp = new char[length];
        for(int i =0; i < length-1;++i)
        {
         temp[i] = ' ';
        }
        temp[length/2] = pixel;
        return temp;
    }

     static char[] normalize(int[] arr)
     {
        char[] normVector = new char[arr.length];
        for(int i =0; i < arr.length-1;++i)
        {
            if(arr[i] == 1)
                normVector[i] = pixel;
            else
                normVector[i] = ' ';
        }
        return normVector;
     }

      static  int [] denormalize(char[] arr)
     {
        int[] denormVector = new int[arr.length];
        for(int i =0; i < arr.length-1;++i)
        {
            if(arr[i] == pixel)
                denormVector[i] = 1;
            else
                denormVector[i] = 0;
        }
        return denormVector;
     }

    static void printCharArr(char arr[])
    {
        String print ="", padding ="";
        for(int i =0; i < arr.length-1;++i)
        {
            print += arr[i];
        }

        int pos = print.length()/2;
      
        for(int i =0; i < (X_PADDING/2) - pos ;++i)
        {
            padding += " ";
        }

        System.out.println(padding+print);
    }

}