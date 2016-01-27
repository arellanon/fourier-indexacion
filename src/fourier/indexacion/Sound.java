package fourier.indexacion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.tritonus.sampled.convert.PCM2PCMConversionProvider;

public class Sound {
	
	boolean running = false;
	
	private static AudioFormat getFormat() {
		float sampleRate = 44100;
		int sampleSizeInBits = 8;
		int channels = 1; // mono
		boolean signed = true;
		boolean bigEndian = true;
		return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
	}
	
	public byte [] listen( String filePath  ) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		AudioInputStream in = null;
		AudioInputStream din = null;
		AudioInputStream outDin = null;
		
		PCM2PCMConversionProvider conversionProvider = new PCM2PCMConversionProvider();
		
		File file = new File(filePath);
		try {
			in = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		AudioFormat baseFormat = in.getFormat();
		System.out.println(baseFormat.toString());
		
		AudioFormat decodedFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
				baseFormat.getChannels() * 2, baseFormat.getSampleRate(),
				false);

		din = AudioSystem.getAudioInputStream(decodedFormat, in);	

		if (!conversionProvider.isConversionSupported(getFormat(), decodedFormat)) {
			System.out.println("Conversion is not supported");
		}

		outDin = conversionProvider.getAudioInputStream(getFormat(), din);
		final AudioInputStream outDinSound = outDin;

		running = true;
		int n = 0;
		byte[] buffer = new byte[(int) 1024];

		try {
			int count = 0;
			while (count != -1) {
				n++;
				if (n > 10000)
					break;				
				count = outDinSound.read(buffer, 0, 1024);
				if (count > 0) {
					out.write(buffer, 0, count);
				}
			}
		} catch (IOException e) {
			System.err.println("I/O problems: " + e);
			System.exit(-1);
		}
		
		byte audio[] = out.toByteArray();
		return audio;	
	}
	

}
