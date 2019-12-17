import java.io.*;


public class LCS {
    static int MININPUT = 2;
    static int MAXINPUT = 1000;
    static long numberOfTrials = 1000;
    static String ResultsFolderPath = "/home/teresa/Results/"; // pathname to results folder
    static FileWriter resultsFile;
    static PrintWriter resultsWriter;


    public static void main(String[] args) {
        correctnessTesting();

        // Worst experiments
        runFullExperimentWorst("LCS-Worst-Exp1.txt");
        runFullExperimentWorst("LCS-Worst-Exp2.txt");
        runFullExperimentWorst("LCS-Worst-Exp3.txt");
    }


    public static int LCS(String s1, String s2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int lcsLen = 0;

        char[] charS1 = s1.toCharArray();
        char[] charS2 = s2.toCharArray();

        for (i = 0; i < charS1.length; i++) {
            for (j = 0; j < charS2.length; j++) {
                k = 0;
                char char1 = charS1[i + k];
                char char2 = charS2[j + k];

                while (char1 != ' ' && char2 != ' ') {
                    char1 = charS1[i + k];
                    char2 = charS2[j + k];

                    if (k > lcsLen) {
                        lcsLen = k;
                    }

                    if (char1 != char2) break;

                    k += 1;
                }
            }
        }
        return lcsLen;
    }

    public static void correctnessTesting() {
        boolean answer;

        // Correctness Test 1
        System.out.println();
        System.out.println(" Test 1 ");
        String test1 = "TERESA ";
        System.out.println("String 1: " + test1);
        String test2 = "tERESA ";
        System.out.println("String 2: " + test2);
        int test3 = 5;
        System.out.println("Expected: " + test3);
        int test4 = LCS(test1, test2);
        System.out.println("Actual: " + test4);
        answer = (test3 == test4);
        System.out.println("True or False: " + answer);
        System.out.println();
        // End Test 1

        // Correctness Test 2
        System.out.println(" Test 2 ");
        String test5 = "TERESA ";
        System.out.println("String 1: " + test5);
        String test6 = "kFGHIJ ";
        System.out.println("String 2: " + test6);
        int test7 = 0;
        System.out.println("Expected: " + test7);
        int test8 = LCS(test5, test6);
        System.out.println("Actual: " + test8);
        status = (test7 == test8);
        System.out.println("True or False: " + answer);
        System.out.println();
        // End Test 2

        // Correctness Test 3
        System.out.println(" Test 3 ");
        String test9 = "12345TERESA ";
        System.out.println("String 1: " + test9);
        String test10 = "67890ABCDEF ";
        System.out.println("String 2: " + test10);
        int test11 = 3;
        System.out.println("Expected: " + test11);
        int test12 = LCS(t9, t10);
        System.out.println("Actual: " + test12);
        status = (test11 == test12);
        System.out.println("True or False: " + answer);
        System.out.println();
        // End Test 3

      
    }

    // Test worst case
    static void runFullExperimentWorst(String resultsFileName) {
        try {
            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);
            resultsWriter = new PrintWriter(resultsFile);
        } catch (Exception e) {
            System.out.println("There was a problem opening the results file: " + ResultsFolderPath + resultsFileName);
            return; // not very foolproof... but we do expect to be able to create/open the file...
        }

        ThreadCpuStopWatch BatchStopwatch = new ThreadCpuStopWatch(); // for timing an entire set of trials

        resultsWriter.println();
        resultsWriter.println("#N(Size)   AvgTime   Trials"); // # marks a comment in gnuplot data
        resultsWriter.flush();

        // for each size input we want to test: in this case starting small and doubling the size each time
        for (int x = MININPUT; x <= MAXINPUT; x *= 2) {
            // String to test
            String testString1 = StringCreate.worstCase(x, 'A');
            String testString2 = testString1;

            // progress message...
            System.out.println("Running test for input: " + x);

            // repeat for desired number of trials (for a specific size of input)...
            long batchElapsedTime = 0;

            System.out.print("    Running trial batch...");

            // force garbage collection before each batch of trials run so it is not included in the time
            System.gc();

            // instead of timing each individual trial, we will time the entire set of trials (for a given input size)
            // and divide by the number of trials -- this reduces the impact of the amount of time it takes to call the
            // stopwatch methods themselves
            BatchStopwatch.start(); // comment this line if timing trials individually

            // run the trials
            for (long trial = 0; trial < numberOfTrials; trial++) {
                // run the function we're testing on the trial input
                LCS(testString1, testString2);
            }

            // comment this line if timing trials individually
            batchElapsedTime = BatchStopwatch.elapsedTime();

            // calculate the average time per trial in this batch
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials;

            // print data for this size of input
            resultsWriter.printf("%8d %10.2s %7d\n", Long.toBinaryString(x).length(), averageTimePerTrialInBatch, numberOfTrials);
            resultsWriter.flush();
            System.out.println(" ....done.");
        }
    }

    // Test random
    static void runFullExperimentRandom(String resultsFileName) {
        try {
            resultsFile = new FileWriter(ResultsFolderPath + resultsFileName);
            resultsWriter = new PrintWriter(resultsFile);
        } catch (Exception e) {
            System.out.println("There was a problem opening the results file: " + ResultsFolderPath + resultsFileName);
            return; // not very foolproof... but we do expect to be able to create/open the file...
        }

        ThreadCpuStopWatch BatchStopwatch = new ThreadCpuStopWatch(); // for timing an entire set of trials

        resultsWriter.println();
        resultsWriter.println("#N(Size)   AvgTime   Trials"); // # marks a comment in gnuplot data
        resultsWriter.flush();

        // for each size input we want to test: in this case starting small and doubling the size each time
        for (int x = MININPUT; x <= MAXINPUT; x *= 2) {
            // String to test
            String testString1 = StringCreate.randomString(x);
            String testString2 = StringCreate.randomString(x);

            // progress message...
            System.out.println("Running test for input: " + x);

            // repeat for desired number of trials (for a specific size of input)...
            long batchElapsedTime = 0;

            System.out.print("    Running trial batch...");

            // force garbage collection before each batch of trials run so it is not included in the time
            System.gc();

            // instead of timing each individual trial, we will time the entire set of trials (for a given input size)
            // and divide by the number of trials -- this reduces the impact of the amount of time it takes to call the
            // stopwatch methods themselves
            BatchStopwatch.start(); // comment this line if timing trials individually

            // run the trials
            for (long trial = 0; trial < numberOfTrials; trial++) {
                // run the function we're testing on the trial input
                LCS(testString1, testString2);
            }

            // comment this line if timing trials individually
            batchElapsedTime = BatchStopwatch.elapsedTime();

            // calculate the average time per trial in this batch
            double averageTimePerTrialInBatch = (double) batchElapsedTime / (double) numberOfTrials;

            // print data for this size of input
            resultsWriter.printf("%8d %10.2s %7d\n", Long.toBinaryString(x).length(), averageTimePerTrialInBatch, numberOfTrials);
            resultsWriter.flush();
            System.out.println(" ....done.");
        }
    }

}
