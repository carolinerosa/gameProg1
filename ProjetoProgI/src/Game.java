/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eduardo
 */
import java.awt.Color;
import jplay.Window;
import jplay.Scene;
import jplay.Keyboard;
import jplay.GameImage;
import jplay.Time;
import java.awt.Font;
import jplay.Sound;
class Game {
    static Window window;
    static Scene scene;
    static Keyboard keyboard;
    static GameImage fundo;
    int tempoAtual;
    Time tempo;
    Font fonteTempo,fontePont;
    boolean pausado;
    static MenuFrame menu;
    private static Sound musica;
    static Player player;
    
    void carregar(int volumeMusica){
        window = MenuInicial.janela;
        scene = new Scene();
        scene.loadFromFile("fases/fase1.scn");
        fundo = new GameImage("images/fundo.png");
        scene.setDrawStartPos(-30, 470);
        keyboard = window.getKeyboard();
        tempo = new Time(900,900,false);
        tempo.setSecond(200);
        fonteTempo = new Font("Super Mario Bros. 3 Regular", Font.TRUETYPE_FONT, 40);
        fontePont = new Font("Super Mario Bros. 3 Regular", Font.TRUETYPE_FONT, 20);
        keyboard.setBehavior(Keyboard.ENTER_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
        keyboard.setBehavior(Keyboard.ESCAPE_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
        menu = new MenuFrame();
        musica = new Sound("sons/musica1.wav");
        musica.setVolume(volumeMusica);
        player = new Player(30,0,350);        
    }
    private void executar(boolean playMusic){
        boolean continuar = true;
        if(playMusic){
        musica.play();
        }
        player.play();
        while(continuar){
            scene.draw();
            window.drawText((int) tempo.getTotalSecond() + "", 650, 40, Color.WHITE, fonteTempo);
            window.drawText(player.getPontuacao()+"",150,40,Color.WHITE, fontePont);
            player.update();
            player.draw(); 
            atualizarVidas();
            window.update();
            if(keyboard.keyDown(Keyboard.ESCAPE_KEY)){
                abrirMenu();
            }
        }
    }
    private void fechar(){
        window.exit();
    }
    public Game(boolean executarMusica,int volumeMusica,int dificuldade){
        carregar(volumeMusica);
        executar(executarMusica);
        fechar();
    }

    private void abrirMenu() {
        tempoAtual = (int) tempo.getTotalSecond();
        pausado = true;
        menu.setVisible(true);
        musica.pause();
        while(menu.isVisible()){
            
        }
        musica.play();
        tempo.setTime(0,0,tempoAtual);
    }
    void setPausado(boolean b) {
        pausado = b;
    }
    static void exitGame() {
        window.exit();
    }

    private void atualizarVidas() {
        for (int i = 0; i < player.getVidas(); i++) {
            player.hearts[i].x = 10+ i*25;
            player.hearts[i].y = 10;            
            player.hearts[i].draw();
        }
    }
}
