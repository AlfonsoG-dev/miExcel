import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import Operations.Operator;

class miExcel {
    static String path = "./docs/miExcelFile.txt";
    public static void main(String[] args) {
        try {
            getOperations();
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
     * @throws Exception
     */
    static String getOperations() throws Exception {
        String operation = "";
        String fileText = readFileText(path);
        String[] fileLines = readFileText(path).split("\n");
        for(int i=1; i<fileLines.length; ++i) {
            String[] rows = fileLines[i].split("\\.");
            String row = rows[rows.length-1].replaceAll("[<.>=]", "");
            String[] op = row.split("");
            for(int o=0; o<op.length; ++o) {
                /*
                if(op[o].matches("[*]")) {
                    System.out.print("Resultado mult :" + "\n");
                    Operator.Multiplicar(fileText, row);
                    System.out.print("\n");
                    break;
                }
                if(op[o].matches("[\\/]")) {
                    System.out.print("Resultado div :" + "\n");
                    Operator.Dividir(fileText, row);
                    System.out.print("\n");
                    break;
                }
                */
                if(op[o].matches("[+]")) {
                    System.out.print("Resultado sum :" + "\n");
                    Operator.Sumar(fileText, row);
                    System.out.print("\n");
                    break;
                }
                /*
                if(op[o].matches("[\\-]")) {
                    System.out.print("Resultado rest :" + "\n");
                    Operator.Restar(fileText, row);
                    System.out.print("\n");
                    break;
                }
                */
            }
        }
        return operation;
    }
}
