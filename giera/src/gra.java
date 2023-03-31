import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class gra extends JPanel implements KeyListener, ActionListener {
    private int x=300;
    private int y=875;
    private final int wh=75;//ustawienia gracza
    private int speed;
    private int speedwrog;
    private final Timer time;//odswierzanie
    private int ey=0;
    public  int wybor;
    private final Random liczba =new Random();
    private int wrogx1=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private int wrogx2=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private int wrogx3=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private int wrogx4=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private int wrogx5=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private int wrogx6=liczba.nextInt(12)*100;//6 bo rozmiar okna to 600
    private final int wWroga=100;
    private final int szWroga=100;
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

        JPanel panel = new JPanel(new GridLayout(5, 1));
        JLabel label = new JLabel("Wybierz poziom trudności " );
        JLabel labelv1 = new JLabel(" " );
        JLabel labelv2 = new JLabel("                                 Sterowanie strzałkami zwieksza" );
        JLabel labelv3 = new JLabel("predkosc poruszania sie X2 " );

        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        panel.add(labelv1);


        JButton easyButton = new JButton("łatwy");
        easyButton.addActionListener(e -> {
            wybor=1;
            speedwrog = 10;
            speed=20;
            reset();
            frame.dispose();
        });
        panel.add(easyButton);

        JButton mediumButton = new JButton("średni");
        mediumButton.addActionListener(e -> {
            wybor=2;
            speedwrog = 14;
            speed=25;
            frame.dispose();
            reset();
        });
        panel.add(mediumButton);

        JButton hardButton = new JButton("trudny");
        hardButton.addActionListener(e -> {
            wybor=3;
            speedwrog = 18;
            speed=50;
            frame.dispose();
            reset();
        });
        panel.add(hardButton);

        JButton exitButton = new JButton("Wyjdź");
        exitButton.addActionListener(e -> System.exit(0));
        panel.add(exitButton);
        panel.add(labelv2);
        panel.add(labelv3);
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
        if(wybor==1) {
            g.setColor(Color.red);
            g.fillRect(wrogx1, ey, wWroga, szWroga);
            g.fillRect(wrogx2, ey, 100, 100);
            g.fillRect(wrogx3, ey, 100, 100);
        }else if (wybor==2){
            g.setColor(Color.red);
            g.fillRect(wrogx1, ey, wWroga, szWroga);
            g.fillRect(wrogx2, ey, 100, 100);
            g.fillRect(wrogx3, ey, 100, 100);
            g.fillRect(wrogx4, ey, 100, 100);

        }else if (wybor==3){
            g.setColor(Color.red);
            g.fillRect(wrogx1, ey, wWroga, szWroga);
            g.fillRect(wrogx2, ey, 100, 100);
            g.fillRect(wrogx3, ey, 100, 100);
            g.fillRect(wrogx4, ey, 100, 100);
            g.fillRect(wrogx5, ey, 100, 100);
            g.fillRect(wrogx6, ey, 100, 100);

        }
        //punkty
        g.setColor(Color.yellow);
        setFont(new Font("serif",Font.BOLD,20));
        g.drawString("punkty"+punkty,580,20);
    }
    @Override

    public void actionPerformed(ActionEvent e) {
        repaint();//ciagle odswierza sie obraz
        ey += speedwrog;
        Rectangle gracz = new Rectangle(x,y,wh,wh);
        if(wybor==1) {
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
                        new Object[]{"Zagraj ponownie", "Wróć do menu wyboru poziomu trudności","Wyjdz z gry"},
                        "Zagraj ponownie");

                // wykonanie wybranego działania
                if (choice == 0) { // zagraj ponownie
                    reset();
                } else if (choice == 1) { // wróć do menu wyboru poziomu trudności
                    chooseDifficult();
                }else if(choice ==2){
                    System.exit(0);
                }

            }
        }else if(wybor==2) {
            Rectangle wrog1 = new Rectangle(wrogx1,ey,wWroga,szWroga);
            Rectangle wrog2 = new Rectangle(wrogx2,ey,wWroga,szWroga);
            Rectangle wrog3 = new Rectangle(wrogx3,ey,wWroga,szWroga);
            Rectangle wrog4 = new Rectangle(wrogx4,ey,wWroga,szWroga);

            if(gracz.intersects(wrog1)||gracz.intersects(wrog2)||gracz.intersects(wrog3)||gracz.intersects(wrog4)){
                time.stop();
                int choice = JOptionPane.showOptionDialog(this,
                        "Game over! Co chcesz zrobić?",
                        "Koniec gry",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Zagraj ponownie", "Wróć do menu wyboru poziomu trudności","Wyjdz z gry"},
                        "Zagraj ponownie");

                // wykonanie wybranego działania
                if (choice == 0) { // zagraj ponownie
                    reset();
                } else if (choice == 1) { // wróć do menu wyboru poziomu trudności
                    chooseDifficult();
                }else if(choice ==2){
                    System.exit(0);
                }

            }
        } else if (wybor==3) {
            Rectangle wrog1 = new Rectangle(wrogx1,ey,wWroga,szWroga);
            Rectangle wrog2 = new Rectangle(wrogx2,ey,wWroga,szWroga);
            Rectangle wrog3 = new Rectangle(wrogx3,ey,wWroga,szWroga);
            Rectangle wrog4 = new Rectangle(wrogx4,ey,wWroga,szWroga);
            Rectangle wrog5 = new Rectangle(wrogx5,ey,wWroga,szWroga);
            Rectangle wrog6 = new Rectangle(wrogx6,ey,wWroga,szWroga);
            if(gracz.intersects(wrog1)||gracz.intersects(wrog2)||gracz.intersects(wrog3)||gracz.intersects(wrog4)||gracz.intersects(wrog5)||gracz.intersects(wrog6)){
                time.stop();
                int choice = JOptionPane.showOptionDialog(this,
                        "Game over! Co chcesz zrobić?",
                        "Koniec gry",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        new Object[]{"Zagraj ponownie", "Wróć do menu wyboru poziomu trudności","Wyjdz z gry"},
                        "Zagraj ponownie");

                // wykonanie wybranego działania
                if (choice == 0) { // zagraj ponownie
                    reset();
                } else if (choice == 1) { // wróć do menu wyboru poziomu trudności
                    chooseDifficult();
                }else if(choice ==2){
                    System.exit(0);
                }

            }

        }


        if (ey >= 850) {
            ey=0;
            wrogx1 = liczba.nextInt(12) * 100;
            wrogx3 = liczba.nextInt(12) * 100;
            wrogx2 = liczba.nextInt(12) * 100;
            wrogx4 = liczba.nextInt(12) * 100;
            wrogx5 = liczba.nextInt(12) * 100;
            wrogx6 = liczba.nextInt(12) * 100;
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
                }
            }

            if (e.getKeyCode() == KeyEvent.VK_D) {
                if (x == 1100) {
                    x = 1100;
                } else {
                    x += speed;//porusza sie w prawo

                }

            }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (x == 0) {
                x = 0;
            } else {
                x -= 2*speed;//porusza sie w lewo
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (x == 1100) {
                x = 1100;
            } else {
                x += 2*speed;//porusza sie w prawo

            }

        }

        if(e.getKeyCode()==KeyEvent.VK_W){
            if (y ==15 ) {
                y = 15;
            } else {
                y -= speed;//porusza sie do tylu
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_S){
            if (y ==875){
                y=875;
            }
                else{
                    y += speed;//porusza sie do przodu
                }
        }
        if(e.getKeyCode()==KeyEvent.VK_UP){
            if (y ==15 ) {
                y = 15;
            } else {
                y -= 2*speed;//porusza sie do tylu
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_DOWN){
            if (y ==875){
                y=875;
            }
            else{
                y += 2*speed;//porusza sie do przodu
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
        wrogx4 =liczba.nextInt(12)*100;
        wrogx5 =liczba.nextInt(12)*100;
        wrogx6 =liczba.nextInt(12)*100;
        x=600;
        time.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}