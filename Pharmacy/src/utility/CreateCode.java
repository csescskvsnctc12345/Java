package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.ITFWriter;
import com.google.zxing.qrcode.QRCodeWriter;

public class CreateCode {

	private String id;
	private String pass;

	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * QRコードを生成する。
	 */
	public void createQR(String id){
	    try {
	      String contents = id;
	      if(contents.length() < 14){
	    	  for(int i = 0; contents.length() < 14; i++){
	    		  contents = contents + "0";
	    	  }
	      }
	      BarcodeFormat format = BarcodeFormat.QR_CODE;
	      int width = 160;
	      int height = 160;

	      QRCodeWriter writer = new QRCodeWriter();
	      BitMatrix bitMatrix = writer.encode(contents, format, width, height);
	      BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
	      ImageIO.write(image, "png", new FileOutputStream("C:/work/jv22/hal_work/WebContent/img/qrcode"+id+".png"));
	      System.out.println("QRできたやで");
	    }
	    catch( IOException e ) {
	      e.printStackTrace();
	    }
	    catch (WriterException e) {
	      e.printStackTrace();
	    }
	}
	/**
	 * バーコードを生成する。
	 */
  public void createBarcode(String id) {
    try {
      String contents = id;
      if(contents.length() < 14){
    	  for(int i = 0; contents.length() < 14; i++){
    		  contents = contents + "0";
    	  }
      }
      BarcodeFormat format = BarcodeFormat.ITF;
      int width = 200;
      int height = 50;

      ITFWriter writer = new ITFWriter();
      BitMatrix bitMatrix = writer.encode(contents, format, width, height);
      BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
      ImageIO.write(image, "png", new FileOutputStream("C:/work/jv22/hal_work/WebContent/img/barcode"+id+".png"));
      System.out.println("バーコードできたやで →" + contents);
    }
    catch( IOException e ) {
      e.printStackTrace();
    }
    catch (WriterException e) {
      e.printStackTrace();
    }
  }


}
