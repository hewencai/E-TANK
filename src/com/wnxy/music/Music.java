package com.wnxy.music;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;



import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Music {
public static final String m1="filemusic/baozha1.wav";
public static final String m2="filemusic/bullet.wav";
public static final String m3="filemusic/beijing.wav";
//public static final String m4="filemusic/startBMG.wav";
public static AudioStream as=null;
public Music() {
	
}
/**s获取声音的输入流播放*/
public static void soundEffect(String path){
	try {
		FileInputStream  fis=new FileInputStream(path);
		as=new AudioStream(fis);
		AudioPlayer.player.start(as);
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
