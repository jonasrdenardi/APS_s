package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class JPDesenho extends JPanel {

    public BufferedImage bufferedImage;//buffer

    public JPDesenho() {
        super(true);//bufferstrategy '2', acelera a atualizaçao da imagem
        int width = 800;
        int height = 600;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);// cria um buffer com o tamanho e o tipo definido, outro tipo comum BufferedImage.TYPE_INT_RGB
        addMouseListener(new AcaoMouse());//acao de clike
        addMouseMotionListener(new AcaoMouse());//acao de arrastar (drag)
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE); //seleciona cor de fundo
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());//pinta tudo com a cor selecionada
        g.drawImage(bufferedImage, 0, 0, null); // pinta o jpanel com o buffer
        g.dispose();
    }

    private class AcaoMouse implements MouseListener, MouseMotionListener {

        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_INT_ARGB);// cria um buffer com o tamanho e o tipo definido, outro tipo comum BufferedImage.TYPE_INT_RGB
            }
        }

        public void mousePressed(MouseEvent e) {
            pinta(e.getX(), e.getY());
        }

        public void mouseReleased(MouseEvent e) {
            pinta(e.getX(), e.getY());
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        public void mouseDragged(MouseEvent e) {
            pinta(e.getX(), e.getY());
        }

        public void mouseMoved(MouseEvent e) {
        }

        private void pinta(int x, int y) {
            Graphics gDoBuffer = bufferedImage.createGraphics();//pega o graphics do buffer para edicao
            gDoBuffer.setColor(Color.BLACK); //seta a cor do pincel
            gDoBuffer.fillRect(x, y, 5, 5); //desenha um ponto
            gDoBuffer.dispose();
            updateUI();//atualiza o jpanel, ou seja, "diz ao jpanel q seu desenho foi atualizado e vc qé q seja exibido"
        }
    }

    public static void main(String[] args) {

    }
}
