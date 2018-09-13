package prj.ijava.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageToBase64 {

	public static void main(String[] args) {

		String sa = "d:/Hydrangeas.jpg"; 
		try {
			String out = new ImageToBase64().getBase64String(sa);
			System.out.println(out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBase64String( String imageUrl ) throws Exception{

		String out = "";

		String filePathName = imageUrl.replace("file:///", "");
		String fileExtName = filePathName.substring( filePathName.lastIndexOf(".") + 1);

		
		File file = new File( filePathName );
		try(FileInputStream inputStream = new FileInputStream( file );
				ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();)
		{
			if( file.exists() )
			{
				int len = 0;
				byte[] buf = new byte[1024];
				while( (len = inputStream.read( buf )) != -1 ) {
					byteOutStream.write(buf, 0, len);
				}

				byte[] fileArray = byteOutStream.toByteArray();
				String core = new String( Base64.getEncoder().encodeToString(fileArray) );

				out = "data:image/" + fileExtName + " ;base64, "+ core;	
			}
			
			return out;
		}
		catch( IOException e )
		{
			e.printStackTrace();
			throw e;
		}

	}
}


