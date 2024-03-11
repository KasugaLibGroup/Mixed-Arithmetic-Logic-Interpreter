package interpreter.compute.data.functions;

import interpreter.compute.data.Namespace;
import interpreter.compute.infrastructure.Formula;

public class NoParamFunction extends Function {
    final Computer computer;

    public NoParamFunction(String codec, Namespace namespace, Computer computer) {
        super(codec, namespace);
        this.computer = computer;
    }

    @Override
    public float operate() {
        return computer.getResult();
    }

    @Override
    public Function clone(Namespace namespace) {
        return new NoParamFunction(this.codec, namespace, this.computer);
    }

    @Override
    public NoParamFunction clone() {
        return new NoParamFunction(codec, getNamespace(), computer);
    }

    public interface Computer {
        float getResult();
    }
}
