package interpreter.code;

import interpreter.code.var.VarObj;

public class DataType {
    private final String name;
    private final DataType parent;
    private final Executable instanceInvoker;

    public DataType(String name, DataType parent, Executable instanceInvoker) {
        this.name = name;
        this.parent = parent;
        this.instanceInvoker = instanceInvoker;
    }

    public Executable getInstanceInvoker() {
        return instanceInvoker;
    }

    @Override
    public String toString() {
        if (parent == null) return name;
        return parent + "." + this.name;
    }

    public Executable defaultInvoker(String name) {
        return new VarObj(this, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DataType dataType)) return false;
        return this.toString().equals(dataType.toString());
    }
}
