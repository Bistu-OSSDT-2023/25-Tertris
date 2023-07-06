package ELUOSIFANGKUAI;

import java.awt.Color;

public class QINGCHUSHUAXING {
   public void draw(int m,int n) {
	   //定义变量
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
}
