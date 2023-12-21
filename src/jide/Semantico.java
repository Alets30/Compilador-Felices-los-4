package jide;

import java.util.Stack;
import java.util.HashMap;

public class Semantico {

    public boolean isAsign = false, isWhileOrIf = false, isElse = false;
    public String error = "";
    public String relationalOp = "";
    public int type, opType = -1, tempVar, tempVarIf, majorIf = 0, tempVarWhile, majorWhile = 0;
    public String asign = "";
    public String middleCode = "";
    //Tabla de operaciones semánticas
    private final int semTable[][] = {
        {0, 1, -1, -1},
        {1, 1, -1, -1},
        {-1, -1, -1, -1},
        {-1, -1, -1, -1}
    };
    private final int relationalTable1[][] = {
        {1, 1, -1, -1, -1},
        {1, 1, -1, -1, -1},
        {-1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1},
        {-1, -1, -1, -1, -1}
    };
    private final int relationalTable2[][] = {
        {1, 1, -1, -1, -1},
        {1, 1, -1, -1, -1},
        {-1, -1, 1, 1, -1},
        {-1, -1, 1, 1, -1},
        {-1, -1, -1, -1, -1}
    };
    private final HashMap<String, HashMap> sTable = new HashMap<>();
    public final HashMap<Integer, String> datatypes = new HashMap<>();
    private final HashMap<Integer, String> sentenceTypes = new HashMap<>();
    private final Stack<String> printStack;
    private final Stack<String> semStack;
    private final Stack<String> stackOp;
    private final Stack<String> expPosf;
    private final Stack<String> middleCodeStack;
    public final Stack<String> ifStack;
    public final Stack<String> whileStack;
    public final Stack<Integer> sentenceType;

    public Semantico() {
        semStack = new Stack();
        stackOp = new Stack();
        expPosf = new Stack();
        ifStack = new Stack();
        printStack = new Stack();
        middleCodeStack = new Stack();
        whileStack = new Stack();
        sentenceType = new Stack();
        datatypes.put(0, "int");
        datatypes.put(1, "float");
        datatypes.put(2, "char");
        datatypes.put(3, "litcad");
        sentenceTypes.put(0, "asig");
        sentenceTypes.put(1, "print");
        sentenceTypes.put(2, "if");
        sentenceTypes.put(3, "while");
        semStack.push("$");
        ifStack.push("$");
        stackOp.push("$");
        expPosf.push("$");
        whileStack.push("$");
        middleCodeStack.push("$");
        printStack.push("$");
        sentenceType.push(-1);
        tempVar = 0;
        tempVarIf = 0;
    }

    public void AddSymbol(String id, String value, int line) {
        HashMap<String, String> symbol = new HashMap<>();
        symbol.put("tipo", "" + type);
        symbol.put("valor", value);
        symbol.put("linea", "" + line);
        //System.out.println(id);
        sTable.put(id, symbol);
        middleCode += id + ";\n";
        //System.out.println(sTable);
    }

    public String SearchSymbol(String symbol, int line) {
        if (!sTable.containsKey(symbol)) {
            return "Error semantico en la linea " + line + " el elemento " + symbol + " no se ha declarado.\n";
        } else {
            return "";
        }
    }

    public void AddSemStack(String token, String originalToken, int line) {
        if (sentenceType.peek() == 1) {
            switch (token) {
                case "num":
                    middleCode += "%" + (RecognizeNumber(originalToken) == 1 ? "f" : "d");
                    break;
                case "litcar":
                    middleCode += "%c";
                    break;
                case "litcad":
                    middleCode += "%s";
                    break;
                default:
                    if (sTable.containsKey(originalToken)) {
                        middleCode += "%" + (Integer.parseInt("" + sTable.get(originalToken).get("tipo")) == 0 ? "d"
                                : Integer.parseInt("" + sTable.get(originalToken).get("tipo")) == 1 ? "f"
                                : Integer.parseInt("" + sTable.get(originalToken).get("tipo")) == 2 ? "c" : "s");
                    }
            }
            printStack.push(originalToken);
        } else {
            switch (token) {
                case "num":
                    semStack.push("" + RecognizeNumber(originalToken));
                    expPosf.push(originalToken);
                    //System.out.println(semStack);
                    break;
                case "litcar":
                    //System.out.println(token);
                    semStack.push("2");
                    expPosf.push(originalToken);
                    break;
                case "litcad":
                    if (isAsign) {
                        error += "Error semantico en la linea " + line + " tipos de dato incompatibles.\n";
                    } else {
                        semStack.push("3");
                        expPosf.push(originalToken);
                    }
                    break;
                default:
                    if (sTable.containsKey(originalToken)) {
                        semStack.push("" + sTable.get(originalToken).get("tipo"));
                        expPosf.push(originalToken);
                    } else {
                        error += "Error semantico en la linea " + line + " el elemento " + originalToken + " no se ha declarado.\n";
                        return;
                    }
            }
        }
    }

