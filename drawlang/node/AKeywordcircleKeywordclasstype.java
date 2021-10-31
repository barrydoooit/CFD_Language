/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class AKeywordcircleKeywordclasstype extends PKeywordclasstype
{
    private TKeywordcircle _keywordcircle_;

    public AKeywordcircleKeywordclasstype()
    {
        // Constructor
    }

    public AKeywordcircleKeywordclasstype(
        @SuppressWarnings("hiding") TKeywordcircle _keywordcircle_)
    {
        // Constructor
        setKeywordcircle(_keywordcircle_);

    }

    @Override
    public Object clone()
    {
        return new AKeywordcircleKeywordclasstype(
            cloneNode(this._keywordcircle_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAKeywordcircleKeywordclasstype(this);
    }

    public TKeywordcircle getKeywordcircle()
    {
        return this._keywordcircle_;
    }

    public void setKeywordcircle(TKeywordcircle node)
    {
        if(this._keywordcircle_ != null)
        {
            this._keywordcircle_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._keywordcircle_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._keywordcircle_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._keywordcircle_ == child)
        {
            this._keywordcircle_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._keywordcircle_ == oldChild)
        {
            setKeywordcircle((TKeywordcircle) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}