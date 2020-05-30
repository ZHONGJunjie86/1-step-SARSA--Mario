package ch.idsia.agents.controllers;
import java.util.Random;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.environments.Environment;

public class MyRandomAgent extends BasicMarioAIAgent implements Agent
{
	private Random R = null;

    public MyRandomAgent()
    {
    	super("MyRandomAgent");
    	reset();
    }

    public boolean[] getAction()
    {
    	boolean rand;
    	rand =  R.nextBoolean(); // ヒント： true or false をランダムに取得する

    	///   0左 1右 2蹲 3跳 4冲飞 5向上
    	boolean[] action = new boolean[Environment.numberOfKeys];
    	for(int i = 0;i<=5;i++)
    	{
    		action[i] = rand;
    		rand =  R.nextBoolean(); // ヒント： true or false をランダムに取得する
    	}
    	if (action[1])
    		action[0] = false;
    	action[0]= false;  //ずっと右へ行く
    	return action;
    }
    public void reset()
    {
    	R = new Random();
    	action = new boolean[Environment.numberOfKeys];
    }
}