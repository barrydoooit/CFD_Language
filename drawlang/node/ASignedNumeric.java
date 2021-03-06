/* This file was generated by SableCC (http://www.sablecc.org/). */

package drawlang.node;

import drawlang.analysis.*;

@SuppressWarnings("nls")
public final class ASignedNumeric extends PNumeric
{
    private PSign _sign_;
    private TNumber _number_;

    public ASignedNumeric()
    {
        // Constructor
    }

    public ASignedNumeric(
        @SuppressWarnings("hiding") PSign _sign_,
        @SuppressWarnings("hiding") TNumber _number_)
    {
        // Constructor
        setSign(_sign_);

        setNumber(_number_);

    }

    @Override
    public Object clone()
    {
        return new ASignedNumeric(
            cloneNode(this._sign_),
            cloneNode(this._number_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASignedNumeric(this);
    }

    public PSign getSign()
    {
        return this._sign_;
    }

    public void setSign(PSign node)
    {
        if(this._sign_ != null)
        {
            this._sign_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._sign_ = node;
    }

    public TNumber getNumber()
    {
        return this._number_;
    }

    public void setNumber(TNumber node)
    {
        if(this._number_ != null)
        {
            this._number_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._number_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._sign_)
            + toString(this._number_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._sign_ == child)
        {
            this._sign_ = null;
            return;
        }

        if(this._number_ == child)
        {
            this._number_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._sign_ == oldChild)
        {
            setSign((PSign) newChild);
            return;
        }

        if(this._number_ == oldChild)
        {
            setNumber((TNumber) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
