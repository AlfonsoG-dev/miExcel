import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class miExcel {
    public static void main(String[] args) {
        String path = "./docs/miExcelFile.txt";
        performOperations(path);
    }
    static String readFileText(String filePath) {
        String fileLines = "";
        BufferedReader miBufferedReader = null;
        try {
            File miFile = new File(filePath);
            if(miFile.exists()) {
                miBufferedReader = new BufferedReader(new FileReader(miFile.getCanonicalPath()));
                while(miBufferedReader.read() != -1) {
                    fileLines += miBufferedReader.readLine() + "\n";
                }
            } else {
                throw new Exception("file not found");
            }
        } catch(Exception e) {
            System.err.println(e);
        } finally {
            if(miBufferedReader != null) {
                try {
                    miBufferedReader.close();
                } catch(Exception e) {
                    System.err.println(e);
                }
                miBufferedReader = null;
            }
        }
        return fileLines;
    }
    /**
     * columns declare on top of the file 
     */
    static String getColumns(String filePath) {
        String[] fileLines = readFileText(filePath).split("\n");
        String columns ="";
        String[] co = fileLines[0].split("\\.");
        for(String c: co) {
            columns += c + "\n";
        }
        return columns;
    }
    /**
     * last column have the operations between prev columns
     */
    static String getRowsOfColumn(String filePath, String column) {
        String[] fileLines = readFileText(filePath).split("\n");
        String rows = "";
        String[] columns = fileLines[0].split("\\.");
        for(int i=0; i<columns.length; ++i) {
            if(columns[i].equals(column)) {
                for(int j=1; j<fileLines.length; ++j) {
                    rows += fileLines[j].split("\\.")[i]  + "\n";
                }
            }
        }
        return rows;
    }
    /**
     */
    static String getOperations(String filePath) {
        String operation = "";
        String[] fileLines = readFileText(filePath).split("\n");
        for(int i=1; i<fileLines.length; ++i) {
            String[] rows = fileLines[i].split("\\.");
            String row = rows[rows.length-1].replaceAll("[<.>=]", "");
            String[] op = row.split("");
            for(int o=0; o<op.length; ++o) {
                if(op[o].matches("[*+-/]")) {
                    operation += op[o-2].concat(op[o-1]) + op[o] + op[o+1].concat(op[o+2]) + "\n";
                }
            }
        }
        return operation;
    }
    /**
     */
    static void performOperations(String filePath) {
        String[] operations = getOperations(filePath).split("\n");
        for(int i=0; i<operations.length; ++i) {
            System.out.print(operations[i] + " == ");
            String[] op = operations[i].split("");
            String[] rowsOfColumn = getRowsOfColumn(filePath, "<" + op[0] + ">").split("\n");
            String[] rowsOfColumn2 = getRowsOfColumn(filePath, "<" + op[3] + ">").split("\n");
            int position = Integer.parseInt(op[1])-1;
            int position2 = Integer.parseInt(op[op.length-1])-1;
            String operador = op[2];
            switch(operador) {
                case "+":
                    System.out.print(rowsOfColumn[position] + operador + rowsOfColumn2[position2] + ":");
                    System.out.println(Integer.parseInt(rowsOfColumn[position].replaceAll("[<.>]", "")) + 
                            Integer.parseInt(rowsOfColumn2[position2].replaceAll("[<.>]", "")));
                    break;
                case "-":
                    System.out.print(rowsOfColumn[position] + operador + rowsOfColumn2[position2] + ":");
                    System.out.println(Integer.parseInt(rowsOfColumn[position].replaceAll("[<.>]", "")) - 
                            Integer.parseInt(rowsOfColumn2[position2].replaceAll("[<.>]", "")));
                    break;
                case "*":
                    System.out.print(rowsOfColumn[position] + operador + rowsOfColumn2[position2] + ":");
                    System.out.println(Integer.parseInt(rowsOfColumn[position].replaceAll("[<.>]", "")) * 
                            Integer.parseInt(rowsOfColumn2[position2].replaceAll("[<.>]", "")));
                    break;
                case "/":
                    System.out.print(rowsOfColumn[position] + operador + rowsOfColumn2[position2] + ":");
                    System.out.println(Integer.parseInt(rowsOfColumn[position].replaceAll("[<.>]", "")) / 
                            Integer.parseInt(rowsOfColumn2[position2].replaceAll("[<.>]", "")));
                    break;
                default:
                    System.out.println("no operation know");
                    break;
            }
        }
    }
}
