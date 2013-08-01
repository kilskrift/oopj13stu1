// Denna klass implementerar ett enkelt program i Java. En boll
// studsar inom en given rektangel. Din uppgift blir att ut�ka detta
// program med m�jlighet till fler bollar, m�jlighet att �ndra
// storleken p� rektangeln etc.

// F�ljande rader �r till f�r att Java skall kunna f�rst� och tolka
// anv�ndandet av standardklasser som Color och JApplet.
//
// Alla klasser och underpaket i paketen java.awt, java.awt.event samt
// javax.swing kan nu refereras direkt.

import java.awt.*;          
import java.awt.event.*;    
import javax.swing.*;       

// Ball. Denna klass beskriver konceptet boll. En boll har en position
// (givet av en koordinat (x,y)), en hastighet (givet av en
// differential (dx,dy)) och en f�rg.

class Ball {
    // Standardkonstanter (for alla instanser av Ball) 
    static int        defaultDiameter  = 10;
    static Color      defaultColor     = Color.yellow;
    static Rectangle  defaultBox       = new Rectangle(0,0,100,100);

    // Position 
    private int x, y;

    // Hastighet och riktning 
    private int dx, dy;

    // Diameter (storlek) 
    private int diameter;

    // F�rg 
    private Color color;

    // Begr�nsande rektangul�ra omr�de inom vilket bollen studsar 
    private Rectangle box;

    // Konstruktion av nya bollar kr�ver position och riktning 
    public Ball( int x0, int y0, int dx0, int dy0 ) {
        x = x0;
        y = y0;
        dx = dx0;
        dy = dy0;

        color = defaultColor;
        diameter = defaultDiameter;
    }

    public void setDiameter( int newDiameter ) {
        this.diameter = newDiameter;
    }

    // S�tt ny f�rg 
    public void setColor( Color c ) {
        color = c;
    }

    // S�tt nytt begr�nsande rektangul�rt omr�de 
    public void setBoundingBox( Rectangle r ) {
        box = r;
    }

    // Rita ut en boll p� givet grafiskt omr�de 
    public void paint( Graphics g ) {
        // Byt till bollens f�rg 
        g.setColor( color );

        // Bollen representeras som en fylld cirkel, dvs en ellips (oval)
        // med lika h�jd och bredd 
        g.fillOval( x, y, diameter, diameter );
    }

    // Begr�nsa bollen inom det rektangul�ra omr�det. Uppdatera hastigheten
    // om det beh�vs.
    void constrain() {
        // Ge absoluta koordinater f�r det rektangul�ra omr�det
        int x0 = box.x;
        int y0 = box.y;
        int x1 = x0 + box.width - diameter;
        int y1 = y0 + box.height - diameter;

        // �ndra hastighet och riktning om bollen �r utanf�r det
        // rektangul�ra omr�det
        if (x < x0)
            dx = Math.abs(dx);
        if (x > x1)
            dx = -Math.abs(dx);
        if (y < y0)
            dy = Math.abs(dy);
        if (y > y1)
            dy = -Math.abs(dy);
    }

    // Flytta bollen med aktuell riktning och hastighet ett steg 
    public void action() {
        x = x + dx;
        y = y + dy;

        constrain();
    }
}

// Klassen BallPanel definierar en rityta d�r bollarna ritas upp. Den
// �rver klassen JPanel och implementerar ActionListener. Genom att
// implementera ActionListener kan man l�ta en Timer med j�mna
// mellanrum ge ett 'tick' d� uppdatering av panelen ska g�ras.

class BallPanel extends JPanel implements ActionListener {
    // Bredd och h�jd  
    private int width, height;

    // En boll 
    private Ball ball;

    // Timer. Skickar en signal var 50e millisekund till panelen som
    // skickas med som ActionListener.

    private Timer timer = new Timer(50, this);

    // Initiera attributen
    public BallPanel (int width, int height) {
        // Ta reda p� bredd och h�jd f�r ritytan
        this.width = width;
        this.height = height;

        // Skapa en ny boll
        ball = new Ball( width / 10, height / 5, 5, 5 );

        ball.setColor( Color.blue ); // deluppgift 2
        ball.setDiameter( 20 ); // deluppgift 4

        // S�tt bollens rektangul�ra begr�nsande omr�de (bounding box)
        ball.setBoundingBox( new Rectangle( 0, 0, width, height ) );
        
        // Starta timern.
        timer.start();
    }

    // Uppdatera (anropas vid omritning, repaint())
    public void paintComponent( Graphics g ) {
        // Rensa hela ritytan (med svart f�rg)
        
        g.setColor( Color.black );
        g.fillRect( 0, 0, width, height );

        // Rita ut bollen (p� svart bakgrund)
        ball.paint( g );
    }

    // N�r vi f�r en signal fr�n timern... 
    public void actionPerformed(ActionEvent e) {
        if(width != getWidth() || height != getHeight())
            wasResized(getWidth(),getHeight());
        ball.action();  // G�r vad som �r relevant med bollen
        repaint();      // G�r automatiskt ett anrop till
                        // paintComponent()
    }

    // Anropas om f�nstret �ndrar storlek
    public void wasResized( int newWidth, int newHeight ) {
        width = newWidth;
        height = newHeight;

        ball.setBoundingBox( new Rectangle( 0, 0, width, height ) );
    }
}


// Denna klass definierar det f�nster som skapas av programmet. Ett
// f�nster (JFrame) skapas d�r en instans av BallPanel (ritytan)
// placeras.

public class BallWorld extends JFrame {
    
    // Skapa en panel 
    private BallPanel panel = new BallPanel (180, 180);

    public BallWorld () {

        // L�gg till bollpanelen i mitten p� ramen.
        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);

        setSize(200, 200);
        // G�r s� att ramen syns.
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Denna metod startas av Javas virtuella maskin vid anropet java
    // BallWorld
    public static void main(String args[]) {
        BallWorld world = new BallWorld();
    }
}


