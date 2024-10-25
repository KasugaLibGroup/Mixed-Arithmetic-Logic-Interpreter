module Mixed.Arithmetic.Logic.Interpreter {
    requires com.google.common;
    requires jsr305;
    exports interpreter;

    exports interpreter.compute.data;
    exports interpreter.compute.infrastructure;
    exports interpreter.compute.exceptions;
    exports interpreter.compute.data.functions;

    exports interpreter.logic.data;
    exports interpreter.logic.infrastructure;
    exports interpreter.logic.operations;
    exports interpreter.logic.data.functions;
}