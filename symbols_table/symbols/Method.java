package symbols_table.symbols;

import symbols_table.*;

public class Method extends Symbol {
    private Type returnType;
    private Parameter[] parameter;

    public Method(String name) {
        super(name);
        parameter = null;
        returnType = null;
    }

    public Method(String name, Parameter[] parameter) {
        super(name);
        this.parameter = parameter;
    }

    public Type getReturnType() {
        return returnType;
    }

    public Parameter[] getParameters() {
        return parameter;
    }
}
