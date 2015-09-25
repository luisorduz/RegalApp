package regalapp.co.regalapp.regaControlador;

/*
* Esta activity cumple la funcion de reducir la imagen al tamaño ideal
* en cuestion de su espacio
* */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Luis Orduz on 14/01/15.
 */


public class autoimagen extends ImageView
{

    public autoimagen(Context context)
    {
        super(context);
    }

    public autoimagen(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public autoimagen(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

       // Si no hay dibujable sólo podemos usar el resultado de super.
        if (getDrawable() == null)
            return;

        final int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);

        int w = getDrawable().getIntrinsicWidth();
        int h = getDrawable().getIntrinsicHeight();
        if (w <= 0)
            w = 1;
        if (h <= 0)
            h = 1;

        // Relación de aspecto del contenido de la vista (no incluyendo las protecciones)
        float desiredAspect = (float) w / (float) h;

        //Se nos permite cambiar el ancho de la vista
        boolean resizeWidth = widthSpecMode != MeasureSpec.EXACTLY;

        //Se nos permite cambiar la altura de la vista
        boolean resizeHeight = heightSpecMode != MeasureSpec.EXACTLY;

        int pleft = getPaddingLeft();
        int pright = getPaddingRight();
        int ptop = getPaddingTop();
        int pbottom = getPaddingBottom();

        // Obtener el tamaño que ImageView decidido.
        int widthSize = getMeasuredWidth();
        int heightSize = getMeasuredHeight();

        if (resizeWidth && !resizeHeight)
        {
            // Cambiar el tamaño de la anchura a la altura, el mantenimiento de la relación de aspecto.
            int newWidth = (int) (desiredAspect * (heightSize - ptop - pbottom)) + pleft + pright;
            setMeasuredDimension(newWidth, heightSize);
        }
        else if (resizeHeight && !resizeWidth)
        {
            int newHeight = (int) ((widthSize - pleft - pright) / desiredAspect) + ptop + pbottom;
            setMeasuredDimension(widthSize, newHeight);
        }
    }
}