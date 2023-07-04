package ELUOSIFANGKUAI;

public class MANHANGDIAOLUO11 {
   //移除某一行的所有方块，让上面的方块掉落
	public void removeRow(int row) {
		int temp=100;
		for(int i=row;i>=1;i--) {
			for(int j=1;j<=(game_y-2;j++)) {
				//进行覆盖
				date[i][j]=date[i-1][j];
			}
		}
		//刷新游戏区域
		reflesh(row);
		//方块加速
		if(time>temp) {
			time -= temp;
		}
		
		score += temp;
		
		//显示变化后的分数
		label.setText("游戏得分："+score);
	}
}
