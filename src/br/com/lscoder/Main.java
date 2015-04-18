package br.com.lscoder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String inputFilesPath = System.getProperty("user.dir") + "/files/input";
        String outputFilesPath = System.getProperty("user.dir") + "/files/output";
        FileManager fileManager = new FileManager(inputFilesPath, outputFilesPath);
        AsciiMenuFileSelector inputFileSelector = new AsciiMenuFileSelector(fileManager, System.in, System.out);

        ProblemInputFile inputFile = inputFileSelector.Select();

        if (inputFile == null)
            return;

        Scanner scanner = fileManager.OpenInputFile(inputFile);
        BufferedWriter outputFile = fileManager.CreateOutputFile(inputFile);

        Solve(scanner, outputFile);

        System.out.println("\nOutput file for `" + inputFile.getName() + "` was created!");

        outputFile.close();
    }

    private static void Solve(Scanner scanner, BufferedWriter outputFile) throws IOException {
        int testCasesCount = scanner.nextInt();

        System.out.println("\nRunning `" + testCasesCount + "` test cases...\n");

        for (int testCaseId = 1; testCaseId <= testCasesCount; testCaseId++)
        {
            String result = Solve(scanner);
            WriteResult(testCaseId, result, outputFile);
        }
    }

    private static String Solve(Scanner scanner) {
        int b = scanner.nextInt();
        long p = scanner.nextLong();
        int[] t = new int[b];
        int[] tb = new int[b];
        long l = 1;

        for(int i = 0; i < b; i++) {
            t[i] = scanner.nextInt();
            tb[i] = 0;

            if(i == 1) {
                l = lcm(t[i], t[i - 1]);
            } else if(i > 1) {
                l = lcm(t[i], l);
            }
        }

        if(b == 1) {
            l = t[0];
        }

        int cl = 0;
        for(int i = 0; i < b; i++) {
            cl += l / t[i];
        }

        p = p % cl;
        if(p == 0) {
            p = cl;
        }

        while(p > 0) {
            int m = tb[0];

            for (int i = 0; (i < b) && (m != 0); i++) {
                m = Math.min(m, tb[i]);
            }

            if(m > 0) {
                for (int i = 0; i < b; i++) {
                    tb[i] -= m;
                }
            }

            for (int i = 0; i < b; i++) {
                if (tb[i] == 0) {
                    tb[i] = t[i];
                    p--;

                    if (p == 0) {
                        return Integer.toString((i + 1));
                    }
                }
            }
        }

        return "-1";
    }

    private static long lcm(long a, long b) {
        long m = Math.max(a * b, -a * b);
        return m / gcd(a, b);
    }

    private static long gcd(long a, long b) {
        long t;

        while (b != 0) {
            t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    private static void WriteResult(int testCaseId, String result, BufferedWriter outputFile) throws IOException {
        String formatedLine = "Case #" + testCaseId + ": " + result + "\n";

        System.out.print(formatedLine);
        outputFile.write(formatedLine);
    }
}
