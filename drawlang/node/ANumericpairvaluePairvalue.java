/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class ANumericpairvaluePairvalue extends PPairvalue
{
    private PNumeric _numeric_;

    public ANumericpairvaluePairvalue()
    {
        // Constructor
    }

    public ANumericpairvaluePairvalue(
        @SuppressWarnings("hiding") PNumeric _numeric_)
    {
        // Constructor
        setNumeric(_numeric_);

    }

    @Override
    public Object clone()
    {
        return new ANumericpairvaluePairvalue(
            cloneNode(this._numeric_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANumericpairvaluePairvalue(this);
    }

    public PNumeric getNumeric()
    {
        return this._numeric_;
    }

    public void setNumeric(PNumeric node)
    {
        if(this._numeric_ != null)
        {
            this._numeric_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._numeric_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._numeric_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._numeric_ == child)
        {
            this._numeric_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._numeric_ == oldChild)
        {
            setNumeric((PNumeric) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
