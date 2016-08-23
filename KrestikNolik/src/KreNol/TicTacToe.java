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
 * Метод init - це конструктор апплета
 */

public void init(){
	
	//Встановлює менеджер розміщення аплета, шрифт і колір
	
	this.setLayout(new BorderLayout());
	this.setBackground(Color.CYAN);
	
	
	//Змінюєм шрифт апплета так, щоб він  був 
	//жирним і мав розмір 20
	
	Font appletFont=new Font("Monospased", Font.BOLD, 15);
	this.setFont(appletFont);
	
	//Створюєм кнопку "New Game" і реєструємо в ній
	//слухача дій
	
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
	
	// створюєм масив для ссилок на 9 кнопок
	
	squares=new Button[9];
	
	//Створюєм кнопки, зберігаєм ссилки на них в масиві
	//реєструєм в них слухача, фарбуєм 
	// їх в оранж колір і добавляєм на панель
	
	for (int i = 0; i < 9; i++) {
	
		squares[i]=new Button();
		squares[i].addActionListener(this);
		squares[i].setBackground(Color.ORANGE);
		centerPanel.add(squares[i]);
			
	}
}

/**
 * Цей метод буде обробляти  всі події
 * @param actionEvent обєкт
 */

public void actionPerformed(ActionEvent e){
	
	Button theButton = (Button) e.getSource();
	
	
	//Це кнопка New Game?
	
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
	//вихід з методу
	}
	
	String winner ="";
	
	//Чи це одна з кліток,
	
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
	
}// в кінець цикла for
	
	if (winner.equals("X")) {
	

		score.setText("You won!");
		//додаємо підрахунок виграшів
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
	
} //кінець метода actionPerformed


	
	
	/**
	 * Цей метод викликається після кожного ходу, щоб дізнатись 
	 * чи є переможець. Він перевіряє кожен ряд, колонку і 
	 * діагональ, щоб знайти три клітки з однаковими надписами
	 * (не пустими)
	 *@return "X", "O", "T"-ніичя,  ""- ще немає переможця
	 */

