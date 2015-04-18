package br.com.lscoder;

import java.io.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by leonardo on 4/17/15.
 */
public class AsciiMenuFileSelector {
    private FileManager _fileManager;
    private InputStream _input;
    private PrintStream _output;

    public AsciiMenuFileSelector(FileManager fileManager, InputStream in, PrintStream out) {
        this._fileManager = fileManager;
        this._input = in;
        this._output = out;
    }

    private void ShowMenu(HashMap<Integer, ProblemInputFile> files) {
        this._output.println("Choose one of the input files listed below\n");

        for (Iterator<Integer> iter = files.keySet().iterator(); iter.hasNext();)
        {
            int id = iter.next();
            this._output.println("\t[" + id + "] " + files.get(id).getName());
        }
    }

    public ProblemInputFile Select() throws IOException {
        int fileId;
        ProblemInputFile inputFile;
        InputStreamReader streamReader = new InputStreamReader(_input);
        BufferedReader bufferReader = new BufferedReader(streamReader);
        HashMap<Integer, ProblemInputFile> files = _fileManager.ScanInputFiles();

        do {
            ShowMenu(files);
            String option = bufferReader.readLine();

            fileId = Integer.parseInt(option);

            if(fileId == 0)
                return null;

            inputFile = files.get(fileId);
            _output.println("\n");
        } while(inputFile == null);

        return inputFile;
    }
}
