# 1-step-SARSA--Mario
　This is my first reinforcement learning work. I didn't use deep learning at that time, so it will shows how the traditional RL algorithm works.  
 僕の初めての強化学習ワークでした。 当時は深層学習を使用していなかったため、従来のRLアルゴリズムがどのようにするかのは示されています。
# Architecture
![image](https://github.com/ZHONGJunjie86/1-step-SARSA--Mario/blob/master/illustrate/architecture.JPG)
# SARSA(TD, on-policy)
Q表をこのように更新する：　　
Q(S,A) = Q(S,A) + α[reward + γQ(S’,A’) – Q(S,A)]　  　

ε-グリーディ：確率的に最優方策を選んで、ε = ε *(1+0.005)    
実際に dead-road があるため、εは固定値になります。　　

# Setting
I pepared 9 actions for agent to choose and let agent see 8 blocks around itself.  And the data of each block is equal to map's data plus enemy's data.  
九つの利用可能な行動を準備しておいて、緑の部分はエージェント自身見える所にします。動的なマップで、Q表が巨大化を防いで、もっと汎化のため、ただ八つの数値を取ります。    
データ = マップの値 + 敵の値 = getReceptiveFieldCellValue(x,y) + getEnemiesCellValue(x,y)

![image](https://github.com/ZHONGJunjie86/1-step-SARSA--Mario/blob/master/illustrate/setting1.jpg)
# Experiment
This model learns very fast but unpleasant. ####It shows the agent has no sense of taking actions to 
![image](https://github.com/ZHONGJunjie86/1-step-SARSA--Mario/blob/master/illustrate/experiment.JPG)




The marioAI is presented by The University of Electro-Communications.
----------
