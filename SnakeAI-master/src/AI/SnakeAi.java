package AI;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import Mode.Node;
import Mode.Snake;



public class SnakeAi {
	/**
	 * Make it work
	 * ֱ���������پ���,ע�⣺����Node����д��equals������Snake���move�������s.remove��ɾ��һ��ĩβ�ڵ�ʱ��ɾ��2���ڵ㣬
	 * ���������ظ��ĵط����ử��������ʾ��������ˣ������޸�����ɾ����ע��equals����
	 * @param s
	 * @param f
	 * @param dir
	 * @return
	 */
	public int play(Snake s,Node f){
		if(f.getX()>s.getFirst().getX()&&s.getDir()!=4){
			return 6;
		}
		if(f.getX()<s.getFirst().getX()&&s.getDir()!=6){
			return 4;
		}
		if(f.getX()==s.getFirst().getX()){//X�����
			if(f.getY()>s.getFirst().getY()&&s.getDir()!=8)return 2;
			else if(f.getY()>s.getFirst().getY()&&s.getDir()==8){
				if(s.canMove(4))return 4;
				else if(s.canMove(6))return 6;
				else return -1;
			}
			else if(f.getY()<s.getFirst().getY()&&s.getDir()!=2)return 8;
			else if(f.getY()<s.getFirst().getY()&&s.getDir()==2){
				if(s.canMove(4))return 4;
				else if(s.canMove(6))return 6;
				else return -1;
			}
		}
		
		if(f.getY()>s.getFirst().getY()&&s.getDir()!=2){
			return 8;
		} 
		if(f.getY()==s.getFirst().getY()){
			if(f.getX()>s.getFirst().getX()&&s.getDir()!=4)return 6;
			else if(f.getX()>s.getFirst().getX()&&s.getDir()==4){
				if(s.canMove(8))return 8;
				else if(s.canMove(2))return 2;
				else return -1;
			}
			else if(f.getX()<s.getFirst().getX()&&s.getDir()!=6)return 4;
			else if(f.getX()<s.getFirst().getX()&&s.getDir()==6){
				if(s.canMove(8))return 8;
				else if(s.canMove(2))return 2;
				else return -1;
			}
		}
		if(f.getY()<s.getFirst().getY()&&s.getDir()!=8){
			return 2;
		}
		return -1;
	}
	/**
	 * Make it right��
	 * BFS,��������ȥ������ְ��Լ�Χ����GG��
	 * @param s ��
	 * @param f Ŀ��,����Ŀ����ܲ���ʳ�����Ե���������
	 * @return  �ܵ��Ļ�����ȥ��·����һ�������ܵĻ�����-1��
	 */
	
