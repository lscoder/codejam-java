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
        int c = scanner.nextInt();
        int[] m = new int[c];
        int prev;
        int t1 = 0;
        int t2 = 0;
        int rate = 0;

        for(int i = 0; i < c; i++) {
            m[i] = scanner.nextInt();

            if((i > 0) && (m[i] <= m[i-1])) {
                int d = m[i-1] - m[i];
                t1 += d;
                rate = Math.max(rate, d);
            }
        }

        for(int i = 0; i < c - 1; i++) {
            t2 += Math.min(rate, m[i]);
        }

        return t1 + " " + t2;
    }


    private static void WriteResult(int testCaseId, String result, BufferedWriter outputFile) throws IOException {
        String formatedLine = "Case #" + testCaseId + ": " + result + "\n";

        System.out.print(formatedLine);
        outputFile.write(formatedLine);
    }
}
