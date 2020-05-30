package ch.idsia.agents.controllers;
//import java.util.Random;

import java.util.Random;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.scenarios.Datas;

public class MyLearningAgent extends BasicMarioAIAgent implements Agent
{
	public int STATE_NUM = 12;
    public int ACTION_NUM = 9;
    int local = 0;
    int local_next = 0;
    int action_next = 0;
    int action_choose =0;
    int action_choose_next =0;
    int[]action_max=new int[] {0,0,0,0,0,0,0,0};
    int[]state=new int[] {0,0,0,0,0,0,0,0,0,0,0,0};//,0,0,0
                          //learning_rate reward_decay e_greedy
    RL_Sarsa RL = new RL_Sarsa(0.01, 0.9, 0.85);

    public MyLearningAgent(){
        super("MyLearningAgent");
        reset();
     }

    public boolean[] getAction(){   //实施行动

    	 if(isMarioOnGround==false)  //只在地面行动，否则Q表变得巨大，成功率照样收敛
    	        {
    	     		return action;
    	     	}
    	 else //地上才动
        {
        	action_choose=uploadSA();
        	for(int j=0;j<=5;j++) action[j]=false; //清零
          	switch(action_choose){
              case 0 :   //右
              	  action[Mario.KEY_RIGHT] = true;
                  break;
              case 1 :   //右小跳
            	  action[Mario.KEY_RIGHT] =action[Mario.KEY_JUMP] = isMarioAbleToJump;
                  break;
              case 2 :   //右大跳
            	  action[Mario.KEY_RIGHT] =action[Mario.KEY_SPEED] =action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
                  break;
              case 3 :   //左
              	  action[Mario.KEY_LEFT] = true;
                 break;
              case 4 :   //左小跳
            	  action[Mario.KEY_LEFT] =action[Mario.KEY_JUMP] = isMarioAbleToJump;
                  break;
              case 5 :   //左大跳
            	  action[Mario.KEY_LEFT] =action[Mario.KEY_SPEED] =action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
                  break;
              case 6 :   //fire
              	  action[Mario.KEY_SPEED] = true;
                  break;
              case 7 :   //蹲也可
              	  action[Mario.KEY_DOWN] = true;
              	break;
              case 8:  //jump
            	  action[Mario.KEY_JUMP] = isMarioAbleToJump;
                  break;
                ///0左 1右 2蹲 3跳 4冲火 5向上
              }
             return action;
       }
    }

    public int uploadSA() {
       //地上了
   	   int row = marioEgoRow;
 	   int col = marioEgoCol;

   	   //当前位置观测 station  8位
 	  for(int i = 0;i<=STATE_NUM-1;i++) {
   		  if(i<=2)  //10 11 12
            state[i] = (getReceptiveFieldCellValue(row-1,col+i)+getEnemiesCellValue(row-1,col+i));//==0?0:1;//7+i ;//
   		  else if(3<=i&&i<=4)
   		    state[i] = (getReceptiveFieldCellValue(row,col+i-2)+getEnemiesCellValue(row,col+i-2));//==0?0:1;//i+3
   		  else if(i==5)
     	    state[i] = (getReceptiveFieldCellValue(row+1,col+i-4)+getEnemiesCellValue(row+1,col+i-4));//==0?0:1;  //i-1
   		  else {
   			state[6] = (getReceptiveFieldCellValue(row-2,col+3)+getEnemiesCellValue(row-2,col+3));//9,8
   		    state[7] = (getReceptiveFieldCellValue(row,col-1)+getEnemiesCellValue(row,col-1)); //7,12
   		    state[8] = (getReceptiveFieldCellValue(7,9)+getEnemiesCellValue(7,9)); //7,9
   		    state[9] = (getReceptiveFieldCellValue(8,12)+getEnemiesCellValue(8,12)); //8,12
   		    state[10] = (getReceptiveFieldCellValue(7,10)+getEnemiesCellValue(7,10)); //7,10
		    state[11] = (getReceptiveFieldCellValue(7,11)+getEnemiesCellValue(7,11)); //7,11
   		    }
       }

       if(Datas.Action_queue[0]==-1) {           //はじめ 开始
     	    local = RL.check_state_exist(state);
          	action_choose = choose_action(local);
   	    	Datas.Action_queue[0]=action_choose;
   	    	Datas.State_queue[0]=local;

   		    action_choose=Datas.Action_queue[0];
   	   }
       else {//到第二次 甚至以后
  	     local_next = RL.check_state_exist(state);
         action_choose_next = choose_action(local_next);
         //学习
         Datas.Action_queue[1]=action_choose_next;
         Datas.State_queue[1]=local_next;
         RL.learn(Datas.State_queue[0],Datas.Action_queue[0],Datas.State_queue[1],Datas.Action_queue[1]);
         //更新
         Datas.Action_queue[0]= Datas.Action_queue[1];
  	     Datas.Action_queue[1]=action_choose_next;
  	     Datas.State_queue[0]=Datas.State_queue[1];
  	     Datas.State_queue[1]=local_next;
  	      //选择
  	     action_choose=Datas.Action_queue[0];
        }
      return action_choose;
    }

