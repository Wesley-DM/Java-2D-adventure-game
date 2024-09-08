package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
    
    private static final long serialVersionUID = 1L;

    // Screen settings
    final int originalTileSize = 24; // 12x12
    final int scale = 4;

    public int tileSize = originalTileSize * scale; // 96x96 pixels
    public int maxScreenCol = 8;
    public int maxScreenRow = 6;
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    
    public CollisionDetector cDetector = new CollisionDetector(this);
    
    public Player player = new Player(this, keyH);

    // Track initial tile size for zoom reference
    int initialTileSize = tileSize;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.blue);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // Zoom in/out logic with console output
    public void zoomInOut(int i) {
        int oldWorldWidth = tileSize * maxWorldCol;

        // Set the minimum and maximum tile size
        int minTileSize = 50; // adjust this as needed
        int maxTileSize = 100; // adjust this as needed

        // Update tileSize with the zoom increment, ensuring it stays within the limits
        tileSize = Math.max(minTileSize, Math.min(tileSize + i, maxTileSize));

        int newWorldWidth = tileSize * maxWorldCol;

        // Update player speed based on the new world width
        player.speed = (double)newWorldWidth / 2400;

        double multiplier = (double)newWorldWidth / oldWorldWidth;

        // Adjust player position according to the new zoom level
        player.WorldX *= multiplier;
        player.WorldY *= multiplier;

        // Calculate zoom factor relative to initial size
        double zoomFactor = (double)tileSize / initialTileSize;

        // Print zoom information to the console
        System.out.println("Current Tile Size: " + tileSize + "px");
        System.out.println("Zoom Factor: " + zoomFactor + "x");
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCounter = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCounter++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCounter);
                drawCounter = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2); 
        player.draw(g2);

        g2.dispose();
    }
}
