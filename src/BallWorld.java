// Denna klass implementerar ett enkelt program i Java. En boll
// studsar inom en given rektangel. Din uppgift blir att utöka detta
// program med möjlighet till fler bollar, möjlighet att ändra
// storleken på rektangeln etc.

// Följande rader är till för att Java skall kunna förstå och tolka
// användandet av standardklasser som Color och JApplet.
//
// Alla klasser och underpaket i paketen java.awt, java.awt.event samt
// javax.swing kan nu refereras direkt.

import java.awt.*;          
import java.awt.event.*;    
import javax.swing.*;       

// Ball. Denna klass beskriver konceptet boll. En boll har en position
// (givet av en koordinat (x,y)), en hastighet (givet av en
// differential (dx,dy)) och en färg.

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

    // Färg 
    private Color color;

    // Begränsande rektangulära område inom vilket bollen studsar 
    private Rectangle box;

    // Konstruktion av nya bollar kräver position och riktning 
    public Ball( int x0, int y0, int dx0, int dy0 ) {
        x = x0;
        y = y0;
        dx = dx0;
        dy = dy0;

        color = defaultColor;
        diameter = defaultDiameter;
    }

    // Sätt ny färg 
    public void setColor( Color c ) {
        color = c;
    }

    // Sätt nytt begränsande rektangulärt område 
    public void setBoundingBox( Rectangle r ) {
        box = r;
    }

    // Rita ut en boll på givet grafiskt område 
    public void paint( Graphics g ) {
        // Byt till bollens färg 
        g.setColor( color );

        // Bollen representeras som en fylld cirkel, dvs en ellips (oval)
        // med lika höjd och bredd 
        g.fillOval( x, y, diameter, diameter );
    }

    // Begränsa bollen inom det rektangulära området. Uppdatera hastigheten
    // om det behövs.
    void constrain() {
        // Ge absoluta koordinater för det rektangulära området
        int x0 = box.x;
        int y0 = box.y;
        int x1 = x0 + box.width - diameter;
        int y1 = y0 + box.height - diameter;

        // Ändra hastighet och riktning om bollen är utanför det
        // rektangulära området
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

// Klassen BallPanel definierar en rityta där bollarna ritas upp. Den
// ärver klassen JPanel och implementerar ActionListener. Genom att
// implementera ActionListener kan man låta en Timer med jämna
// mellanrum ge ett 'tick' då uppdatering av panelen ska göras.

class BallPanel extends JPanel implements ActionListener {
    // Bredd och höjd  
    private int width, height;

    // En boll 
    private Ball ball;

    // Timer. Skickar en signal var 50e millisekund till panelen som
    // skickas med som ActionListener.

    private Timer timer = new Timer(50, this);

    // Initiera attributen
    public BallPanel (int width, int height) {
        // Ta reda på bredd och höjd för ritytan
        this.width = width;
        this.height = height;

        // Skapa en ny boll
        ball = new Ball( width / 10, height / 5, 5, 5 );

        // Sätt bollens rektangulära begränsande område (bounding box)
        ball.setBoundingBox( new Rectangle( 0, 0, width, height ) );
        
        // Starta timern.
        timer.start();
    }

    // Uppdatera (anropas vid omritning, repaint())
    public void paintComponent( Graphics g ) {
        // Rensa hela ritytan (med svart färg)
        
        g.setColor( Color.black );
        g.fillRect( 0, 0, width, height );

        // Rita ut bollen (på svart bakgrund)
        ball.paint( g );
    }

    // När vi får en signal från timern... 
    public void actionPerformed(ActionEvent e) {
        if(width != getWidth() || height != getHeight())
            wasResized(getWidth(),getHeight());
        ball.action();  // Gör vad som är relevant med bollen
        repaint();      // Gör automatiskt ett anrop till
                        // paintComponent()
    }

    // Anropas om fönstret ändrar storlek
    public void wasResized( int newWidth, int newHeight ) {
        width = newWidth;
        height = newHeight;
    }
}


// Denna klass definierar det fönster som skapas av programmet. Ett
// fönster (JFrame) skapas där en instans av BallPanel (ritytan)
// placeras.

public class BallWorld extends JFrame {
    
    // Skapa en panel 
    private BallPanel panel = new BallPanel (180, 180);

    public BallWorld () {

        // Lägg till bollpanelen i mitten på ramen.
        Container c = getContentPane();
        c.add(panel, BorderLayout.CENTER);

        setSize(200, 200);
        // Gör så att ramen syns.
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Denna metod startas av Javas virtuella maskin vid anropet java
    // BallWorld
    public static void main(String args[]) {
        BallWorld world = new BallWorld();
    }
}


