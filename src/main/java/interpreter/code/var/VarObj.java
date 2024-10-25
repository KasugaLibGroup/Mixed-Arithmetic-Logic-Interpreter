package interpreter.code.var;

import interpreter.code.DataType;
import interpreter.code.Executable;

public class VarObj extends VarCube {
    public VarObj(DataType type, String name, Executable data) {
        super(type, name, data);
    }

    public VarObj(DataType type, String name) {
        super(type, name, null);
    }
}
