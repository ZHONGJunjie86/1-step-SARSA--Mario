package ch.idsia.scenarios;
import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.MyLearningAgent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.EvaluationInfo;
import ch.idsia.tools.MarioAIOptions;

public final class Main
{
	public static void main(String[] args)
    {
		int goalNum= 0; // ゴールした回数
	    int N =2000; // ゲームの繰り返し回数
	    Datas.GAME_NUM = N;
	    int flag_clear=0;
	    int flag_reward=0;
	    int flag_enemy=0;
	    int flag_enemy_total=0;

        MarioAIOptions marioAIOptions = new MarioAIOptions(args);

        for (int i = 0; i < N; i++) {
        	//初始化
        	Datas.Action_queue[0]=-1;
            Datas.Action_queue[1]=-1;
            Datas.State_queue[0]=-1;
            Datas.State_queue[1]=-1;

        	int seed = 45 ;//i
        	marioAIOptions.setLevelRandSeed(seed);
        	int d =2;//0
        	marioAIOptions.setLevelDifficulty(d);

        	//marioAIOptions.setHillStraightCount(false); // 无山
        	marioAIOptions.setGapsCount(false); // 穴なし
        	marioAIOptions.setEnemies("g"); //gk
			//marioAIOptions.setCannonsCount(false);
			//marioAIOptions.setTubesCount(false);
        	//agent
            final Agent agent = new MyLearningAgent();  ////自作 Random
            marioAIOptions.setAgent(agent);

            //不可视化
            int turn_sum = 500;
            marioAIOptions.setVisualization(false);
            if((i+1)%turn_sum==0||i==0)  //
              marioAIOptions.setVisualization(true);

            final BasicTask basicTask = new BasicTask(marioAIOptions);
            basicTask.setOptionsAndReset(marioAIOptions);
            basicTask.runSingleEpisode(1);
            EvaluationInfo e = basicTask.getEvaluationInfo();
            //System.out.println(e.marioStatus);

            goalNum= goalNum+ e.marioStatus;  // ゴール可否(marioStatus(=1:ゴール)) を足す
            flag_clear = flag_clear + e.marioStatus;
            flag_reward = flag_reward + e.computeWeightedFitness();
            flag_enemy = flag_enemy + e.killsTotal;
            flag_enemy_total = flag_enemy_total +  e.totalNumberOfCreatures;//

        	if((i+1)%turn_sum==0&&i!=0) {System.out.print((i+1)+"回: ");
               System.out.println("クリア率: "+ ((double)flag_clear/turn_sum));
               System.out.println("適合度 :" + ((double)flag_reward/turn_sum));
               //System.out.println("敵討伐数: " + ((double)flag_enemy/flag_enemy_total));
           	   System.out.println(" ");
               flag_clear=0;
               flag_reward=0;
       	       flag_enemy=0;
       	       flag_enemy_total=0;
        	 }
            }
            System.out.println("总クリア率: "+ ((double)goalNum/ N)); // クリア率を出力 クリア率
        }
	/*public static void main(String[] args) {
			final MarioAIOptions marioAIOptions = new MarioAIOptions(args);
		    int seed = 10;
		    marioAIOptions.setLevelRandSeed(seed);
		    int d = 10 ;//0
			marioAIOptions.setLevelDifficulty(d);

			marioAIOptions.setEnemies("g");
			marioAIOptions.setGapsCount(false);
			marioAIOptions.setCannonsCount(false);
		    final BasicTask basicTask = new BasicTask(marioAIOptions);
		    basicTask.setOptionsAndReset(marioAIOptions);
		    basicTask.doEpisodes(1, true, 1);
		 }
*/
        //System.exit(0);
    }
/*
            marioAIOptions.setGapsCount(false); // 穴なし
        	marioAIOptions.setTubesCount(false);
        	marioAIOptions.setCannonsCount(false);
             * System.out.println("Datas.station size"+Datas.Stations.size());
            System.out.println("Datas.Actions size"+Datas.Actions.size());
            int []action_table_1 = new int[12];
        	action_table_1 = Datas.Stations.get(2);
        	for(int j=0;j<=11;j++)System.out.println("action_table_3:"+action_table_1[i]);

            Stations.clear();
        	Actions.clear();
            Stations.addAll(Datas.Stations);
        	Actions.addAll(Datas.Actions);

        	int []action_table_3 = new int[12];
        	action_table_3 = Stations.get(2);
        	for(int j=0;j<=11;j++)System.out.println("action_table_3:"+action_table_3[i]);
 *
 *
 *
 *

*/
//环境
//marioAIOptions.setEnemies("g");    //gk
//marioAIOptions.setGapsCount(true); // 穴あり

// System.out.println("Main中Stations，Actions="+Stations+","+Actions);
/*marioAIOptions.setEnemies("off"); // 敵なし
marioAIOptions.setFlatLevel(true); // 平坦

marioAIOptions.setTubesCount(false); // 土管なし
marioAIOptions.setBlocksCount(false);
//marioAIOptions.setHiddenBlocksCount(false);
marioAIOptions.setCannonsCount(false);*/
//自动
//final Agent agent = new ForwardJumpingAgent();
//marioAIOptions.setAgent(agent);
/*old license

 * Copyright (c) 2009-2010, Sergey Karakovskiy and Julian Togelius
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Mario AI nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */