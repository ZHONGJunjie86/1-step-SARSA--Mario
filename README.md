# 1-step-SARSA--Mario
　This is my first reinforcement learning work. I didn't use deep learning at that time, so it will shows how the traditional RL algorithm works.  
　僕の初めての強化学習ワークでした。 当時は深層学習を使用していなかったため、従来のRLアルゴリズムがどのようにするかのは示されています。
# Architecture
![image](https://github.com/ZHONGJunjie86/1-step-SARSA--Mario/blob/master/illustrate/architecture.JPG)
# SARSA(TD, on-policy)
　Q表をこのように更新する：　　
　Q(S,A) = Q(S,A) + α[reward + γQ(S’,A’) – Q(S,A)]　  　

　ε-グリーディ：確率的に最優方策を選んで、ε = ε *(1+0.005)    
　##### 実際に dead-road があるため、εは固定値になります。　　There are some dead roads so ε is assigned to be a consistent value.
![image](https://github.com/ZHONGJunjie86/1-step-SARSA--Mario/blob/master/illustrate/sarsa.jpg)
# Setting
### State Action
　I pepared 9 actions for agent to choose and let agent see 8 blocks around itself.  And the data of each block is equal to map's data plus enemy's data. Imitating the CNN.  
　九つの利用可能な行動を準備しておいて、緑の部分はエージェント自身見える所にします。動的なマップで、Q表が巨大化を防いで、もっと汎化のため、ただ八つの数値を取ります。CNNを模倣したいです。    
　データ = マップの値 + 敵の値 = getReceptiveFieldCellValue(x,y) + getEnemiesCellValue(x,y)

![image](https://github.com/ZHONGJunjie86/1-step-SARSA--Mario/blob/master/illustrate/setting1.jpg)
### reward shaping
reward = A*coins + B*win + C*kills - D*hurts -  E * die;
*目的と設定によって、係数を変える.例えば、難しい場合には A を高くして、エージェントを右へ行かせます。
*Change the coefficients depending on purposes and settings. For example, in difficult rounds it is supposed to make the A higher which can let the agent forward to right.
# Experiment
##### 　This model learns very fast but unpleasant. Due to its simpleness, the model just makes simple judgements(inaccurate and overestimate). It shows the agent has no sense of taking actions to respond to the complex environment. It has learn nothing about the environment although it just can see a little part of state and take them into Q-table.
##### 　エージェントは学習し速いですが、効果的ではありません。モデルは簡単で、確かに学習し速いですが、状態を不正確で過大評価します。複雑な環境を対応できないです。見える所は有限で、状態をストレージしかなくて、学習していません。
##### 　Using CNN as input and DRL will get better result.
![image](https://github.com/ZHONGJunjie86/1-step-SARSA--Mario/blob/master/illustrate/experiment.JPG)



# Referce  
The mario game is presented by The University of Electro-Communications.

