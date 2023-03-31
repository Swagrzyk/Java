import javax.swing.*;//
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;//do myszy
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;//keylistener-z klawiatury
import java.util.Random;
import java.util.logging.XMLFormatter;

public class gra extends JPanel implements KeyListener, ActionListener {
    private int x=300, y=900,wh=75;//ustawienia gracza
    private int speed;
    private int speedwrog;
    private Timer time;//odswierzanie
    private int ey=0;
    private Random liczba =new Random();
    private int wrogx1=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private int wrogx2=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private int wrogx3=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private int wWroga=100, szWroga=100;
    private float punkty;


    private void chooseDifficult() {
        time.stop();
        chooseDifficulty();
    }
    private void chooseDifficulty() {
        JFrame frame = new JFrame("GRA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 1));

        JLabel label = new JLabel("Wybierz poziom trudności");
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);

        JButton easyButton = new JButton("łatwy");
        easyButton.addActionListener(e -> {
            speedwrog = 5;
            speed=20;
            reset();
            frame.dispose();
        });
        panel.add(easyButton);

        JButton mediumButton = new JButton("średni");
        mediumButton.addActionListener(e -> {
            speedwrog = 6;
            speed=25;
            reset();
            frame.dispose();
        });
        panel.add(mediumButton);

        JButton hardButton = new JButton("trudny");
        hardButton.addActionListener(e -> {
            speedwrog = 10;
            reset();
            speed=50;
            frame.dispose();
        });
        panel.add(hardButton);

        JButton exitButton = new JButton("Wyjdź");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);

    }
    public gra(){
        chooseDifficulty();
        setFocusable(true);//czy moze byc zaznaczony przez uzytkownika
        setFocusTraversalKeysEnabled(false);//wlaczenie lub wylaczenie klawiszy nawigacyjnych tab+shift np
        addKeyListener(this);//odnosi sie do klasy gra
        while (speedwrog == 0) {//blokuje uruchomienie sie gry przed wybraniem poziomu
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        time = new Timer(10,this);//10 ms dzieje sie cos w actione performed
        time.start();
    }
    public void paint(Graphics g){
        //plansza
        g.setColor(Color.black);
        g.fillRect(0,0,1200,1000);
        //gracz
        g.setColor(Color.blue);
        g.fillRect(x,y,wh,wh);
        //wrog
        g.setColor(Color.red);
        g.fillRect(wrogx1,ey,wWroga,szWroga);
        g.fillRect(wrogx2,ey,wWroga,szWroga);
        g.fillRect(wrogx3,ey,wWroga,szWroga);
        //punkty
        g.setColor(Color.yellow);
        setFont(new Font("serif",Font.BOLD,20));
        g.drawString("punkty"+punkty,600,20);
    }
    @Override

    public void actionPerformed(ActionEvent e) {
        repaint();//ciagle odswierza sie obraz
        ey += speedwrog;
        Rectangle gracz = new Rectangle(x,y,wh,wh);
        Rectangle wrog1 = new Rectangle(wrogx1,ey,wWroga,szWroga);
        Rectangle wrog2 = new Rectangle(wrogx2,ey,wWroga,szWroga);
        Rectangle wrog3 = new Rectangle(wrogx3,ey,wWroga,szWroga);
        if(gracz.intersects(wrog1)||gracz.intersects(wrog2)||gracz.intersects(wrog3)){
            time.stop();
            int choice = JOptionPane.showOptionDialog(this,
                    "Game over! Co chcesz zrobić?",
                    "Koniec gry",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    new Object[]{"Zagraj ponownie", "Wróć do menu wyboru poziomu trudności"},
                    "Zagraj ponownie");

            // wykonanie wybranego działania
            if (choice == 0) { // zagraj ponownie
                reset();
            } else if (choice == 1) { // wróć do menu wyboru poziomu trudności
                chooseDifficult();
            }

        }

        if (ey == 900) {
            ey = 10;
            wrogx1 = liczba.nextInt(12) * 100;
            wrogx3 = liczba.nextInt(12) * 100;
            wrogx2 = liczba.nextInt(12) * 100;
            punkty++;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_A) {
                if (x == 0) {
                    x = 0;
                } else {
                    x -= speed;//porusza sie w lewo
                    System.out.println(x);

                }
            }

            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (x == 1100) {
                    x = 1100;
                } else {
                    x += speed;//porusza sie w prawo
                    System.out.println(x);
                }

            }

        if(e.getKeyCode()==KeyEvent.VK_W){
            if (y ==15 ) {
                y = 15;
            } else {

                y -= speed;//porusza sie do tylu
                System.out.println(y);
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            if (y ==475){
                y=475;
            }
                else{
                    y += speed;//porusza sie do przodu
                System.out.println(y);
                }
        }
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                reset();
            }
        }
    private void reset() {
        ey=10;
        punkty=0;
        wrogx1 =liczba.nextInt(12)*100;
        wrogx3 =liczba.nextInt(12)*100;
        wrogx2 =liczba.nextInt(12)*100;
        x=300;
        time.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}