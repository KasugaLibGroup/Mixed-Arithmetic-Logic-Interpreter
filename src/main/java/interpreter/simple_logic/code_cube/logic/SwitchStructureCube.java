package interpreter.simple_logic.code_cube.logic;

import com.google.common.collect.Lists;
import interpreter.compute.data.Namespace;
import interpreter.compute.data.Variable;
import interpreter.logic.infrastructure.LogicalData;
import interpreter.simple_logic.code_cube.LogicalCode;
import interpreter.simple_logic.code_cube.LogicalSwitch;

import java.util.List;

// 分支语句(if)
public class SwitchStructureCube implements LogicalCode, LogicalSwitch {
    private final List<LogicalCode> next;
    private final Namespace namespace;
    private LogicalData formula;
    private String varName;
    private float cache;
    public SwitchStructureCube(Namespace namespace, String formula, String varName) {
        this.next = Lists.newArrayList();
        this.namespace = namespace;
        this.formula = namespace.decodeLogical(formula);
        this.namespace.registerInstance(varName, new Variable(varName, namespace, 0f));
        this.varName = varName;
    }

    public void initOutput() {
        this.next.clear();
        this.next.add(new PlugCube()); // true
        this.next.add(new PlugCube()); // false
        this.next.add(new PlugCube()); // var_output
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
        this.namespace.registerInstance(varName, new Variable(varName, namespace, 0f));
    }

    public void setFormula(String formula) {
        this.formula = namespace.decodeLogical(formula);
    }

    @Override
    public List<LogicalCode> getNext() {
        return next;
    }

    @Override
    public void update() {
        this.cache = formula.getResult() ? 1 : 0;
        namespace.assign(varName, cache);
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
    public SwitchStructureCube copy(Namespace namespace) {
        return new SwitchStructureCube(namespace, this.formula.toString(), this.varName);
    }

    @Override
    public List<LogicalCode> getCorrectNext() {
        return List.of(cache == 0f ? next.get(0) : next.get(1));
    }
}
