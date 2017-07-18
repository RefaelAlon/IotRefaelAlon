import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.enums.AWB;
import com.hopding.jrpicam.enums.DRC;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RasPiCamera {

    private String saveDir = "C:\\Users\\Adi\\Desktop\\temp";

    public File takePhoto(){

        RPiCamera piCamera = null;
        try {

            piCamera = new RPiCamera(saveDir);
        } catch (FailedToRunRaspistillException e) {
            e.printStackTrace();
        }
        // Take a still image and save it
        if (piCamera != null)
            try {
                return shootBufferedStill(piCamera);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
    }


    private File shootBufferedStill(RPiCamera piCamera) throws IOException, InterruptedException {
        piCamera.setAWB(AWB.AUTO). // Change Automatic White Balance setting to automatic
                setDRC(DRC.OFF) 			// Turn off Dynamic Range Compression
                .setContrast(100) 			// Set maximum contrast
                .setSharpness(100)		    // Set maximum sharpness
                .setQuality(100) 		    // Set maximum quality
                .setTimeout(1000)		    // Wait 1 second to take the image
                .turnOnPreview()            // Turn on image preview
                .setEncoding(com.hopding.jrpicam.enums.Encoding.PNG); // Change encoding of images to PNG

        BufferedImage buffImg = piCamera.takeBufferedStill(650, 650); // Take image and store in BufferedImage
        File saveFile = new File(saveDir + "LN.png"); // Create file to save image
        ImageIO.write(buffImg, "png", saveFile); // Save image to file
        System.out.println("New PNG image saved to:\n\t" + saveFile.getAbsolutePath());
// Print out location of image

        return saveFile;
    }
}
