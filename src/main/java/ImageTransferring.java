import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImageTransferring {


    public static   JLabel readImage(String urlString){
        JLabel pic=new JLabel();
        try {
        URL url=new URL(urlString);
        BufferedImage bufferedImage= ImageIO.read(url);
        int width=bufferedImage.getWidth();
        int height=bufferedImage.getHeight();
        pic=minimizeImage(width,height,bufferedImage);
    } catch (
    IOException e) {
        e.printStackTrace();
    }
        return pic;
    }

    private static JLabel minimizeImage(int width,int height,BufferedImage bufferedImage){
        while (width>Constants.WIN_WIDTH/2-Constants.TEXT_FIELD_WIDTH/2||height>Constants.WIN_HEIGHT-Constants.TOP_MARGIN){
            width= (int) (width*0.9);
            height=(int) (height*0.9);
        }
        JLabel pic=new JLabel();
        pic.setBounds(0,0,width,height);
        System.out.println(width);
        System.out.println(height);
        Image image=bufferedImage.getScaledInstance(width,height, Image.SCALE_AREA_AVERAGING);
        pic.setIcon(new ImageIcon(image));
        return pic;
    }
}
