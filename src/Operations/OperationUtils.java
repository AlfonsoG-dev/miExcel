package Operations;

public record OperationUtils() {
    public static int setPriorityValueOfSum(String fileText, String partitions, int value) {
        if(partitions.contains(")")) {
            String[] another = partitions.split("\\)");
            if(another[0].length() > 3) {
                value += new MenuOperation(fileText, another[0]).AssignOperation();
            }
            if(1 < another.length && another[1].length() > 3) {
                String operar = partitions.substring(0, partitions.length()-1);
                value += new MenuOperation(fileText, operar).AssignOperation();
            }
            if(1 < another.length && another[1].length() == 3) {
                value += new MenuOperation(fileText, another[1]).AssigOneOperation();
            }
        } else {
            if(partitions.length() > 3) {
                String operar = partitions.substring(0, partitions.length()-1);
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
                String operar = partitions.substring(0, partitions.length()-1);
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
    public static int setPriorityValueOfDiv(String fileText, String partitions, int value) {
        if(partitions.contains(")")) {
            String[] another = partitions.split("\\)");
            if(another[0].length() > 3) {
                value = new MenuOperation(fileText, another[0]).AssignOperation();
            }
            if(1 < another.length && another[1].length() >= 3) {
                value = new MenuOperation(fileText, another[1]).AssigOneOperation();
            }
        } else {
            if(partitions.length() > 3) {
                String operar = partitions.substring(0, partitions.length()-1);
                value = new MenuOperation(fileText, operar).AssignOperation();
            } else {
                value = new MenuOperation(fileText, partitions).AssigOneOperation();
            }
        }
        return value;
    }
    public static int performDiv(String nums, int inicial) {
        int div = 1;
        String[] mNums = nums.split("\n");
        for(int i=1; i<mNums.length; ++i) {
            inicial = Integer.parseInt(mNums[0]);
            div = inicial / Integer.parseInt(mNums[i]);
        }
        return div;
    }
}
