/* The following code was generated by JFlex 1.4.3 on 03/12/23, 21:38 */

package jide;
import java.io.*;
import static jide.Tokens.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 03/12/23, 21:38 from the specification file
 * <tt>C:/Users/Ettos/OneDrive/Documents/Alejandro/7mo Semestre/Lenguajes y automatas II/Compilador Felices los 4/src/jide/Alexer.flex</tt>
 */
class Alexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\15\1\11\2\0\1\16\22\0\1\14\1\53\1\7\4\0"+
    "\1\12\1\35\1\36\1\20\1\6\1\46\1\3\1\4\1\17\12\2"+
    "\1\0\1\47\1\51\1\50\1\52\2\0\32\1\1\0\1\10\2\0"+
    "\1\1\1\0\1\26\1\13\1\41\1\43\1\5\1\37\1\25\1\42"+
    "\1\32\2\1\1\40\1\27\1\33\1\24\1\22\1\1\1\23\1\44"+
    "\1\34\2\1\1\45\3\1\1\30\1\0\1\31\42\0\1\21\uff5f\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\0\1\1\1\4\1\0"+
    "\1\5\1\0\3\5\1\6\1\7\2\1\1\10\1\11"+
    "\2\1\1\12\1\13\3\1\1\14\1\15\1\16\1\17"+
    "\1\20\1\0\1\1\1\2\1\0\1\1\1\21\3\0"+
    "\1\22\1\0\3\1\1\23\4\1\1\24\1\25\1\26"+
    "\1\27\1\1\2\0\1\2\1\0\1\1\1\21\1\30"+
    "\3\0\3\1\1\31\4\1\1\0\1\1\1\0\1\32"+
    "\2\0\2\1\1\33\1\34\1\1\1\35\1\1\1\0"+
    "\1\22\1\1\1\36\1\37\1\40\2\1\1\41";

  private static int [] zzUnpackAction() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\54\0\130\0\204\0\260\0\334\0\u0108\0\u0134"+
    "\0\u0108\0\u0160\0\u018c\0\u01b8\0\u01e4\0\u0210\0\u0108\0\u023c"+
    "\0\u0268\0\u0108\0\u0108\0\u0294\0\u02c0\0\u0108\0\u0108\0\u02ec"+
    "\0\u0318\0\u0344\0\u0108\0\u0108\0\u0370\0\u039c\0\u03c8\0\u03f4"+
    "\0\u0420\0\u044c\0\u0478\0\u04a4\0\u04d0\0\u04fc\0\u0528\0\u0554"+
    "\0\u0580\0\u05ac\0\u05d8\0\u0604\0\u0630\0\u0420\0\u065c\0\u0688"+
    "\0\u06b4\0\u06e0\0\u0108\0\u0108\0\u0108\0\u0108\0\u070c\0\u0738"+
    "\0\u0764\0\u0790\0\u0790\0\u07bc\0\u04fc\0\u07e8\0\u0814\0\u0840"+
    "\0\u086c\0\u0898\0\u08c4\0\u08f0\0\u0420\0\u091c\0\u0948\0\u0974"+
    "\0\u09a0\0\u09cc\0\u09f8\0\u0a24\0\u0420\0\u0a50\0\u0a7c\0\u0aa8"+
    "\0\u0ad4\0\u0420\0\u0420\0\u0b00\0\u0420\0\u0b2c\0\u0b58\0\u0108"+
    "\0\u0b84\0\u0420\0\u0420\0\u0420\0\u0bb0\0\u0bdc\0\u0420";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\0\1\2\1\3\1\4\1\5\1\6\1\7\1\10"+
    "\1\0\1\11\1\12\1\2\1\13\1\14\1\15\1\16"+
    "\1\17\1\0\1\20\1\21\4\2\1\22\1\23\1\24"+
    "\1\2\1\25\1\26\1\27\1\30\1\2\1\31\3\2"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\1\0"+
    "\1\41\3\0\1\41\5\0\1\41\6\0\6\41\2\0"+
    "\3\41\2\0\7\41\10\0\1\3\1\0\1\42\1\43"+
    "\50\0\1\3\1\0\1\5\51\0\1\42\52\0\1\41"+
    "\3\0\1\41\5\0\1\41\6\0\6\41\2\0\3\41"+
    "\2\0\1\41\1\44\5\41\62\0\7\10\1\45\1\46"+
    "\43\10\10\47\1\50\1\47\1\0\41\47\14\0\1\13"+
    "\54\0\1\14\54\0\1\15\54\0\1\51\1\52\34\0"+
    "\1\41\3\0\1\41\5\0\1\41\6\0\1\41\1\53"+
    "\4\41\2\0\3\41\2\0\7\41\7\0\1\41\3\0"+
    "\1\54\5\0\1\41\6\0\6\41\2\0\3\41\2\0"+
    "\7\41\7\0\1\41\3\0\1\41\5\0\1\41\6\0"+
    "\6\41\2\0\1\41\1\55\1\41\2\0\1\56\6\41"+
    "\7\0\1\41\3\0\1\41\5\0\1\41\6\0\6\41"+
    "\2\0\3\41\2\0\3\41\1\57\3\41\7\0\1\41"+
    "\3\0\1\41\5\0\1\41\6\0\6\41\2\0\3\41"+
    "\2\0\1\41\1\60\5\41\7\0\1\41\3\0\1\41"+
    "\5\0\1\41\6\0\6\41\2\0\3\41\2\0\3\41"+
    "\1\61\3\41\7\0\1\41\3\0\1\41\5\0\1\41"+
    "\6\0\6\41\2\0\3\41\2\0\3\41\1\62\3\41"+
    "\56\0\1\63\53\0\1\64\53\0\1\65\53\0\1\66"+
    "\4\0\1\41\1\67\1\70\1\71\1\41\5\0\1\41"+
    "\6\0\6\41\2\0\3\41\2\0\7\41\10\0\1\42"+
    "\2\0\1\43\50\0\1\72\1\73\2\0\1\73\46\0"+
    "\1\41\1\67\1\70\1\71\1\41\5\0\1\41\6\0"+
    "\6\41\2\0\3\41\2\0\5\41\1\74\1\41\15\0"+
    "\1\10\44\0\7\10\1\75\1\46\43\10\12\0\1\76"+
    "\51\0\1\77\43\0\11\51\1\0\42\51\20\100\1\101"+
    "\33\100\1\0\1\41\1\67\1\70\1\71\1\41\5\0"+
    "\1\41\6\0\2\41\1\102\3\41\2\0\1\103\2\41"+
    "\2\0\7\41\7\0\1\41\1\67\1\70\1\71\1\41"+
    "\5\0\1\41\6\0\4\41\1\104\1\41\2\0\3\41"+
    "\2\0\7\41\7\0\1\41\1\67\1\70\1\71\1\41"+
    "\5\0\1\41\6\0\6\41\2\0\2\41\1\105\2\0"+
    "\7\41\7\0\1\41\1\67\1\70\1\71\1\106\5\0"+
    "\1\41\6\0\6\41\2\0\3\41\2\0\7\41\7\0"+
    "\1\41\1\67\1\70\1\71\1\41\5\0\1\41\6\0"+
    "\2\41\1\107\3\41\2\0\3\41\2\0\7\41\7\0"+
    "\1\41\1\67\1\70\1\71\1\41\5\0\1\41\6\0"+
    "\4\41\1\110\1\41\2\0\3\41\2\0\7\41\7\0"+
    "\1\41\1\67\1\70\1\71\1\41\5\0\1\41\6\0"+
    "\6\41\2\0\1\111\2\41\2\0\7\41\7\0\1\112"+
    "\1\67\1\0\1\113\1\114\5\0\1\112\6\0\6\112"+
    "\2\0\3\112\2\0\7\112\10\0\1\67\1\0\1\71"+
    "\51\0\1\113\53\0\1\72\52\0\1\41\1\67\1\70"+
    "\1\71\1\115\5\0\1\41\6\0\6\41\2\0\3\41"+
    "\2\0\7\41\20\0\1\12\51\0\1\47\1\0\2\47"+
    "\7\0\1\47\7\0\2\47\2\0\1\47\14\0\20\100"+
    "\1\116\33\100\20\0\1\101\1\117\33\0\1\41\1\67"+
    "\1\70\1\71\1\41\5\0\1\41\6\0\3\41\1\120"+
    "\2\41\2\0\3\41\2\0\7\41\7\0\1\41\1\67"+
    "\1\70\1\71\1\41\5\0\1\41\6\0\6\41\2\0"+
    "\1\41\1\121\1\41\2\0\7\41\7\0\1\41\1\67"+
    "\1\70\1\71\1\41\5\0\1\41\6\0\6\41\2\0"+
    "\3\41\2\0\4\41\1\122\2\41\7\0\1\41\1\67"+
    "\1\70\1\71\1\41\5\0\1\41\6\0\6\41\2\0"+
    "\1\41\1\123\1\41\2\0\7\41\7\0\1\41\1\67"+
    "\1\70\1\71\1\41\5\0\1\41\6\0\4\41\1\124"+
    "\1\41\2\0\3\41\2\0\7\41\7\0\1\41\1\67"+
    "\1\70\1\71\1\41\5\0\1\41\6\0\1\41\1\125"+
    "\4\41\2\0\3\41\2\0\7\41\7\0\1\41\1\67"+
    "\1\70\1\71\1\41\5\0\1\41\6\0\6\41\2\0"+
    "\3\41\2\0\1\41\1\126\5\41\7\0\1\112\1\67"+
    "\1\70\1\71\1\112\5\0\1\112\6\0\6\112\2\0"+
    "\3\112\2\0\7\112\7\0\1\112\1\113\2\0\1\114"+
    "\5\0\1\112\6\0\6\112\2\0\3\112\2\0\7\112"+
    "\7\0\1\112\1\67\1\70\1\71\1\112\1\127\4\0"+
    "\1\112\6\0\6\112\2\0\3\112\2\0\7\112\6\0"+
    "\17\100\1\130\1\116\33\100\17\0\1\130\35\0\1\41"+
    "\1\67\1\70\1\71\1\41\5\0\1\41\6\0\1\41"+
    "\1\131\4\41\2\0\3\41\2\0\7\41\7\0\1\41"+
    "\1\67\1\70\1\71\1\41\5\0\1\41\6\0\6\41"+
    "\2\0\2\41\1\132\2\0\7\41\7\0\1\41\1\67"+
    "\1\70\1\71\1\41\5\0\1\41\6\0\6\41\2\0"+
    "\2\41\1\133\2\0\7\41\7\0\1\41\1\67\1\70"+
    "\1\71\1\134\5\0\1\41\6\0\6\41\2\0\3\41"+
    "\2\0\7\41\10\0\1\135\52\0\1\41\1\67\1\70"+
    "\1\71\1\41\5\0\1\41\6\0\4\41\1\136\1\41"+
    "\2\0\3\41\2\0\7\41\7\0\1\112\1\135\2\0"+
    "\1\112\5\0\1\112\6\0\6\112\2\0\3\112\2\0"+
    "\7\112\7\0\1\41\1\67\1\70\1\71\1\41\5\0"+
    "\1\41\6\0\5\41\1\137\2\0\3\41\2\0\7\41"+
    "\6\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3080];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\3\1\1\0\1\1\1\11\1\0\1\11\1\0"+
    "\4\1\1\11\2\1\2\11\2\1\2\11\3\1\2\11"+
    "\3\1\1\0\2\1\1\0\2\1\3\0\1\1\1\0"+
    "\10\1\4\11\1\1\2\0\1\1\1\0\3\1\3\0"+
    "\10\1\1\0\1\1\1\0\1\1\2\0\7\1\1\0"+
    "\1\11\7\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[95];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    public String lexeme;
    Analisis c = new Analisis();
