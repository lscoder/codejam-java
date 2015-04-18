package br.com.lscoder;

/**
 * Created by leonardo on 4/17/15.
 */
public class ProblemInputFile {

    private int _id;
    private String _name;
    private String _path;

    public ProblemInputFile(int id, String name, String path) {
        this._id = id;
        this._name = name;
        this._path = path;
    }

    public int getId() {
        return this._id;
    }

    public String getName() {
        return this._name;
    }

    public String getPath() {
        return this._path;
    }
}
