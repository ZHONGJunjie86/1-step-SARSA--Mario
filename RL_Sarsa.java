package ch.idsia.agents.controllers;
import java.util.ArrayList;
import java.util.Arrays;

import ch.idsia.scenarios.Datas;

public class RL_Sarsa
{
    public int STATE_NUM = 8; //12
    public int ACTION_NUM = 9;
	public double lr = 0.01;
	public double gamma = 0.9;  //奖励衰减
	public double epsilon = 0.9;  //贪婪度
	//public double trace_decay=0.9;   //λ衰减
	private static ArrayList<int[]>Stations = new ArrayList<>();   //static必须
	private static ArrayList<double[]>Actions = new ArrayList<>();  //static必须
	public double[] empty_arr = new double[]{0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0};

	public RL_Sarsa(double learning_rate, double reward_decay, double e_greedy)
	{
		lr = learning_rate;
		gamma = reward_decay;  //奖励衰减
		epsilon = e_greedy;    //贪婪度
	}

	public double get_epsilon() {
		return epsilon;
	}

	//获得动作行
	public double[] get_action_table(int i) {
		if(i>=Actions.size()) {
			System.out.println("get_action_table错误 ");
			return Actions.get(i);
		}
		else return Actions.get(i);
	}

	//检验存在
    public int check_state_exist(int state[]) { //从这传的就是引用
       int flag = -1;
       int local=0;

       if (Stations.isEmpty()) {  //空
    	   flag = -2;
       }
       else  //先变后再变前???
       {
    	   flag = -1;
    	   //正常范围
       	   for(int i=0;(i<=Stations.size()-1)&&(flag!=1);i++)
           {
       		  int[] temp=new int[STATE_NUM];
          	  temp = Stations.get(i);
              if(Arrays.equals(state, temp)==true)  //原来就是引用，引用值变了，里面就变了
              {
             	 flag =1;
              	 local=i;
              	//相同状态
           	  }
            }
       }

       if(flag == -2){   //空
    	   int[] temp_S=new int[STATE_NUM];
    	   for(int j=0;j<=STATE_NUM-1;j++) temp_S[j]=state[j];
    	   double[] temp_A=new double[ACTION_NUM];
    	   for(int j=0;j<=ACTION_NUM-1;j++)temp_A[j]=empty_arr[j];
    	   Stations.add(temp_S);
    	   Actions.add(temp_A);

    	   local = 0;
       }

       if(flag == -1){   //无
    	   int[] temp_S=new int[state.length];
    	   for(int j=0;j<=STATE_NUM-1;j++) temp_S[j]=state[j];
    	   double[] temp_A=new double[ACTION_NUM];
    	   for(int j=0;j<=ACTION_NUM-1;j++)temp_A[j]=empty_arr[j];
    	   Stations.add(temp_S);
    	   Actions.add(temp_A);
    	   local = Stations.size()-1;
       }
       return local;
    }

    public void learn(int local,int action_choose,int local_next,int action_next) {
    	//if(Datas.qTable_new == 1) return ; //已更新或无奖励
    	double [] action_oprate = new double[ACTION_NUM];

    	for(int j=0;j<=ACTION_NUM-1;j++)
    		action_oprate[j] = Actions.get(local)[j];

    	double q_predict = action_oprate[action_choose];

    	double reward = Datas.coins*1.5 + Datas.win*12 + Datas.kills*0.5 - Datas.hurts*4 - Datas.die*5;

    	double q_target = reward + gamma * Actions.get(local_next)[action_next];//当前奖励 +计算表格下一值  self.q_table.loc[s_, a_]
    	double new_num = q_predict + lr*(q_target-q_predict);

    	action_oprate[action_choose] = new_num;
    	Actions.set(local_next,action_oprate);

        if(Datas.win!=0||Datas.die!=0) {
          Datas.GAME_now++;
          epsilon = epsilon*(1+0.005);
          if(Datas.GAME_now==Datas.GAME_NUM-1) {
          System.out.println("Stations.size():" +Stations.size()+"Actions.size():"+Actions.size());
          /*for(int j=0;j<=10;j++) {
        	  System.out.print("[");
        	  for(int k=0;k<=STATE_NUM-1;k++) {System.out.print(Stations.get(j)[k]);}
        	  System.out.print("] ");
        	  for(int k=0;k<=ACTION_NUM-1;k++) {System.out.print(Actions.get(j)[k]+" ");}
        	  System.out.println();
          }*/
          }
        }
        Datas.kills= 0;
    	Datas.coins = 0;
        Datas.win = 0;
        Datas.die = 0;
        Datas.hurts = 0;
    }
}
