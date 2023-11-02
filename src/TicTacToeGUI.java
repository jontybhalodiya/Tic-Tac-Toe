import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeGUI {
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private JFrame frame;
    private JButton[][] buttons;
    private JButton restartButton;
    private JLabel turnLabel; // JLabel to display the current player's turn

    public TicTacToeGUI() {
        frame = new JFrame("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 750);
        frame.setLayout(new BorderLayout()); // Use BorderLayout to organize components

        buttons = new JButton[3][3];

        initializeBoard();
        createButtons();
        createRestartButton();
        createTurnLabel(); // Create the JLabel for displaying player's turn
        frame.setVisible(true);
    }

    private void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = ' ';
            }
        }
    }

    private void createButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 36));
                buttonPanel.add(buttons[row][col]);
                final int r = row;
                final int c = col;

                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleButtonClick(r, c);
                    }
                });
            }
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
    }

    private void createRestartButton() {
        restartButton = new JButton("Restart");
        restartButton.setFont(new Font("Arial", Font.PLAIN, 24));
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRestartClick();
            }
        });

        JPanel restartPanel = new JPanel();
        restartPanel.add(restartButton);

        frame.add(restartPanel, BorderLayout.SOUTH);
        restartButton.setEnabled(false);
    }

    private void createTurnLabel() {
        turnLabel = new JLabel("Player " + currentPlayer + "'s turn", JLabel.CENTER);
        turnLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        frame.add(turnLabel, BorderLayout.NORTH);
    }

    private void handleButtonClick(int row, int col) {
        if (board[row][col] == ' ' && !isGameEnded()) {
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer));
            buttons[row][col].setEnabled(false);

            if (hasPlayerWon(currentPlayer)) {
                JOptionPane.showMessageDialog(frame, "Player " + currentPlayer + " has won!");
                restartButton.setEnabled(true);
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(frame, "Game Draw!");
                restartButton.setEnabled(true);
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                updateTurnLabel(); // Update the turnLabel with the current player's turn
            }
        }
    }

    private void handleRestartClick() {
        initializeBoard();
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
        restartButton.setEnabled(false);
        currentPlayer = 'X';
        updateTurnLabel(); // Update the turnLabel with the starting player's turn
    }

    private boolean isGameEnded() {
        return hasPlayerWon('X') || hasPlayerWon('O') || isBoardFull();
    }

    private boolean hasPlayerWon(char player) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true; // Row win
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true; // Column win
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true; // Diagonal win (top-right to bottom-left)
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateTurnLabel() {
        turnLabel.setText("Player " + currentPlayer + "'s turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGUI();
            }
        });
    }
}
