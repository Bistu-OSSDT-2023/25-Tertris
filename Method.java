package Test;

import java.awt.event.KeyEvent;

public interface Method {
	//开始游戏 包括游戏状态、标签改变
	public void begin();
	
	//随机生成方块(需要初始化所有方块类型
	public void fallRect();
	
	//判断方块是否可以继续下落
	public boolean isfall();
	
	//改变不可下落方块对应区域值
	public void changeData(int m , int n);
	
	//消除满行并掉落
	public void remove(int row);
	
	//消除后刷新界面
	public void reflesh(int row);
	
	//方块掉落一层的方法
	public void fallRect(int m,int n);
	
	//修复：清除方块值后的颜色刷新
	public void clean(int m,int n);
	
	//修复：重新绘制掉落后方块的颜色
	public void draw(int m , int n);
	
	//控制方块移动以及边界判断
	public void keyPressed(KeyEvent e);
	//左移
	//右移
	//下移
	
	
	//控制方块转向
	public void keyTyped(KeyEvent e); 
	
	//额外：暂停功能…………
	
	
	
}
