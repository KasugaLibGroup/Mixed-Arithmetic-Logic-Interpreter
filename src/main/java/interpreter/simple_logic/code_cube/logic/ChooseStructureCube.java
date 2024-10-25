package interpreter.simple_logic.code_cube.logic;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import interpreter.Pair;
import interpreter.compute.data.Namespace;
import interpreter.compute.data.Variable;
import interpreter.compute.infrastructure.Formula;
import interpreter.simple_logic.code_cube.LogicalCode;
import interpreter.simple_logic.code_cube.LogicalSwitch;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 增强分之语句(选择语句)
public class ChooseStructureCube implements LogicalCode, LogicalSwitch {
    private final HashMap<LogicalCode, Pair<Float, Float>> ranges;
    private Pair<Float, Float> defaultRange;
    private final Namespace namespace;
    private Formula formula;
    private final List<LogicalCode> logicalNext;
    private String varName;
    float cachedValue;
    public ChooseStructureCube(Namespace namespace, String varName, String formula, Pair<Float, Float> defaultRange) {
        ranges = Maps.newHashMap();
        this.namespace = namespace;
        this.formula = namespace.decodeFormula(formula);
        this.defaultRange = defaultRange;
        logicalNext = Lists.newArrayList();
        namespace.registerInstance(varName, new Variable(varName, namespace, 0f));
        this.varName = varName;
    }

    @Override
    public List<LogicalCode> getNext() {
        ArrayList<LogicalCode> result = new ArrayList<>();
        result.addAll(logicalNext);
        return result;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName = varName;
        namespace.registerInstance(varName, new Variable(varName, namespace, 0f));
    }

    public void setFormula(String formula) {
        this.formula = namespace.decodeFormula(formula);
    }

    public List<LogicalCode> getLogicalNext() {
        return logicalNext;
    }

    @Override
    @Deprecated
    public void addNext(LogicalCode logicalCode) {}

    public void addLogicalNext(LogicalCode logicalCode) {
        this.logicalNext.add(logicalCode);
    }

    @Override
    public boolean removeNext(LogicalCode object) {
        logicalNext.remove(object);
        ranges.remove(object);
        return true;
    }

    @Override
    public void update() {
        this.cachedValue = formula.getResult();
        namespace.assign(this.varName, cachedValue);
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
        return cachedValue;
    }

    @Override
    public ChooseStructureCube copy(Namespace namespace) {
        return null;
    }

    public void setRange(LogicalCode code, Pair<Float, Float> range) {
        ranges.put(code, range);
    }

    public @Nullable Pair<Float, Float> getRange(LogicalCode code) {
        return ranges.getOrDefault(code, null);
    }

    public void setDefaultRange(Pair<Float, Float> defaultRange) {
        this.defaultRange = defaultRange;
    }

    public Pair<Float, Float> getDefaultRange() {
        return defaultRange;
    }

    public List<LogicalCode> getCorrectNext() {
        float data = getData();
        ArrayList<LogicalCode> result = new ArrayList<>();
        for (LogicalCode code : logicalNext) {
            Pair<Float, Float> r = ranges.get(code);
            if (r.getFirst() <= data && r.getSecond() > data) result.add(code);
        }
        return result;
    }
}
