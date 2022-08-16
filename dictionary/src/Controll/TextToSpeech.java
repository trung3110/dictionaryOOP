package Controll;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TextToSpeech {
    public static void speakText(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin16");
        if (voice != null) {
            voice.allocate();
        }
        try {
            assert voice != null;
            voice.setRate(120);
            voice.setPitch(120);
            voice.setVolume(10);
            voice.speak(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}