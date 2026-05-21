package symbols_table;

public enum Modifier {
    PUBLIC, PROTECTED, PRIVATE, STATIC, ABSTRACT, FINAL;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

} // Modifier
