/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class TOpcolon extends Token
{
    public TOpcolon()
    {
        super.setText(":");
    }

    public TOpcolon(int line, int pos)
    {
        super.setText(":");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TOpcolon(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTOpcolon(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TOpcolon text.");
    }
}
