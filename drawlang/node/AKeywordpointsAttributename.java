/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class AKeywordpointsAttributename extends PAttributename
{
    private TKeywordpoints _keywordpoints_;

    public AKeywordpointsAttributename()
    {
        // Constructor
    }

    public AKeywordpointsAttributename(
        @SuppressWarnings("hiding") TKeywordpoints _keywordpoints_)
    {
        // Constructor
        setKeywordpoints(_keywordpoints_);

    }

    @Override
    public Object clone()
    {
        return new AKeywordpointsAttributename(
            cloneNode(this._keywordpoints_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAKeywordpointsAttributename(this);
    }

    public TKeywordpoints getKeywordpoints()
    {
        return this._keywordpoints_;
    }

    public void setKeywordpoints(TKeywordpoints node)
    {
        if(this._keywordpoints_ != null)
        {
            this._keywordpoints_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._keywordpoints_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._keywordpoints_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._keywordpoints_ == child)
        {
            this._keywordpoints_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._keywordpoints_ == oldChild)
        {
            setKeywordpoints((TKeywordpoints) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}