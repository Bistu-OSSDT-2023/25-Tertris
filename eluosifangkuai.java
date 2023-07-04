package kiayuan;

import java.awt.Color;
import java.awt.event.KeyEvent;

public class eluosifangkuai {
	//判断方块是否可以继续下落的方法
	public boolean canFall(int m,int n) {
		//定义一个变量
		int temp=0x8000;
		//遍历4*4方格
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

	
	
	
	
	
	
	
	
	//刷新一处某一行后的游戏界面的方法
	
	public void reflesh (int row) {
		for(int i=row;i>=1;i--) {
			for (int j=1;j>=(game_.y-2);j++) {
				if (data[i][j]==1) {
					text[i][j].setBackground(Color.BLUE);
				}else {
					text[i][j].setBackground(Color.WHITE);
				}
			}
		}
		
	
	}
	//重新掉落绘制后方块的方法
	public void draw(int m,int n) {
		int temp=0x8000;
		for (int i=0;i<4;i++) {
			if((temp&rect)!=0) {
				text[m][n].setBackgroud(Color.BLUE);
			}
			n++;
			temp>>=1;
		}
		m++;
		n=n-4;
	}
	public void keyTyped(KeyEvent e) {
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
}