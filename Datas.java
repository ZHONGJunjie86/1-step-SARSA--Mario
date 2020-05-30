package ch.idsia.scenarios;

public class Datas {
   public static int coins = 0 ;
   public static int win = 0;
   public static int hurts = 0;
   public static int die = 0;
   public static int kills = 0;
   public static int GAME_NUM = 0;
   public static int GAME_now = 0;

   public static int[]Action_queue=new int [2];
   public static int[]State_queue=new int [2];
}









/*
*
*random = Math.random();
        	   if(random>0.5)
               action_choose= max1;
        	   else
        		   action_choose = max2;
        		  
下一位置  station   得到而非预测
        	for(int i = 0;i<=6;i++) {
        		if(i<=2)  //12 13 14
                 state_next[i] = (getReceptiveFieldCellValue(row-1,col+i+2)+getEnemiesCellValue(row-1,col+i+2))==0?0:1;
               else if(3<=i&&i<=4)
          	      state_next[i] = (getReceptiveFieldCellValue(row,col+i)+getEnemiesCellValue(row,col+i))==0?0:1;
          	    else
            	  state_next[i] = (getReceptiveFieldCellValue(row+1,col+i-2)+getEnemiesCellValue(row-10+i,col+i-2))==0?0:1;
             }
*
*
*else  //以后每次只选下一步
        	  {
        		if(near_win!=1) {
        		  local_next = RL.check_state_exist(state_next);
        		  action_choose_next = choose_action(state_next,local_next);
        		  }

        		Datas.Action_queue[0]= Datas.Action_queue[1];
        		Datas.Action_queue[1]=action_choose_next;
        		Datas.State_queue[0]=Datas.State_queue[1];
        		Datas.State_queue[1]=local_next;
              action_choose=Datas.Action_queue[0];
        	  }
*
* for(int i = 0;i<=11;i++) {
        		if(i<=3)  //10 11 12
                state[i] = (getReceptiveFieldCellValue(row-2+i,col+1)+getEnemiesCellValue(row-2+i,col+1))==0?0:1;//7+i ;//
        		else if(4<=i&&i<=7)
        		  state[i] = (getReceptiveFieldCellValue(row-6+i,col+2)+getEnemiesCellValue(row-6+i,col+2))==0?0:1;//i+3
        		else
          	  state[i] = (getReceptiveFieldCellValue(row-10+i,col+3)+getEnemiesCellValue(row-10+i,col+3))==0?0:1;  //i-1
            }
        	//下一位置  station
        	for(int i = 0;i<=11;i++) {
        		if(i<=3)  //12 13 14
                state_next[i] = (getReceptiveFieldCellValue(row-2+i,col+3)+getEnemiesCellValue(row-2+i,col+3))==0?0:1;
              else if(4<=i&&i<=7)
          	  state_next[i] = (getReceptiveFieldCellValue(row-6+i,col+4)+getEnemiesCellValue(row-6+i,col+4))==0?0:1;
          	else
            	  state_next[i] = (getReceptiveFieldCellValue(row-10+i,col+5)+getEnemiesCellValue(row-10+i,col+5))==0?0:1;
             }
*
*
*for(int i = 0;i<=11;i++) {
        		if(i<=3)  //12 13 14
                state_next[i] = (getReceptiveFieldCellValue(row-2+i,col+3)+getEnemiesCellValue(row-2+i,col+3))==0?0:1;
              else if(4<=i&&i<=7)
          	  state_next[i] = (getReceptiveFieldCellValue(row-6+i,col+4)+getEnemiesCellValue(row-6+i,col+4))==0?0:1;
          	else
            	  state_next[i] = (getReceptiveFieldCellValue(row-10+i,col+5)+getEnemiesCellValue(row-10+i,col+5))==0?0:1;
             }
*
*
*
*/
/*
 *if(state.length==3) for(int j=0;j<=2;j++) temp_S[j]=state[j];
    	      else
 *
 *
 *  else //快结束
       	  {
       		for(int i=0;(i<=Stations.size()-1) &&(flag!=1);i++)
            {
       		  int[] temp2 = new int[3];
              temp2 = Stations.get(i);
              //相同状态
              if(Arrays.equals(state, temp2)) {
             	 flag =1;
             	 local=i;
             	/*System.out.print("相等:"+i+"      ");
             	for(int j =0;j<=5;j++)System.out.print(Actions.get(i)[j]);
             	System.out.println(" ");
             }
            }
       	 }



 * Datas.Stations.clear();
           Datas.Actions.clear();
       	   Datas.Stations.addAll(Stations);
       	   Datas.Actions.addAll(Actions);
 *
 *
 * if(Datas.win!=0||Datas.die!=0) {
System.out.println("win:"+Datas.win+"die:"+Datas.die);
Stations.clear();
Actions.clear();
if(!Datas.Stations.isEmpty()) {
	for(int i=0;i<=Datas.Stations.size();i++)
	  {
        if(Datas.Stations.get(i).length==12) {
  	    Stations.add();
        }
      }
}
Actions.addAll(Datas.Actions);
int[]S_12=new int[] {0,0,0,0,0,0,0,0,0,0,0,0};
double[] A = new double[]{0.0,0.0,0.0,0.0,0.0,0.0};
Datas.Stations.clear();
Datas.Actions.clear();
Datas.Stations.addAll(Stations);
Datas.Actions.addAll(Actions);
System.out.println("已复制: Stations.size():"+Datas.Stations.size()
                         +"已复制: Actions.size():"+Datas. Actions.size());
}*/
/*System.out.print("无时增加:");
   for(int j =0;j<=11;j++)System.out.print(state[j]);
   System.out.println("");
System.out.println("无时增加 Stations.size():" +Stations.size());
local=Datas.Stations.size()-1;*/
/*
 * //int near_win = 0;
   	   if(getReceptiveFieldCellValue(row,col+3)==5||getReceptiveFieldCellValue(row,col+4)==5||
   			      getReceptiveFieldCellValue(row,col+5)==5)
   	   {
   		   int[] state_final=new int[] {0,0,0};
   		   int[] state_final_next=new int[] {0,0,0};
   		   for(int i = 0;i<=2;i++) {
        	      state_final[i] = (getReceptiveFieldCellValue(row-1+i,col+1)==0&&getEnemiesCellValue(row-1+i,col+1)==0)?0:1;
        		   }
   		   for(int i = 0;i<=2;i++) {
    		      state_final_next[i]=(getReceptiveFieldCellValue(row-1+i,col+2)==0&&getEnemiesCellValue(row-1,col+2)==0)?0:1;
    		       }
   		   local_next = RL.check_state_exist(state_final_next);
   		   action_choose_next = choose_action(local_next);
   		   //near_win=1;
   	   }
 * */