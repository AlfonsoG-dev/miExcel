package Operations;

public record OperationUtils() {
    public static int setPriorityValueOfSum(String fileText, String partitions, int value) {
        if(partitions.contains(")")) {
            String[] another = partitions.split("\\)");
            if(another[0].length() > 3) {
                value += new MenuOperation(fileText, another[0]).AssignOperation();
            }
            if(1 < another.length && another[1].length() > 3) {
                String operar = partitions.substring(1, partitions.length());
                value += new MenuOperation(fileText, operar).AssignOperation();
            }
            if(1 < another.length && another[1].length() == 3) {
                value += new MenuOperation(fileText, another[1]).AssigOneOperation();
            }
        } else {
            if(partitions.length() > 3) {
                String operar = partitions.substring(1, partitions.length());
                value += new MenuOperation(fileText, operar).AssignOperation();
            } else {
                value += new MenuOperation(fileText, partitions).AssigOneOperation();
            }
        }
        return value;
    }
    public static int setPriorityValueOfMult(String fileText, String partitions, int value) {

        if(partitions.contains(")")) {
            String[] another = partitions.split("\\)");
            if(another[0].length() > 3) {
                value *= new MenuOperation(fileText, another[0]).AssignOperation();
            }
            if(1 < another.length && another[1].length() > 3) {
                String operar = partitions.substring(1, partitions.length());
                value *= new MenuOperation(fileText, operar).AssignOperation();
            }
            if(1 < another.length && another[1].length() == 3) {
                value *= new MenuOperation(fileText, another[1]).AssigOneOperation();
            }
        } else {
            if(partitions.length() > 3) {
                String operar = partitions.substring(0, partitions.length()-1);
                value *= new MenuOperation(fileText, operar).AssignOperation();
            } else {
                value *= new MenuOperation(fileText, partitions).AssigOneOperation();
            }
        }
        return value;
    }
}
