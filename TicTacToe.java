package new_game;

//public class TicTacToe {
//
//}
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private char currentSymbol;
    private JButton[] buttons;

    public TicTacToe() {
        currentSymbol = 'X';
        buttons = new JButton[9];
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setText("");
            buttons[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton buttonClicked = (JButton)e.getSource();
                    buttonClicked.setText(String.valueOf(currentSymbol));
                    buttonClicked.setEnabled(false);
                    checkForWin();
                    switchSymbol();
                }
            });

            add(buttons[i]);
        }

        setVisible(true);
    }

    public void switchSymbol() {
        currentSymbol = (currentSymbol == 'X') ? 'O' : 'X';
    }

    public void checkForWin() {
        // check horizontal lines
        for (int i = 0; i < 9; i += 3)
            if (checkLine(i, i+1, i+2))
                endGame(buttons[i].getText());

        // check vertical lines
        for (int i = 0; i < 3; ++i)
            if (checkLine(i, i+3, i+6))
                endGame(buttons[i].getText());

        // check the diagonals
        if (checkLine(0, 4, 8) || checkLine(2, 4, 6))
            endGame(buttons[4].getText());
    }

    public boolean checkLine(int a, int b, int c) {
        return buttons[a].getText().equals(buttons[b].getText()) &&
               buttons[b].getText().equals(buttons[c].getText()) &&
               !buttons[a].getText().equals("");
    }

    public void endGame(String winner) {
        JOptionPane.showMessageDialog(this, String.format("%s wins", winner), "Game Over", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
