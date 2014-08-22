package king.jaiden.util;

import static org.lwjgl.openal.AL10.*;

import java.io.FileNotFoundException;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;

public abstract class Audio {
	/** Sources are points emitting sound. */
	protected IntBuffer sources;

	/** Buffers hold sound data. */
	protected IntBuffer buffer;
	
	public Audio(int num_buffers){
		sources = BufferUtils.createIntBuffer(num_buffers);
		buffer = BufferUtils.createIntBuffer(num_buffers);
		alGenBuffers(buffer);
		try{initSounds();}catch(FileNotFoundException e){e.printStackTrace();}
		alGenSources(sources);
		setupSounds();
	}
	public abstract void initSounds() throws FileNotFoundException;
	/* 
	 * Here's an example in case I forget...
	 * WaveData data = WaveData.create(new BufferedInputStream(new FileInputStream("res/sounds/FancyPants.wav")));
	 * alBufferData(buffer.get(0), data.format, data.data, data.samplerate);
	 * data.dispose(); 
	 */
	public abstract void setupSounds();
	/*
	 * alSourcei(sauces.get(0), AL_BUFFER, buffer.get(0));
	 */
	public void play(int source){
		alSourcePlay(sources.get(source));
	}
	public void pause(int source){
		alSourcePause(sources.get(source));
	}
	public void stop(int source){
		alSourceStop(sources.get(source));
	}
}
