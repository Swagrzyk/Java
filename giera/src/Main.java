import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        gra Gra= new gra();
        JFrame jf = new JFrame("GRA");// OKNO Z GRA (Z TYTULEM)
        jf.setBounds(200,200,1200,1000);//wymiary
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//co sie dzieje jesli sie wylaczy
        jf.setResizable(false);//nie wolno zmieniac rozmiaru
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);//zeby widoczne bylo
        jf.add(Gra);//wyswietla w tym oknie a nie w nowym
    }
}