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
                 sum = OperationUtils.setPriorityValueOfSum(fileText, p, sum);
             }
         } else {
             String[] spaces = sentences.split("\\+");
             for(String s: spaces) {
                 String[] data = s.split("");
                 String co = data[0];
                 int p= Integer.parseInt(data[1])-1;
                 String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                 if(rows[p].contains("=")) {
                     String operate = rows[p].replaceAll("[<>=]", "");
                     sum += new MenuOperation(fileText, operate).AssignOperation();
                 } else {
                     sum += Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                 }
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
            for(String p: prioridad) {
                mult = OperationUtils.setPriorityValueOfMult(fileText, p, mult);
            }
        } else {
            String[] spaces = sentences.split("\\*");
            for(String s: spaces) {
                String[] data = s.split("");
                String co = data[0];
                int p = Integer.parseInt(data[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                if(rows[p].contains("=")) {
                    String operate = rows[p].replaceAll("[<>=]", "");
                    mult *= new MenuOperation(fileText, operate).AssignOperation();
                } else {
                    mult *= Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                }
            }
        }
        res = mult;
        System.out.println(sentences + "==" + res);
        return res;
    }
    public static double Dividir(String fileText, String sentences) {
        double result = 0, div = 1, inicial = 0;
        if(sentences.contains("(")) {
            String[] prioridad = sentences.split("\\(");
            for(String p: prioridad) {
                String[] another = p.split("\\)");
                if(another[0].length() > 3) {
                    inicial = new MenuOperation(fileText, another[0]).AssignOperation();
                }
                if(another[0].length() == 3) {
                    div = new MenuOperation(fileText, another[0]).AssigOneOperation();
                }
                if(1 < another.length && another[1].length() > 3) {
                    String operar = another[1].substring(1, another[1].length());
                    div /= new MenuOperation(fileText, operar).AssignOperation();
                }
                if(1 < another.length && another[1].length() == 3) {
                    div /= new MenuOperation(fileText, another[1]).AssigOneOperation();
                }
            }
            div /= inicial;
        } else {
            String[] spaces = sentences.split("\\/");
            for(int i=0; i<spaces.length; ++i) {
                String s = spaces[i];
                String[] data = s.split("");
                String co = data[0];
                int p = Integer.parseInt(data[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                if (rows[p].contains("=")) {
                    String operate = rows[p].replaceAll("[<>=]", "");
                    inicial = new MenuOperation(fileText, operate).AssignOperation();
                } else {
                    if(sentences.length() > 3) {
                        if(i == 0) {
                            inicial = Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                        } else {
                            div = inicial / Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                            inicial = Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                        }
                    } else {
                        div = Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                    }
                }
            }
        }
        result = div;
        System.out.println(sentences + "==" + result);
        return result;
    }
    public static int Restar(String fileText, String sentences) {
        int resolve = 0, rest = 0, inicial = 0;
        if(sentences.contains("(")) {
            String[] prioridad = sentences.split("\\(");
            for(String p: prioridad) {
                String[] another = p.split("\\)");
                if(another[0].length() > 3) {
                    inicial = new MenuOperation(fileText, another[0]).AssignOperation();
                }
                if(another[0].length() == 3) {
                    rest = new MenuOperation(fileText, another[0]).AssigOneOperation();
                }
                if(1 < another.length && another[1].length() > 3) {
                    String operar = another[1].substring(1, another[1].length());
                    rest -= new MenuOperation(fileText, operar).AssignOperation();
                }
                if(1 < another.length && another[1].length() == 3) {
                    rest -= new MenuOperation(fileText, another[1]).AssigOneOperation();
                }
            }
            rest -= inicial;
        } else {
            String[] spaces = sentences.split("\\-");
            for(int i=0; i<spaces.length; ++i) {
                String s = spaces[i];
                String[] data = s.split("");
                String co = data[0];
                int p = Integer.parseInt(data[1])-1;
                String[] rows = getRowsOfColumn(fileText, "<" + co + ">").split("\n");
                if(rows[p].contains("=")) {
                    String operate = rows[p].replaceAll("[<>=]", "");
                    inicial = new MenuOperation(fileText, operate).AssignOperation();
                } else {
                    if(sentences.length() > 3) {
                        if(i == 0) {
                            inicial = Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                        } else {
                            rest = inicial - Integer.parseInt(rows[p].replaceAll("[<>]", ""));
                            inicial = Integer.parseInt(rows[p].replaceAll("[<>]", ""));;
                        }
                    } else {
                        rest = Integer.parseInt(rows[p].replaceAll("[<>]", ""));;
                    }
                }
            }
        }
        resolve = rest;
        System.out.println(sentences + "==" + resolve);
        return resolve;
    }
}