	public int play1(Snake s,Node f) {
		Queue<Node> q = new LinkedList<Node>();
		Set<String> vis = new HashSet<String>();// ��¼���ʹ��Ľڵ�
		Map<String, String> path = new HashMap<String, String>();//��¼���ʵ�·��,������A*�㷨��Node�����father�ڵ㣬�������ȥ����
		Stack<String> stack = new Stack<String>();//��ȥ�Ե�·��
		
		q.add(s.getFirst());
		while (!q.isEmpty()) {
			Node n = q.remove();
			if (n.getX() == f.getX() && n.getY() == f.getY()) {
			//����ѵ���ʳ���ʼ����·������Ϊ�ǴӺ���ӣ�������ջ������
				String state = f.toString();
				while (state != null &&!state.equals(s.getFirst().toString())) {
					stack.push(state);
					state = path.get(state);
				}
				
				String []str;
				//��ʱ��ʳ���ͷ���žͻᵼ��ջΪ��
				if(stack.isEmpty()){
					str = state.split("-");
				}else  str = stack.peek().split("-");
				int x = Integer.parseInt(str[0]);
				int y = Integer.parseInt(str[1]);
				if (x > s.getFirst().getX() && y == s.getFirst().getY()) {
					return 6;
				}
				if (x < s.getFirst().getX() && y == s.getFirst().getY()) {
					return 4;
				}
				if (x == s.getFirst().getX() && y > s.getFirst().getY()) {
					return 2;
				}
				if (x == s.getFirst().getX() && y < s.getFirst().getY()) {
					return 8;
				}
			}
			Node up = new Node(n.getX(), n.getY() - Snake.size);
			Node right = new Node(n.getX() + Snake.size, n.getY());
			Node down = new Node(n.getX(), n.getY() + Snake.size);
			Node left = new Node(n.getX() - Snake.size, n.getY());
			if (!s.getMap().contains(up.toString()) && !vis.contains(up.toString()) 
					&& up.getX() <= Snake.map_size&& up.getX() >= 10 
					&& up.getY() <= Snake.map_size && up.getY() >= 10) {
				q.add(up);
				vis.add(up.toString());
				path.put(up.toString(),n.toString());
			}
			if (!s.getMap().contains(right.toString()) && !vis.contains(right.toString())
					&& right.getX() <= Snake.map_size&& right.getX() >= 10 
					&& right.getY() <= Snake.map_size && right.getY() >= 10) {
				q.add(right);
				vis.add(right.toString());
				path.put(right.toString(),n.toString());
			}
			if (!s.getMap().contains(down.toString()) && !vis.contains(down.toString()) 
					&& down.getX() <= Snake.map_size&& down.getX() >= 10 
					&& down.getY() <= Snake.map_size && down.getY() >= 10) {
				q.add(down);
				vis.add(down.toString());
				path.put(down.toString(),n.toString());
			}
			if (!s.getMap().contains(left.toString()) && !vis.contains(left.toString()) 
					&& left.getX() <= Snake.map_size&& left.getX() >= 10 
					&& left.getY() <= Snake.map_size && left.getY() >= 10) {
				q.add(left);
				vis.add(left.toString());
				path.put(left.toString(),n.toString());
			}
		}
		return -1;
	}
	/**
	 * Make it right+   //make it fast�е���
	 * 
	 * ��������Գ�ʳ���׷β�ͣ����ԳԾ�����һ��������ȥ�ԣ�����Ե�ʳ��󻹿���ȥ׷β���Ǿ�ȥ�ԣ�������׷β�ͣ�ֱ��ȥ��ʳ���Ҳ�ǰ�ȫ��,��ȥ�ԡ�
	 * ��������Գ�ʳ��Ҳ����׷β�;�����ߣ���Ӧ����С�����¼�
	 * @param s ��
	 * @param f Ŀ��
	 * @return ����
	 */

