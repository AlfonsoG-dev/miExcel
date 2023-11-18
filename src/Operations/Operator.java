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
         int result = 0, sum = 0;
         if(sentences.contains("(")) {
             String[] llaves = sentences.split("\\(");
             for(String p: llaves) {
                 if(p.contains(")")) {
                     String[] another = p.split("\\)");
                     if(another[0].length() > 3) {
                         sum += new MenuOperation(fileText, another[0]).AssignOperation();
                     }
                     if(1 < another.length && another[1].length() >= 3) {
                         sum += new MenuOperation(fileText, another[1]).AssigOneOperation();
                     }
                 } else {
                     String operar = p.substring(0, p.length()-1);
                     sum += new MenuOperation(fileText, operar).AssignOperation();
                 }
             }
         } else {
             String[] spaces = sentences.split("\\+");
             for(String s: spaces) {
                 String[] data = s.split("");
                 String co = data[0];
                 int p= Integer.parseInt(data[1])-1;
                 String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                 sum += Integer.parseInt(rows[p].replaceAll("[<>]", ""));
             }
         }
         result += sum;
         System.out.println(sentences + "==" + result);
         return result;
    }
    public static int Multiplicar(String fileText, String sentences) {
        int res = 0, mult = 1;
        if(sentences.contains("(") == true && sentences.contains(")")) {
            String[] prioridad = sentences.split("\\(");
            String another = "";
            for(String p: prioridad) {
                String[] ap = p.split("\\)");
                if(ap.length > 1) {
                    another = ap[1];
                } else if(ap[0].split("").length <= 3) {
                    another = ap[0];
                } else if(ap[0].split("").length >= 8) {
                    mult = new MenuOperation(fileText, ap[0]).AssignOperation();
                }
                MenuOperation miMenu = new MenuOperation(fileText, ap[0]);
                mult = miMenu.AssignOperation();
            }
            if(another.contains("*")) {
                String[] spaces = another.replace("*", "").split("");
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
            String another = "";
            for(String p: prioridad) {
                String[] ap = p.split("\\)");
                if(ap.length > 1) {
                    another = ap[1];
                } else if(ap[0].split("").length <= 3) {
                    another = ap[0];
                } else if(ap[0].split("").length >= 8) {
                    div = new MenuOperation(fileText, ap[0]).AssignOperation();
                }
                MenuOperation miMenu = new MenuOperation(fileText, ap[0]);
                div = miMenu.AssignOperation();
            }
            if(another.contains("/")) {
                String[] spaces = another.replace("/", "").split("");
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
        int rest = 1;
        int res = 0;
        String nums = "";
        if(sentences.contains("(") == true && sentences.contains(")") == true) {
            String[] prioridad = sentences.split("\\(");
            String another = "";
            for(String p: prioridad) {
                String[] ap = p.split("\\)");
                if(ap.length > 1) {
                    another = ap[1];
                } else if(ap[0].split("").length <=3) {
                    another = ap[0];
                } else if(ap[0].split("").length >= 8) {
                    rest = new MenuOperation(fileText, ap[0]).AssignOperation();
                }
                MenuOperation miMenu = new MenuOperation(fileText, ap[0]);
                rest = miMenu.AssignOperation();
            }
            if(another.contains("-")) {
                String[] spaces = another.replace(")", "").split("");
                String col = spaces[0];
                int p = Integer.parseInt(spaces[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + col + ">").split("\n");
                res = Integer.parseInt(rows[p].replaceAll("[<>]", "")) - rest;
            }
        } else {
            String[] spaces = sentences.split("\\-");
            for(String s: spaces) {
                String[] data = s.split("");
                String co = data[0];
                int p = Integer.parseInt(data[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                rest = Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                nums += rest + "\n";
            }
            String[] mNums = nums.split("\n");
            int inicial = 0, siguiente = 0;
            for(int i=1; i<mNums.length; ++i) {
                inicial = Integer.parseInt(mNums[0]);
                siguiente += Integer.parseInt(mNums[i]);
            }
            res = inicial - siguiente;
        }
        System.out.println(sentences + "=" + res);
        return res;
    }
}
