package interpreter.code.var;

import interpreter.code.DataType;
import interpreter.code.Executable;

public abstract class VarCube implements Executable {
    private final DataType type;
    private final String name;
    private Executable data;

    public VarCube(DataType type, String name, Executable data) {
        this.type = type;
        this.data = data;
        this.name = name;
    }

    public VarCube(DataType type, String name) {
        this.type = type;
        this.data = null;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return type + "." + name;
    }

    public boolean isNull() {
        return data == null;
    }

    public DataType getType() {
        return type;
    }

    public Executable getData() {
        return data;
    }

    public boolean isInstance(DataType type) {
        return type.equals(this.type);
    }

    @Override
    public Executable[] execute(Executable... params) {
        if (data == null)
            throw new NullPointerException("variable " + name + " is Null");
        return data.execute(params);
    }
}
