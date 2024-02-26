import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;
public class Game implements ActionListener,KeyListener
{
    JFrame frame;
    JPanel panel = null;
    JButton start = null;
    JButton exit = null;
    JPanel prompt = null;
    private Timer timer;
    JPanel line = null;
    JPanel hero_car;
    int gameOver=0;
    JPanel villain_car1;
    JPanel villain_car2;
    JPanel villain_car3;
    JPanel p1 = null;
    JPanel p2 = null;
    JPanel[] villain_cars = new JPanel[3];
    int car_x_pos = 210;
    int car_y_pos = 550;
    private int lineSpeed = 5; // Adjust the speed at which lines move
    private int lineSpacing = 40;
    Game()
    {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1600, 800);
        frame.setLayout(null);
        panel = new JPanel();
        hero_car = new JPanel();
        
        villain_car1 = new JPanel();
        villain_car2 = new JPanel();
        villain_car3 = new JPanel();
        
        ImageIcon image1 = new ImageIcon("red.png");
        JLabel imageLabel = new JLabel(image1);
        
        ImageIcon image2 = new ImageIcon("yel.png");
        JLabel imageLabel2 = new JLabel(image2);
        ImageIcon image3 = new ImageIcon("yel.png");
        JLabel imageLabel3 = new JLabel(image3);
        ImageIcon image4 = new ImageIcon("yel.png");
        JLabel imageLabel4 = new JLabel(image4);
        imageLabel.setOpaque(false);
        imageLabel2.setOpaque(false);
        imageLabel3.setOpaque(false);
        imageLabel4.setOpaque(false);
        hero_car.setBounds(210, 550, 77, 155);
        villain_car1.setBounds(310, 100, 77, 155);
        villain_car2.setBounds(500, 200, 77, 155);
        villain_car3.setBounds(700, 300, 77, 155);
        villain_car1.setBackground(Color.gray);
        villain_car2.setBackground(Color.gray);
        villain_car3.setBackground(Color.gray);
        hero_car.setBackground(Color.gray);
        villain_car1.add(imageLabel2);
        villain_car2.add(imageLabel3);
        villain_car3.add(imageLabel4);
        hero_car.add(imageLabel);
        // Green Panel
        p1 = new JPanel();
        p1.setBackground(Color.green);
        p1.setBounds(0,0,200,800); 
        // green Panel
        p2 = new JPanel();
        p2.setBackground(Color.green);
        p2.setBounds(1100,0,200,800); 
        ImageIcon treeIcon = new ImageIcon("C:\\Users\\tejes\\Downloads\\tej.jpg");
        JLabel tree1Label = new JLabel(treeIcon);
        p1.add(tree1Label);

        JLabel tree2Label = new JLabel(treeIcon);
        p2.add(tree2Label);

        frame.setVisible(true);
        frame.add(hero_car);
        frame.add(villain_car1);
        frame.add(villain_car2);
        frame.add(villain_car3);
        frame.add(p1);
        frame.add(p2);
        panel.setBounds(200,0,900,800);
        panel.setBackground(Color.gray);
        panel.addKeyListener(this); // Register the KeyListener with the panel
        panel.setFocusable(true);  // Enable keyboard focus on the panel
        panel.requestFocusInWindow();
        frame.setBackground(Color.green);
        prompt=new JPanel(new GridLayout(2,2,20,0));
        prompt.setBounds(500, 200, 300, 300);
        prompt.add(new JLabel("Press start to start the game"));
        prompt.add(new JLabel(" "));
        start = new JButton("start", null);
        exit = new JButton("exit", null);
        start.addActionListener(this);
        prompt.add(start);
        prompt.add(exit);
        prompt.setBackground(Color.green);
        frame.add(prompt);
        for(int i=0;i<800;i+=100)
        {
            line=new JPanel();
            line.setBounds(650, i, 20, 50);
            line.setBackground(Color.white);
            frame.add(line);
        }
        villain_cars[0]=villain_car1;
        villain_cars[1]=villain_car2;
        villain_cars[2]=villain_car3;
        frame.add(panel);
        frame.setVisible(true);
        
    }
    public static void main(String[] args)
    {
        Game game = new Game();   
    }
    @Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{ 
            int x = hero_car.getX();
            if(x-10 != 200)
            {
                hero_car.setLocation(x-10, hero_car.getY());
                car_x_pos = x-10;
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{ 
            int x = hero_car.getX();
            if(x+10 != 1000){
                hero_car.setLocation(x+10, hero_car.getY());
                car_x_pos = x+10;
            }
        }
        frame.repaint();
    }
    @Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar()=='a')
		{ // if the key pressed is 'a' then move the car left
			System.out.println("aaaaa");// decrement the xpos by 100
			
		}
		if(e.getKeyChar()=='s')
		{ // if the key pressed is 's' then move the car right
			System.out.println("ssss"); // increment the xpos by 100
		}
		
		frame.repaint();
		
	}
    @Override
    public void keyReleased(KeyEvent e)
    {

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==start)
        {
            System.out.println("hello");
            frame.remove(prompt);
            frame.revalidate();
            frame.repaint();
            moveLinePanels();
            timer = new Timer(100, this);
            timer.start();
        }
        else if (e.getSource() == timer) {
            if(gameOver==0){
                moveLinePanels();
                if(gameOver==1){
                    frame.dispose();
                    new Game();
                    return ;
                }
            }
        }
        
    }
    private void moveLinePanels() {
        
        Component[] components = frame.getContentPane().getComponents();
        int new_car_position=0,new_cary_position=0;
        for (Component component : components) {
            if (component instanceof JPanel && component != panel && component!=hero_car && component!=p1 && component!=p2) {
                int newY = component.getY() + 10;
                if (newY > frame.getHeight()) {
                    newY = -lineSpacing;
                    component.setLocation(component.getX(), newY);
                }
                else {
                    component.setLocation(component.getX(), newY);
                }
                newY = component.getY()+10;
                if(component == villain_car1 || component == villain_car2 || component == villain_car3)
                {
                    int minValue = 210; // Minimum value
                    int maxValue = 1000;
                    Random rand = new Random();
                    new_car_position= rand.nextInt((maxValue - minValue) / 100 + 1) * 100 + minValue;
                    int miny=0;
                    int maxy=10;
                    new_cary_position= rand.nextInt((maxy - miny) / 50 + 1) * 50 + miny;
                    if(newY > frame.getHeight())
                    {
                        newY =- lineSpacing;
                        component.setLocation(new_car_position, new_cary_position);
                    }
                    else{
                        component.setLocation(component.getX(), newY);
                    }
                    new_cary_position=newY;
                }
            }
            Rectangle vc = hero_car.getBounds();
            for(JPanel c:villain_cars)
            {
                Rectangle cvc = c.getBounds();
                if(vc.intersects(cvc))
                {
                    gameOver=1;
                    return;
                }
            }
        }
        frame.revalidate();
        frame.repaint();
    }
}