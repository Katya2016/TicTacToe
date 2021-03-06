package KreNol;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.nio.channels.SelectableChannel;
import java.applet.Applet;

public class TicTacToe extends Applet implements ActionListener
{
Button squares[];
Button newGameButton;
Label score;
Label winn;
Label loss;
int emptySquaresLeft=9;
int w;
int l;
/**
 * ����� init - �� ����������� �������
 */

public void init(){
	
	//���������� �������� ��������� ������, ����� � ����
	
	this.setLayout(new BorderLayout());
	this.setBackground(Color.CYAN);
	
	
	//������ ����� ������� ���, ��� ��  ��� 
	//������ � ��� ����� 20
	
	Font appletFont=new Font("Monospased", Font.BOLD, 15);
	this.setFont(appletFont);
	
	//�������� ������ "New Game" � �������� � ���
	//������� ��
	
	FlowLayout fl = new FlowLayout(FlowLayout.CENTER);
	Panel topPanel = new Panel();
	topPanel.setLayout(fl);	
	
newGameButton=new Button("New Game");
	
	newGameButton.addActionListener(this);
	winn=new Label("Your winner");
	loss=new Label("Your loss");
	
	topPanel.add(newGameButton);
	topPanel.add(winn);
	topPanel.add(loss);
	this.add(topPanel,"North");
	setSize(300, 200);
	
	
	Panel centerPanel = new Panel();
	centerPanel.setLayout(new GridLayout(3, 3));
	this.add(centerPanel, "Center");
			
	score=new Label("Your turn!");
	this.add(score, "South");
	
	// �������� ����� ��� ������ �� 9 ������
	
	squares=new Button[9];
	
	//�������� ������, ������� ������ �� ��� � �����
	//������� � ��� �������, ������ 
	// �� � ����� ���� � ��������� �� ������
	
	for (int i = 0; i < 9; i++) {
	
		squares[i]=new Button();
		squares[i].addActionListener(this);
		squares[i].setBackground(Color.ORANGE);
		centerPanel.add(squares[i]);
			
	}
}

/**
 * ��� ����� ���� ���������  �� ��䳿
 * @param actionEvent ����
 */

public void actionPerformed(ActionEvent e){
	
	Button theButton = (Button) e.getSource();
	
	
	//�� ������ New Game?
	
	if (theButton == newGameButton){
		
	for(int i=0; i<9; i++)	{
		squares[i].setEnabled(true);
		squares[i].setLabel("");
		squares[i].setBackground(Color.ORANGE);
		
	}
	
	emptySquaresLeft=9;
	score.setText("your turn!");
	
	
	
	
	newGameButton.setEnabled(false);
	return; 
	//����� � ������
	}
	
	String winner ="";
	
	//�� �� ���� � �����,
	
	for (int i =0; i<9; i++){
		
		if (theButton == squares[i] & !squares[i].getLabel().equals("O") &  !squares[i].getLabel().equals("X")) {
			
			squares[i].setLabel("X");
			winner = lookForWinner();
			
			if (!"".equals(winner)){
				endTheGame();
			}else {
				computerMove();
				winner = lookForWinner();
				
				if (!"".equals(winner)){
					endTheGame();
			}
		}
			break;	
	}	
	
}// � ����� ����� for
	
	if (winner.equals("X")) {
	

		score.setText("You won!");
		//������ ��������� ��������
		while (true){
		
		w++; 
		break;}
		winn.setText("U won " + String.valueOf(w));
	
	
	}else if (winner.equals("O")){
		score.setText("You lost ");
		while (true){
			l++;
			break;}
		
		
		loss.setText("U lost" + String.valueOf(l));
	


	}
	
} //����� ������ actionPerformed


	
	
