package controller;

import java.awt.Image;
import java.util.Base64;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author ADMIN
 */
public class Function {

    public String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    public String decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    public void displayImage(String path, JLabel jLabel, byte[] imagebyte) {
        ImageIcon icon;
        if (imagebyte != null) {
            icon = new ImageIcon(imagebyte);
        } else {
            icon = new ImageIcon(path);
        }

        int labelWidth = jLabel.getWidth();
        int labelHeight = jLabel.getHeight();

        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(labelWidth, labelHeight, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        jLabel.setIcon(scaledIcon);
    }

    public void displayIcon(int width, int height, String imagePath, JLabel label) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        Image img = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        label.setIcon(new ImageIcon(img));
    }
}
