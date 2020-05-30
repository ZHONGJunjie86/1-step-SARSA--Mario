package ch.idsia.agents.controllers;
import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.engine.sprites.Mario;
import ch.idsia.benchmark.mario.environments.Environment;

public class MyAgent extends BasicMarioAIAgent implements Agent
{

	public MyAgent(){
        super("MyAgent");
        reset();
     }
	 public boolean[] getAction(){
    for(int j=0;j<=5;j++) action[j]=false; //清零减少十个百分点
	action[Mario.KEY_RIGHT] = true;//
    //getReceptiveFieldCellValue(int x, int y); //当前位置，横纵  或marioEgoRow, marioEgoCol
    //getEnemiesCellValue(int x, int y)；  //参数指定的位置返回地图值的方法

    // 敵をかわす 小ジャンプ
    if ( getEnemiesCellValue(marioEgoRow, marioEgoCol + 1) != 0 ) {
	    action[Mario.KEY_JUMP] = isMarioAbleToJump;
    }
    // 穴をジャンプ
    if (getReceptiveFieldCellValue(marioEgoRow + 3, marioEgoCol + 1)==0&&isMarioOnGround) {
        action[Mario.KEY_SPEED] =action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
     }

    //障害物
    if(getReceptiveFieldCellValue(marioEgoRow,marioEgoCol+1)!=0) {
	     //System.out.println(marioEgoRow+marioEgoCol); 总是18
	    int seed = (int) (Math.random());//？？？？
	    if(seed<=0.9){
        action[Mario.KEY_SPEED] = action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;//防落地反弹
	    }
	else
		action[Mario.KEY_DOWN]=true;
	//System.out.println("コイン取得数: " + e.coinsGained + " / " + e.totalNumberOfCoins);
    //System.out.println("障害物");
    }
    // 敵をかわす
    if (getEnemiesCellValue(marioEgoRow, marioEgoCol + 1) != 0 ||
		   getEnemiesCellValue(marioEgoRow, marioEgoCol + 2) != 0||
		    getEnemiesCellValue(marioEgoRow, marioEgoCol + 2) != 0||
		    getEnemiesCellValue(marioEgoRow-1, marioEgoCol + 2) != 0) {
	    int seed = (int) (Math.random());
	    if(seed<=0.9){
		    action[Mario.KEY_SPEED] = action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
	    }
	else
		action[Mario.KEY_JUMP] = isMarioAbleToJump || !isMarioOnGround;
}

    return action;
}

public void reset()
{
    action = new boolean[Environment.numberOfKeys];
    action[Mario.KEY_RIGHT] = true;
    action[Mario.KEY_SPEED] = true;
}
}
/*if(isMarioAbleToJump==true)
System.out.println("isMarioAbleToJump");
else
System.out.println("NOTisMarioAbleToJump");
if(isMarioOnGround==true)
System.out.println("isMarioOnGround");
else
System.out.println("NOTisMarioOnGround");
*/

//isMarioAbleToJump = true;


/*System.out.println("下3右一是"+getReceptiveFieldCellValue(marioEgoRow + 3, marioEgoCol + 1));
 if(action[Mario.KEY_JUMP]==true) {
 	System.out.println("跳了");
 	System.out.println("第"+_Counter_+"次结束");
 	_Counter_++;
 }
 else
 	System.out.println("没跳");*/