    public int choose_action(int local) {
        int action_choose;
        boolean all_equal = false;

    	double []action_table = new double[ACTION_NUM];
    	action_table = RL.get_action_table(local);

    	//是否新状态栏
    	all_equal =true;
    	for(int i=0;i<=ACTION_NUM-1&&all_equal;i++) {
    	  if(action_table[i]!=0)all_equal =false;
    	}

    	double random = Math.random();
    	//System.out.println("选择用随机数="+random);

    	//贪婪。取最大
    	if (random < RL.get_epsilon()&&all_equal==false) {
           int max1 = 0; //索引唯一
           int max2 = 0;  //索引多个？
           double max_=action_table[0];//最大值
           for(int i=1;i<=ACTION_NUM-1;i++) { //选最大（且是最后一个）
               if(action_table[i]>max_) {
            	   max_ = action_table[i];
            	   max1 = i;
               }
           }
         //若有相同最大值
           for(int i =0;i<=ACTION_NUM-1;i++) {
        	   if(action_table[i] == max_&&i!=max1) {
        		   //System.out.println("相同");
        		   action_max[max2] = i;  //存index索引
        		   max2++;
        	   }
           }
           action_max[max2] = max1;
           if(max2!=0) {   //有相同最大值, 一共max2+1个，
        	   Random rand = new Random();
        	   action_choose = action_max[rand.nextInt(max2+1)];  //0<=rand.nextInt()<max2+1
        	   //System.out.println("相同"+(max2+1)+"个"+"，选"+action_choose);
           }
           else action_choose = max1;
    	}
    	//非贪婪随机
    	else{
    		random = Math.random();
    		if(random<(double)(1.0/ACTION_NUM)) action_choose = 0;  //右
    		else if((double)(1.0/ACTION_NUM)<random&&random<(double)(2.0/ACTION_NUM)) action_choose= 1; //小跳
    		else if((double)(2.0/ACTION_NUM)<random&&random<(double)(3.0/ACTION_NUM)) action_choose= 2; //大跳
    		else if((double)(3.0/ACTION_NUM)<random&&random<(double)(4.0/ACTION_NUM)) action_choose= 3; //左
    		else if((double)(4.0/ACTION_NUM)<random&&random<(double)(5.0/ACTION_NUM)) action_choose= 4; //左小跳
    		else if((double)(5.0/ACTION_NUM)<random&&random<(double)(6.0/ACTION_NUM)) action_choose= 5; //左大跳
    		else if((double)(6.0/ACTION_NUM)<random&&random<(double)(7.0/ACTION_NUM)) action_choose= 6; //火
    		else if((double)(7.0/ACTION_NUM)<random&&random<(double)(8.0/ACTION_NUM)) action_choose= 7; //蹲
    		else action_choose = 8; //jump
    	}
    	return action_choose;
    }

    public void reset()
    {
        action = new boolean[Environment.numberOfKeys];
        //action[Mario.KEY_RIGHT] = true;
        //action[Mario.KEY_SPEED] = true;
    }
}