	/**
	 * ��� ����� ����������� ���� ������� ����, ��� �������� 
	 * �� � ����������. ³� �������� ����� ���, ������� � 
	 * ��������, ��� ������ ��� ����� � ���������� ���������
	 * (�� �������)
	 *@return "X", "O", "T"-�����,  ""- �� ���� ���������
	 */

String lookForWinner() {
	
	String theWinner = "";
	emptySquaresLeft--;
	
	if (emptySquaresLeft==0) {
		return "T"; //�� �����,  �� ���� ����� tie
	}
	
	//�������� ��� 1 - �������� ������� 0. 1 .2
	if (!squares[0].getLabel().equals("") &&
			squares[0].getLabel().equals(squares[1].getLabel())
		&& squares[0].getLabel().equals(squares[2].getLabel())) {
		
		theWinner = squares[0].getLabel();
		highlightWinner(0,1,2);
		
		//��������� ��� 2 - �������� ������� 3,4,5
		
		}else if (!squares[3].getLabel().equals("") &&
				squares[3].getLabel().equals(squares[4].getLabel())
				&& squares[3].getLabel().equals(squares[5].getLabel()))
		{
			
			theWinner = squares[3].getLabel();
			highlightWinner(3,4,5);
			
			//��������� ��� 3 - �������� ������� 6,7,8
		}else if (!squares[6].getLabel().equals("") &&
				squares[6].getLabel().equals(squares[7].getLabel())
				&& squares[6].getLabel().equals(squares[8].getLabel()))
		{
			
			theWinner = squares[6].getLabel();
			highlightWinner(6, 7, 8);
			
			//��������� ������� 1 - �������� ������ 0,3,6
		}else if (!squares[0].getLabel().equals("") &&
					squares[0].getLabel().equals(squares[3].getLabel())
					&& squares[0].getLabel().equals(squares[6].getLabel()))
			{
				
				theWinner = squares[0].getLabel();
				highlightWinner(0,3,6);
				
				//���������  ������� 2 - �������� ������ 1,4,7
			}else if (!squares[1].getLabel().equals("") &&
					squares[1].getLabel().equals(squares[4].getLabel())
					&& squares[1].getLabel().equals(squares[7].getLabel()))
			{
				
				theWinner = squares[1].getLabel();
				highlightWinner(1, 4, 7);
				
				
				//���������  ������� 3 - �������� ������ 2,5,8
			}else if (!squares[2].getLabel().equals("") &&
					squares[2].getLabel().equals(squares[5].getLabel())
					&& squares[2].getLabel().equals(squares[8].getLabel()))
			{
				
				theWinner = squares[2].getLabel();
				highlightWinner(2, 5, 8);
				
				//��������� ����� �������� - �������� ������� 0,4,8
			}else if (!squares[0].getLabel().equals("") &&
					squares[0].getLabel().equals(squares[4].getLabel())
					&& squares[0].getLabel().equals(squares[8].getLabel()))
			{
				
				theWinner = squares[0].getLabel();
				highlightWinner(0, 4, 8);
				
				//��������� ����� �������� - �������� ������� 2,4,6
			}else if (!squares[2].getLabel().equals("") &&
					squares[2].getLabel().equals(squares[4].getLabel())
					&& squares[2].getLabel().equals(squares[6].getLabel()))
			{
				
				theWinner = squares[2].getLabel();
				highlightWinner(2, 4, 6);
				
			}
			return theWinner;
		}
	
				/**
				 * ��� ����� ��������� ���� ������  ��� ������
				 * ������ ����������� ���.���� ������� ���
				 * �� ���������, ���������� ��������� �����
				 */
	
void computerMove(){
	
	int selectedSquare;
	
	//������ ���� ����� ������ ����� �������
	//����� � 2-�� ������� � ��������, ��� �������
	
	selectedSquare = findEmptySquare("O");
	
	//���� �� ��� ��������� ��� ������, ���
	//����� �� ���� �������� ������� ��� �� 3-�
	//��������, ��������� ����� ����� � 2-�� ����������
	
	if (selectedSquare==-1){
		selectedSquare = findEmptySquare("X");
	
}

//���� selectedSquare ��� �� == -1, ��
//����� ������� ���������� �������

if ((selectedSquare== -1) 
		&& (squares[4].getLabel().equals(""))){
	selectedSquare=4;
}
	
//���� ���������� ������� �������
//������ ������ ��������� �������
if (selectedSquare == -1){
	selectedSquare = getRandomSquare();
}

squares[selectedSquare].setLabel("O");
}
	

/**
 * ��� ����� �������� ����� �����, ������� � ��������
 * ��� ��������, �� � � ��� �� ����� �
 * ����������� �������� � ������ ��������
 * @param ���������� � - ��� ����������� � � ��� �����

 * @return ������� ������ �������,
 * ��� -1, ���� �� �������� �� �������
 *� ����������� ��������
 */

