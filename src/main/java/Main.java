import com.ryan.gengine.Version1.display.CardImages;
import com.ryan.gengine.Version1.display.CivilWarDisplay;
import com.ryan.gengine.Version1.display.DraggableDisplayTest;
import com.ryan.gengine.Version1.display.GridWarDisplay;
import com.ryan.gengine.Version1.impl.StdInInput;
import com.ryan.gengine.Version1.impl.WarGame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

class MainClass extends JPanel {

    public void paint(Graphics g) {
        g.setColor(Color.red);
        Graphics clippedGraphics = g.create();
        clippedGraphics.clipRect(25, 25, 50, 50);
        clippedGraphics.fillRect(0, 0, 100, 100);
        clippedGraphics.dispose();
        clippedGraphics = null;
        g.drawLine(0, 100, 100, 0);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.getContentPane().add(new MainClass());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }
}

public class Main {
    public static int sum(List<Integer> cards) {
        if (cards.size() == 1) {
            return cards.get(0);
        } else {
            return cards.get(0) + sum(cards.subList(1, cards.size()));
        }
    }

    public static boolean wins(Stack<Integer> cards, int threshold) throws EmptyStackException {
        List<Integer> hand = new ArrayList<>();
        hand.add(cards.pop());
        hand.add(cards.pop());
        while (sum(hand) < threshold) {
            hand.add(cards.pop());
        }
        return sum(hand) < 21;
    }

    public static int[] blackJackHand(int threshold) {

        int wins, losses;
        wins = losses = 0;
        for (int x = 0; x < 100; x++) {
            Stack<Integer> cards = new Stack<>();
            for (int i = 0; i < 52; i++) {
                cards.push(i % 13);
            }
            Collections.shuffle(cards);
            while (!cards.empty()) {
                try {
                    int fluff = (wins(cards, threshold) ? wins++ : losses++);
                } catch (EmptyStackException e) {
                    break;
                }

            }
        }
        return new int[]{wins, losses};
    }

    public static void war() {
        CardImages.init();
        Dimension tileDimension = new Dimension(60, 60);
        Dimension boardDimension = new Dimension(5, 13);
        Dimension gameDimension = new Dimension((tileDimension.width * boardDimension.width) + 15,
                (tileDimension.height * boardDimension.height) + 35);


        WarGame game = new WarGame();
        GridWarDisplay gridWarDisplay =
                new GridWarDisplay("War Game", boardDimension, tileDimension, gameDimension);
        Thread displayThread = new Thread(gridWarDisplay, "Display Thread");
        game.registerOutput(gridWarDisplay);


        StdInInput stdInInput = new StdInInput();
        Thread inputThread = new Thread(stdInInput, "Input Thread");
        game.registerInput(stdInInput);

        displayThread.start();
        inputThread.start();
        game.begin();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        /*for (int i = 12; i < 21; i++) {
            int[] results = blackJackHand(i);
            System.out.println("At threshold " + i + ", results are " + results[0] + " to " + results[1] +
                    ", or a " + ((results[0] + 0.0f) / (results[0] + results[1])) + " success rate");
        }
        */
        //ButtonTest bt = new ButtonTest();
        //war();

        DraggableDisplayTest d = new DraggableDisplayTest("Draggable Test", new Dimension(400, 400));
        new Thread(d).start();

    }


    public static void oldwar() {

        WarGame game = new WarGame();
        //game.registerOutput(new SimplePrintOutput());
        CivilWarDisplay c = new CivilWarDisplay();

        JFrame frame = new JFrame("War Display");

        frame.setLayout(new BorderLayout());
        frame.add(c);


        frame.setSize(4 * c.squareSize + 15, 14 * c.squareSize + 37);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        StdInInput stdInInput = new StdInInput();
        Thread thread1 = new Thread(stdInInput, "Input Thread");
        Thread thread2 = new Thread(c, "Display Thread");
        game.registerOutput(c);
        game.registerInput(stdInInput);
        thread1.start();
        thread2.start();
        game.begin();
    }
}