    public void AddOpStack(String token, int line) {
        int semTableResult = -1;
        //System.out.println(stackOp);

        switch (token) {
            case "+", "-":
                if (sentenceType.peek() == 1) {
                    error += "Error sintáctico en la linea " + line + " esperaba: id, litcad, litcar, num.\n";
                }
                if (semStack.peek().equals("$") && stackOp.peek().equals("(")) {
                    return;
                }
                if (stackOp.peek().equals("$") || stackOp.peek().equals("(")) {
                    stackOp.push(token);
                } else {
                    //System.out.println(stackOp);
                    do {
                        expPosf.push(stackOp.pop());
                        semTableResult = semTable[Integer.parseInt(semStack.pop())][Integer.parseInt(semStack.pop())];
                        if (semTableResult != -1) {
                            semStack.push("" + semTableResult);

                        } else {
                            error += "Error semantico en la linea " + line + " tipos de dato incompatibles.\n";
                        }
                    } while (stackOp.peek().equals("-") || stackOp.peek().equals("+"));
                    //System.out.println(semTableResult);
                    stackOp.push(token);
                }
                //System.out.println(stackOp);
                break;
            case "*", "/":
                //System.out.println(stackOp.peek());
                if (sentenceType.peek() == 1) {
                    error += "Error sintáctico en la linea " + line + " esperaba: id, litcad, litcar, num.\n";
                }
                if (stackOp.peek().equals("-") || stackOp.peek().equals("+") || stackOp.peek().equals("$") || stackOp.peek().equals("(")) {
                    stackOp.push(token);
                } else {
                    //System.out.println(semStack + " " + token);
                    do {
                        expPosf.push(stackOp.pop());
                    } while (!stackOp.peek().equals("-") && !stackOp.peek().equals("+") && !stackOp.peek().equals("$") && !stackOp.peek().equals("("));
                    System.out.println(stackOp);

                    semTableResult = semTable[Integer.parseInt(semStack.pop())][Integer.parseInt(semStack.pop())];
                    if (semTableResult != -1) {
                        semStack.push("" + semTableResult);
                        System.out.println(semStack.size());
                        stackOp.push(token);
                    } else {
                        error += "Error semantico en la linea " + line + " tipos de dato incompatibles.\n";
                    }
                }
                break;
            case "(":
                stackOp.push(token);
                //System.out.println(stackOp);
                break;
            case ")", ";":
                while (!stackOp.peek().equals("$") && !semStack.peek().equals("$")) {
                    //System.out.println(stackOp);
                    if (stackOp.peek().equals("(")) {
                        stackOp.pop();
                        break;
                    }
                    expPosf.push(stackOp.pop());
                    //System.out.println(semStack);

                    semTableResult = semTable[Integer.parseInt(semStack.pop())][Integer.parseInt(semStack.pop())];
                    if (semTableResult != -1) {
                        semStack.push("" + semTableResult);

                        //System.out.println(semTableResult);
                    } else {
                        error += "Error semantico en la linea " + line + " tipos de dato incompatibles.\n";
                        return;
                    }
                }
                if (semStack.size() == 2 && isAsign) {
                    if (semTable[Integer.parseInt("" + sTable.get(asign).get("tipo"))][Integer.parseInt(semStack.peek())] == -1
                            && !(sTable.get(asign).get("tipo").equals("2") && semStack.peek().equals("2"))) {
                        error += "Error de tipo en la línea " + line + " tipos de dato incompatibles.";
                    }
                }
                if (!isAsign && sentenceType.peek() < 1 && !semStack.peek().equals("$")) {
                    semStack.pop();
                }
                if (token.equals(";")) {
                    if (!semStack.peek().equals("$") && semStack.size() <= 2) {
                        semStack.pop();
                    } else {
                        error += "Error semantico en la linea " + line + " tipos de dato incompatibles.\n";
                        return;
                    }
                    FlipStack();
                    GenerateMiddleCode();
                    isAsign = false;
                }
                //System.out.println(expPosf);
                break;
        }
    }

