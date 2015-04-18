package br.com.lscoder;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        String currentPath = System.getProperty("user.dir");
        String inputFilesPath = currentPath + "/files/input";
        String outputFilesPath = currentPath + "/files/output";
        File inputFile = chooseInputFile(inputFilesPath);
        File outputFile;

        if (inputFile == null)
            return;

        Scanner scanner = new Scanner(inputFile);
        outputFile = createOutputFile(inputFile, outputFilesPath);

        FileWriter fileWriter = new FileWriter(outputFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        try
        {
            Solve(scanner, bufferedWriter);
        } finally {
            bufferedWriter.close();
            fileWriter.close();
        }

        System.out.println("\nOutput file `" + outputFile.getName() + "` created!");

        Runtime.getRuntime().exec(new String[] {"sh", currentPath + "/submit.sh"});
        System.out.println("Files available in the `submit` folder");
    }

    public static File chooseInputFile(String inputFilesPath) throws IOException {
        int fileId;
        InputStreamReader streamReader = new InputStreamReader(System.in);
        BufferedReader bufferReader = new BufferedReader(streamReader);
        File inputFolder = new File(inputFilesPath);
        File[] files = inputFolder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".in");
            }
        });

        do {
            System.out.println("\nChoose one of the input files listed below\n");
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                System.out.println("\t[" + (i + 1) + "] " + file.getName());
            }
            System.out.println("\t[0] Sair");

            fileId = Integer.parseInt(bufferReader.readLine());

            if(fileId == 0)
                return null;

            if((fileId < 1) || (fileId > files.length)) {
                System.out.println("Opção inválida!");
                fileId = -1;
            }

        } while(fileId == -1);

        return files[fileId - 1];
    }

    private static File createOutputFile(File inputFile, String outputFilesPath ) throws IOException {
        String inputFileName = inputFile.getName();
        String outputFileName = inputFileName.substring(0, inputFileName.indexOf('.'));
        File outputFile = new File(outputFilesPath  + "/" + outputFileName + ".out");

        return outputFile;
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
        /************************** YOUR CODE GOES HERE **************************/

        return "";
    }


    private static void WriteResult(int testCaseId, String result, BufferedWriter outputFile) throws IOException {
        String formattedLine = "Case #" + testCaseId + ": " + result + "\n";

        System.out.print(formattedLine);
        outputFile.write(formattedLine);
    }
}
