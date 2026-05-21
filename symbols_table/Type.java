package symbols_table;

import symbols_table.symbols.*;

public class Type {
    // Enum
    public enum PrimitiveType {
        INT, FLOAT, VOID 
    }

    // Atributos
    private PrimitiveType primitiveType;
    private ClassSymbol classType;
    private boolean isPrimitive; 

    // Construtores
    public Type(PrimitiveType type) {
        this.primitiveType = type;
        this.isPrimitive = true;
    }

    public Type(ClassSymbol classType) {
        this.classType = classType;
        this.isPrimitive = false;
    }

    // Métodos
    public boolean isPrimitive() {
        return isPrimitive;
    }

    public PrimitiveType getPrimitiveType() {
        return primitiveType;
    }

    public ClassSymbol getClassType() {
        return classType;
    }
    
    @Override
    public String toString() {
        if (isPrimitive) {
            return primitiveType.toString();
        } 
        else {
            return classType.getName();
        }
    }

} // Type
