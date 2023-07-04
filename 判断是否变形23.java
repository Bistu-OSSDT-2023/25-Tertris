package ELUOSIFANGKUAI;

public class 判断是否变形23 {
  //判断方块此时是否能够变形
	public boolean canTurn(int a,int m,int n) {
		//创建变量
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
