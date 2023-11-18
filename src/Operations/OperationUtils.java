package Operations;

public record OperationUtils() {
    public static int setPriorityValueOfSum(String fileText, String partitions, int value) {
        if(partitions.contains(")")) {
            String[] another = partitions.split("\\)");
            if(another[0].length() > 3) {
                value += new MenuOperation(fileText, another[0]).AssignOperation();
            }
            if(1 < another.length && another[1].length() >= 3) {
                value += new MenuOperation(fileText, another[1]).AssigOneOperation();
            }
        } else {
            String operar = partitions.substring(0, partitions.length()-1);
            value += new MenuOperation(fileText, operar).AssignOperation();
        }
        return value;
    }
    public static int setPriorityValueOfMult(String fileText, String partitions, int value) {
        if(partitions.contains(")")) {
            String[] another = partitions.split("\\)");
            if(another[0].length() > 3) {
                value *= new MenuOperation(fileText, another[0]).AssignOperation();
            }
            if(1 < another.length && another[1].length() >= 3) {
                value *= new MenuOperation(fileText, another[1]).AssigOneOperation();
            }
        } else {
            String operar = partitions.substring(0, partitions.length()-1);
            value *= new MenuOperation(fileText, operar).AssignOperation();
        }
        return value;
    }
}
