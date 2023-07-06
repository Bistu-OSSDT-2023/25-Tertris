package Test;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class Tertris extends JFrame implements KeyListener,Method{
	//必需变量
		private static final int game_x=26;
		private static final int game_y=12;
		boolean isrunning;
		JLabel label1;
		JLabel label;
		boolean isruning;
		int[] allRect;
		int rect;
		int temp;
		//方块坐标
		int x,y;
		//休眠时间
		int time = 1000;
		int score = 0;
		
		//_____________________________________________
		JTextArea[][] text;
		int[][] data;
	public void initWindow(){
	this.setSize(600, 850);
	this.setVisible(true);
	this.setLocationRelativeTo(null);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setResizable(false);
	this.setTitle("我们的俄罗斯方块游戏");
	
	
	}
	
	public void initGamepanel(){
		JPanel game_main=new JPanel();
		game_main.setLayout(new GridLayout(game_x,game_y,1,1));
		for (int i=0;i<text.length;i++){
			for(int j=0;j<text[i].length;j++){
				text[i][j]=new JTextArea(game_x,game_y);
				text[i][j].setBackground(Color.WHITE); 
				text[i][j].addKeyListener(this);
				if(j==0||j==text[i].length-1||i==text.length-1){
					text[i][j].setBackground(Color.BLUE);
					data[i][j]=1;
				}
				text[i][j].setEditable(false);
				game_main.add(text[i][j]);
			
			}
			
		}
		
		this.setLayout(new BorderLayout());
		this.add(game_main,BorderLayout.CENTER);
		
	}
	public void initExplainPanel(){
		JPanel left=new JPanel();
		JPanel right=new JPanel();
		left.setLayout(new GridLayout(4,1));
		right.setLayout(new GridLayout(2,1));
		left.add(new JLabel("按空格键，空格变形"));
		left.add(new JLabel("按a键，空格左移"));
		left.add(new JLabel("按d键，空格右移"));
		left.add(new JLabel("按s键，空格下落"));
	    label1.setForeground(Color.GRAY);
	    right.add(label);
	    right.add(label1);
	    this.add(left,BorderLayout.WEST);
	    this.add(right,BorderLayout.EAST);
	    }
	
	//空参构造方法
	public  Tertris(){ 	
		text=new JTextArea[game_x][game_y];
		data= new int[game_x][game_y];
		label1=new JLabel("游戏状态：正在游戏中！");
		label=new JLabel("游戏得分为：0");
		initGamepanel();
		initExplainPanel();
		initWindow();
		
		//初始化游戏开始
		isrunning = true ;
		allRect = new int[] {0x00cc,0x8888,0x000f,0x888f,0xf888,0xf111,0x111f,0x0eee,0xffff,0x0008,0x0888,0x000e,0x0088,0x000c,0x08c8,0x00e4,0x04c4,0x004e,0x08c4,0x006c,0x04c8,0x00c6};
		}


	
	//功能部分
	
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//方块进行左移
		if(e.getKeyCode()==37) {
			//判断游戏是否结束
			if(!isrunning) {
				return;
			}
			//方块是否碰到左壁
			if(y<=1) {
				return;
			}
			//定义一个变量
			int temp = 0x8000;
			for(int i = x;i<x+4;i++) {
				for(int j = y;j<y+4;j++) {
					if((temp&rect)!= 0) {
						if(data[i][j-1]==1) {
							return;
						}
					}
					temp>>=1;
				}
			}
			//首先清除目前方块
			clear(x,y);
			y--;
			draw(x,y);
		}
		//方块进行右移
		if(e.getKeyCode()==39) {
			//判断游戏是否结束
			if(!isrunning) {
				
				return;
			}
			//定义变量
			int temp = 0x8000;
			int m = x;
			int n = y;
			//储存最右边的坐标值
			int num = 1;
			for(int i = 0;i<4;i++) {
				for(int j = 0;j<4;j++) {
					if((temp&rect)!=0) {
						if(n>num) {
							num = n;
						}
					}
					n++;
					temp>>=1;
				}
				m++;
				n = n-4;
			}
			//判断是否碰到右墙壁
			if(num>=(game_y-2)) {
				return;
			}
			//方块右移途中是否碰到别的方块
			temp = 0x8000;
			for(int i = x;i<x+4;i++) {
				for(int j = y;j<y+4;j++) {
					if((temp&rect)!=0) {
						if(data[i][j+1]==1) {
							return;
						}
					}
					temp>>=1;
				}
			}
			//清除当前方块
			clear(x,y);
			y++;
			draw(x,y);
		}
		//方块进行下移
		if(e.getKeyCode()==40) {
			//判断游戏是否结束
			if(!isrunning) {
				return;
			}
			//判断方块是否可以下落
			if(!canfall(x,y)) {
				return;
			}
			clear(x,y);
			//改变方块的坐标
			x++;
			draw(x,y);
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//控制方块变形
				if (e.getKeyChar()==KeyEvent.VK_SPACE) {
					//控制游戏是否结束
					if(!isrunning) {
						return;
					}
					//定义变量，存储目前方块的索引；
					int old;
					for(old=0;old<allRect.length;old++) {
						//判断是否是当前方块
						if(rect == allRect[old]) {
							break;
						}
					}
					//定义变量，存储变形后方块
					int next;
					//判断是方块
					if(old==0||old==7||old==8||old==9) {
						return;
					}
					//清除当前方块
					clear(x,y);
					
					if(old==1||old==2) {
						next =allRect[old==1?2:1];
						if (canTurn(next,x,y)) {
							rect=next;	
						}
							
					}
					if (old>=3&&old<=6) {
						next=allRect[old+1>6?3:old+1];
						if(canTurn(next,x,y)) {
							rect=next;
						}
					}
					if(old==10||old==11) {
						next=allRect[old==10?11:10];
						if (canTurn(next,x,y)) {
							rect=next;
						}
					}
					if (old==12||old==13) {
						next=allRect[old==12?13:12];
						if(canTurn(next,x,y)) {
							rect=next;
						}
					}
					if(old>=14&&old<=17) {
						next=allRect[old+1>17?14:old+1];
						if(canTurn(next,x,y)) {
							rect=next;
						}
					}
					if(old==18||old==19) {
						next=allRect[old==18?19:18];
						if(canTurn(next,x,y)) {
							rect=next;
					}
				
				}
					if(old==20||old==21) {
						next=allRect[old==20?21:20];
						if(canTurn(next,x,y)) {
							rect=next;
					}
				
				}
					//重新绘制变形后方块
					draw(x,y);
					
			}
	}
	
	//开始游戏 包括游戏状态、标签改变
	@Override
	public void begin() {
		// TODO Auto-generated method stub
		while(true) {
			if(!isrunning) {
				break;
			}
			//进行游戏
		game_run();
			
		}
		//标签：游戏结束
		label1.setText("游戏状态：游戏结束！");
		
		
	}
	public void ranRect() {
		Random random = new Random();
		
		rect = allRect[random.nextInt(22)];
	}
		
	@Override
	public void fall(int m,int n) {
		// TODO Auto-generated method stub
		if(m>0) {
			clear(m-1,n);
		}
		draw(m,n);
	}
	
	@Override
	public boolean canfall(int m,int n) {
		//遍历4*4方格
		int temp=0x8000;
		
		for (int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if((temp&rect)!=0) {
					//判断该位置的下一行是否有方块
					if(data[m+1][n]==1) {
						return false;
					}
				}
				n++;
				temp>>=1;
			}
			m++;
			n=n-4;
		}
		//可以下落
		return true;
	}
	
	@Override
	public void removeRow(int row) {	
		int temp=100;
		for(int i=row;i>=1;i--) {
			for(int j=1;j<=(game_y-2);j++) {
				//进行覆盖
				data[i][j]=data[i-1][j];
			}
		}
		//刷新游戏区域
		reflesh(row);
		//方块加速
		if(time > temp) {
			time -= temp;
		}
		
		score += temp;
		
		//显示变化后的分数
		label.setText("游戏得分："+score);
	}
	@Override
	public void changeData(int m, int n) {
		int temp = 0x8000;
		for(int i = 0;i<4;i++) {
			for(int j = 0;j<4;j++) {
				if((temp&rect) !=0) {
					data[m][n] = 1;
				}
				n++;
				temp>>=1;
			}
			m++;
			n = n-4;
		}
		
	}
	//消除后刷新界面
	@Override
	public void reflesh(int row) {
		// TODO Auto-generated method stub
		for(int i=row;i>=1;i--) {
			for (int j=1;j <= (game_y-2);j++) {
				if (data[i][j]==1) {
					text[i][j].setBackground(Color.BLUE);
				}else {
					text[i][j].setBackground(Color.WHITE);
				}
			}
		}
		
	
	}

	
	//方块掉落一层的方法
	@Override
	public void fallRect(int m, int n) {
		// TODO Auto-generated method stub
		if(m>0) {
			clear(m-1,n);
		}
		draw(m,n);
	}
	@Override
	public void clear(int m, int n) {
		  int temp=0x8000;
		   for(int i=0;i<4;i++) {
			   for(int j=0;j<4;j++) {
				   if((temp&rect)!=0) {
					   text[m][n].setBackground(Color.WHITE);
				   }
				   n++;
				   temp>>=1;
			   }
			   m++;
			   n=n-4;
		   }
		
	}
	//重新掉落绘制后方块的方法
	@Override
	public void draw(int m, int n) {
		// TODO Auto-generated method stub
		 
		   int temp=0x8000;
		   for(int i=0;i<4;i++) {
			   for(int j=0;j<4;j++) {
				   if((temp&rect)!=0) {
					   text[m][n].setBackground(Color.BLUE);
				   }
				   n++;
				   temp>>=1;
			   }
			   m++;
			   n=n-4;
		   }
	}

	@Override
	public boolean canTurn(int a, int m, int n) {
		// TODO Auto-generated method stub
		int temp=0x8000;
		//遍历整个方块
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if((a&temp) !=0) {
					if(data[m][n]==1) {
						return false;
					}
				}
				n++;
				temp>>=1;
			}
			m++;
			n=n-4;
		}
		//可以变形
		return true;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void game_run() {
		// TODO Auto-generated method stub
		ranRect();
		//下落位置
		x = 0;
		y = 5;
		 
		for(int i = 0;i <game_x;i++) {
			 try {
			 Thread.sleep(time);
			 //判断是否可以下落
			 if(!canfall(x,y)) {
				 //方块占用
				 changeData(x,y);
				 //循环遍历4层判定消除
				 for(int j = x;j < x + 4; j++) {
					  int sum = 0;
					  
					  for(int k = 1;k <= (game_y-2);k++) {
						  if(data[j][k] == 1) {
							  sum++;
						  }
					  }
					  
					  if(sum ==(game_y-2)) {
						  removeRow(j);
					  }
				 }
				 
				 //判断游戏是否失败
				 for (int j = 1;j<=game_y-2;j++) {
					 if(data[3][j] == 1 ) {
						 isrunning  = false;
						 break;
					 }
				 }
				 break;
				 
				 
			 }else {
				 //层数+1
				 x++;
				 
				 fall(x,y);
			 }
			 }catch(InterruptedException e) {
				 e.printStackTrace();
			 }
			 
		 }
	}
}