	public int play2(Snake snake,Node f){
		Snake virSnake =new Snake(snake.getFirst(),snake.getLast(),snake.getFood(),snake.getTail());
		virSnake.setS((ArrayList<Node>) snake.getS().clone());
		virSnake.setMap((HashSet<String>) snake.getMap().clone());

		//����ȥ��ʳ��ķ���
		int realGoTofoodDir=play1(snake,f);
		//����Եõ�ʳ��
		if(realGoTofoodDir!=-1){
			 //��������ȥ��
			while(!virSnake.getFirst().toString().equals(f.toString())){
				virSnake.move(play1(virSnake, f));
			}
			 //�����ߵ�β��ȥ�ķ���
				int goToDailDir=play1(virSnake,virSnake.getTail());//������������
				 //��������߳�����ȥβ�ͣ����߾�ȥ��
				if(goToDailDir!=-1)return realGoTofoodDir;
				else {
					snake.c++;
					/**
					 * ����Ե�����ȥ�Լ�β�ͣ��͸�����β��
					 * ����������޸�����β�ܣ���Ҫԭ������׷β��Ҳ�õ�BFS��׷β��Ӧ������Զ����
					 * ��Զ���������A*�㷨��Ҳ����bfs��Ȩֵ̰��
					 */

					if(snake.c<200)return Asearch(snake,snake.getTail());
					else {
//						System.out.println("ok");
						return realGoTofoodDir;//ֱ��ȥ������


					}
				}
		}else{// ����Բ���ʳ��
			 //���ߵ�β��ȥ�ķ���
			//int realGoToDailDir = Asearch(snake, snake.getTail());
			int realGoToDailDir = zhuiwei(snake);

			if(realGoToDailDir==-1){
				 // ����Բ���ʳ��Ҳ������β�;�����ߣ�����������
				realGoToDailDir=randomDir();
				int i=0;
				while(!snake.canMove(realGoToDailDir)){
					//���������ѭ�������涼������
					realGoToDailDir=randomDir();
					i++;
					if(i>300)return -1;//����ѭ����ֻ��GG��
				}
				return realGoToDailDir;
			}
			return realGoToDailDir;
		}

	}
	/**
	 * A*����Զ·��
	 * @param s
	 * @param f
	 * @return
	 */
	public int Asearch(Snake s,Node f){
		ArrayList<Node> openList = new ArrayList<Node>();
		ArrayList<Node> closeList = new ArrayList<Node>();
		Stack<Node> stack = new Stack<Node>();//��ȥ�Ե�·��
		openList.add(s.getFirst());// ����ʼ�ڵ���뿪���б�;
		s.getFirst().setH(dis(s.getFirst(),f));


		while(!openList.isEmpty()){
			Node now=null;
			int max=-1;
			for(Node n:openList){//������Fֵ����(˵����Ŀ����Զ),�������ͬ����ѡ�����ں����Ҳ����������ӵġ�
				if(n.getF()>=max){
					max=n.getF();
					now=n;
				}
			}
			// �ѵ�ǰ�ڵ�ӿ����б�ɾ��, ���뵽����б�;
			openList.remove(now);
			closeList.add(now);
			//�ĸ���������ڽڵ�
			Node up = new Node(now.getX(), now.getY() - Snake.size);
			Node right = new Node(now.getX() + Snake.size, now.getY());
			Node down = new Node(now.getX(), now.getY() + Snake.size);
			Node left = new Node(now.getX() - Snake.size, now.getY());
			ArrayList<Node> temp = new ArrayList<Node>(4);
			temp.add(up);
			temp.add(right);
			temp.add(down);
			temp.add(left);
			for (Node n : temp){
				// ��������ڽڵ㲻��ͨ�л��߸����ڽڵ��Ѿ��ڷ���б���,��ʲô����Ҳ��ִ��,����������һ���ڵ�;
				if (s.getMap().contains(n.toString()) || closeList.contains(n)
						|| n.getX() > Snake.map_size|| n.getX() < 10 
						|| n.getY() > Snake.map_size || n.getY() < 10)
					continue;
				
				// ��������ڽڵ㲻�ڿ����б���,�򽫸ýڵ���ӵ������б���,
				// ���������ڽڵ�ĸ��ڵ���Ϊ��ǰ�ڵ�,ͬʱ��������ڽڵ��G��Hֵ,Fֵ�ļ���ֱ����д����Node����
				if(!openList.contains(n)){
//					System.out.println("ok");
					n.setFather(now);
					n.setG(now.getG()+10);
					n.setH(dis(n,f));
					openList.add(n);
					// ���յ�ڵ㱻���뵽�����б���Ϊ������ڵ�ʱ, ��ʾ·�����ҵ�,��ʱ��ֹѭ��,���ط���;
					if (n.equals(f)) {
						
						//��Ŀ��ڵ���ǰ�ң�....�Բ������и��ӣ�node������f����Ϊf���ҵ��Ľڵ���Ȼ������ͬ��fû�м�¼father
						Node node = n;
						while(node!=null&&!node.equals(s.getFirst())){
							stack.push(node);
							node=node.getFather();
						}
						int x = stack.peek().getX();
						int y = stack.peek().getY();
						if (x > s.getFirst().getX() && y == s.getFirst().getY()) {
							return 6;
						}
						if (x < s.getFirst().getX() && y == s.getFirst().getY()) {
							return 4;
						}
						if (x == s.getFirst().getX() && y > s.getFirst().getY()) {
							return 2;
						}
						if (x == s.getFirst().getX() && y < s.getFirst().getY()) {
							return 8;
						}
					}
				}
				// ��������ڽڵ��ڿ����б���,
				// ���ж������ɵ�ǰ�ڵ㵽������ڽڵ��Gֵ�Ƿ���ڻ�С��(��������Զ�ô���)ԭ�������Gֵ,�����ڻ�С��,�򽫸����ڽڵ�ĸ��ڵ���Ϊ��ǰ�ڵ�,���������ø����ڽڵ��G��Fֵ.
				if (openList.contains(n)) {
					if (n.getG() > (now.getG() + 10)) {
						n.setFather(now);
						n.setG(now.getG() + 10);
					}
				}
			}
		}
		// �������б�Ϊ��,�������޿�����ӵ��½ڵ�,���Ѽ���Ľڵ���û���յ�ڵ�����ζ��·���޷����ҵ�,��ʱҲ����ѭ������-1;
		return -1;
	}
	/**
	 * ���������پ���
	 * @param src
	 * @param des
	 * @return
	 */
	public int dis(Node src,Node des){
		return Math.abs(src.getX()-des.getX())+Math.abs(src.getY()-des.getY());
	}
	/**
	 * �����������
	 * @return
	 */
	public int randomDir(){
		int dir=(int)Math.random()*4;
		if(dir==0)return 8;
		else if(dir==1)return 6;
		else if(dir==2)return 2;
		else return 4;
	}