int estado=1;
int nu=0;


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Alexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Alexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 120) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Tokens yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 19: 
          { c.linea=yyline; lexeme=yytext();return ifStr;
          }
        case 34: break;
        case 12: 
          { c.linea=yyline; lexeme=yytext();return comma;
          }
        case 35: break;
        case 18: 
          { /* Ignore */
          }
        case 36: break;
        case 17: 
          { c.linea=yyline; lexeme=yytext();return litcad;
          }
        case 37: break;
        case 4: 
          { c.linea=yyline; lexeme=yytext();return plus;
          }
        case 38: break;
        case 27: 
          { c.linea=yyline; lexeme=yytext();return read;
          }
        case 39: break;
        case 10: 
          { c.linea=yyline; lexeme=yytext();return open_parenth;
          }
        case 40: break;
        case 26: 
          { c.linea=yyline; lexeme=yytext();return elseStr;
          }
        case 41: break;
        case 9: 
          { c.linea=yyline; lexeme=yytext();return close_key;
          }
        case 42: break;
        case 3: 
          { c.linea=yyline; lexeme=yytext();return minus;
          }
        case 43: break;
        case 29: 
          { c.linea=yyline; lexeme=yytext();return character;
          }
        case 44: break;
        case 15: 
          { c.linea=yyline; lexeme=yytext();return less_than;
          }
        case 45: break;
        case 32: 
          { c.linea=yyline; lexeme=yytext();return whileStr;
          }
        case 46: break;
        case 33: 
          { estado = 1;c.linea=yyline; lexeme=yytext();return program;
          }
        case 47: break;
        case 1: 
          { 
              c.linea = yyline;
              lexeme = yytext();
              if(estado == 1){
                  estado=0;
                  return idp;
              }
              else{
                  return id;
              }
          }
        case 48: break;
        case 20: 
          { c.linea=yyline; lexeme=yytext();return equals_to;
          }
        case 49: break;
        case 28: 
          { c.linea=yyline; lexeme=yytext();return then;
          }
        case 50: break;
        case 23: 
          { c.linea=yyline; lexeme=yytext();return different_to;
          }
        case 51: break;
        case 11: 
          { c.linea=yyline; lexeme=yytext();return close_parenth;
          }
        case 52: break;
        case 31: 
          { c.linea=yyline; lexeme=yytext();return floatType;
          }
        case 53: break;
        case 21: 
          { c.linea=yyline; lexeme=yytext();return less_or_equals;
          }
        case 54: break;
        case 6: 
          { c.linea=yyline; lexeme=yytext();return div;
          }
        case 55: break;
        case 13: 
          { c.linea=yyline; lexeme=yytext();return semicolon;
          }
        case 56: break;
        case 2: 
          { c.linea=yyline; lexeme=yytext();return num;
          }
        case 57: break;
        case 22: 
          { c.linea=yyline; lexeme=yytext();return greater_or_equals;
          }
        case 58: break;
        case 14: 
          { c.linea=yyline; lexeme=yytext();return equal;
          }
        case 59: break;
        case 16: 
          { c.linea=yyline; lexeme=yytext();return greater_than;
          }
        case 60: break;
        case 24: 
          { c.linea=yyline; lexeme=yytext();return litcar;
          }
        case 61: break;
        case 5: 
          { /*Ignore*/
          }
        case 62: break;
        case 25: 
          { c.linea=yyline; lexeme=yytext();return integer;
          }
        case 63: break;
        case 30: 
          { c.linea=yyline; lexeme=yytext();return print;
          }
        case 64: break;
        case 8: 
          { c.linea=yyline; lexeme=yytext();return open_key;
          }
        case 65: break;
        case 7: 
          { c.linea=yyline; lexeme=yytext();return mult;
          }
        case 66: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          } 
          else {
            return Error;
          }
      }
    }
  }


}
