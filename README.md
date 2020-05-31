# 1-step-SARSA--Mario
　This is my first reinforcement learning work. I didn't use deep learning at that time, so it will shows how the traditional RL algorithm works.
 僕の初めての強化学習ワークでした。 当時は深層学習を使用していなかったため、従来のRLアルゴリズムがどのようにするかのは示されています。
# SARSA(TD, on-policy)
Q表をこのように更新する：　　
Q(S,A) = Q(S,A) + α[reward + γQ(S’,A’) – Q(S,A)]　　

ε-グリーディ：確率的に最優方策を選んで、ε = ε *(1+0.005)  
実際に dead-road があるため、εは固定値になります。　　

# Setting
九つの利用可能な行動を準備しておいて、  
動的なマップで、Q表が巨大化を防いで、もっと汎化のため、ただ八つの数値を取ります。  
# Experiment





The marioAI is presented by The University of Electro-Communications.
----------
