/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class TKeywordrect extends Token
{
    public TKeywordrect()
    {
        super.setText("Rectangle");
    }

    public TKeywordrect(int line, int pos)
    {
        super.setText("Rectangle");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TKeywordrect(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTKeywordrect(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TKeywordrect text.");
    }
}