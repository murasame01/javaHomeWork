package View.utils;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PictureView extends JPanel {
    private Image image;
    private  Color bgColor;

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    private Color borderColor = Color.BLUE;
    public boolean isBorder = false;
    public boolean isCover = false;
    public PictureView(){

    }
    public void setBackgroundColor(Color color){
        this.bgColor = color;
        this.repaint();
    }
    public void setImage(Image image){
        this.image = image;
        this.repaint();
    }
    public void setImage(File file){
        try {
            this.image = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void setImage(String res){
        try {
            this.image = ImageIO.read(new File(res));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int width = getWidth();
        int height =getHeight();

        if(bgColor != null){
            g.setColor(bgColor);
            g.fillRect(0, 0, width, height);
        }

        //添加边框
        if(isBorder){
            g.setColor(borderColor);
            g.drawRect(0, 0, width - 2, height - 2);
        }

        if(image != null) {
            if(isCover){
                g.drawImage(image, 0, 0, width, height, null);
            }
            else {
                int imgWidth = image.getWidth(null);
                int imgHeight = image.getHeight(null);
                Rectangle rect = new Rectangle(0, 0, width, height);
                rect.grow(-2, -2);
                Rectangle fit = fitCenter(rect, imgWidth, imgHeight);
                g.drawImage(image, fit.x, fit.y, fit.width, fit.height, null);
            }
        }
    }

    private Rectangle fitCenter(Rectangle rect, int imgW, int imgH){
        int fitW = rect.width;
        int fitH = rect.width * imgH / imgW;
        if (fitH > rect.height) {
            fitH = rect.height;
            fitW = rect.height * imgW / imgH;
        }
        int fitX = rect.x + (rect.width -fitW) / 2;
        int fitY = rect.y + (rect.height-fitH) / 2;
        return new Rectangle(fitX, fitY, fitW, fitH);
    }
}