    public void EndWhileOrIf(int line) {
        if (opType == 0) {
            if (relationalTable1[Integer.parseInt(semStack.pop())][Integer.parseInt(semStack.pop())] == -1) {
                error += "Error semantico en la linea " + line + " tipo de dato inválido.\n";
            } else {
                FlipStack();
                GenerateMiddleCode();
                switch (sentenceType.peek()) {
                    case 2 -> {
                        middleCode += "Vi1 = Vi1 " + relationalOp + " Vi2;\n";
                        middleCode += "if(!Vi1)\n";
                        middleCode += "goto Else" + ifStack.peek() + ";\n";
                    }
                    case 3 -> {
                        middleCode += "Vw1 = Vw1 " + relationalOp + " Vw2;\n";
                        middleCode += "if(!Vw1)\n";
                        middleCode += "goto Fin_While" + whileStack.peek() + ";\n";
                    }
                }
            }
        } else if (sentenceType.peek() >= 1 && sentenceType.peek() <= 3 && opType == -1 || relationalTable2[Integer.parseInt(semStack.pop())][Integer.parseInt(semStack.pop())] == -1) {
            error += "Error semantico en la linea " + line + " tipo de dato inválido.\n";
        } else {
            FlipStack();
            GenerateMiddleCode();
            middleCode += "V1 = V1 " + relationalOp + " V2;\n";
            switch (sentenceType.peek()) {
                case 2 -> {
                    middleCode += "if(!V1)\n";
                    middleCode += "goto Else" + ifStack.peek() + ";\n";
                }
            }
        }
        if (sentenceType.peek() >= 1 && sentenceType.peek() <= 3) {
            opType = -1;
        }
    }

    public void IdentifyOp(String token, int linea) {
        relationalOp = token;

        if (!isAsign && sentenceType.peek() != 1) {
            switch (token) {
                case ">", "<", ">=", "<=":
                    opType = 0;
                    break;
                default:
                    opType = 1;
            }
        } else {
            error += "Error semantico en la linea " + linea + " tipo de dato inválido.\n";
        }
    }

    public void AsignReadMiddleCode() {
        middleCode += "scanf(\"";
        switch ("" + sTable.get(asign).get("tipo")) {
            case "0" ->
                middleCode += "%d\", &" + asign + ");\n";
            case "1" ->
                middleCode += "%f\", &" + asign + ");\n";
            case "2" ->
                middleCode += "%c\", &" + asign + ");\n";
        }
    }

    public void AddExpPos(String token) {
        expPosf.push(token);
    }

    private int RecognizeNumber(String number) {
        return (number.matches("^[+-]?\\d*\\.\\d+([eE][+-]?\\d+)?$")) ? 1 : 0;
    }

    private void FlipStack() {
        //System.out.println(expPosf);
        while (!expPosf.peek().equals("$")) {
            middleCodeStack.push(expPosf.pop());
        }
    }
    
    public void EndPrint() {
        middleCode += "\", ";
        while (!printStack.peek().equals("$")) {
            ifStack.push(printStack.pop());
        }
        while (!ifStack.peek().equals("$")) {
            middleCode += ifStack.pop() + ((ifStack.size() == 1) ? "" : ", ");
        }
        middleCode += ");\n";
    }


