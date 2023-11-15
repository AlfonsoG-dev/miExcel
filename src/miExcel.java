import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

class miExcel {
    public static void main(String[] args) {
        String path = "./docs/miExcelFile.txt";
        getColumns(path);
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
    static String getColumns(String filePath) {
        String[] fileLines = readFileText(filePath).split("\n");
        String columns ="";
        String[] co = fileLines[0].split("\\.");
        for(String c: co) {
            columns += c + "\n";
        }
        System.out.println(columns);
        return columns;
    }
}