String lookForWinner() {
	
	String theWinner = "";
	emptySquaresLeft--;
	
	if (emptySquaresLeft==0) {
		return "T"; //це нічия,  від англ слова tie
	}
	
	//провіряєм ряд 1 - елементи массива 0. 1 .2
	if (!squares[0].getLabel().equals("") &&
			squares[0].getLabel().equals(squares[1].getLabel())
		&& squares[0].getLabel().equals(squares[2].getLabel())) {
		
		theWinner = squares[0].getLabel();
		highlightWinner(0,1,2);
		
		//перевіряєм ряд 2 - елементи массива 3,4,5
		
		}else if (!squares[3].getLabel().equals("") &&
				squares[3].getLabel().equals(squares[4].getLabel())
				&& squares[3].getLabel().equals(squares[5].getLabel()))
		{
			
			theWinner = squares[3].getLabel();
			highlightWinner(3,4,5);
			
			//перевіряєм ряд 3 - елементи массива 6,7,8
		}else if (!squares[6].getLabel().equals("") &&
				squares[6].getLabel().equals(squares[7].getLabel())
				&& squares[6].getLabel().equals(squares[8].getLabel()))
		{
			
			theWinner = squares[6].getLabel();
			highlightWinner(6, 7, 8);
			
			//перевіряєм колонку 1 - елементи масива 0,3,6
		}else if (!squares[0].getLabel().equals("") &&
					squares[0].getLabel().equals(squares[3].getLabel())
					&& squares[0].getLabel().equals(squares[6].getLabel()))
			{
				
				theWinner = squares[0].getLabel();
				highlightWinner(0,3,6);
				
				//перевіряєм  колонку 2 - елементи масива 1,4,7
			}else if (!squares[1].getLabel().equals("") &&
					squares[1].getLabel().equals(squares[4].getLabel())
					&& squares[1].getLabel().equals(squares[7].getLabel()))
			{
				
				theWinner = squares[1].getLabel();
				highlightWinner(1, 4, 7);
				
				
				//перевіряєм  колонку 3 - елементи масива 2,5,8
			}else if (!squares[2].getLabel().equals("") &&
					squares[2].getLabel().equals(squares[5].getLabel())
					&& squares[2].getLabel().equals(squares[8].getLabel()))
			{
				
				theWinner = squares[2].getLabel();
				highlightWinner(2, 5, 8);
				
				//перевіряєм першу діагональ - елементи массива 0,4,8
			}else if (!squares[0].getLabel().equals("") &&
					squares[0].getLabel().equals(squares[4].getLabel())
					&& squares[0].getLabel().equals(squares[8].getLabel()))
			{
				
				theWinner = squares[0].getLabel();
				highlightWinner(0, 4, 8);
				
				//перевіряєм другу діагональ - елементи массива 2,4,6
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
				 * цей метод застосовує набір правил  щоб знайти
				 * кращий компютерний хід.Якщо хороший хід
				 * не знайдений, вибирається випадкова клітка
				 */
	
void computerMove(){
	
	int selectedSquare;
	
	//Спершу комп пробує знайти пусту клітинку
	//поруч з 2-ма клітками з ноликами, щоб виграти
	
	selectedSquare = findEmptySquare("O");
	
	//якщо він нен знаходить два нолика, тоді
	//пробує не дати опоненту зробити ряд із 3-х
	//хрестиків, помістивши нолик поруч з 2-ма хрестиками
	
	if (selectedSquare==-1){
		selectedSquare = findEmptySquare("X");
	
}

//якщо selectedSquare все ще == -1, то
//пробує зайняти центральну клітинку

if ((selectedSquare== -1) 
		&& (squares[4].getLabel().equals(""))){
	selectedSquare=4;
}
	
//якщо центральна клітинка зайнята
//просто займаєм випадкову клітинку
if (selectedSquare == -1){
	selectedSquare = getRandomSquare();
}

squares[selectedSquare].setLabel("O");
}
	

/**
 * Цей метод перевіряє кожен рядок, колонку і діагональ
 * щоб дізнатись, чи є в ній дві клітки з
 * одинаковими написами і пустою клітинкою
 * @param передається Х - для користувача і Щ для компа

 * @return кількість вільних клітинок,
 * або -1, якщо не знайдено дві клітинки
 *з одинаковими написами
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
	
	//перевіримо чи є в рядку 1 дві однакові 
	//клітки і одна пуста
	if (weight[0] + weight[1] + weight[2] == twoWeights) {
		if (weight[0] == 0)
			return 0;
		else if (weight[1] == 0)
			return 1;
		else return 2;
	}
	//перевіримо чи є в рядку 2 дві однакові 
		//клітки і одна пуста
	if (weight[3] + weight[4] + weight[5] == twoWeights) {
		if (weight[3] == 0)
			return 3;
		else if (weight[4] == 0)
			return 4;
		else return 5;
	
	}
	//перевіримо чи є в рядку 3  дві однакові 
			//клітки і одна пуста
	if (weight[6] + weight[7] + weight[8] == twoWeights) {
		if (weight[6] == 0)
			return 6;
		else if (weight[7] == 0)
			return 7;
		else return 8;
	}//перевіримо чи є в колонці 1 дві однакові 
	//клітки і одна пуста
if (weight[0] + weight[3] + weight[6] == twoWeights) {
	if (weight[0] == 0)
		return 0;
	else if (weight[3] == 0)
		return 3;
	else return 6;
}//перевіримо чи є в олонці 2 дві однакові 
			//клітки і одна пуста
		if (weight[1] + weight[4] + weight[7] == twoWeights) {
			if (weight[1] == 0)
				return 1;
			else if (weight[4] == 0)
				return 4;
			else return 7;
		}//перевіримо чи є в kолонці 3 дві однакові 
		//клітки і одна пуста
	if (weight[2] + weight[5] + weight[8] == twoWeights) {
		if (weight[2] == 0)
			return 2;
		else if (weight[5] == 0)
			return 5;
		else return 8;
	}//перевіримо чи є в іагоналі 1 дві однакові 
	//клітки і одна пуста
if (weight[0] + weight[4] + weight[8] == twoWeights) {
	if (weight[0] == 0)
		return 0;
	else if (weight[4] == 0)
		return 4;
	else return 8;

}//перевіримо чи є в діагоналі 2 дві однакові 
//клітки і одна пуста
if (weight[2] + weight[4] + weight[6] == twoWeights) {
if (weight[2] == 0)
	return 2;
else if (weight[4] == 0)
	return 4;
else return 6;		
}

				
//Не знайдено двох сусідніх одинакових клітинок
return -1;
	}
	//кінець метода findEmptySquare()
	/**
	 * цей метод вибирає будьяку пусту клітку
	 *@return випадково вибраний номер клітки
	 */

	int getRandomSquare(){
		
		boolean gotEmptySquare = false;
		int selectedSquare = -1;
		
		do {
			
			selectedSquare = (int) (Math.random() * 9);
			
			if (squares[selectedSquare].getLabel().equals("")){
				gotEmptySquare = true;  //щоб закінчити цикл
				
			}
		} while(!gotEmptySquare);
		return selectedSquare;}
		
		//кінець метода etRandomSquare()
		
		/**
		 * цей метод виділяє лінію яка виграла
		 * @param перша, друга і третя клітинки для виділнння
		 */
		
		void highlightWinner(int win1, int win2, int win3){
			
			
			squares[win1].setBackground(Color.CYAN);
			squares[win2].setBackground(Color.CYAN);
			squares[win3].setBackground(Color.CYAN);
		}
		//робимо недоступними клітинки і доступною кнопку "New Game"
		
		void endTheGame(){
			
			newGameButton.setEnabled(true);
			for(int i = 0; i<9; i++) {
				squares[i].setEnabled(false);
			}
		}
		 public static void main(String[] args){
             //Запускаем через main() как Java приложение
             Applet tictac=new TicTacToe();
             JFrame frame=new JFrame("Крестики и нолики");
             frame.getContentPane().add(tictac);
             frame.setSize(310,320);
             tictac.init();
             tictac.start();
             //обязтельное условие, иначе 
             //не будет виден созданный фрейм
             frame.setVisible(true); 
                }      
	} //кінець класса

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

