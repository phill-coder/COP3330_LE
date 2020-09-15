public class Decrypter {

    public String decrypt(String value){
        int[] dList = toArray(value);
        for(int i = 0; i < 4; i++)
        {
            for(int j = 7; j < 17; j++)
            {
                if(j % 10 == dList[i])
                {
                    dList[i] = j;

                }
            }
            dList[i]= dList[i]-7;
        }

        //swap first with third
        int temp = dList[0];
        dList[0] = dList[2];
        dList[2] = temp;

        //swap second with fourth
        temp = dList[1];
        dList[1] = dList[3];
        dList[3] = temp;

        return toString(dList);
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
