package interpreter.simple_logic.code_cube.logic;

import com.google.common.collect.Lists;
import interpreter.compute.data.Namespace;
import interpreter.compute.data.Variable;
import interpreter.compute.infrastructure.Formula;
import interpreter.simple_logic.code_cube.LogicalCode;

import java.util.List;

// 赋值语句
public class EvaluationCube implements LogicalCode {
    private String name;
    private final Namespace namespace;
    private Formula formula;
    private final List<LogicalCode> next;
    private float cache;

    public EvaluationCube(String name, Namespace namespace, String formula) {
        this.name = name;
        this.namespace = namespace;
        this.formula = namespace.decodeFormula(formula);
        Variable variable = new Variable(name, namespace, 0f);
        namespace.registerInstance(name, variable);
        next = Lists.newArrayList();
    }

    public void setFormula(String formula) {
        this.formula = namespace.decodeFormula(formula);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        Variable variable = new Variable(name, namespace, 0f);
        namespace.registerInstance(name, variable);
    }

    public Formula getFormula() {
        return formula;
    }

    @Override
    public List<LogicalCode> getNext() {
        return next;
    }

    @Override
    public void update() {
        this.cache = formula.getResult();
        namespace.assign(name, cache);
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
        return cache;
    }

    @Override
    public LogicalCode copy(Namespace namespace) {
        return new EvaluationCube(this.name, namespace, this.formula.getString());
    }
}
