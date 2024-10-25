package interpreter.simple_logic.adapter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import interpreter.compute.data.Namespace;
import interpreter.compute.data.Variable;
import interpreter.simple_logic.code_cube.LogicalCode;

import java.util.*;

public abstract class Adapter implements LogicalCode {
    private final List<LogicalCode> next;
    private final boolean shouldPause;
    public final AdapterType adapterType;

    public final Namespace namespace;
    private boolean letPass;
    private final HashMap<String, Float> cache;
    protected String[] varName;

    public Adapter(AdapterType type, Namespace namespace, boolean shouldPause, String... varName) {
        next = Lists.newArrayList();
        this.namespace = namespace;
        this.shouldPause = shouldPause;
        adapterType = type;
        this.cache = Maps.newHashMap();
        this.varName = varName;
        letPass = false;
    }

    @Override
    public List<LogicalCode> getNext() {
        return next;
    }

    public boolean shouldPause() {
        return shouldPause;
    }

    public boolean canLetPass() {
        return letPass;
    }

    public boolean detectLetPassAndReset() {
        if (letPass) {
            letPass = false;
            return true;
        }
        return false;
    }

    @Override
    public float getData() {
        return 0;
    }

    @Override
    public void update() {
        letPass = onUpdate(this);
    }

    public abstract boolean onUpdate(Adapter namespace);

    @Override
    public boolean readOnly() {
        return false;
    }

    @Override
    public boolean writeOnly() {
        return false;
    }

    protected HashMap<String, Float> getCache() {
        return cache;
    }

    protected Iterator<Map.Entry<String, Float>> cacheIterator() {
        return cache.entrySet().iterator();
    }

    protected Float get(String key) {
        return getCache().getOrDefault(key, 0F);
    }

    protected void set(String key, Float value) {
        this.getCache().put(key, value);
    }

    protected void setValues() {
        this.getCache().forEach(
                (name, value) -> {
                    if (namespace.containsInstance(name))
                        namespace.assign(name, value);
                    else
                        namespace.registerInstance(name, new Variable(name, namespace, value));
                }
        );
    }

    protected void getValues() {
        getCache().clear();
        for (String s : varName) {
            float var = namespace.containsInstance(s) ? namespace.getInstance(s).getValue(s) : 0f;
            getCache().put(s, var);
        }
    }
}
