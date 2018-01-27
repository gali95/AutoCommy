package logic.base.classes;

import java.awt.*;

public class Drawable {

    public enum DrawableType
    {
        UNDRAWABLE,
        WHOLE_SQUARE,
        SHARE_SQUARE;
    }

    private Image img;
    private DrawableType interactionType;
    private double drawOrder;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public DrawableType getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(DrawableType interactionType) {
        this.interactionType = interactionType;
    }

    public double getDrawOrder() {
        return drawOrder;
    }

    public void setDrawOrder(double drawOrder) {
        this.drawOrder = drawOrder;
    }
}
