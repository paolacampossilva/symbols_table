package symbols_table.symbols;

import symbols_table.*;

//Parameter não pode ser um Parameter[], sugestao: usar Set ou outra coleção de Util
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

    public Parameter[] getParameters() {
        return parameter;
    }
}
