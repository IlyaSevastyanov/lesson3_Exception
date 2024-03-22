package exceptionsForProject;

public class IncorrectLength extends NegativeArraySizeException {

    public IncorrectLength() {
        super("Введено больше или меньше данных");
    }

    public IncorrectLength(String s) {
        super(s);
    }

}