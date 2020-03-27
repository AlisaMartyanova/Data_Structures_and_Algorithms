import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//50922730 - submittion number

public class CorrectionSuggestion {

    public static void main(String args[])
    {
        ArrayList<String> dict = new ArrayList<>();     //dictionary for correct text
        ArrayList<String> output = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        int N = input.nextInt();

        for (int i = 0; i<N; i++)
            dict.add(i, input.next());

        String str = input.next();

        correctSuggest(dict, str, output);


        for (int i = 0; i<output.size(); i++)
        {
            System.out.print(output.get(i));
            if(i!=output.size()-1)
                System.out.print(" ");
        }

    }

    //  Damerau-Levenshtein distance algorithm from 'Wiki conspecti'

    public static int DamerauLevenshteinDistance(String s1, String s2) {
        int M = s1.length();
        int N = s2.length();

        int[][] arr = new int[M+1][N+1];

        arr[0][0] = 0;                                  // leave for empty string

        for (int i = 1; i <= M; i++)
            arr[i][0] = arr[i - 1][0] + 1;              //as string increases by one character the distance from empty increases by 1

        for (int j = 1; j <= N; j++)
            arr[0][j] = arr[0][j - 1] + 1;


        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1))   // if characters are equal the distance remains the same
                    arr[i][j] = arr[i - 1][j - 1];
                else                                    // find the shortest possible distance
                {
                    arr[i][j] = arr[i - 1][j - 1] + 1;  // as all operations of correcting cost 1, the distance increases by 1
                    arr[i][j] = min(arr[i][j], arr[i - 1][j] + 1, arr[i][j - 1] + 1);
                }
                if (i > 1 && j > 1 && s1.charAt(i-1) == s2.charAt(j - 2) && s1.charAt(i - 2) == s2.charAt(j-1))
                {
                    arr[i][j] = Math.min(arr[i][j], arr[i - 2][j - 2] + 1);
                }

            }
        }

        /*for (int i = 0; i < M+1; i++) {
            for (int j = 0; j < N+1; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }*/

        return arr[M][N];


    }


    private static int min(int n1, int n2, int n3) {
        return Math.min(Math.min(n1, n2), n3);
    }


    public static void correctSuggest(ArrayList<String> dict, String str, ArrayList<String> output)
    {
        int mist[] = new int[dict.size()];

        for (int i = 0; i<dict.size(); i++)
            mist[i] = DamerauLevenshteinDistance(str, dict.get(i));  //using Damerau Levenshtein Distance find distances to all words in dictionary


        int min = str.length();

        for (int i = 0; i<dict.size(); i++)                          //find minimum distance
        {
            if(mist[i] < min)
                min = mist[i];
        }

        for (int i = 0; i<dict.size(); i++)                         //find all words with minimum distance
        {
            if(mist[i] == min)
                output.add(dict.get(i));
        }
        Collections.sort(output, String.CASE_INSENSITIVE_ORDER);    //sort words in alphabetic order

    }

}
