/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.lexer;

import java.io.*;
import drawlang.node.*;

@SuppressWarnings("nls")
public class Lexer
{
    protected Token token;
    protected State state = State.INITIAL;

    private PushbackReader in;
    private int line;
    private int pos;
    private boolean cr;
    private boolean eof;
    private final StringBuffer text = new StringBuffer();

    @SuppressWarnings("unused")
    protected void filter() throws LexerException, IOException
    {
        // Do nothing
    }

    public Lexer(@SuppressWarnings("hiding") PushbackReader in)
    {
        this.in = in;
    }
 
    public Token peek() throws LexerException, IOException
    {
        while(this.token == null)
        {
            this.token = getToken();
            filter();
        }

        return this.token;
    }

    public Token next() throws LexerException, IOException
    {
        while(this.token == null)
        {
            this.token = getToken();
            filter();
        }

        Token result = this.token;
        this.token = null;
        return result;
    }

    protected Token getToken() throws IOException, LexerException
    {
        int dfa_state = 0;

        int start_pos = this.pos;
        int start_line = this.line;

        int accept_state = -1;
        int accept_token = -1;
        int accept_length = -1;
        int accept_pos = -1;
        int accept_line = -1;

        @SuppressWarnings("hiding") int[][][] gotoTable = Lexer.gotoTable[this.state.id()];
        @SuppressWarnings("hiding") int[] accept = Lexer.accept[this.state.id()];
        this.text.setLength(0);

        while(true)
        {
            int c = getChar();

            if(c != -1)
            {
                switch(c)
                {
                case 10:
                    if(this.cr)
                    {
                        this.cr = false;
                    }
                    else
                    {
                        this.line++;
                        this.pos = 0;
                    }
                    break;
                case 13:
                    this.line++;
                    this.pos = 0;
                    this.cr = true;
                    break;
                default:
                    this.pos++;
                    this.cr = false;
                    break;
                }

                this.text.append((char) c);

                do
                {
                    int oldState = (dfa_state < -1) ? (-2 -dfa_state) : dfa_state;

                    dfa_state = -1;

                    int[][] tmp1 =  gotoTable[oldState];
                    int low = 0;
                    int high = tmp1.length - 1;

                    while(low <= high)
                    {
                        int middle = (low + high) / 2;
                        int[] tmp2 = tmp1[middle];

                        if(c < tmp2[0])
                        {
                            high = middle - 1;
                        }
                        else if(c > tmp2[1])
                        {
                            low = middle + 1;
                        }
                        else
                        {
                            dfa_state = tmp2[2];
                            break;
                        }
                    }
                }while(dfa_state < -1);
            }
            else
            {
                dfa_state = -1;
            }

            if(dfa_state >= 0)
            {
                if(accept[dfa_state] != -1)
                {
                    accept_state = dfa_state;
                    accept_token = accept[dfa_state];
                    accept_length = this.text.length();
                    accept_pos = this.pos;
                    accept_line = this.line;
                }
            }
            else
            {
                if(accept_state != -1)
                {
                    switch(accept_token)
                    {
                    case 0:
                        {
                            @SuppressWarnings("hiding") Token token = new0(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 1:
                        {
                            @SuppressWarnings("hiding") Token token = new1(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 2:
                        {
                            @SuppressWarnings("hiding") Token token = new2(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 3:
                        {
                            @SuppressWarnings("hiding") Token token = new3(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 4:
                        {
                            @SuppressWarnings("hiding") Token token = new4(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 5:
                        {
                            @SuppressWarnings("hiding") Token token = new5(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 6:
                        {
                            @SuppressWarnings("hiding") Token token = new6(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 7:
                        {
                            @SuppressWarnings("hiding") Token token = new7(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 8:
                        {
                            @SuppressWarnings("hiding") Token token = new8(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 9:
                        {
                            @SuppressWarnings("hiding") Token token = new9(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 10:
                        {
                            @SuppressWarnings("hiding") Token token = new10(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 11:
                        {
                            @SuppressWarnings("hiding") Token token = new11(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 12:
                        {
                            @SuppressWarnings("hiding") Token token = new12(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 13:
                        {
                            @SuppressWarnings("hiding") Token token = new13(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 14:
                        {
                            @SuppressWarnings("hiding") Token token = new14(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 15:
                        {
                            @SuppressWarnings("hiding") Token token = new15(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 16:
                        {
                            @SuppressWarnings("hiding") Token token = new16(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 17:
                        {
                            @SuppressWarnings("hiding") Token token = new17(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 18:
                        {
                            @SuppressWarnings("hiding") Token token = new18(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 19:
                        {
                            @SuppressWarnings("hiding") Token token = new19(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 20:
                        {
                            @SuppressWarnings("hiding") Token token = new20(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 21:
                        {
                            @SuppressWarnings("hiding") Token token = new21(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 22:
                        {
                            @SuppressWarnings("hiding") Token token = new22(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 23:
                        {
                            @SuppressWarnings("hiding") Token token = new23(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 24:
                        {
                            @SuppressWarnings("hiding") Token token = new24(
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    case 25:
                        {
                            @SuppressWarnings("hiding") Token token = new25(
                                getText(accept_length),
                                start_line + 1,
                                start_pos + 1);
                            pushBack(accept_length);
                            this.pos = accept_pos;
                            this.line = accept_line;
                            return token;
                        }
                    }
                }
                else
                {
                    if(this.text.length() > 0)
                    {
                        throw new LexerException(
                            "[" + (start_line + 1) + "," + (start_pos + 1) + "]" +
                            " Unknown token: " + this.text);
                    }

                    @SuppressWarnings("hiding") EOF token = new EOF(
                        start_line + 1,
                        start_pos + 1);
                    return token;
                }
            }
        }
    }

    Token new0(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordclass(line, pos); }
    Token new1(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordcolor(line, pos); }
    Token new2(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordposition(line, pos); }
    Token new3(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordborder(line, pos); }
    Token new4(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordsize(line, pos); }
    Token new5(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordpoints(line, pos); }
    Token new6(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new THexcolor(text, line, pos); }
    Token new7(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TNumber(text, line, pos); }
    Token new8(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordcircle(line, pos); }
    Token new9(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordrect(line, pos); }
    Token new10(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TKeywordpoly(line, pos); }
    Token new11(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TIdentifier(text, line, pos); }
    Token new12(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TDollarsign(line, pos); }
    Token new13(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TLBrace(line, pos); }
    Token new14(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TRBrace(line, pos); }
    Token new15(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TLBrk(line, pos); }
    Token new16(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TRBrk(line, pos); }
    Token new17(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TOpsemicolon(line, pos); }
    Token new18(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TOpcolon(line, pos); }
    Token new19(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TOpcomma(line, pos); }
    Token new20(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TOpequal(line, pos); }
    Token new21(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TOpplus(line, pos); }
    Token new22(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TOpminus(line, pos); }
    Token new23(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TOpdot(line, pos); }
    Token new24(@SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TOpleftangle(line, pos); }
    Token new25(@SuppressWarnings("hiding") String text, @SuppressWarnings("hiding") int line, @SuppressWarnings("hiding") int pos) { return new TBlank(text, line, pos); }

    private int getChar() throws IOException
    {
        if(this.eof)
        {
            return -1;
        }

        int result = this.in.read();

        if(result == -1)
        {
            this.eof = true;
        }

        return result;
    }

    private void pushBack(int acceptLength) throws IOException
    {
        int length = this.text.length();
        for(int i = length - 1; i >= acceptLength; i--)
        {
            this.eof = false;

            this.in.unread(this.text.charAt(i));
        }
    }

    protected void unread(@SuppressWarnings("hiding") Token token) throws IOException
    {
        @SuppressWarnings("hiding") String text = token.getText();
        int length = text.length();

        for(int i = length - 1; i >= 0; i--)
        {
            this.eof = false;

            this.in.unread(text.charAt(i));
        }

        this.pos = token.getPos() - 1;
        this.line = token.getLine() - 1;
    }

    private String getText(int acceptLength)
    {
        StringBuffer s = new StringBuffer(acceptLength);
        for(int i = 0; i < acceptLength; i++)
        {
            s.append(this.text.charAt(i));
        }

        return s.toString();
    }

    private static int[][][][] gotoTable;
/*  {
        { // INITIAL
            {{10, 10, 1}, {13, 13, 2}, {32, 32, 3}, {35, 35, 4}, {36, 36, 5}, {40, 40, 6}, {41, 41, 7}, {43, 43, 8}, {44, 44, 9}, {45, 45, 10}, {46, 46, 11}, {48, 57, 12}, {58, 58, 13}, {59, 59, 14}, {61, 61, 15}, {62, 62, 16}, {65, 66, 17}, {67, 67, 18}, {68, 79, 17}, {80, 80, 19}, {81, 81, 17}, {82, 82, 20}, {83, 90, 17}, {97, 97, 21}, {98, 98, 22}, {99, 99, 23}, {100, 111, 21}, {112, 112, 24}, {113, 114, 21}, {115, 115, 25}, {116, 122, 21}, {123, 123, 26}, {125, 125, 27}, },
            {{10, 32, -2}, },
            {{10, 32, -2}, },
            {{10, 32, -2}, },
            {{48, 57, 28}, {65, 70, 29}, {97, 102, 30}, },
            {},
            {},
            {},
            {},
            {},
            {},
            {},
            {{48, 57, 12}, },
            {},
            {},
            {},
            {},
            {{48, 57, 31}, {65, 90, 32}, {95, 95, 33}, {97, 122, 34}, },
            {{48, 95, -19}, {97, 104, 34}, {105, 105, 35}, {106, 122, 34}, },
            {{48, 95, -19}, {97, 110, 34}, {111, 111, 36}, {112, 122, 34}, },
            {{48, 95, -19}, {97, 100, 34}, {101, 101, 37}, {102, 122, 34}, },
            {{48, 122, -19}, },
            {{48, 110, -21}, {111, 111, 38}, {112, 122, 34}, },
            {{48, 95, -19}, {97, 107, 34}, {108, 108, 39}, {109, 110, 34}, {111, 111, 40}, {112, 122, 34}, },
            {{48, 110, -21}, {111, 111, 41}, {112, 122, 34}, },
            {{48, 104, -20}, {105, 105, 42}, {106, 122, 34}, },
            {},
            {},
            {{48, 57, 43}, {65, 70, 44}, {97, 102, 45}, },
            {{48, 102, -30}, },
            {{48, 102, -30}, },
            {{48, 122, -19}, },
            {{48, 122, -19}, },
            {{48, 122, -19}, },
            {{48, 122, -19}, },
            {{48, 95, -19}, {97, 113, 34}, {114, 114, 46}, {115, 122, 34}, },
            {{48, 107, -25}, {108, 108, 47}, {109, 122, 34}, },
            {{48, 95, -19}, {97, 98, 34}, {99, 99, 48}, {100, 122, 34}, },
            {{48, 113, -37}, {114, 114, 49}, {115, 122, 34}, },
            {{48, 95, -19}, {97, 97, 50}, {98, 122, 34}, },
            {{48, 107, -25}, {108, 108, 51}, {109, 122, 34}, },
            {{48, 104, -20}, {105, 105, 52}, {106, 114, 34}, {115, 115, 53}, {116, 122, 34}, },
            {{48, 95, -19}, {97, 121, 34}, {122, 122, 54}, },
            {{48, 57, 55}, {65, 70, 56}, {97, 102, 57}, },
            {{48, 102, -45}, },
            {{48, 102, -45}, },
            {{48, 98, -39}, {99, 99, 58}, {100, 122, 34}, },
            {{48, 95, -19}, {97, 120, 34}, {121, 121, 59}, {122, 122, 34}, },
            {{48, 95, -19}, {97, 115, 34}, {116, 116, 60}, {117, 122, 34}, },
            {{48, 95, -19}, {97, 99, 34}, {100, 100, 61}, {101, 122, 34}, },
            {{48, 95, -19}, {97, 114, 34}, {115, 115, 62}, {116, 122, 34}, },
            {{48, 110, -21}, {111, 111, 63}, {112, 122, 34}, },
            {{48, 95, -19}, {97, 109, 34}, {110, 110, 64}, {111, 122, 34}, },
            {{48, 104, -20}, {105, 105, 65}, {106, 122, 34}, },
            {{48, 100, -22}, {101, 101, 66}, {102, 122, 34}, },
            {{48, 57, 67}, {65, 70, 68}, {97, 102, 69}, },
            {{48, 102, -57}, },
            {{48, 102, -57}, },
            {{48, 107, -25}, {108, 108, 70}, {109, 122, 34}, },
            {{48, 95, -19}, {97, 102, 34}, {103, 103, 71}, {104, 122, 34}, },
            {{48, 95, -19}, {97, 97, 72}, {98, 122, 34}, },
            {{48, 100, -22}, {101, 101, 73}, {102, 122, 34}, },
            {{48, 114, -52}, {115, 115, 74}, {116, 122, 34}, },
            {{48, 113, -37}, {114, 114, 75}, {115, 122, 34}, },
            {{48, 115, -50}, {116, 116, 76}, {117, 122, 34}, },
            {{48, 115, -50}, {116, 116, 77}, {117, 122, 34}, },
            {{48, 122, -19}, },
            {{48, 57, 78}, {65, 70, 79}, {97, 102, 80}, },
            {{48, 102, -69}, },
            {{48, 102, -69}, },
            {{48, 100, -22}, {101, 101, 81}, {102, 122, 34}, },
            {{48, 110, -21}, {111, 111, 82}, {112, 122, 34}, },
            {{48, 109, -54}, {110, 110, 83}, {111, 122, 34}, },
            {{48, 113, -37}, {114, 114, 84}, {115, 122, 34}, },
            {{48, 122, -19}, },
            {{48, 122, -19}, },
            {{48, 114, -52}, {115, 115, 85}, {116, 122, 34}, },
            {{48, 104, -20}, {105, 105, 86}, {106, 122, 34}, },
            {{48, 57, 87}, {65, 70, 88}, {97, 102, 89}, },
            {{48, 102, -80}, },
            {{48, 102, -80}, },
            {{48, 122, -19}, },
            {{48, 109, -54}, {110, 110, 90}, {111, 122, 34}, },
            {{48, 102, -61}, {103, 103, 91}, {104, 122, 34}, },
            {{48, 122, -19}, },
            {{48, 122, -19}, },
            {{48, 110, -21}, {111, 111, 92}, {112, 122, 34}, },
            {},
            {},
            {},
            {{48, 122, -19}, },
            {{48, 107, -25}, {108, 108, 93}, {109, 122, 34}, },
            {{48, 109, -54}, {110, 110, 94}, {111, 122, 34}, },
            {{48, 100, -22}, {101, 101, 95}, {102, 122, 34}, },
            {{48, 122, -19}, },
            {{48, 122, -19}, },
        }
    };*/

    private static int[][] accept;
/*  {
        // INITIAL
        {-1, 25, 25, 25, -1, 12, 15, 16, 21, 19, 22, 23, 7, 18, 17, 20, 24, 11, 11, 11, 11, 11, 11, 11, 11, 11, 13, 14, -1, -1, -1, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, 11, -1, -1, -1, 11, 11, 11, 11, 11, 11, 11, 11, 11, -1, -1, -1, 11, 11, 11, 11, 11, 11, 11, 11, 4, -1, -1, -1, 11, 11, 11, 11, 0, 1, 11, 11, -1, -1, -1, 8, 11, 11, 3, 5, 11, 6, 6, 6, 10, 11, 11, 11, 2, 9, },

    };*/

    public static class State
    {
        public final static State INITIAL = new State(0);

        private int id;

        private State(@SuppressWarnings("hiding") int id)
        {
            this.id = id;
        }

        public int id()
        {
            return this.id;
        }
    }

    static 
    {
        try
        {
            DataInputStream s = new DataInputStream(
                new BufferedInputStream(
                Lexer.class.getResourceAsStream("lexer.dat")));

            // read gotoTable
            int length = s.readInt();
            gotoTable = new int[length][][][];
            for(int i = 0; i < gotoTable.length; i++)
            {
                length = s.readInt();
                gotoTable[i] = new int[length][][];
                for(int j = 0; j < gotoTable[i].length; j++)
                {
                    length = s.readInt();
                    gotoTable[i][j] = new int[length][3];
                    for(int k = 0; k < gotoTable[i][j].length; k++)
                    {
                        for(int l = 0; l < 3; l++)
                        {
                            gotoTable[i][j][k][l] = s.readInt();
                        }
                    }
                }
            }

            // read accept
            length = s.readInt();
            accept = new int[length][];
            for(int i = 0; i < accept.length; i++)
            {
                length = s.readInt();
                accept[i] = new int[length];
                for(int j = 0; j < accept[i].length; j++)
                {
                    accept[i][j] = s.readInt();
                }
            }

            s.close();
        }
        catch(Exception e)
        {
            throw new RuntimeException("The file \"lexer.dat\" is either missing or corrupted.");
        }
    }
}
