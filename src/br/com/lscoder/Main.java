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
        int[] times = new int[b];

        for(int i = 0; i < b; i++) {
            times[i] = scanner.nextInt();
        }

        long l = 0;
        long r = 1000000000000000L;

        while(l < r) {
            long mid = (l + r) / 2;

            long total = 0;
            for(int i = 0; i < b; i++) {
                total += (mid / times[i]) + 1;
            }

            if(total >= p) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        long time = l - 1;
        long total = 0;
        for(int i = 0; i < b; i++) {
            total += time / times[i] + 1;
        }

        int diff = (int) (p - total);

        time++;
        for(int i = 0; i < b; i++) {
            if(time % times[i] == 0) {
                if(--diff == 0) {
                    return Integer.toString(i + 1);
                }
            }
        }

        return "-1";
    }

    private static void WriteResult(int testCaseId, String result, BufferedWriter outputFile) throws IOException {
        String formatedLine = "Case #" + testCaseId + ": " + result + "\n";

        System.out.print(formatedLine);
        outputFile.write(formatedLine);
    }
}
