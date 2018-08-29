package nvd.hasan.imagecrop;

public class ImageCoordinates {
    private float leftX;
    private float topY;
    private float rightX;
    private float bottomY;
    private String btnName;

    public ImageCoordinates(float leftX, float topY, float rightX, float bottomY, String btnName) {
        this.leftX = leftX;
        this.topY = topY;
        this.rightX = rightX;
        this.bottomY = bottomY;

        this.btnName = btnName;

    }

    public float getLeftX() {
        return leftX;
    }

    public void setLeftX(float leftX) {
        this.leftX = leftX;
    }

    public float getTopY() {
        return topY;
    }

    public void setTopY(float topY) {
        this.topY = topY;
    }

    public float getRightX() {
        return rightX;
    }

    public void setRightX(float rightX) {
        this.rightX = rightX;
    }

    public float getBottomY() {
        return bottomY;
    }

    public void setBottomY(float bottomY) {
        this.bottomY = bottomY;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }
}
