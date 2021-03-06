/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class AAttrlistContentlist extends PContentlist
{
    private PAttributelist _attributelist_;

    public AAttrlistContentlist()
    {
        // Constructor
    }

    public AAttrlistContentlist(
        @SuppressWarnings("hiding") PAttributelist _attributelist_)
    {
        // Constructor
        setAttributelist(_attributelist_);

    }

    @Override
    public Object clone()
    {
        return new AAttrlistContentlist(
            cloneNode(this._attributelist_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAttrlistContentlist(this);
    }

    public PAttributelist getAttributelist()
    {
        return this._attributelist_;
    }

    public void setAttributelist(PAttributelist node)
    {
        if(this._attributelist_ != null)
        {
            this._attributelist_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._attributelist_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._attributelist_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._attributelist_ == child)
        {
            this._attributelist_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._attributelist_ == oldChild)
        {
            setAttributelist((PAttributelist) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
