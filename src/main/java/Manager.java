import java.io.File;


/**
 * Created by Adi on 07-Jul-17.
 */
public class Manager {

    private DBHandler dbHandler;
    private RasPiCamera rasPiCamera;
    private ImageProcessor imageProcessor;

    public Manager(){
        this.dbHandler = new DBHandler();
        this.rasPiCamera = new RasPiCamera();
        this.imageProcessor = new ImageProcessor();
    }

    public void start(){
        File fileFromCamera = rasPiCamera.takePhoto();
        File fileFromDB = dbHandler.getPhotoFromDB();
        imageProcessor.compareImage(fileFromCamera, fileFromDB);
    }

}