	public int zhuiwei(Snake snake) {


		/**
		 * ����Ե�����ȥ�Լ�β�ͣ��͸�����β��
		 * ����������޸�����β�ܣ���Ҫԭ������׷β��Ҳ�õ�BFS��׷β��Ӧ������Զ����
		 * ��Զ���������A*�㷨��Ҳ����bfs��Ȩֵ̰��
		 */

		if (snake.c < 100) return Asearch(snake, snake.getTail());
		else {
//						System.out.println("ok");
			//return realGoTofoodDir;//ֱ��ȥ������
			if (snake.getFirst().getX() == snake.getTail().getX()
					&& (snake.getFirst().getY() == snake.getTail().getY() + 10
					|| snake.getFirst().getY() == snake.getTail().getY() - 10)) {

				Node node = new Node(snake.getFirst().getX() - 10, snake.getFirst().getY());
				Node node1 = new Node(snake.getTail().getX() - 10, snake.getTail().getY());

				if (!snake.getMap().contains(node.toString())
						&& !snake.getMap().contains(node1.toString())) {
					if (snake.getFirst().getX() > 10) {

						return 4;
					}
				}
				Node node2 = new Node(snake.getFirst().getX() + 10, snake.getFirst().getY());
				Node node3 = new Node(snake.getTail().getX() + 10, snake.getTail().getY());

				if (!snake.getMap().contains(node2.toString())
						&& !snake.getMap().contains(node3.toString())) {
					if (snake.getFirst().getX() < 150) {

						return 6;
					}
				}

			}

			if (snake.getFirst().getY() == snake.getTail().getY()
					&& (snake.getFirst().getX() == snake.getTail().getX() + 10
					|| snake.getFirst().getX() == snake.getTail().getX() - 10)) {

				Node node = new Node(snake.getFirst().getX(), snake.getFirst().getY() - 10);
				Node node1 = new Node(snake.getTail().getX(), snake.getTail().getY() - 10);

				if (!snake.getMap().contains(node.toString())
						&& !snake.getMap().contains(node1.toString())) {
					if (snake.getFirst().getY() > 10) {

						return 8;
					}
				}
				Node node2 = new Node(snake.getFirst().getX(), snake.getFirst().getY() + 10);
				Node node3 = new Node(snake.getTail().getX(), snake.getTail().getY() + 10);

				if (!snake.getMap().contains(node2.toString())
						&& !snake.getMap().contains(node3.toString())) {
					if (snake.getFirst().getY() < 150) {

						return 2;
					}
				}

				return Asearch(snake, snake.getTail());

			}
		}
		return Asearch(snake, snake.getTail());
	}
}
