/*
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

package ch.idsia.scenarios;

import java.util.ArrayList;

import ch.idsia.agents.controllers.MyLearningAgent;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;

/**
 * ランダム行動でゲームを複数回繰り返し、報酬をもとに最適な行動を保存する
 */
public final class MyGame{

	public static void main(String[] args){
		final MarioAIOptions marioAIOptions = new MarioAIOptions(args);

		int maxReward = 0;
		ArrayList<boolean[]> actionList = new ArrayList<boolean[]>();


		for (int i = 0; i < 10000; i++) {
			MyLearningAgent agent = new MyLearningAgent();
			marioAIOptions.setAgent(agent);
			marioAIOptions.setVisualization(false);

			int d = 2;
			marioAIOptions.setLevelDifficulty(d);

			//    marioAIOptions.setEnemies("off");
			marioAIOptions.setEnemies("ggk");

//			marioAIOptions.setDeadEndsCount(true);   //死路
//			marioAIOptions.setCannonsCount(true);    //炮台
//			marioAIOptions.setHillStraightCount(true); //丘
//			marioAIOptions.setTubesCount(true);       //土管
//			marioAIOptions.setGapsCount(true);       //落とし穴
//			marioAIOptions.setHiddenBlocksCount(true);
//			marioAIOptions.setBlocksCount(true);
//			marioAIOptions.setCoinsCount(true);
//			marioAIOptions.setFlatLevel(true);

			final BasicTask basicTask = new BasicTask(marioAIOptions);
			basicTask.runSingleEpisode(1);

			int reward = basicTask.getEnvironment().getEvaluationInfo().computeWeightedFitness();

			System.out.println(i + "	" + reward + "	" + maxReward);

			if (maxReward < reward) {
				maxReward = reward;
				//actionList = agent.getActionList();
			}
		}
		System.out.println(maxReward);

		/*MyLearningAgent agent = new MyLearningAgent(actionList);
		marioAIOptions.setAgent(agent);
		marioAIOptions.setVisualization(true);

		final BasicTask basicTask = new BasicTask(marioAIOptions);
		basicTask.runSingleEpisode(1);
		int reward = basicTask.getEnvironment().getEvaluationInfo().computeWeightedFitness();

		System.out.println(reward);*/
	}

}
