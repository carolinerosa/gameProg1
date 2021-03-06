/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Eduardo
 */
import jplay.Keyboard;
import jplay.Sound;
import jplay.Window;
import jplay.Mouse;
import jplay.GameImage;

public class MenuInicial {

    public static Window janela;
    private static GameImage fundo;
    private static Keyboard teclado;
    private static Mouse mouse;
    private static Sound musica, somOpcao;
    private static Button botaoSair, botaoLogin, botaoConfig, botaoStart;
    private static boolean playerLogou = false;
    private static boolean loginAberto = false;
    private static boolean configAberto = false;
    static LoginFrame login;
    static ConfigFrame config;
    private static Game jogo;
    static int volumeMusica,dificuldade;

    public static void main(String[] args) {
        janela = new Window(800, 600);
        carregar();
        executar();
        startGame();
    }

    private static void carregar() {
        janela.setCursor(janela.createCustomCursor("images/cursor.png"));
        fundo = new GameImage("images/menuinicial.png");
        teclado = janela.getKeyboard();
        teclado.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
        teclado.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
        mouse = janela.getMouse();
        mouse.setBehavior(Mouse.BUTTON_RIGHT, Mouse.DETECT_INITIAL_PRESS_ONLY);
        musica = new Sound("sons/Super Mario Theme.wav");
        musica.setRepeat(true);
        musica.play();
        botaoSair = new Button("images/exit_icon.png", 770, 10);
        botaoLogin = new Button("images/login_button.png", 300, 400);
        botaoStart = new Button("images/start_button.png", 300, 400);
        botaoConfig = new Button("images/config_button.png", 730, 10);
        login = new LoginFrame();
        login.setVisible(false);
        config = new ConfigFrame();
    }

    private static void desenhar() {
        fundo.draw();
        botaoSair.draw();
        botaoStart.draw();
        if (!playerLogou) {
            botaoLogin.draw();
        }
        botaoConfig.draw();
        janela.update();
    }

    private static void startGame() {
        musica.stop();
       jogo = new Game(musica.isExecuting(),volumeMusica,dificuldade);
       musica = null;
    }

    private static void executar() {
        boolean sair = false;
        do {
            desenhar();
            if (mouse.isOverObject(botaoSair) && mouse.isLeftButtonPressed()) {
                fechar();
            }
            if (mouse.isOverObject(botaoConfig) && mouse.isLeftButtonPressed() && (!configAberto)) {
                configAberto = true;
                config.setVisible(true);
            }
            if (mouse.isOverObject(botaoLogin) && mouse.isLeftButtonPressed() && (!playerLogou) && (!loginAberto)) {
                loginAberto = true;
                login.setVisible(true);
            }
            if (mouse.isOverObject(botaoStart) && mouse.isLeftButtonPressed() && playerLogou) {
                sair = true;
            }

        } while (sair == false);
    }

    public static void fechar() {
        janela.exit();
    }

    public static void setLogou(boolean b) {
        playerLogou = b;
        if(b == true){
            botaoLogin.x = 1000;
            botaoLogin.y = 1000;
        }
    }

    public static void setLoginAberto(boolean z) {
        loginAberto = z;
    }

    static void setVolumeMusica(int vol) {
        musica.setVolume(vol);
        volumeMusica = vol;
    }

    static void setConfigAberto(boolean w) {
        configAberto = w;
    }

    static void desligarMusica() {
        musica.pause();
    }

    static void ligarMusica() {
            musica.play();
    }
    static void setDificuldade(int nivel){
        dificuldade = nivel;
    }
}
