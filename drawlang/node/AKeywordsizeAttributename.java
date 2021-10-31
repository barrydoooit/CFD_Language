/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class AKeywordsizeAttributename extends PAttributename
{
    private TKeywordsize _keywordsize_;

    public AKeywordsizeAttributename()
    {
        // Constructor
    }

    public AKeywordsizeAttributename(
        @SuppressWarnings("hiding") TKeywordsize _keywordsize_)
    {
        // Constructor
        setKeywordsize(_keywordsize_);

    }

    @Override
    public Object clone()
    {
        return new AKeywordsizeAttributename(
            cloneNode(this._keywordsize_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAKeywordsizeAttributename(this);
    }

    public TKeywordsize getKeywordsize()
    {
        return this._keywordsize_;
    }

    public void setKeywordsize(TKeywordsize node)
    {
        if(this._keywordsize_ != null)
        {
            this._keywordsize_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._keywordsize_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._keywordsize_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._keywordsize_ == child)
        {
            this._keywordsize_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._keywordsize_ == oldChild)
        {
            setKeywordsize((TKeywordsize) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}