    private void GenerateMiddleCode() {
        String middleCodeStackItem, variableString;
        switch (sentenceType.peek()) {
            case 2:
                if (!isAsign) {
                    while (!middleCodeStack.peek().equals("$")) {
                        middleCodeStackItem = middleCodeStack.pop();
                        //System.out.println(expPosf.size());
                        if ("*+-/".contains(middleCodeStackItem)) {
                            expPosf.pop();
                            expPosf.pop();
                            variableString = "Vi" + expPosf.size() + " = " + "Vi" + expPosf.size() + " " + middleCodeStackItem + " " + "Vi" + (expPosf.size() + 1) + ";\n";
                            middleCode += variableString;
                            expPosf.push(variableString);
                        } else {
                            if (expPosf.size() > tempVarIf) {
                                tempVarIf = expPosf.size();
                                middleCode += "float Vi" + tempVarIf + ";\n";
                            }

                            middleCode += "Vi" + expPosf.size() + " = " + middleCodeStackItem + ";\n";
                            expPosf.push(middleCodeStackItem);
                        }
                    }
                } else {
                    while (!middleCodeStack.peek().equals("$")) {
                        middleCodeStackItem = middleCodeStack.pop();
                        //System.out.println(expPosf.size());
                        if ("*+-/".contains(middleCodeStackItem)) {
                            expPosf.pop();
                            expPosf.pop();
                            variableString = "V" + expPosf.size() + " = " + "V" + expPosf.size() + " " + middleCodeStackItem + " " + "V" + (expPosf.size() + 1) + ";\n";
                            middleCode += variableString;
                            expPosf.push(variableString);
                        } else {
                            if (expPosf.size() > tempVar) {
                                tempVar = expPosf.size();
                                middleCode += "float V" + tempVar + ";\n";
                            }

                            middleCode += "V" + expPosf.size() + " = " + middleCodeStackItem + ";\n";
                            expPosf.push(middleCodeStackItem);
                        }
                    }
                }
                break;
            case 3:
                if (!isAsign) {
                    while (!middleCodeStack.peek().equals("$")) {
                        middleCodeStackItem = middleCodeStack.pop();
                        //System.out.println(expPosf.size());
                        if ("*+-/".contains(middleCodeStackItem)) {
                            expPosf.pop();
                            expPosf.pop();
                            variableString = "Vw" + expPosf.size() + " = " + "Vw" + expPosf.size() + " " + middleCodeStackItem + " " + "Vw" + (expPosf.size() + 1) + ";\n";
                            middleCode += variableString;
                            expPosf.push(variableString);
                        } else {
                            if (expPosf.size() > tempVarWhile) {
                                tempVarWhile = expPosf.size();
                                middleCode += "float Vw" + tempVarWhile + ";\n";
                            }

                            middleCode += "Vw" + expPosf.size() + " = " + middleCodeStackItem + ";\n";
                            expPosf.push(middleCodeStackItem);
                        }
                    }
                } else {
                    while (!middleCodeStack.peek().equals("$")) {
                        middleCodeStackItem = middleCodeStack.pop();
                        //System.out.println(expPosf.size());
                        if ("*+-/".contains(middleCodeStackItem)) {
                            expPosf.pop();
                            expPosf.pop();
                            variableString = "V" + expPosf.size() + " = " + "V" + expPosf.size() + " " + middleCodeStackItem + " " + "V" + (expPosf.size() + 1) + ";\n";
                            middleCode += variableString;
                            expPosf.push(variableString);
                        } else {
                            if (expPosf.size() > tempVar) {
                                tempVar = expPosf.size();
                                middleCode += "float V" + tempVar + ";\n";
                            }

                            middleCode += "V" + expPosf.size() + " = " + middleCodeStackItem + ";\n";
                            expPosf.push(middleCodeStackItem);
                        }
                    }
                }
                break;
            default:
                while (!middleCodeStack.peek().equals("$")) {
                    middleCodeStackItem = middleCodeStack.pop();
                    //System.out.println(expPosf.size());
                    if ("*+-/".contains(middleCodeStackItem)) {
                        expPosf.pop();
                        expPosf.pop();
                        variableString = "V" + expPosf.size() + " = " + "V" + expPosf.size() + " " + middleCodeStackItem + " " + "V" + (expPosf.size() + 1) + ";\n";
                        middleCode += variableString;
                        expPosf.push(variableString);
                    } else {
                        if (expPosf.size() > tempVar) {
                            tempVar = expPosf.size();
                            middleCode += "float V" + tempVar + ";\n";
                        }

                        middleCode += "V" + expPosf.size() + " = " + middleCodeStackItem + ";\n";
                        expPosf.push(middleCodeStackItem);
                    }
                }
        }
        if (isAsign) {
            middleCode += asign + " = " + "V1" + ";\n";
        }
        while (expPosf.size() > 1) {
            expPosf.pop();
        }
    }
}
