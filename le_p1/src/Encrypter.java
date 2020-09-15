
public class Encrypter {

    public String encrypt(String value){
        int[] eList = toArray(value);

        //add each digit with 7 and remainder 10
        for(int i = 0; i < 4; i ++)
        {
            eList[i] = eList[i]+7;
            eList[i] = eList[i]%10;
        }

        //swap first with third
        int temp = eList[0];
        eList[0] = eList[2];
        eList[2] = temp;

        //swap second with fourth
        temp = eList[1];
        eList[1] = eList[3];
        eList[3] = temp;

        return toString(eList);


    }

    static int[] toArray(String toEncrypt)
    {
        int value = Integer.parseInt(toEncrypt);

        int[] eList = new int[4];
        for(int i = 3; i >= 0; i--)
        {
            eList[i] = value % 10;
            value = value / 10;
        }
        return eList;
    }

    private String toString(int [] eList)
    {
        int encrypt = 0;
       for(int i = 0; i < 4; i ++)
       {
           encrypt *= 10;
           encrypt += eList[i];
       }
       String cipher = String.valueOf(encrypt);

       while(cipher.length() != 4)
       {
           cipher = '0' + cipher;
       }

       return cipher;
    }

}
