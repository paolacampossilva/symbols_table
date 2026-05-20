package symbols_table;

public class Type {
    private String name;

    // Construtor
    Type() {
    };

    Type(String name) {
        this.name = name;
    }

    // Copia
    Type(Type x) {
        this.name = x.name;
    }

    // Métodos
    public String id() {
        return name;
    }
}// Type
