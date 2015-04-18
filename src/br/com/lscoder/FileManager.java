package br.com.lscoder;

import java.io.*;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by leonardo on 4/17/15.
 */
public class FileManager {

    private final String _inputFilesPath;
    private final String _outputFilesPath;

    public FileManager(String inputFilesPath, String outputFilesPath) {
        this._inputFilesPath = inputFilesPath;
        this._outputFilesPath = outputFilesPath;
    }

    public HashMap<Integer, ProblemInputFile> ScanInputFiles()
    {
        int id = 1;
        File folder = new File(_inputFilesPath);
        HashMap<Integer, ProblemInputFile> inputFiles = new HashMap<Integer, ProblemInputFile>();
        File[] files =  folder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".in");
            }
        });

        for(File file : files) {
            ProblemInputFile inputFile = new ProblemInputFile(id++, file.getName(), file.getPath());
            inputFiles.put(inputFile.getId(), inputFile);
        }

        return inputFiles;
    }

    public Scanner OpenInputFile(ProblemInputFile problemInputFile) throws FileNotFoundException {
        return new Scanner(new File(problemInputFile.getPath()));
    }

    public BufferedWriter CreateOutputFile(ProblemInputFile problemInputFile) throws IOException {
        String inputFileName = problemInputFile.getName();
        String outputFileName = inputFileName.substring(0, inputFileName.indexOf('.'));
        File outputFile = new File(this._outputFilesPath + "/" + outputFileName + ".out");

        if(!outputFile.exists()) {
            outputFile.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(outputFile.getAbsoluteFile());
        return new BufferedWriter(fileWriter);
    }
}
