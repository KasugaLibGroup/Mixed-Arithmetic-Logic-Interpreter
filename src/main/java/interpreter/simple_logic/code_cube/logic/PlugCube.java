package interpreter.simple_logic.code_cube.logic;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.code_cube.LogicalCode;

import java.util.List;

// 空体
public class PlugCube implements LogicalCode {

    public PlugCube() {}

    @Override
    public List<LogicalCode> getNext() {
        return List.of();
    }

    @Override
    public void update() {}

    @Override
    public boolean readOnly() {
        return false;
    }

    @Override
    public boolean writeOnly() {
        return true;
    }

    @Override
    public float getData() {
        return 0;
    }

    @Override
    public PlugCube copy(Namespace namespace) {
        return new PlugCube();
    }
}
