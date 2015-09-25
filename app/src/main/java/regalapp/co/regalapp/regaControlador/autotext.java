package regalapp.co.regalapp.regaControlador;

/*
* Esta activity cumple la funcion de reducir el texto al tamaño ideal
* en cuestion de su espacio
* */



import android.content.Context;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

/**
 * Created by Luis Orduz
 * Text view that auto adjusts text size to fit within the view.
 * If the text size equals the minimum text size and still does not
 * fit, append with an ellipsis.
 */


public class autotext extends TextView {

    // Tamaño de texto mínimo para esta textview
    public static final float MIN_TEXT_SIZE = 5;

    // Tamaño de texto mínimo para esta textview
    public static final float MAX_TEXT_SIZE = 128;

    private static final int BISECTION_LOOP_WATCH_DOG = 30;

    // Interfaz para las notificaciones de cambio de tamaño
    public interface OnTextResizeListener {
        public void onTextResize(TextView textView, float oldSize, float newSize);
    }

    //Nuestra cadena de elipse
    private static final String mEllipsis = "...";

    // Registrado oyente de cambio de tamaño
    private OnTextResizeListener mTextResizeListener;

    // forzar un cambio de tamaño
    private boolean mNeedsResize = false;

    // Tamaño del texto que se establece a partir del código. Esto actúa como un punto de partida para el cambio de tamaño
    private float mTextSize;

    // Límites superiores temporales sobre el tamaño del texto de partida
    private float mMaxTextSize = MAX_TEXT_SIZE;

    // Límites más bajos para el tamaño del texto
    private float mMinTextSize = MIN_TEXT_SIZE;

    // Ver texto interlineado multiplicador
    private float mSpacingMult = 1.0f;

    // Ver texto interlineado adicional
    private float mSpacingAdd = 0.0f;

    //Añadir puntos suspensivos al texto que se desborda en el tamaño del texto más pequeño
    private boolean mAddEllipsis = true;

    //Por defecto constructor override
    public  autotext(Context context) {
        this(context, null);
    }

    // Por defecto constructor al inflar de archivo XML
    public  autotext(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    // Por defecto constructor override
    public  autotext(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mTextSize = getTextSize();
    }

    /**
     *Cuando los cambios de texto, establezca el indicador de fuerza de cambio de tamaño en true y reajustar el tamaño del texto.
     */
    @Override
    protected void onTextChanged(final CharSequence text, final int start, final int before, final int after) {
        mNeedsResize = true;
        // Since this view may be reused, it is good to reset the text size
        resetTextSize();
    }

    /**
     * Si el tamaño de la vista de texto cambiado, ajuste el indicador de fuerza de cambio de tamaño a la verdadera
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw || h != oldh) {
            mNeedsResize = true;
        }
    }

    /**
     * Regístrese oyente para recibir notificaciones de cambio de tamaño
     * @param listener
     */
    public void setOnResizeListener(OnTextResizeListener listener) {
        mTextResizeListener = listener;
    }

    /**
     * Reemplace el tamaño del texto conjunto para actualizar nuestros valores de referencia interna
     */
    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
        mTextSize = getTextSize();
    }

