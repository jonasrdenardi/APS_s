package model;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class Som{
     AudioClip tiro = Applet.newAudioClip(getClass().getResource("/som/tiro.wav"));
     AudioClip explosaoInimigos = Applet.newAudioClip(getClass().getResource("/som/explosaoinimigos.wav"));
     AudioClip explosaoNave = Applet.newAudioClip(getClass().getResource("/som/explosaoNave.wav"));
     AudioClip fundo = Applet.newAudioClip(getClass().getResource("/som/fundo.wav"));
    
    public void somAtirar(boolean isSom){
        tiro.stop();
        if(isSom == true){        
        tiro.play();
        }else{
            tiro.stop();
        }
    }
    
    public void somExplodirInimigo(boolean isSom){
        explosaoInimigos.stop();
        AudioClip som;
        URL url = getClass().getResource("/som/explosaoinimigos.wav");
        som = Applet.newAudioClip(url);
        if(isSom == true){        
        explosaoInimigos.play();
        }else{
            explosaoInimigos.stop();
        }
        
    }
    
    public void somExplodirNave(boolean isSom){        
        explosaoNave.stop();
        if(isSom == true){        
        explosaoNave.play();
        }else{
            explosaoNave.stop();
        }
    }
    
    public void somFundo(boolean isSom){
        fundo.stop();
        if(isSom == true){        
        fundo.loop();
        }else{
            fundo.stop();
        }
    }
    
}