	int findEmptySquare(String player) {
		
	int weight[] = new int[9];
	
	for (int i = 0; i<9; i++) {
		
		if (squares[i].getLabel().equals("O"))
			weight[i] = -1;
		else if (squares[i].getLabel().equals("X"))
			weight[i] = 1;
		else
			weight[i] = 0;
	}
	
	int twoWeights = player.equals("O") ? -2 : 2;
	
	//��������� �� � � ����� 1 �� ������� 
	//����� � ���� �����
	if (weight[0] + weight[1] + weight[2] == twoWeights) {
		if (weight[0] == 0)
			return 0;
		else if (weight[1] == 0)
			return 1;
		else return 2;
	}
	//��������� �� � � ����� 2 �� ������� 
		//����� � ���� �����
	if (weight[3] + weight[4] + weight[5] == twoWeights) {
		if (weight[3] == 0)
			return 3;
		else if (weight[4] == 0)
			return 4;
		else return 5;
	
	}
	//��������� �� � � ����� 3  �� ������� 
			//����� � ���� �����
	if (weight[6] + weight[7] + weight[8] == twoWeights) {
		if (weight[6] == 0)
			return 6;
		else if (weight[7] == 0)
			return 7;
		else return 8;
	}//��������� �� � � ������� 1 �� ������� 
	//����� � ���� �����
if (weight[0] + weight[3] + weight[6] == twoWeights) {
	if (weight[0] == 0)
		return 0;
	else if (weight[3] == 0)
		return 3;
	else return 6;
}//��������� �� � � ������ 2 �� ������� 
			//����� � ���� �����
		if (weight[1] + weight[4] + weight[7] == twoWeights) {
			if (weight[1] == 0)
				return 1;
			else if (weight[4] == 0)
				return 4;
			else return 7;
		}//��������� �� � � k������ 3 �� ������� 
		//����� � ���� �����
	if (weight[2] + weight[5] + weight[8] == twoWeights) {
		if (weight[2] == 0)
			return 2;
		else if (weight[5] == 0)
			return 5;
		else return 8;
	}//��������� �� � � ������� 1 �� ������� 
	//����� � ���� �����
if (weight[0] + weight[4] + weight[8] == twoWeights) {
	if (weight[0] == 0)
		return 0;
	else if (weight[4] == 0)
		return 4;
	else return 8;

}//��������� �� � � ������� 2 �� ������� 
//����� � ���� �����
if (weight[2] + weight[4] + weight[6] == twoWeights) {
if (weight[2] == 0)
	return 2;
else if (weight[4] == 0)
	return 4;
else return 6;		
}

				
//�� �������� ���� ������� ���������� �������
return -1;
	}
	//����� ������ findEmptySquare()
	/**
	 * ��� ����� ������ ������� ����� �����
	 *@return ��������� �������� ����� �����
	 */

	int getRandomSquare(){
		
		boolean gotEmptySquare = false;
		int selectedSquare = -1;
		
		do {
			
			selectedSquare = (int) (Math.random() * 9);
			
			if (squares[selectedSquare].getLabel().equals("")){
				gotEmptySquare = true;  //��� �������� ����
				
			}
		} while(!gotEmptySquare);
		return selectedSquare;}
		
		//����� ������ etRandomSquare()
		
		/**
		 * ��� ����� ������ ���� ��� �������
		 * @param �����, ����� � ����� ������� ��� ��������
		 */
		
		void highlightWinner(int win1, int win2, int win3){
			
			
			squares[win1].setBackground(Color.CYAN);
			squares[win2].setBackground(Color.CYAN);
			squares[win3].setBackground(Color.CYAN);
		}
		//������ ������������ ������� � ��������� ������ "New Game"
		
		void endTheGame(){
			
			newGameButton.setEnabled(true);
			for(int i = 0; i<9; i++) {
				squares[i].setEnabled(false);
			}
		}
		 public static void main(String[] args){
             //��������� ����� main() ��� Java ����������
             Applet tictac=new TicTacToe();
             JFrame frame=new JFrame("�������� � ������");
             frame.getContentPane().add(tictac);
             frame.setSize(310,320);
             tictac.init();
             tictac.start();
             //����������� �������, ����� 
             //�� ����� ����� ��������� �����
             frame.setVisible(true); 
                }      
	} //����� ������

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

