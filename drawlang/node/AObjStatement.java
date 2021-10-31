/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class AObjStatement extends PStatement
{
    private PObject _object_;

    public AObjStatement()
    {
        // Constructor
    }

    public AObjStatement(
        @SuppressWarnings("hiding") PObject _object_)
    {
        // Constructor
        setObject(_object_);

    }

    @Override
    public Object clone()
    {
        return new AObjStatement(
            cloneNode(this._object_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAObjStatement(this);
    }

    public PObject getObject()
    {
        return this._object_;
    }

    public void setObject(PObject node)
    {
        if(this._object_ != null)
        {
            this._object_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._object_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._object_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._object_ == child)
        {
            this._object_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._object_ == oldChild)
        {
            setObject((PObject) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
