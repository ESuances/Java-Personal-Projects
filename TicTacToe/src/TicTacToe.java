import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RectangularShape;
import javax.swing.*;

public class TicTacToe {
    int boardWidth = 600;
    int boardHeight = 600;

    JFrame frame = new JFrame("Gato / Tic-Tac-Toe ESuances"); //Creamos la ventana o UI
    JLabel textLabel = new JLabel(); //Creamos el label del titulo del juego
    JPanel textPanel = new JPanel(); //Creamos el Panel del titulo
    JPanel boardPanel = new JPanel(); //Creamos el Panel del 3 x 3 del juego

    JButton[][] board = new JButton[3][3]; // Se crea el arreglo de 3 x 3 con botones para el juego
    String playerX = "X"; //Se declara el valor del jugador X
    String playerO = "O"; //Se declara el valor del jugador O
    String currentPlayer = playerX; // Se declara el jugador inicial, este siendo X cuando empieza el programa

    boolean gameOver = false; //Se declara la variable inicial del juego.
    int turns = 0; //Declaramos esta funcion para saber si hay un tie, si se llega a 9 y no hubo un game over, entonces ya tenemos un tie

    TicTacToe(){
        frame.setVisible(true); //Hacemos la ventana visible
        frame.setSize(boardWidth, boardHeight); //Le damos un tamaño
        frame.setLocationRelativeTo(null); //No necesita estar localizada
        frame.setResizable(false); //No puede cabiarse su tamaño
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Se termina el programa cuando se cierra
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray); //Se le da un background gris obscuro al fondo del texto
        textLabel.setForeground(Color.white); //Se le coloca el color blanco a las letras
        textLabel.setFont(new Font("Arial", Font.BOLD, 50)); //Se le da el formato al texto
        textLabel.setHorizontalAlignment(JLabel.CENTER); //Se centra el texto
        textLabel.setText("Tic-Tac-Toe"); //Se coloca lo que quiere que diga el texto
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout()); //Se crea ell Layout (donde va el texto)
        textPanel.add(textLabel); //Se agrega el Texto al layout
        frame.add(textPanel, BorderLayout.NORTH); //Se agrega el layout al UI y se coloca hasta arriba.

        boardPanel.setLayout(new GridLayout(3,3)); //Se crea el grid de 3 x 3
        boardPanel.setBackground(Color.darkGray); // Se le da un background de gris obscuro al panel
        frame.add(boardPanel); //Se agrega al UI

        for (int f = 0; f < 3; f++){ // f = las filas
            for (int c = 0; c < 3; c++){ // c = las columnas
                JButton tile = new JButton(); //Se crea el boton por cada uno de los botones y se asigna a un tile
                board[f][c] = tile; //Se declara la posicion de los tiles
                boardPanel.add(tile); //Se coloca en el Panel

                tile.setBackground(Color.darkGray); //Le asignamos el color del background a los botones / tiles
                tile.setForeground(Color.white); // Le asignamos color de letra tambien
                tile.setFont(new Font("Arial", Font.BOLD, 120)); // Asignamos formato
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener(){    //Accion para poder presionar los botones
                    public void actionPerformed(ActionEvent e){ //Evento de presionar boton
                        if (gameOver) return; //Si el juego ya acabo, el jugador no puede hacer nada
                        JButton tile = (JButton) e.getSource(); //Se obtiene la fuente osea que boton es presionado
                        if (tile.getText() == "") { //Si el lugar actual esta vacio... esto para que no se pueda cambiar el resultado.
                            tile.setText(currentPlayer); //Se declara que es lo que se escribe en ese boton, en este caso el turno del jugador actual
                            turns++;
                            checkWinnier(); //Cuando un jugador coloca una pieza, se checa si gano en ese momento.
                            if(!gameOver){ //Si el juego aun no se acaba... osea aun no hay ganador
                                currentPlayer = currentPlayer == playerX ? playerO : playerX; // Ternerary para alternar entre turnos
                                textLabel.setText(currentPlayer + "'s turn."); //Se declara en el texto del titulo para que se sepa quien sigue
                            }
                        }

                    }
                });
            }
        }
    }

    void checkWinnier(){
        //Horizontal
        for(int f = 0; f < 3; f++){ //Se checan las filas
            if (board[f][0].getText() == "") continue; //Si las filas no tienen nada, se continua
            if (board[f][0].getText() == board[f][1].getText() && board[f][1].getText() == board[f][2].getText()){ //Si en alguna de las filas, todas son X o O, se obtiene el ganador
                for(int i = 0; i < 3; i++){
                    setWinner(board[f][i]);
                }
                gameOver = true; //Se declara la variable de que se acabo el juego
                return;
            }
        }

        //Vertical
        for(int c = 0; c < 3; c++){ //Se checan las columnas
            if (board[0][c].getText() == "") continue; //Si las columnas no tienen nada, se continua
            if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()){ //Si en alguna de las columnas, todas son X o O, se obtiene el ganador
                for(int i = 0; i < 3; i++){
                    setWinner(board[i][c]);
                }
                gameOver = true; //Se declara la variable de que se acabo el juego
                return;
            }
        }

        // Diagonal (L to R)
        if(board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText() && board[0][0].getText() != ""){
            for (int i = 0; i < 3; i++){
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }
        // Diagonal (R to L)
        if(board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText() && board[0][2].getText() != ""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9){
            for (int f = 0; f < 3; f++){
                for (int c = 0; c < 3; c++){
                    setTie(board[f][c]);
                }
            }
            gameOver = true;
        }

    }

    void setWinner(JButton tile){ //Funcion para dar formato al ganador
        tile.setForeground(Color.green); //Colocamos el color de la letra del ganador
        tile.setBackground(Color.gray); //Colocamos el background color tambien a un gris mas claro
        textLabel.setText(currentPlayer + " is the winner!"); //Declaramos el ganador mas un texto de que es el que gano
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("TIE!");
    }
}
