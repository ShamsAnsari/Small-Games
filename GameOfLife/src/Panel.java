import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class Panel extends JPanel {

    private JFrame jFrame;
    private Button startButton;
    private Timer paintTimer;
    private Timer gameTimer;
    private ConcurrentHashMap<Location, Cell> grid = new ConcurrentHashMap<>();

    private int cellSize;
    private int numCells;
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    private Queue<Cell> setAlive = new LinkedList<Cell>();
    private Queue<Cell> setDead = new LinkedList<Cell>();


    public Panel() {

        setCellSize(20);
        setWidth(1280);
        setHeight(700);

        //JPanel
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        setVisible(true);
        setBackground(Color.WHITE);

        //Button
        startButton = new Button("start");
        startButton.setBounds(0, 700, 100, 20);
        startButton.addActionListener(e -> {
            if (!gameTimer.isRunning()) {
                gameTimer.start();
                startButton.setLabel("stop");
            } else {
                gameTimer.stop();
                startButton.setLabel("start");
            }
        });

        //JFrame
        jFrame = new JFrame("Grid");
        jFrame.add(this);
        jFrame.pack();
        jFrame.setLayout(null);
        jFrame.setSize(new Dimension(1280, 750));
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.add(startButton);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked(coordinateToLocation(e.getX(), e.getY()));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }


        });
        setPaintTimer(new Timer(100, e -> {
            if (e.getSource() == getPaintTimer()) {
                repaint();
            }
        }));
        setGameTimer(new Timer(500, e -> {
            update();
        }));

        paintTimer.start();
        fillCells();


    }


    private void fillCells() {
        for (int r = 0; r < getNumRows(); r++) {
            for (int c = 0; c < getNumCols(); c++) {
                grid.put(new Location(r, c), new Cell());
            }
        }
    }

    public void clicked(Location loc) {
        grid.get(loc).setAlive(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawCells(g);
        drawGrid(g);

    }

    private void update() {

        for (Location loc : grid.keySet()) {
            Cell cell = grid.get(loc);
            int aliveNeighbors = getNumAliveNeighbors(loc);
            if (cell.isAlive()) {

                if (aliveNeighbors <= 1) {
                    setDead.add(cell);
                }
                if (aliveNeighbors >= 4) {
                    setDead.add(cell);
                }
            } else {
                if (aliveNeighbors == 3) {
                    setAlive.add(cell);
                }
            }
        }

        for (Cell c : setAlive) {
            c.setAlive(true);
        }
        for (Cell c : setDead) {
            c.setAlive(false);
        }
        setAlive.clear();
        setDead.clear();

    }

    public int getNumAliveNeighbors(Location loc) {
        int numAlive = 0;
        ArrayList<Cell> neighbors = getNeighbors(loc);

        for (Cell neighbor : neighbors) {
            if (neighbor.isAlive()) {
                numAlive++;
            }
        }
        return numAlive;
    }

    public ArrayList<Location> getAliveLocation() {
        ArrayList<Location> alive = new ArrayList<>();
        for (Location loc : grid.keySet()) {
            if (grid.get(loc).isAlive()) {
                alive.add(loc);
            }
        }
        return alive;
    }

    public ArrayList<Cell> getNeighbors(Location loc) {
        ArrayList<Cell> neighbors = new ArrayList<>();

        int[][] adj = new int[][]{
                {-1, 0},//N
                {-1, 1},//NE
                {0, 1},//E
                {1, 1,},//SE
                {1, 0},//S
                {1, -1},//SW
                {0, -1},//W
                {-1, -1},//NW

        };

        for (int[] dir : adj) {
            Location newLoc = new Location(loc.getRow() + dir[0], loc.getCol() + dir[1]);
            if (isValid(newLoc)) {
                neighbors.add(grid.get(newLoc));
            }
        }
        return neighbors;
    }

    private boolean isValid(Location loc) {
        if (loc.getRow() >= 0 &&
                loc.getCol() >= 0 &&
                loc.getRow() < getNumRows() && loc.getCol() < getNumCols()) {
            return true;
        }
        return false;
//        return loc.getRow() < getNumRows() && loc.getRow() > -1 && loc.getCol() < getNumCols() && loc.getCol() > -1;
    }


    public void drawCells(Graphics g) {
        for (Location location : grid.keySet()) {
            drawCell(location, grid.get(location), g);
        }
    }

    public void drawCell(Location loc, Cell cell, Graphics g) {
        Color color = Color.DARK_GRAY;
        if (cell.isAlive()) {
            color = Color.ORANGE;
        }
        g.setColor(color);
        int x = getCellSize() * loc.getCol();
        int y = getCellSize() * loc.getRow();


        g.fillRect(0, 0, getCellSize(), getCellSize());
        g.fillRect(x, y, getCellSize(), getCellSize());

    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.BLACK);
        for (int r = 0; r < getNumRows(); r++) {
            int x1 = 0;
            int y1 = r * getCellSize();
            int x2 = getWidth();
            int y2 = y1;
            g.drawLine(x1, y1, x2, y2);
        }

        for (int c = 0; c < getNumCols(); c++) {
            int x1 = c * getCellSize();
            int y1 = 0;
            int x2 = x1;
            int y2 = getHeight();
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public Location coordinateToLocation(int x, int y) {
        int row = y / cellSize;
        int col = x / cellSize;

        return new Location(row, col);
    }


    //GETTERS AND SETTERS
    public int getNumRows() {
        return getHeight() / getCellSize();
    }

    public int getCellSize() {
        return cellSize;
    }

    public int getNumCols() {
        return getWidth() / getCellSize();
    }

    public int getNumCells() {
        return getNumRows() * getNumCols();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public static void main(String[] args) {
        new Panel();
    }

    public Timer getPaintTimer() {
        return paintTimer;
    }

    public void setPaintTimer(Timer paintTimer) {
        this.paintTimer = paintTimer;
    }

    public void setCellSize(int cellSize) {
        this.cellSize = cellSize;
    }

    public void setNumCells(int numCells) {
        this.numCells = numCells;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public void setNumCols(int numCols) {
        this.numCols = numCols;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Timer getGameTimer() {
        return gameTimer;
    }

    public void setGameTimer(Timer gameTimer) {
        this.gameTimer = gameTimer;
    }
}







