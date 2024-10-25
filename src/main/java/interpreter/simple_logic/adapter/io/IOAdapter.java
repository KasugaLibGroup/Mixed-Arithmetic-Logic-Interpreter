package interpreter.simple_logic.adapter.io;

import interpreter.compute.data.Namespace;
import interpreter.simple_logic.adapter.Adapter;
import interpreter.simple_logic.adapter.AdapterType;
import interpreter.simple_logic.adapter.Input;

public abstract class IOAdapter extends Adapter implements Input {
    private boolean dirty;
    public IOAdapter(Namespace namespace, boolean shouldPause, String... varName) {
        super(AdapterType.IO, namespace, shouldPause, varName);
    }

    public Float get(String key) {
        return super.get(key);
    }

    @Override
    protected void set(String key, Float value) {
        super.set(key, value);
    }

    public boolean isDirty() {
        return dirty;
    }

    public void markDirty() {
        this.dirty = true;
    }

    @Override
    public void update() {
        super.getValues();
        super.update();
        if (isDirty()) super.setValues();
        dirty = false;
    }
}
