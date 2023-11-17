package Operations;
public record Operator() {
    
    /**
     * columns declare on top of the file 
     */
    public static String getColumns(String fileText) {
        String[] fileLines = fileText.split("\n");
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
    public static String getRowsOfColumn(String fileText, String column) {
        String[] fileLines = fileText.split("\n");
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
    public static int Sumar(String fileText, String sentences) {
        int res = 0, sum = 0;
        if(sentences.contains("(") && sentences.contains(")")) {
            String[] prioridad = sentences.split("\\(");
            String another = prioridad[prioridad.length-1].replace(")", "");
            MenuOperation miMenu = new MenuOperation(fileText, another);
            sum = miMenu.AssignOperation();
            if(prioridad[0].contains("+")) {
                String[] spaces = prioridad[0].replace("+", "").split("");
                String col = spaces[0];
                int p = Integer.parseInt(spaces[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + col + ">").split("\n");
                res = Integer.parseInt(rows[p].replaceAll("[<>]", "")) + sum;
            }
        } else {
            String[] spaces = sentences.split("\\+");
            for(String s: spaces) {
                String[] data = s.split("");
                String co = data[0];
                int p = Integer.parseInt(data[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                sum += Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                res = sum;
            }
        }
        System.out.println(sentences + "=" + res);
        return res;
    }
    public static int Multiplicar(String fileText, String sentences) {
        int res = 0, mult = 1;
        if(sentences.contains("(") == true && sentences.contains(")")) {
            String[] prioridad = sentences.split("\\(");
            String another = prioridad[prioridad.length-1].replace(")", "");
            MenuOperation miMenu = new MenuOperation(fileText, another);
            mult = miMenu.AssignOperation();
            if(prioridad[0].contains("*")) {
                String[] spaces = prioridad[0].replace("*", "").split("");
                String col = spaces[0];
                int p = Integer.parseInt(spaces[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + col + ">").split("\n");
                res = Integer.parseInt(rows[p].replaceAll("[<>]", "")) * mult;
            }
        } else {
            String[] spaces = sentences.split("\\*");
            for(String s: spaces) {
                String[] data = s.split("");
                String co = data[0];
                int p = Integer.parseInt(data[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                mult *= Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                res = mult;
            }
        }
        System.out.println(sentences + "=" + res);
        return res;
    }
    public static float Dividir(String fileText, String sentences) {
        int div = 1;
        float res = 0;
        String nums = "";
        if(sentences.contains("(") == true && sentences.contains(")") == true) {
            String[] prioridad = sentences.split("\\(");
            String another = prioridad[prioridad.length-1].replace(")", "");
            MenuOperation miMenu = new MenuOperation(fileText, another);
            div = miMenu.AssignOperation();
            if(prioridad[0].contains("/")) {
                String[] spaces = prioridad[0].replace("/", "").split("");
                String col = spaces[0];
                int p = Integer.parseInt(spaces[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + col + ">").split("\n");
                res = Integer.parseInt(rows[p].replaceAll("[<>]", "")) / div;
            }
        } else {
            String[] spaces = sentences.split("\\/");
            for(String s: spaces) {
                String[] data = s.split("");
                String co = data[0];
                int p = Integer.parseInt(data[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                div = Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                nums += div + "\n";
            }
            String[] mNums = nums.split("\n");
            int inicial = 0, siguiente = 0;
            for(int i=1; i<mNums.length; ++i) {
                inicial = Integer.parseInt(mNums[0]);
                siguiente += inicial / Integer.parseInt(mNums[i]);
                res = siguiente/mNums.length-1;
            }
        }
        System.out.println(sentences + "=" + res);
        return res;
    }
    public static int Restar(String fileText, String sentences) {
        String[] spaces = sentences.split("\\-");
        int div = 1;
        int res = 0;
        String nums = "";
        for(String s: spaces) {
            String[] data = s.split("");
            String co = data[0];
            int p = Integer.parseInt(data[1])-1;
            String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
            div = Integer.parseInt(rows[p].replaceAll("[<>]", ""));
            nums += div + "\n";
        }
        String[] mNums = nums.split("\n");
        int inicial = 0, siguiente = 0;
        for(int i=1; i<mNums.length; ++i) {
            inicial = Integer.parseInt(mNums[0]);
            siguiente += Integer.parseInt(mNums[i]);
        }
        res = inicial - siguiente;
        System.out.println(sentences + "=" + res);
        return res;
    }
}
