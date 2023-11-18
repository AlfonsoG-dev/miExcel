package Operations;
public record MenuOperation(String fileText, String sentence) {
    public int AssignOperation() {
        String kind = sentence;
        if(kind.contains("*")) {
            return Operator.Multiplicar(fileText, sentence);
        }
        if(kind.contains("/")) {
            return (int)Operator.Dividir(fileText, sentence);
        }
        if(kind.contains("+")) {
            return Operator.Sumar(fileText, sentence);
        }
        if(kind.contains("-")) {
            return Operator.Restar(fileText, sentence);
        } else {
            return 0;
        }
    }
    public int AssigOneOperation() {
        String kind = sentence;
        if(kind.contains("*")) {
            return Operator.Multiplicar(fileText, sentence.replace("*", ""));
        }
        if(kind.contains("/")) {
            return (int)Operator.Dividir(fileText, sentence.replace("/", ""));
        }
        if(kind.contains("+")) {
            return Operator.Sumar(fileText, sentence.replace("+", ""));
        }
        if(kind.contains("-")) {
            return Operator.Restar(fileText, sentence.replace("-", ""));
        } else {
            return 0;
        }
    }
}


