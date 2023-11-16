import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class miExcel {
    public static void main(String[] args) {
        try {
            String path = "./docs/miExcelFile.txt";
            getOperations(path);
        } catch(Exception e) {
            System.err.println(e);
        }
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
    static void Sumar(String filePath, String operations, String mainOp) throws Exception {
        String[] spaces = operations.split("");
        String[] operador = new String[2];
        for(int i=0; i<spaces.length; ++i) {
        String mColumns = "";
        int position = 0;
            if(mainOp == "sumar") {
             mColumns = spaces[i].matches("[+^1-9]") == false ? spaces[i] : "";
             position = spaces[i].matches("[+^A-Z]") == false ? Integer.parseInt(spaces[i]): -1;
            } else {
                throw new Exception("not implemented yet");
            }
            if(mColumns != "") {
                operador[0] += mColumns + "\n";
            }
            if(position != -1) {
                operador[1] += position + "\n";
            }
        }
        String[] columns = operador[0].replace("null", "").split("\n");
        String[] position = operador[1].replace("null", "").split("\n");
        int res = 0;
        for(int i=0; i<columns.length; ++i) {
            String co = columns[i];
            int p = Integer.parseInt(position[i])-1;
            String[] rows = getRowsOfColumn(filePath, "<" + co + ">").split("\n");
            res += Integer.parseInt(rows[p].replaceAll("[<>]", ""));
        }
        System.out.println(operations.concat("=" + res));
    }
    /**
     * @throws Exception
     */
    static String getOperations(String filePath) throws Exception {
        String operation = "";
        String[] fileLines = readFileText(filePath).split("\n");
        for(int i=1; i<fileLines.length; ++i) {
            String[] rows = fileLines[i].split("\\.");
            String row = rows[rows.length-1].replaceAll("[<.>=]", "");
            String[] op = row.split("");
            for(int o=0; o<op.length; ++o) {
                if(op[o].matches("[+]") && op.length < 8) {
                    Sumar(filePath, row, "sumar");
                    //operation += op[o-2].concat(op[o-1]) + op[o] + op[o+1].concat(op[o+2]) + "\n";
                } else if(op.length >= 8) {
                    Sumar(filePath, row, "sumar");
                    break;
                }
            }
        }
        return operation;
    }
}
