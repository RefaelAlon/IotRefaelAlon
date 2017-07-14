import java.io.File;


/**
 * Created by Adi on 07-Jul-17.
 */
public class Manager {

    private DBHandler dbHandler;
    private RasPiCamera rasPiCamera;
    private ImageProcessor imageProcessor;

    /**
     * Ctor, inits all its class memnbers
     */
    public Manager() {
        this.dbHandler = new DBHandler();
        this.rasPiCamera = new RasPiCamera();
        this.imageProcessor = new ImageProcessor();
    }

    /**
     * Starts the flow
     */
    public void start() {

        boolean isValid = false;

        //Takes photo
        File fileFromCamera = rasPiCamera.takePhoto();
        if(fileFromCamera != null){
            //Fetch lincense plate number from image
            String licensePlateToCheck = imageProcessor.fetchLicensePlateNumber(fileFromCamera);
            if(licensePlateToCheck != null){
                //validate if the number exists in the csv file
                isValid = dbHandler.isExist(licensePlateToCheck);
            }

        }

        //print if valid
        System.out.println("License " + (isValid ? "is" : "isn't") + " valid!");

    }

}
