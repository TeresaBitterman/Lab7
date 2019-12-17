import java.util.Arrays;
import java.util.Random;

public class StringCreate
{
    public static String worstCase(int N, char val)
    {
        char result[] = new char[N];

        for(int i = 0; i < N; i++)
        {
            result[i] = val;
        }
        return result.toString();
    }

    public static String randomString(int N)
    {
        char[] result = new char[N];
        Random random = new Random();

        for(int i = 0; i < N; i++)
        {
            result[i] = (char)(random.nextInt(26) + 'A');
        }
        return result.toString();
    }

}
