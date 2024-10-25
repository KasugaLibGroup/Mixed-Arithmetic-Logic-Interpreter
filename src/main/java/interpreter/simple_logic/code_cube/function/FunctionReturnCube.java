package interpreter.simple_logic.code_cube.function;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.code_cube.logic.EvaluationCube;

public class FunctionReturnCube extends EvaluationCube implements ReturnCube {
    public FunctionReturnCube(Namespace namespace, String formula) {
        super("return_value", namespace, formula);
    }

    @Override
    public boolean isYield() {
        return false;
    }
}
