package interpreter.simple_logic.code_cube.function;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.code_cube.logic.EvaluationCube;

public class FunctionYieldCube extends EvaluationCube implements ReturnCube {
    public FunctionYieldCube(Namespace namespace, String formula) {
        super("return_value", namespace, formula);
    }

    @Override
    public boolean isYield() {
        return true;
    }
}
