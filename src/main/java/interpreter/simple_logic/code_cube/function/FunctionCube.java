package interpreter.simple_logic.code_cube.function;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.adapter.Adapter;
import interpreter.simple_logic.adapter.Input;
import interpreter.simple_logic.code_cube.LogicalCode;
import interpreter.simple_logic.code_cube.LogicalSwitch;
import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FunctionCube implements LogicalCode {
    private final FunctionInputCube func;
    private final List<LogicalCode> stack;
    private final List<LogicalCode> next;
    private float cache;
    public FunctionCube(FunctionInputCube cube) {
        this.func = cube;
        stack = new ArrayList<>();
        this.next = new ArrayList<>();
        stack.add(cube);
    }

    public String getName() {
        return func.funcName;
    }

    public FunctionInputCube getEntry() {
        return func;
    }

    @Override
    public List<LogicalCode> getNext() {
        return next;
    }

    @Override
    public void update() {
        this.cache = goToNearestReturn();
    }

    @Override
    public boolean readOnly() {
        return false;
    }

    @Override
    public boolean writeOnly() {
        return false;
    }

    @Override
    public float getData() {
        return this.cache;
    }

    public void reset() {
        this.stack.clear();
        this.stack.add(this.func);
    }

    public float goToNearestReturn() {
        ReturnCube retValue;
        do {
            step();
            retValue = hasReturnValue();
        } while (!stack.isEmpty() && retValue == null);
        if (retValue != null && !retValue.isYield()) {this.reset();}
        return retValue == null ? 0f : retValue.getData();
    }

    public ReturnCube hasReturnValue() {
        for (LogicalCode code : stack) {
            if (code instanceof ReturnCube r) return r;
        }
        return null;
    }

    public void step() {
        if (stack.isEmpty()) return;
        ArrayList<LogicalCode> result = new ArrayList<>();
        for (LogicalCode code : stack) {
            code.update();
            if (code instanceof Adapter adapter) {
                if (adapter.shouldPause() && !adapter.detectLetPassAndReset())
                    result.add(code);
                else
                    result.addAll(code.getNext());
            } else if (code instanceof LogicalSwitch logicalSwitch) {
                result.addAll(logicalSwitch.getCorrectNext());
            } else {
                result.addAll(code.getNext());
            }
        }
        stack.clear();
        stack.addAll(result);
    }

    @Override
    public LogicalCode copy(Namespace namespace) {
        return new FunctionCube((FunctionInputCube) func.copy(namespace));
    }
}
