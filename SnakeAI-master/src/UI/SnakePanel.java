package UI;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import AI.SnakeAi;
import Mode.Node;
import Mode.Snake;




public class SnakePanel extends JPanel implements Runnable{

	Snake snake;
	SnakeAi ai;
	
	/**
	 * Create the panel.
	 */
	public SnakePanel() {
		snake=new Snake();
		Node n=new Node(10,10);//�ߵĳ�ʼλ��
		snake.getS().add(n);
		snake.setFirst(n);
		snake.setLast(n);
		snake.setTail(new Node(0,10));//last�ĺ�һ���ڵ�
		snake.setFood(new Node(80,80));//ʳ���ʼλ��
		ai=new SnakeAi();
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.setColor(Color.orange);
		g.drawRect(10, 10, Snake.map_size, Snake.map_size);//��ͼ��Χ
		
		g.setColor(Color.white);	
		paintSnake(g,snake);
		
		g.setColor(Color.white);
		paintFood(g,snake.getFood());
		

		int dir=ai.play2(snake,snake.getFood());//ѡ����ԣ�play ,play1,play2
		if(dir==-1){
//			System.out.println("GG");
		}
		else{
			snake.move(dir);
		}
	}
	/**
	 * ����
	 * @param g
	 * @param snake
	 */
	public void paintSnake(Graphics g,Snake snake){
		for(Node n:snake.getS()){
			if(n.toString().equals(snake.getFirst().toString())){
				g.setColor(Color.green);//Ϊ�˷��㿴��ͷΪ��ɫ
			}
			if(n.toString().equals(snake.getLast().toString())&&!snake.getFirst().toString().equals(snake.getLast().toString())){
				g.setColor(Color.blue);//��β��ɫ
			}
			g.fillRect(n.getX(),n.getY(),Snake.size, Snake.size);
			g.setColor(Color.white);//�����ɫ
		}
	}
	/**
	 * ��ʳ��
	 * @param g
	 * @param food
	 */
	public void paintFood(Graphics g,Node food){
		g.setColor(Color.red);
		g.fillOval(food.getX(), food.getY(), snake.size, snake.size);
	}
	
	@Override
	public void run() {
		while (true) {
			// TODO Auto-generated method stub
			try {
				Thread.sleep(10);//�ӳ��ٶ�
				this.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
