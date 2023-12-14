package jide;

import java.util.Stack;

public class Sintactico {

    private int ip, it;
    //private boolean asign = false;
    private String result;
    public String error, originalToken, middleCode;
    private final String tnt[] = {"program", "idp", "{", "}", "print", "(", ")", "int", "float", "char", "read", "if", "then", "else", "while", ",", ";", "id", "num", "litcar", "litcad", "+", "-", "*", "/", "=", "<", ">", "!=", "==", "<=", ">=", "$", "Program",
        "bloque", "Sentencias", "SenSimple", "Dec", "restid", "Asig", "Asigrl", "lis_para", "Sigpara", "SenComp", "si", "restsi", "mientras", "R", "R'", "E", "E'", "T", "T'", "F"};
    private final String productions[] = {"Program'#Program", "Program#program idp ; Dec bloque", "Dec#int id restid ; Dec", "Dec#float id restid ; Dec", "Dec#char id restid ; Dec", "Dec#vacia", "bloque#{ Sentencias }", "restid#, id restid", "restid#vacia", "Sentencias#SenSimple Sentencias",
        "Sentencias#SenComp Sentencias", "Sentencias#vacia", "SenSimple#print ( lis_para )", "SenSimple#Asig ;", "SenComp#si", "SenComp#mientras", "lis_para#R Sigpara", "lis_para#vacia", "Sigpara#, R Sigpara", "Sigpara#vacia", "Asig#id = Asigrl", "Asigrl#R", "Asigrl#read ( )", "si#if ( R ) then bloque restsi",
        "restsi#else bloque", "restsi#vacia", "mientras#while ( R ) bloque", "R#E R'", "R'#< E", "R'#> E", "R'#!= E", "R'#<= E", "R'#>= E", "R'#== E", "R'#vacia", "E#+ T E'", "E#- T E'", "E#T E'", "E'#+ T E'", "E'#- T E'", "E'#vacia", "T#F T'", "T'#* F T'", "T'#/ F T'", "T'#vacia", "F#( R )", "F#id", "F#num", "F#litcar", "F#litcad"};
    private final String table[][] = {
        {"I2", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I1", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "P0", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "I3", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I4", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "P5", "", "", "", "", "I6", "I7", "I8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I5", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "I10", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I9", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I11", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I12", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I13", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "P1", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P11", "I17", "", "", "", "", "", "", "I22", "", "", "I23", "", "", "I21", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I14", "I15", "", "", "I18", "", "", "", "I16", "I19", "", "I20", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I25", "P8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I24", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I25", "P8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I26", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I25", "P8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I27", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "I28", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P11", "I17", "", "", "", "", "", "", "I22", "", "", "I23", "", "", "I21", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I29", "I15", "", "", "I18", "", "", "", "I16", "I19", "", "I20", "", "", "", "", "", "", ""},
        {"", "", "", "P11", "I17", "", "", "", "", "", "", "I22", "", "", "I23", "", "", "I21", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I30", "I15", "", "", "I18", "", "", "", "I16", "I19", "", "I20", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "I31", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I32", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P14", "P14", "", "", "", "", "", "", "P14", "", "", "P14", "", "", "P14", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P15", "P15", "", "", "", "", "", "", "P15", "", "", "P15", "", "", "P15", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I33", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "I34", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "I35", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I36", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I37", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I38", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I39", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P6", "P6", "", "", "", "", "", "", "P6", "", "P6", "P6", "", "", "P6", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "P6", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P9", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P10", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "I47", "P17", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I40", "", "", "", "", "", "I41", "", "I42", "", "I45", "", "I46"},
        {"", "", "", "P13", "P13", "", "", "", "", "", "", "P13", "", "", "P13", "", "", "P13", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "I47", "", "", "", "", "I54", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I52", "", "", "", "", "", "", "I53", "", "I42", "", "I45", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I55", "", "I42", "", "I45", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I56", "I42", "", "I45", "", "I46"},
        {"", "", "P5", "", "", "", "", "I6", "I7", "I8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I57", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I25", "P8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I58", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "P5", "", "", "", "", "I6", "I7", "I8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I59", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "P5", "", "", "", "", "I6", "I7", "I8", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I60", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "I61", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P19", "", "", "", "", "", "", "", "", "I63", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I62", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P34", "", "", "", "", "", "", "", "", "P34", "P34", "", "", "", "", "", "", "", "", "", "I65", "I66", "I67", "I70", "I68", "I69", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I64", "", "", "", "", ""},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I71", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I72", "", "I46"},
        {"", "", "", "", "", "", "P40", "", "", "", "", "", "", "", "", "P40", "P40", "", "", "", "", "I74", "I75", "", "", "", "P40", "P40", "P40", "P40", "P40", "P40", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I73", "", "", "",},
        {"", "", "", "", "", "", "P44", "", "", "", "", "", "", "", "", "P44", "P44", "", "", "", "", "P44", "P44", "I77", "I78", "", "P44", "P44", "P44", "P44", "P44", "P44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I76", "",},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I79", "", "I42", "", "I45", "", "I46"},
        {"", "", "", "", "", "", "P46", "", "", "", "", "", "", "", "", "P46", "P46", "", "", "", "", "P46", "P46", "P46", "P46", "", "P46", "P46", "P46", "P46", "P46", "P46", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
        {"", "", "", "", "", "", "P47", "", "", "", "", "", "", "", "", "P47", "P47", "", "", "", "", "P47", "P47", "P47", "P47", "", "P47", "P47", "P47", "P47", "P47", "P47", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
        {"", "", "", "", "", "", "P48", "", "", "", "", "", "", "", "", "P48", "P48", "", "", "", "", "P48", "P48", "P48", "P48", "", "P48", "P48", "P48", "P48", "P48", "P48", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
        {"", "", "", "", "", "", "P49", "", "", "", "", "", "", "", "", "P49", "P49", "", "", "", "", "P49", "P49", "P49", "P49", "", "P49", "P49", "P49", "P49", "P49", "P49", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "P20", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "P21", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
        {"", "", "", "", "", "I80", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
        {"", "", "", "", "", "", "I81", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "I82", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "P2", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "P7", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "P3", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "P4", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I83", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P16", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I84", "", "I42", "", "I45", "", "I46"},
        {"", "", "", "", "", "", "P27", "", "", "", "", "", "", "", "", "P27", "P27", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "",},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I85", "", "I45", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I86", "", "I45", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I87", "", "I45", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I88", "", "I45", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I89", "", "I45", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "I43", "I44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I90", "", "I45", "", "I46"},
        {"", "", "", "", "", "", "P40", "", "", "", "", "", "", "", "", "P40", "P40", "", "", "", "", "I74", "I75", "", "", "", "P40", "P40", "P40", "P40", "P40", "P40", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I91", "", "", ""},
        {"", "", "", "", "", "", "P40", "", "", "", "", "", "", "", "", "P40", "P40", "", "", "", "", "I74", "I75", "", "", "", "P40", "P40", "P40", "P40", "P40", "P40", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I92", "", "", ""},
        {"", "", "", "", "", "", "P37", "", "", "", "", "", "", "", "", "P37", "P37", "", "", "", "", "", "", "", "", "", "P37", "P37", "P37", "P37", "P37", "P37", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I93", "", "I46"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I94", "", "I46"},
        {"", "", "", "", "", "", "P41", "", "", "", "", "", "", "", "", "P41", "P41", "", "", "", "", "P41", "P41", "", "", "", "P41", "P41", "P41", "P41", "P41", "P41", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I95"},
        {"", "", "", "", "", "I47", "", "", "", "", "", "", "", "", "", "", "", "I48", "I49", "I50", "I51", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I96"},
        {"", "", "", "", "", "", "I97", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "I98", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "I99", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "I10", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I100", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P12", "P12", "", "", "", "", "", "", "P12", "", "", "P12", "", "", "P12", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P19", "", "", "", "", "", "", "", "", "I63", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I101", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P28", "", "", "", "", "", "", "", "", "P28", "P28", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P29", "", "", "", "", "", "", "", "", "P29", "P29", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P30", "", "", "", "", "", "", "", "", "P30", "P30", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P31", "", "", "", "", "", "", "", "", "P31", "P31", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P32", "", "", "", "", "", "", "", "", "P32", "P32", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P33", "", "", "", "", "", "", "", "", "P33", "P33", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P35", "", "", "", "", "", "", "", "", "P35", "P35", "", "", "", "", "", "", "", "", "", "P35", "P35", "P35", "P35", "P35", "P35", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P36", "", "", "", "", "", "", "", "", "P36", "P36", "", "", "", "", "", "", "", "", "", "P36", "P36", "P36", "P36", "P36", "P36", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P40", "", "", "", "", "", "", "", "", "P40", "P40", "", "", "", "", "I74", "I75", "", "", "", "P40", "P40", "P40", "P40", "P40", "P40", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I102", "", "", ""},
        {"", "", "", "", "", "", "P40", "", "", "", "", "", "", "", "", "P40", "P40", "", "", "", "", "I74", "I75", "", "", "", "P40", "P40", "P40", "P40", "P40", "P40", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I103", "", "", ""},
        {"", "", "", "", "", "", "P44", "", "", "", "", "", "", "", "", "P44", "P44", "", "", "", "", "P44", "P44", "I77", "I78", "", "P44", "P44", "P44", "P44", "P44", "P44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I104", ""},
        {"", "", "", "", "", "", "P44", "", "", "", "", "", "", "", "", "P44", "P44", "", "", "", "", "P44", "P44", "I77", "I78", "", "P44", "P44", "P44", "P44", "P44", "P44", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I105", ""},
        {"", "", "", "", "", "", "P45", "", "", "", "", "", "", "", "", "P45", "P45", "", "", "", "", "P45", "P45", "P45", "P45", "", "P45", "P45", "P45", "P45", "P45", "P45", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "P22", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "I10", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I106", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P26", "P26", "", "", "", "", "", "", "P26", "", "", "P26", "", "", "P26", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P18", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P38", "", "", "", "", "", "", "", "", "P38", "P38", "", "", "", "", "", "", "", "", "", "P38", "P38", "P38", "P38", "P38", "P38", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P39", "", "", "", "", "", "", "", "", "P39", "P39", "", "", "", "", "", "", "", "", "", "P39", "P39", "P39", "P39", "P39", "P39", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P42", "", "", "", "", "", "", "", "", "P42", "P42", "", "", "", "", "P42", "P42", "", "", "", "P42", "P42", "P42", "P42", "P42", "P42", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "", "", "", "P43", "", "", "", "", "", "", "", "", "P43", "P43", "", "", "", "", "P43", "P43", "", "", "", "P43", "P43", "P43", "P43", "P43", "P43", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P25", "P25", "", "", "", "", "", "", "P25", "", "I108", "P25", "", "", "P25", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I107", "", "", "", "", "", "", "", ""},
        {"", "", "", "P23", "P23", "", "", "", "", "", "", "P23", "", "", "P23", "", "", "P23", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "I10", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "I109", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""},
        {"", "", "", "P24", "P24", "", "", "", "", "", "", "P24", "", "", "P24", "", "", "P24", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""}};
    private Stack<String> stack;
    private Stack<String> auxStack;
    private final Semantico sem = new Semantico();

    public Sintactico() {
        stack = new Stack();
        auxStack = new Stack();
        stack.push("$");
        stack.push("I0");
        auxStack.push("z");
        result = "$ I0 \n";
        error = "";
    }

    public void Analisis(String token, int linea) {
        BuscarElemento(token, linea);
    }

    private void BuscarElemento(String token, int linea) {
        //System.out.println(token);
        for (it = 0; it < tnt.length; it++) {
            if (tnt[it].equals(token)) {
                //System.out.println(table[Integer.parseInt(stack.peek().split("I")[1])][it]);
                if (table[Integer.parseInt(stack.peek().split("I")[1])][it].equals("")) {
                    //result += " error ";
                    error += "Error sintáctico en la línea " + linea + " esperaba " + Error(Integer.parseInt(stack.peek().split("I")[1])) + "\n";
                    return;
                } else if (table[Integer.parseInt(stack.peek().split("I")[1])][it].startsWith("I")) {
                    //System.out.println(token);
                    Desplazamiento(token, table[Integer.parseInt(stack.peek().split("I")[1])][it], linea);
                } else {
                    Reduccion(token, Integer.parseInt(table[Integer.parseInt(stack.peek().split("I")[1])][it].split("P")[1]), linea);
                }
            }
        }
    }

    private void Desplazamiento(String token, String estado, int linea) {
        stack.push(token);
        stack.push(estado);
        switch (estado) {
            case "I21" -> {
                sem.asign = originalToken;
                sem.isAsign = true;
                //sem.middleCode += "V1 = " + originalToken + "\n";
            }
            case "I6" ->
                sem.type = 0;
            case "I7" ->
                sem.type = 1;
            case "I8" ->
                sem.type = 2;
            case "I11", "I12", "I13", "I37" -> {
                sem.AddSymbol(originalToken, "", linea);
            }
            case "I48", "I51" -> {
                sem.AddSemStack(token, originalToken, linea);
                //sem.AddExpPos(originalToken);
                if (!sem.error.equals("")) {
                    error += sem.error;
                    return;
                }
            }
            case "I49", "I50" -> {
                sem.AddSemStack(token, originalToken, linea);
                //sem.AddExpPos(originalToken);
            }
            case "I43", "I44", "I74", "I75", "I77", "I78", "I47", "I97", "I61", "I31" -> {
                sem.AddOpStack(token, linea);
            }
            case "I65", "I66", "I67", "I68", "I69", "I70" -> {
                sem.IdentifyOp(token, linea);
                if (!sem.error.equals("")) {
                    error += sem.error;
                    return;
                }
            }
            case "I35", "I34" ->
                sem.isWhileOrIf = true;
            case "I81", "I82" -> {
                sem.EndWhileOrIf(linea);
            }
        }
        AñadirResultado();
        //System.out.println(result);
    }

    private void Reduccion(String token, int production, int line) {
        int state;
        switch (production) {
            case 35, 36, 37 -> {
                //System.out.println(token);
                sem.AddOpStack(token, line);
                //System.out.println(token);
                if (!sem.error.equals("")) {
                    error += sem.error;
                    return;
                }
                if (!sem.middleCode.equals("")) {
                    middleCode = sem.middleCode;
                }
            }
            case 22 ->
                sem.isAsign = false;
        }
        while (!productions[production].split("#")[1].equals("vacia")) {
            //System.out.println(productions[production].split("#")[1].split(" ")[0]);
            if (stack.pop().equals(productions[production].split("#")[1].split(" ")[0])) {
                break;
            }
        }
        for (ip = 0; ip < tnt.length; ip++) {
            if (tnt[ip].equals(productions[production].split("#")[0])) {
                state = Integer.parseInt(stack.peek().split("I")[1]);
                if (table[state][ip].equals("")) {
                    //System.out.println(state + " Aqui se muere " + ip);
                    return;
                }
                stack.push(productions[production].split("#")[0]);
                //System.out.println(productions[production]);
                stack.push(table[state][ip]);
                AñadirResultado();
                //System.out.println(result);
                BuscarElemento(token, line);
            }
        }
        if (productions[production].equals(productions[0])) {
            result += productions[0] + " Acepta la cadena";
        }
    }

    private void AñadirResultado() {
        while (!stack.peek().equals("$")) {
            auxStack.push(stack.pop());
        }
        result += "$";
        while (!auxStack.peek().equals("z")) {
            result += " " + stack.push(auxStack.pop());
        }
        result += "\n";
    }

    public String Resultado() {
        return result;
    }

    public String Error(int I) {
        int i, it;
        String expected = "";
        for (i = 0; i <= 32; i++) {
            if (!table[I][i].equals("")) {
                expected += tnt[i] + ", ";
            }
        }
        return expected;
    }
}
