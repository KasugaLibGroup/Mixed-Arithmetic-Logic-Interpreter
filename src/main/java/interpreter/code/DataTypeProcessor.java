package interpreter.code;

import java.util.HashMap;

public class DataTypeProcessor {
    private final HashMap<String, DataType> dataTypes;
    private final DataType object = registerDataType("object", null, params -> new Executable[0]);
    public DataTypeProcessor() {
        dataTypes = new HashMap<>();
    }

    public DataType registerDataType(String name, DataType parent, Executable invoker) {
        DataType type = new DataType(name, parent, invoker);
        dataTypes.put(name, type);
        return type;
    }

    public DataType registerDataType(String name, Executable invoker) {
        DataType type = new DataType(name, object, invoker);
        dataTypes.put(name, type);
        return type;
    }
}