    /**
     * Override the set text size to update our internal reference values
     */
    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        mTextSize = getTextSize();
    }

    /**
     * Reemplace el tamaño del texto conjunto para actualizar nuestros valores de referencia interna
     */
    @Override
    public void setLineSpacing(float add, float mult) {
        super.setLineSpacing(add, mult);
        mSpacingMult = mult;
        mSpacingAdd = add;
    }

    /**
     * Establecer el límite de tamaño de texto superior e invalidar la vista
     * @param maxTextSize
     */
    public void setMaxTextSize(float maxTextSize) {
        mMaxTextSize = maxTextSize;
        requestLayout();
        invalidate();
    }

    /**
     * Volver límite de tamaño de texto superior
     * @return
     */
    public float getMaxTextSize() {
        return mMaxTextSize;
    }

    /**
     * Establezca el límite inferior de tamaño de texto e invalidar la vista
     * @param minTextSize
     */
    public void setMinTextSize(float minTextSize) {
        mMinTextSize = minTextSize;
        requestLayout();
        invalidate();
    }

    /**
     * Volver límite inferior el tamaño del texto
     * @return
     */
    public float getMinTextSize() {
        return mMinTextSize;
    }

    /**
     * Establecer la bandera para añadir puntos suspensivos al texto que se desborda en el tamaño del texto más pequeño
     * @param addEllipsis
     */
    public void setAddEllipsis(boolean addEllipsis) {
        mAddEllipsis = addEllipsis;
    }

    /**
     * Vuelta a añadir puntos suspensivos al texto que se desborda en el tamaño del texto más pequeño
     * @return
     */
    public boolean getAddEllipsis() {
        return mAddEllipsis;
    }

    /**
     * Cambiar el texto a su tamaño original
     */
    public void resetTextSize() {
        if(mTextSize > 0) {
            super.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            //mMaxTextSize = mTextSize;
        }
    }

    /**
     * Cambiar el tamaño de texto después de medir
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if(changed || mNeedsResize) {
            int widthLimit = (right - left) - getCompoundPaddingLeft() - getCompoundPaddingRight();
            int heightLimit = (bottom - top) - getCompoundPaddingBottom() - getCompoundPaddingTop();
            resizeText(widthLimit, heightLimit);
        }
        super.onLayout(changed, left, top, right, bottom);
    }


    /**
     * Redimensionar el tamaño del texto con la anchura y la altura por defecto
     */
    public void resizeText() {
        int heightLimit = getHeight() - getPaddingBottom() - getPaddingTop();
        int widthLimit = getWidth() - getPaddingLeft() - getPaddingRight();
        resizeText(widthLimit, heightLimit);
    }

    /**
     *Redimensionar el tamaño del texto con la anchura y altura especificadas
     * @param width
     * @param height
     */
    public void resizeText(int width, int height) {
        CharSequence text = getText();
        // No cambie el tamaño si la vista no tiene dimensiones, o no hay ningún texto
        if(text == null || text.length() == 0 || height <= 0 || width <= 0 || mTextSize == 0) {
            return;
        }

        // Obtenga objeto pintura de la vista de texto
        TextPaint textPaint = getPaint();

        // Guarde el tamaño del texto actual
        float oldTextSize = textPaint.getTextSize();

        // Método de bisección: rápida y precisa
        float lower = mMinTextSize;
        float upper = mMaxTextSize;
        int loop_counter=1;
        float targetTextSize = (lower+upper)/2;
        int textHeight = getTextHeight(text, textPaint, width, targetTextSize);
        while(loop_counter < BISECTION_LOOP_WATCH_DOG && upper - lower > 1) {
            targetTextSize = (lower+upper)/2;
            textHeight = getTextHeight(text, textPaint, width, targetTextSize);
            if(textHeight > height)
                upper = targetTextSize;
            else
                lower = targetTextSize;
            loop_counter++;
        }

        targetTextSize = lower;
        textHeight = getTextHeight(text, textPaint, width, targetTextSize);

        //Si hubiéramos llegado a nuestro tamaño de texto mínimo y aún así no encaja, añadir puntos suspensivos
        if(mAddEllipsis && targetTextSize == mMinTextSize && textHeight > height) {
            // Dibujar usando un diseño estático
            // Modificación: utilizar una copia del TextPaint para medir
            TextPaint paintCopy = new TextPaint(textPaint);
            paintCopy.setTextSize(targetTextSize);
            StaticLayout layout = new StaticLayout(text, paintCopy, width, Alignment.ALIGN_NORMAL, mSpacingMult, mSpacingAdd, false);
            // Compruebe que tenemos una sola línea de texto representado menos
            if(layout.getLineCount() > 0) {
                // A partir de la línea en la posición vertical específica sería cortado,
                 // Tenemos que recortar hasta la línea anterior
                int lastLine = layout.getLineForVertical(height) - 1;
                // Si el texto no sería ni siquiera cabe en una sola línea, claro que
                if(lastLine < 0) {
                    setText("");
                }
                // De lo contrario, ajuste a la línea anterior y añade unos puntos suspensivos
                else {
                    int start = layout.getLineStart(lastLine);
                    int end = layout.getLineEnd(lastLine);
                    float lineWidth = layout.getLineWidth(lastLine);
                    float ellipseWidth = paintCopy.measureText(mEllipsis);

                    //Recorte personajes fuera hasta que tengamos suficiente espacio para dibujar los puntos suspensivos
                    while(width < lineWidth + ellipseWidth) {
                        lineWidth = paintCopy.measureText(text.subSequence(start, --end + 1).toString());
                    }
                    setText(text.subSequence(0, end) + mEllipsis);
                }
            }
        }

        // Algunos dispositivos intentan auto ajustar el espaciado de línea, por lo que el interlineado vigor defecto
        // Y invalidará el diseño como un efecto secundario
        setTextSize(TypedValue.COMPLEX_UNIT_PX, targetTextSize);
        setLineSpacing(mSpacingAdd, mSpacingMult);

        // Notificar al oyente si está registrado
        if(mTextResizeListener != null) {
            mTextResizeListener.onTextResize(this, oldTextSize, targetTextSize);
        }

        // Restablecer la fuerza del flag de cambio de tamaño
        mNeedsResize = false;
    }

    // Ajuste el tamaño del texto del objeto pintura texto y utilizar un diseño estático para hacer pantalla de texto antes de la medición
    private int getTextHeight(CharSequence source, TextPaint originalPaint, int width, float textSize) {
        // Modificación: hacer una copia del objeto TextPaint original para medir
        // (Al parecer el objeto es modificado durante la medición, véase también el
       // Docs para TextView.getPaint () (que establece para acceder a ella sólo lectura)
        TextPaint paint = new TextPaint(originalPaint);
        //Actualizar el objeto pintura texto
        paint.setTextSize(textSize);
        // Medir el uso de un diseño estático
        StaticLayout layout = new StaticLayout(source, paint, width, Alignment.ALIGN_NORMAL, mSpacingMult, mSpacingAdd, true);
        return layout.getHeight();
    }

}